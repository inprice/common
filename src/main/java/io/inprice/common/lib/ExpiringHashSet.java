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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

/**
 * A HashSet which entries expires after the specified life time. The life-time
 * can be defined on a per-key basis, or using a default one, that is passed to
 * the constructor.
 * 
 * @author mdumlupinar
 * @param <K> the Key type
 */
@SuppressWarnings("unchecked")
public class ExpiringHashSet<K> implements ExpiringSet<K> {

	private final Set<K> internalSet;
	private final Map<K, ExpiringKey<K>> expiringKeys;

	/**
	 * Holds the map keys using the given life time for expiration.
	 */
	private final DelayQueue<ExpiringKey<K>> delayQueue = new DelayQueue<ExpiringKey<K>>();

	/**
	 * The default max life time in milliseconds.
	 */
	private final long maxLifeTimeMillis;

	public ExpiringHashSet() {
		internalSet = ConcurrentHashMap.newKeySet();
		expiringKeys = new WeakHashMap<K, ExpiringKey<K>>();
    this.maxLifeTimeMillis = Long.MAX_VALUE;
	}

	public ExpiringHashSet(long defaultMaxLifeTimeMillis) {
		internalSet = ConcurrentHashMap.newKeySet();
		expiringKeys = new WeakHashMap<K, ExpiringKey<K>>();
		this.maxLifeTimeMillis = defaultMaxLifeTimeMillis;
	}

	public ExpiringHashSet(long defaultMaxLifeTimeMillis, int initialCapacity) {
		internalSet = ConcurrentHashMap.newKeySet(initialCapacity);
		expiringKeys = new WeakHashMap<K, ExpiringKey<K>>(initialCapacity);
		this.maxLifeTimeMillis = defaultMaxLifeTimeMillis;
	}

	@Override
	public int size() {
		cleanup();
		return internalSet.size();
	}

	@Override
	public boolean isEmpty() {
		cleanup();
		return internalSet.isEmpty();
	}

	@Override
	public boolean contains(Object key) {
		cleanup();
		return internalSet.contains((K) key);
	}

	@Override
	public boolean add(K key) {
		return this.add(key, maxLifeTimeMillis);
	}

	@Override
	public boolean add(K key, long lifeTimeMillis) {
		cleanup();
		ExpiringKey<K> delayedKey = new ExpiringKey<>(key, lifeTimeMillis);
		ExpiringKey<K> oldKey = expiringKeys.put((K) key, delayedKey);
		if (oldKey != null) {
			expireKey(oldKey);
			expiringKeys.put((K) key, delayedKey);
		}
		delayQueue.offer(delayedKey);
		return internalSet.add(key);
	}

	@Override
	public boolean remove(Object key) {
		boolean result = internalSet.remove((K) key);
		expireKey(expiringKeys.remove((K) key));
		return result;
	}

	/**
	 * Not supported.
	 */
	@Override
	public boolean addAll(Collection<? extends K> m) {
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
		internalSet.clear();
	}

	private void cleanup() {
		ExpiringKey<K> delayedKey = delayQueue.poll();
		while (delayedKey != null) {
			internalSet.remove(delayedKey.getKey());
			expiringKeys.remove(delayedKey.getKey());
			delayedKey = delayQueue.poll();
		}
	}

	@Override
	public Iterator<K> iterator() {
		cleanup();
		return internalSet.iterator();
	}

	@Override
	public Object[] toArray() {
		cleanup();
		return internalSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		cleanup();
		return internalSet.toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

}