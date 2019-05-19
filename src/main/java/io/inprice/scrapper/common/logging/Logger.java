package io.inprice.scrapper.common.logging;

import io.inprice.scrapper.common.utils.DateUtils;

/**
 * Base logging service
 *
 * @author mdpinar
 */
public class Logger {

    private String name;

    public Logger(String name) {
        this.name = name;
    }

    public Logger(Class clazz) {
        this.name = clazz.getSimpleName();
    }

    public void debug(String message, Object... args) {
        print(LogLevel.DEBUG, message, null, args);
    }

    public void info(String message, Object... args) {
        print(LogLevel.INFO, message, null, args);
    }

    public void warn(String message) {
        print(LogLevel.WARN, message, null);
    }

    public void warn(String message, Object... args) {
        print(LogLevel.WARN, message, null, args);
    }

    public void error(String message) {
        print(LogLevel.ERROR, message, null);
    }

    public void error(String message, Object... args) {
        print(LogLevel.ERROR, message, null, args);
    }

    public void error(String message, Throwable t) {
        print(LogLevel.ERROR, message, t);
    }

    public void error(Throwable t) {
        print(LogLevel.ERROR, null, t);
    }

    public void fatal(String message) {
        print(LogLevel.FATAL, message, null);
    }

    public void fatal(String message, Object... args) {
        print(LogLevel.FATAL, message, null, args);
    }

    public void fatal(String message, Throwable t) {
        print(LogLevel.FATAL, message, t);
    }

    public void fatal(Throwable t) {
        print(LogLevel.FATAL, null, t);
    }

    private void print(LogLevel level, String message, Throwable t, Object... args) {
        StringBuilder sb = new StringBuilder(DateUtils.nowForLogging());
        sb.append(" ");
        sb.append(String.format("[%-5s]", level));
        sb.append(" ");
        sb.append(String.format("(%s)", Thread.currentThread().getName()));
        sb.append(" [");
        sb.append(name);
        sb.append(":");
        sb.append(Thread.currentThread().getStackTrace()[3].getLineNumber());
        sb.append("] ");

        if (message != null) {
            if (args != null && args.length > 0)
                sb.append(String.format(message, args));
            else
                sb.append(message);
        }

        if (level.ordinal() < LogLevel.ERROR.ordinal()) {
            System.out.println(sb.toString());
        } else {
            System.err.println(sb.toString());
            if (t != null) {
                t.printStackTrace();
            }
        }
    }

}
