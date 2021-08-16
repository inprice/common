package io.inprice.common.framework;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Used for excluding field from being serialized by gson
 * 
 * https://stackoverflow.com/a/27986860/1654265
 * 
 * @author mdpinar
 */
public class FieldExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		if (f.getAnnotation(Exclude.class) != null) return true;

		Excludes excludes = f.getDeclaringClass().getAnnotation(Excludes.class);
		if (excludes != null) {
			String[] fields = excludes.fields();
			for (String field: fields) {
				if (field.equals(f.getName())) return true;
			}
		}
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}
