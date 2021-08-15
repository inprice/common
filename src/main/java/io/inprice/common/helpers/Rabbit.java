package io.inprice.common.helpers;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.inprice.common.config.SysProps;

/**
 * 
 * @since 2021-08-15
 * @author mdpinar
 */
public class Rabbit {

	private static Logger logger = LoggerFactory.getLogger(Rabbit.class);
	
  private static ConnectionFactory factory;
  private static Map<String, Connection> connectionsMap;

  /**
   * Must be called during the application starting up
   */
  public static void start() {
  	factory = new ConnectionFactory();
  	factory.setHost(SysProps.RABBIT_HOST);
    factory.setPort(SysProps.RABBIT_PORT);
    factory.setUsername(SysProps.RABBIT_USERNAME);
    factory.setPassword(SysProps.RABBIT_PASSWORD);
    connectionsMap = new HashMap<>();
  }

  public static synchronized Connection createConnection(String forWhom) {
  	return createConnection(forWhom, null);
  }

  public static synchronized Connection createConnection(String forWhom, int capacity) {
  	ExecutorService es = null;
  	if (capacity > 0 && capacity < 20) es = Executors.newFixedThreadPool(capacity);
  	return createConnection(forWhom, es);
  }

  public static synchronized Connection createConnection(String forWhom, ExecutorService es) {
  	if (factory != null) {
  		if (StringUtils.isNotBlank(forWhom)) {
      	try {
      		Connection con = connectionsMap.get(forWhom);
      		if (con == null || con.isOpen() == false) {
      			con = (es == null ? factory.newConnection(forWhom) : factory.newConnection(es, forWhom));
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
  	if (connectionsMap != null && connectionsMap.size() > 0) {
  		for (Entry<String, Connection> entry: connectionsMap.entrySet()) {
  			try {
					entry.getValue().close();
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
