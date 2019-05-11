package io.inprice.scrapper.common.helpers;

import io.inprice.scrapper.common.config.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPools {

	public static final ExecutorService MASTER_POOL;

	static {
		int masterCapacity = Config.TPOOLS_MASTER_CAPACITY;
		MASTER_POOL = new ThreadPoolExecutor(masterCapacity, masterCapacity, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>());
	}

}
