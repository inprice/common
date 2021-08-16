package io.inprice.common.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used for excluding field from being serialized by gson
 * 
 * https://stackoverflow.com/a/27986860/1654265
 * 
 * @author mdpinar
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Exclude {
}