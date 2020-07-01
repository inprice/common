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
import io.inprice.scrapper.common.meta.AppEnv;

public class RabbitMQ {

	private static final Logger log = LoggerFactory.getLogger(RabbitMQ.class);

  private static Connection connection;
  private static int conRetry;

	public static Channel openChannel() {
    synchronized (log) {
      Channel channel = null;

      int retryLimit = 30;

      while (conRetry < retryLimit && (connection == null || !connection.isOpen())) {
        try {
          ConnectionFactory cf = new ConnectionFactory();
          if (SysProps.MQ_URI() != null) {
            cf.setUri(SysProps.MQ_URI());
          } else {
            cf.setHost(SysProps.MQ_HOST());
            cf.setPort(SysProps.MQ_PORT());
            cf.setUsername(SysProps.MQ_USERNAME());
            cf.setPassword(SysProps.MQ_PASSWORD());
          }

          cf.setRequestedHeartbeat(30);
          cf.setConnectionTimeout(30000);

          connection = cf.newConnection();
          channel = connection.createChannel();

          channel.exchangeDeclare(SysProps.MQ_COMPETITORS_EXCHANGE(), "topic");
          channel.exchangeDeclare(SysProps.MQ_CHANGES_EXCHANGE(), "topic");
          channel.exchangeDeclare(SysProps.MQ_DEAD_LETTERS_EXCHANGE(), "topic");

          Map<String, Object> args = new HashMap<String, Object>();
          args.put("x-dead-letter-exchange", SysProps.MQ_DEAD_LETTERS_EXCHANGE());

          channel.queueDeclare(SysProps.MQ_TOBE_CLASSIFIED_COMPETITORS_QUEUE(), true, false, false, args);
          channel.queueDeclare(SysProps.MQ_AVALIABLE_COMPETITORS_QUEUE(), true, false, false, args);
          channel.queueDeclare(SysProps.MQ_FAILED_COMPETITORS_QUEUE(), true, false, false, args);
          channel.queueDeclare(SysProps.MQ_BLOCKED_COMPETITORS_QUEUE(), true, false, false, args);
          channel.queueDeclare(SysProps.MQ_TOBE_AVAILABLE_COMPETITORS_QUEUE(), true, false, false, args);
          channel.queueDeclare(SysProps.MQ_PRODUCT_CREATION_QUEUE(), true, false, false, args);
          channel.queueDeclare(SysProps.MQ_STATUS_CHANGE_QUEUE(), true, false, false, args);
          channel.queueDeclare(SysProps.MQ_PRICE_CHANGE_QUEUE(), true, false, false, args);
          channel.queueDeclare(SysProps.MQ_PRICE_REFRESH_QUEUE(), true, false, false, args);
          channel.queueDeclare(SysProps.MQ_DEAD_LETTERS_EXCHANGE(), true, false, false, null);

          channel.queueBind(SysProps.MQ_TOBE_CLASSIFIED_COMPETITORS_QUEUE(), SysProps.MQ_COMPETITORS_EXCHANGE(), SysProps.MQ_TOBE_CLASSIFIED_COMPETITORS_ROUTING() + ".#");
          channel.queueBind(SysProps.MQ_AVALIABLE_COMPETITORS_QUEUE(), SysProps.MQ_COMPETITORS_EXCHANGE(), SysProps.MQ_AVAILABLE_COMPETITORS_ROUTING() + ".#");
          channel.queueBind(SysProps.MQ_FAILED_COMPETITORS_QUEUE(), SysProps.MQ_COMPETITORS_EXCHANGE(), SysProps.MQ_FAILED_COMPETITORS_ROUTING() + ".#");
          channel.queueBind(SysProps.MQ_BLOCKED_COMPETITORS_QUEUE(), SysProps.MQ_COMPETITORS_EXCHANGE(), SysProps.MQ_BLOCKED_COMPETITORS_ROUTING() + ".#");
          channel.queueBind(SysProps.MQ_TOBE_AVAILABLE_COMPETITORS_QUEUE(), SysProps.MQ_COMPETITORS_EXCHANGE(), SysProps.MQ_TOBE_AVAILABLE_COMPETITORS_ROUTING() + ".#");
          channel.queueBind(SysProps.MQ_PRODUCT_CREATION_QUEUE(), SysProps.MQ_CHANGES_EXCHANGE(), SysProps.MQ_PRODUCT_CREATIONS_ROUTING() + ".#");
          channel.queueBind(SysProps.MQ_STATUS_CHANGE_QUEUE(), SysProps.MQ_CHANGES_EXCHANGE(), SysProps.MQ_STATUS_CHANGES_ROUTING() + ".#");
          channel.queueBind(SysProps.MQ_PRICE_CHANGE_QUEUE(), SysProps.MQ_CHANGES_EXCHANGE(), SysProps.MQ_PRICE_CHANGES_ROUTING() + ".#");
          channel.queueBind(SysProps.MQ_PRICE_REFRESH_QUEUE(), SysProps.MQ_CHANGES_EXCHANGE(), SysProps.MQ_PRICE_REFRESH_ROUTING() + ".#");
          channel.queueBind(SysProps.MQ_DEAD_LETTERS_EXCHANGE(), SysProps.MQ_DEAD_LETTERS_EXCHANGE(), "#");

          if (SysProps.APP_ENV().equals(AppEnv.DEV)) {
            channel.queueDeclare(SysProps.MQ_CATCH_ALL_QUEUE(), true, false, false, null);
            channel.queueBind(SysProps.MQ_CATCH_ALL_QUEUE(), SysProps.MQ_COMPETITORS_EXCHANGE(), "#");
            channel.queueBind(SysProps.MQ_CATCH_ALL_QUEUE(), SysProps.MQ_CHANGES_EXCHANGE(), "#");
          }

          log.info("Connected to RabbitMQ server and checked all the exchanges and queues");
          conRetry = 0;

        } catch (Exception e) {
          log.error("Failed to connect to RabbitMQ server, trying again in 3 seconds. {}, Retry: {}/{}", e.getMessage(), conRetry+1, retryLimit);
          try {
            conRetry++;
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
	}

  public static synchronized void closeConnection() {
    if (connection != null && connection.isOpen()) {
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

	public static void publishCompetitor(Channel channel, String routingKey, String message) {
		publish(channel, SysProps.MQ_COMPETITORS_EXCHANGE(), routingKey, message);
	}

	public static void publish(Channel channel, String exchange, String routingKey, String message) {
    try {
      channel.basicPublish(exchange, routingKey, null, message.getBytes());
		} catch (IOException e) {
			log.error("Failed to send a message to queue", e);
		}
	}

}
