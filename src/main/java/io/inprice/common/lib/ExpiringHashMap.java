/*
 * Copyright (c) 2019 Pierantonio Cangianiello
 * 
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.inprice.common.lib;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

/**
 * A HashMap which entries expires after the specified life time. The life-time
 * can be defined on a per-key basis, or using a default one, that is passed to
 * the constructor.
 * 
 * @author Pierantonio Cangianiello
 * @param <K> the Key type
 * @param <V> the Value type
 */
@SuppressWarnings("unchecked")
public class ExpiringHashMap<K, V> implements ExpiringMap<K, V> {

	private final Map<K, V> internalMap;
	private final Map<K, ExpiringKey<K>> expiringKeys;

	/**
	 * Holds the map keys using the given life time for expiration.
	 */
	private final DelayQueue<ExpiringKey<K>> delayQueue = new DelayQueue<ExpiringKey<K>>();

	/**
	 * The default max life time in milliseconds.
	 */
	private final long maxLifeTimeMillis;

  public ExpiringHashMap() {
    internalMap = new ConcurrentHashMap<K, V>();
    expiringKeys = new WeakHashMap<K, ExpiringKey<K>>();
    this.maxLifeTimeMillis = Long.MAX_VALUE;
  }

	public ExpiringHashMap(long defaultMaxLifeTimeMillis) {
		internalMap = new ConcurrentHashMap<K, V>();
		expiringKeys = new WeakHashMap<K, ExpiringKey<K>>();
		this.maxLifeTimeMillis = defaultMaxLifeTimeMillis;
	}

	public ExpiringHashMap(long defaultMaxLifeTimeMillis, int initialCapacity) {
		internalMap = new ConcurrentHashMap<K, V>(initialCapacity);
		expiringKeys = new WeakHashMap<K, ExpiringKey<K>>(initialCapacity);
		this.maxLifeTimeMillis = defaultMaxLifeTimeMillis;
	}

	@Override
	public int size() {
		cleanup();
		return internalMap.size();
	}

	@Override
	public boolean isEmpty() {
		cleanup();
		return internalMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		cleanup();
		return internalMap.containsKey((K) key);
	}

	@Override
	public boolean containsValue(Object value) {
		cleanup();
		return internalMap.containsValue((V) value);
	}

	@Override
	public V get(Object key) {
		cleanup();
		renewKey((K) key);
		return internalMap.get((K) key);
	}

	@Override
	public V put(K key, V value) {
		return this.put(key, value, maxLifeTimeMillis);
	}

	@Override
	public V put(K key, V value, long lifeTimeMillis) {
		cleanup();
		ExpiringKey<K> delayedKey = new ExpiringKey<>(key, lifeTimeMillis);
		ExpiringKey<K> oldKey = expiringKeys.put((K) key, delayedKey);
		if (oldKey != null) {
			expireKey(oldKey);
			expiringKeys.put((K) key, delayedKey);
		}
		delayQueue.offer(delayedKey);
		return internalMap.put(key, value);
	}

	@Override
	public V remove(Object key) {
		V removedValue = internalMap.remove((K) key);
		expireKey(expiringKeys.remove((K) key));
		return removedValue;
	}

	/**
	 * Not supported.
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean renewKey(K key) {
		ExpiringKey<K> delayedKey = expiringKeys.get((K) key);
		if (delayedKey != null) {
			delayedKey.renew();
			return true;
		}
		return false;
	}

	private void expireKey(ExpiringKey<K> delayedKey) {
		if (delayedKey != null) {
			delayedKey.expire();
			cleanup();
		}
	}

	@Override
	public void clear() {
		delayQueue.clear();
		expiringKeys.clear();
		internalMap.clear();
	}

	private void cleanup() {
		ExpiringKey<K> delayedKey = delayQueue.poll();
		while (delayedKey != null) {
			internalMap.remove(delayedKey.getKey());
			expiringKeys.remove(delayedKey.getKey());
			delayedKey = delayQueue.poll();
		}
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		cleanup();
		return internalMap.entrySet();
	}

	@Override
	public Set<K> keySet() {
		cleanup();
		return internalMap.keySet();
	}

	@Override
	public Collection<V> values() {
		cleanup();
		return internalMap.values();
	}

}