package io.inprice.common;

import java.util.Iterator;

import io.inprice.common.lib.ExpiringHashSet;
import io.inprice.common.lib.ExpiringSet;

public class Test {
  
	public static void main(String[] args) {
		ExpiringSet<String> expiringStringSet = new ExpiringHashSet<>();
		expiringStringSet.add("Adana", 1000);
		expiringStringSet.add("Bursa", 2000);
		expiringStringSet.add("Ceyhan", 3000);
		expiringStringSet.add("Diyarbakır", 4000);

		for (int i = 0; i < 5; i++) {
			for (Iterator<String> iterator = expiringStringSet.iterator(); iterator.hasNext();) {
				System.out.print(iterator.next() + ", ");
			}
			System.out.println(expiringStringSet.size());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			if (i==1)expiringStringSet.clear();
		}
		
  }

}