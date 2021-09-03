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

import java.util.Set;

/**
 *
 * @author mdumlupinar
 * @param <K> the Key type
 */
public interface ExpiringSet<K> extends Set<K> {

	/**
	 * Renews the specified key, setting the life time to the initial value.
	 *
	 * @param key
	 * @return true if the key is found, false otherwise
	 */
	public boolean renewKey(K key);

	/**
	 * Adds the given key in this set, with the specified
	 * life times in milliseconds.
	 *
	 * @param key
	 * @param lifeTimeMillis
	 * @return true for the given key (if exists).
	 */
	public boolean add(K key, long lifeTimeMillis);

}