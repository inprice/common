package io.inprice.scrapper.common.config;

public class Config {

	// RabbitMQ
	public static final String RABBITMQ_HOST;
	public static final int RABBITMQ_PORT;
	public static final String RABBITMQ_USERNAME;
	public static final String RABBITMQ_PASSWORD;
	public static final String RABBITMQ_EXCHANGE;

	// Queues
	public static final String RABBITMQ_TASK_QUEUE;
	public static final String RABBITMQ_REPORT_QUEUE;
	public static final String RABBITMQ_PROBLEM_QUEUE;

	// Thread Pools
	public static final int TPOOLS_MASTER_CAPACITY;

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

	// Others
	public static final long TIME_FOR_TPOOL_TERMINATION;
	public static final long TIME_FOR_GETTING_ACTIVE_SITES;

	public static final long LIMIT_FOR_QUEUE_PROBLEMS;

	static {
		RABBITMQ_HOST = getOrDefault("RABBITMQ_HOST", "localhost");
		RABBITMQ_PORT = getOrDefault("RABBITMQ_PORT", 5672);
		RABBITMQ_USERNAME = getOrDefault("RABBITMQ_USERNAME", "guest");
		RABBITMQ_PASSWORD = getOrDefault("RABBITMQ_PASSWORD", "guest");
		RABBITMQ_EXCHANGE = getOrDefault("RABBITMQ_EXCHANGE", "crawler");
		RABBITMQ_TASK_QUEUE = getOrDefault("RABBITMQ_TASK_QUEUE", "crawler-task");
		RABBITMQ_REPORT_QUEUE = getOrDefault("RABBITMQ_REPORT_QUEUE", "crawler-report");
		RABBITMQ_PROBLEM_QUEUE = getOrDefault("RABBITMQ_PROBLEM_QUEUE", "crawler-problem");

		TPOOLS_MASTER_CAPACITY = getOrDefault("POOLS_MASTER_CAPACITY", 2);

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

		TIME_FOR_TPOOL_TERMINATION = getOrDefault("TF_AWAIT_TERMINATION", 30000L);
		TIME_FOR_GETTING_ACTIVE_SITES = getOrDefault("TF_GETTING_ACTIVE_SITES", 5000L);
		LIMIT_FOR_QUEUE_PROBLEMS = getOrDefault("LF_QUEUE_PROBLEMS", 3);
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
