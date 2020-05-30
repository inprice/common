package io.inprice.scrapper.common.helpers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.inprice.scrapper.common.config.SysProps;

public class RabbitMQ {

	private static final Logger log = LoggerFactory.getLogger(RabbitMQ.class);

  private static Connection connection;

	public synchronized static Channel openChannel() {
    Channel channel = null;

    while (connection == null || !connection.isOpen()) {
      try {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost(SysProps.MQ_HOST());
        cf.setPort(SysProps.MQ_PORT());
        cf.setUsername(SysProps.MQ_USERNAME());
        cf.setPassword(SysProps.MQ_PASSWORD());
        cf.setAutomaticRecoveryEnabled(true);
    
        connection = cf.newConnection();
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
        channel.queueDeclare(SysProps.MQ_DEAD_LETTERS_EXCHANGE(), true, false, false, null);

        channel.queueBind(SysProps.MQ_NEW_LINKS_QUEUE(), SysProps.MQ_LINKS_EXCHANGE(), SysProps.MQ_NEW_LINKS_ROUTING() + ".#");
        channel.queueBind(SysProps.MQ_AVALIABLE_LINKS_QUEUE(), SysProps.MQ_LINKS_EXCHANGE(), SysProps.MQ_AVAILABLE_LINKS_ROUTING() + ".#");
        channel.queueBind(SysProps.MQ_FAILED_LINKS_QUEUE(), SysProps.MQ_LINKS_EXCHANGE(), SysProps.MQ_FAILED_LINKS_ROUTING() + ".#");
        channel.queueBind(SysProps.MQ_TOBE_AVAILABLE_LINKS_QUEUE(), SysProps.MQ_LINKS_EXCHANGE(), SysProps.MQ_TOBE_AVAILABLE_LINKS_ROUTING() + ".#");
        channel.queueBind(SysProps.MQ_STATUS_CHANGE_QUEUE(), SysProps.MQ_CHANGES_EXCHANGE(), SysProps.MQ_STATUS_CHANGES_ROUTING() + ".#");
        channel.queueBind(SysProps.MQ_PRICE_CHANGE_QUEUE(), SysProps.MQ_CHANGES_EXCHANGE(), SysProps.MQ_PRICE_CHANGES_ROUTING() + ".#");
        channel.queueBind(SysProps.MQ_DELETED_LINKS_QUEUE(), SysProps.MQ_CHANGES_EXCHANGE(), SysProps.MQ_DELETED_LINKS_ROUTING() + ".#");
        channel.queueBind(SysProps.MQ_DEAD_LETTERS_EXCHANGE(), SysProps.MQ_DEAD_LETTERS_EXCHANGE(), "#");

        log.info("Connected to RabbitMQ server and checked all the exchanges and queues");

      } catch (Exception e) {
        log.error("Failed to connect to RabbitMQ server, trying again in 3 seconds! " + e.getMessage());
        try {
          Thread.sleep(3000);
        } catch (InterruptedException ignored) { }
      }
    }

    if (channel == null && connection != null && connection.isOpen()) {
      try {
        channel = connection.createChannel();
      } catch (Exception ignored) { }
    }

		return channel;
	}

  public static synchronized void closeConnection() {
    if (connection != null || connection.isOpen()) {
      try {
        connection.close();
      } catch (IOException e) {
        log.error("Failed to close RabbitMQ connection", e);
      }
    }
  }

	public static void closeChannel(Channel channel) {
		try {
      channel.close();
		} catch (IOException | TimeoutException e) {
			log.error("Failed to close a channel", e);
		}
	}

	public static void publishLink(Channel channel, String routingKey, String message) {
		publish(channel, SysProps.MQ_LINKS_EXCHANGE(), routingKey, message);
	}

	public static void publish(Channel channel, String exchange, String routingKey, String message) {
    try {
      channel.basicPublish(exchange, routingKey, null, message.getBytes());
		} catch (IOException e) {
			log.error("Failed to send a message to queue", e);
		}
	}

}
