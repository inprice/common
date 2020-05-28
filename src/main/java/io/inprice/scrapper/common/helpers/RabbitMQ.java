package io.inprice.scrapper.common.helpers;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.inprice.scrapper.common.config.SysProps;

public class RabbitMQ {

	private static final Logger log = LoggerFactory.getLogger(RabbitMQ.class);

  private static boolean isHealthy;
	private static Channel channel;

	public static Channel getChannel() {
		if (!isChannelActive()) {
			synchronized (log) {
				if (!isChannelActive()) {
					final ConnectionFactory connectionFactory = new ConnectionFactory();
					connectionFactory.setHost(SysProps.MQ_HOST());
					connectionFactory.setPort(SysProps.MQ_PORT());
					connectionFactory.setUsername(SysProps.MQ_USERNAME());
					connectionFactory.setPassword(SysProps.MQ_PASSWORD());

          while (!isHealthy) {
            try {
              Connection connection = connectionFactory.newConnection();
              channel = connection.createChannel();

              channel.exchangeDeclare(SysProps.MQ_LINKS_EXCHANGE(), "topic");
              channel.exchangeDeclare(SysProps.MQ_CHANGES_EXCHANGE(), "topic");
              channel.exchangeDeclare(SysProps.MQ_DEAD_LETTERS_EXCHANGE(), "topic");

              Map<String, Object> args = new HashMap<String, Object>();
              args.put("x-dead-letter-exchange", SysProps.MQ_DEAD_LETTERS_EXCHANGE());

              channel.queueDeclare(SysProps.MQ_NEW_LINKS_QUEUE(), true, false, false, args);
              channel.queueDeclare(SysProps.MQ_AVALIABLE_LINKS_QUEUE(), true, false, false, args);
              channel.queueDeclare(SysProps.MQ_FAILED_LINKS_QUEUE(), true, false, false, args);
              channel.queueDeclare(SysProps.MQ_TOBE_AVAILABLE_LINKS_QUEUE(), true, false, false, args);
              channel.queueDeclare(SysProps.MQ_STATUS_CHANGE_QUEUE(), true, false, false, args);
              channel.queueDeclare(SysProps.MQ_PRICE_CHANGE_QUEUE(), true, false, false, args);
              channel.queueDeclare(SysProps.MQ_DELETED_LINKS_QUEUE(), true, false, false, args);

              channel.queueBind(SysProps.MQ_NEW_LINKS_QUEUE(), SysProps.MQ_LINKS_EXCHANGE(), SysProps.MQ_NEW_LINKS_ROUTING() + ".#");
              channel.queueBind(SysProps.MQ_AVALIABLE_LINKS_QUEUE(), SysProps.MQ_LINKS_EXCHANGE(), SysProps.MQ_AVAILABLE_LINKS_ROUTING() + ".#");
              channel.queueBind(SysProps.MQ_FAILED_LINKS_QUEUE(), SysProps.MQ_LINKS_EXCHANGE(), SysProps.MQ_FAILED_LINKS_ROUTING() + ".#");
              channel.queueBind(SysProps.MQ_TOBE_AVAILABLE_LINKS_QUEUE(), SysProps.MQ_LINKS_EXCHANGE(), SysProps.MQ_TOBE_AVAILABLE_LINKS_ROUTING() + ".#");
              channel.queueBind(SysProps.MQ_STATUS_CHANGE_QUEUE(), SysProps.MQ_CHANGES_EXCHANGE(), SysProps.MQ_STATUS_CHANGES_ROUTING() + ".#");
              channel.queueBind(SysProps.MQ_PRICE_CHANGE_QUEUE(), SysProps.MQ_CHANGES_EXCHANGE(), SysProps.MQ_PRICE_CHANGES_ROUTING() + ".#");
              channel.queueBind(SysProps.MQ_DELETED_LINKS_QUEUE(), SysProps.MQ_CHANGES_EXCHANGE(), SysProps.MQ_DELETED_LINKS_ROUTING() + ".#");

              isHealthy = true;
              log.info("Connected to RabbitMQ server and checked all the exchanges and queues");
            } catch (Exception e) {
              log.error("Failed to connect to RabbitMQ server, trying again in 3 seconds! " + e.getMessage());
              try {
                Thread.sleep(3000);
              } catch (InterruptedException ignored) { }
            }
          }
				}
			}
		}

		return channel;
	}

	public static void closeChannel() {
		try {
			if (isChannelActive()) {
				channel.close();
			}
		} catch (IOException | TimeoutException e) {
			log.error("Error while RabbitMQ.channel is closed.", e);
		}
	}

	public static void publish(String routingKey, Serializable message) {
		publish(SysProps.MQ_LINKS_EXCHANGE(), routingKey, message);
	}

	public static void publish(String exchange, String routingKey, Serializable message) {
    try {
			getChannel().basicPublish(exchange, routingKey, null, SerializationUtils.serialize(message));
		} catch (IOException e) {
			log.error("Failed to send a message to queue", e);
		}
	}

	public static boolean isChannelActive() {
		return (channel != null && channel.isOpen());
	}

}
