package io.inprice.scrapper.common.utils;

import java.util.Arrays;

public class ArrayUtils {

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0 ,result, first.length, second.length);
        return result;
    }

}
