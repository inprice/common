package io.inprice.common.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used for excluding fields of a class from being serialized by gson
 * 
 * @author mdpinar
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Excludes {
	
	String[] fields() default "";
	
}