package io.inprice.scrapper.common.config;

public class Config {

	// RabbitMQ
	public static final String RABBITMQ_HOST;
	public static final int RABBITMQ_PORT;
	public static final String RABBITMQ_USERNAME;
	public static final String RABBITMQ_PASSWORD;

	public static final String RABBITMQ_LINK_EXCHANGE;

	// Queues
	public static final String RABBITMQ_NEW_LINKS_QUEUE;
	public static final String RABBITMQ_ACTIVE_LINKS_QUEUE;
	public static final String RABBITMQ_SOCKET_ERRORS_QUEUE;
	public static final String RABBITMQ_FAILED_LINKS_QUEUE;
	public static final String RABBITMQ_CHANGE_STATUS_QUEUE;
	public static final String RABBITMQ_CHANGE_PRICE_QUEUE;

	// Thread Pools
	public static final int TPOOLS_MASTER_CAPACITY;
	public static final int TPOOLS_WORKER_CAPACITY;

	// DB
	public static final String DB_HOST;
	public static final int DB_PORT;
	public static final String DB_DATABASE;
	public static final String DB_USERNAME;
	public static final String DB_PASSWORD;
	public static final int DB_FETCH_LIMIT;

	// Redis
	public static final String REDIS_HOST;
	public static final int REDIS_PORT;
	public static final String REDIS_PASSWORD;
	public static final int REDIS_POOL_SIZE;
	public static final int REDIS_TTL_HOURS_FOR_TOKENS;

	// Crontabs
	public static final String CRONTAB_FOR_NEW_LINKS;
	public static final String CRONTAB_FOR_ACTIVE_LINKS;
	public static final String CRONTAB_FOR_FAILED_LINKS;
	public static final String CRONTAB_FOR_SOCKET_ERRORS;

	// Waiting times
	public static final long WAITING_TIME_FOR_AWAIT_TERMINATION;
	public static final long WAITING_TIME_FOR_GETTING_LINKS_FROM_DB;

	// Retry limits
	public static final long RETRY_LIMIT_FOR_QUEUE_PROBLEMS;
	public static final long RETRY_LIMIT_FOR_FAILED_LINKS;

	static {
		RABBITMQ_HOST = getOrDefault("RABBITMQ_HOST", "localhost");
		RABBITMQ_PORT = getOrDefault("RABBITMQ_PORT", 5672);
		RABBITMQ_USERNAME = getOrDefault("RABBITMQ_USERNAME", "guest");
		RABBITMQ_PASSWORD = getOrDefault("RABBITMQ_PASSWORD", "guest");

		RABBITMQ_LINK_EXCHANGE = getOrDefault("RABBITMQ_LINK_EXCHANGE", "links");

		RABBITMQ_NEW_LINKS_QUEUE = getOrDefault("RABBITMQ_NEW_LINKS_QUEUE", "new-links");
		RABBITMQ_ACTIVE_LINKS_QUEUE = getOrDefault("RABBITMQ_ACTIVE_LINKS_QUEUE", "active-links");
		RABBITMQ_SOCKET_ERRORS_QUEUE = getOrDefault("RABBITMQ_SOCKET_ERRORS_QUEUE", "socket-errors");
		RABBITMQ_FAILED_LINKS_QUEUE = getOrDefault("RABBITMQ_FAILED_LINKS_QUEUE", "failed-links");

		RABBITMQ_CHANGE_STATUS_QUEUE = getOrDefault("RABBITMQ_CHANGE_STATUS_QUEUE", "change-status");
		RABBITMQ_CHANGE_PRICE_QUEUE = getOrDefault("RABBITMQ_CHANGE_PRICE_QUEUE", "change-price");

		TPOOLS_MASTER_CAPACITY = getOrDefault("TPOOLS_MASTER_CAPACITY", 2);
		TPOOLS_WORKER_CAPACITY = getOrDefault("TPOOLS_WORKER_CAPACITY", 2);

		DB_HOST = getOrDefault("DB_HOST", "localhost");
		DB_PORT = getOrDefault("DB_PORT", 3306);
		DB_DATABASE = getOrDefault("DB_DATABASE", "inprice");
		DB_USERNAME = getOrDefault("DB_USERNAME", "root");
		DB_PASSWORD = getOrDefault("DB_PASSWORD", "1234");
		DB_FETCH_LIMIT = getOrDefault("DB_FETCH_LIMIT", 100);

		REDIS_HOST = getOrDefault("REDIS_HOST", "localhost");
		REDIS_PORT = getOrDefault("REDIS_PORT", 6379);
		REDIS_PASSWORD = getOrDefault("REDIS_PASSWORD", null);
		REDIS_POOL_SIZE = getOrDefault("REDIS_POOL_SIZE", 8);
		REDIS_TTL_HOURS_FOR_TOKENS = getOrDefault("REDIS_TTL_HOURS_FOR_TOKENS", 3);

		WAITING_TIME_FOR_AWAIT_TERMINATION = getOrDefault("WTF_AWAIT_TERMINATION", 30000L);
		WAITING_TIME_FOR_GETTING_LINKS_FROM_DB = getOrDefault("WTF_GETTING_LINKS_FROM_DB", 3000L);

		RETRY_LIMIT_FOR_QUEUE_PROBLEMS = getOrDefault("RL_QUEUE_PROBLEMS", 3);
		RETRY_LIMIT_FOR_FAILED_LINKS = getOrDefault("RL_FAILED_LINKS", 3);

		CRONTAB_FOR_NEW_LINKS = getOrDefault("CRONTAB_FOR_NEW_LINKS", "0 */5 * * * ?");
		CRONTAB_FOR_ACTIVE_LINKS = getOrDefault("CRONTAB_FOR_ACTIVE_LINKS", "0 0 */3 * * ?");
		CRONTAB_FOR_SOCKET_ERRORS = getOrDefault("CRONTAB_FOR_SOCKET_ERRORS", "0 */15 * * * ?");
		CRONTAB_FOR_FAILED_LINKS = getOrDefault("CRONTAB_FOR_FAILED_LINKS", "0 0 */4 * * ?");
	}

	private static String getOrDefault(String key, String defauld) {
		String val = System.getenv(key);
		if (val != null && val.trim().length() > 0) return val;
		return defauld;
	}

	private static int getOrDefault(String key, int defauld) {
		String val = System.getenv(key);
		if (val != null && val.trim().length() > 0) return Integer.parseInt(val.trim());
		return defauld;
	}

	private static long getOrDefault(String key, long defauld) {
		String val = System.getenv(key);
		if (val != null && val.trim().length() > 0) return Long.parseLong(val.trim());
		return defauld;
	}

}
