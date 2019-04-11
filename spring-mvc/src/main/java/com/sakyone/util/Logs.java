package com.sakyone.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Logs {

    public static Logger get() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        return LoggerFactory.getLogger(sts[2].getClassName());
    }

}
