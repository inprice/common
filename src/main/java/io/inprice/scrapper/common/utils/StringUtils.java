package io.inprice.scrapper.common.utils;

import java.util.Collection;
import java.util.Iterator;

public class StringUtils {

    public static String join(Collection<?> col, String delim) {
        StringBuilder sb = new StringBuilder();
        Iterator<?> iter = col.iterator();
        if (iter.hasNext()) {
            sb.append(iter.next().toString());
        }
        while (iter.hasNext()) {
            sb.append(delim);
            sb.append(iter.next().toString());
        }
        return sb.toString();
    }

    public static String fill(String s, int n) {
        if (n > 0) {
            return padRight(s, n).replaceAll("\\s", s);
        } else {
            return "";
        }
    }

    public static String trimLastSlash(String path) {
        if (path != null && (path.endsWith("/") || path.endsWith("\\"))) {
            return path.substring(0, path.length() - 1);
        }
        return path;
    }

    public static String pad(String s, int n, int align) {
        if (s != null && s.length() < n) {
            switch (align) {
                case 0:
                    return padRight(s, n);
                case 1:
                    return padCenter(s, n);
                case 2:
                    return padLeft(s, n);
            }
        }
        return s;
    }

    public static String padRight(String s, int n) {
        if (n > 0) {
            return String.format("%1$-" + n + "s", s);
        } else {
            return "";
        }
    }

    public static String padLeft(String s, int n) {
        if (n > 0) {
            return String.format("%1$" + n + "s", s);
        } else {
            return "";
        }
    }

    public static String padCenter(String s, int w) {
        if (w > 0 && s.length() < w) {
            int spaceSize = (w - s.length()) / 2;
            String neu = fill(" ", spaceSize) + s;
            return neu + fill(" ", w - neu.length());
        }
        return s;
    }

    public static String trimLeft(String s) {
        return s.replaceAll("^\\s+", "");
    }

    public static String trimRight(String s) {
        return s.replaceAll("\\s+$", "");
    }

    public static String capitalize(String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

}
