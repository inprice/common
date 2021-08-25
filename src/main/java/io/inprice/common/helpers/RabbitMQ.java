package io.inprice.common.helpers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeoutException;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import io.inprice.common.config.RabbitConf;

/**
 * 
 * @since 2021-08-15
 * @author mdpinar
 */
public class RabbitMQ {

	private static Logger logger = LoggerFactory.getLogger(RabbitMQ.class);
	
  private static ConnectionFactory factory;
  private static Map<String, Connection> connectionsMap;

  /**
   * Must be called during the application starting up
   */
  public static void start(RabbitConf conf) {
  	factory = new ConnectionFactory();
  	factory.setHost(conf.HOST);
    factory.setPort(conf.PORT);
    factory.setUsername(conf.USERNAME);
    factory.setPassword(conf.PASSWORD);
    connectionsMap = new HashMap<>();
  }

  public static Connection createConnection(String forWhom) {
  	if (factory != null) {
  		if (StringUtils.isNotBlank(forWhom)) {
      	try {
      		Connection con = connectionsMap.get(forWhom);
      		if (con == null || con.isOpen() == false) {
    	  		con = factory.newConnection(forWhom);
      			connectionsMap.put(forWhom, con);
      		}
  				return con;
  			} catch (IOException | TimeoutException e) {
  				logger.error("Failed to create a new rabbitmq connection", e);
  			}
    	} else {
    		logger.warn("forWhom value must be given!");
    	}
  	} else {
  		logger.error("Rabbit connection factory has not been initialized!");
  	}
  	return null;
  }

  /**
   * Must be called during the application shutting down
   */
  public static synchronized void stop() {
    if (MapUtils.isNotEmpty(connectionsMap)) {
  		for (Entry<String, Connection> entry: connectionsMap.entrySet()) {
  			try {
					if (entry.getValue().isOpen()) entry.getValue().close();
	  			logger.info(" - Rabbit connection: " + entry.getKey() + " is closed.");
				} catch (IOException e) {
	  			logger.info(" - Failed to close rabbit connection: " + entry.getKey(), e);
				}
  		}
  		connectionsMap.clear();
  		factory = null;
  	}
  }

}
