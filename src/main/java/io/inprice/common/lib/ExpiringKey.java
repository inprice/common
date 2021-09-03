package io.inprice.common.lib;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
public class ExpiringKey<K> implements Delayed {

	private long startTime = System.currentTimeMillis();
	private final long maxLifeTimeMillis;
	private final K key;

	public ExpiringKey(K key, long maxLifeTimeMillis) {
		this.maxLifeTimeMillis = maxLifeTimeMillis;
		this.key = key;
	}

	public K getKey() {
		return key;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ExpiringKey<K> other = (ExpiringKey<K>) obj;
		if (this.key != other.key && (this.key == null || !this.key.equals(other.key))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (this.key != null ? this.key.hashCode() : 0);
		return hash;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(getDelayMillis(), TimeUnit.MILLISECONDS);
	}

	private long getDelayMillis() {
		return (startTime + maxLifeTimeMillis) - System.currentTimeMillis();
	}

	public void renew() {
		startTime = System.currentTimeMillis();
	}

	public void expire() {
		startTime = Long.MIN_VALUE;
	}

	@Override
	public int compareTo(Delayed that) {
		return Long.compare(this.getDelayMillis(), ((ExpiringKey<K>) that).getDelayMillis());
	}

}
