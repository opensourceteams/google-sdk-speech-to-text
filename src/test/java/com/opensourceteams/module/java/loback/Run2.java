package com.opensourceteams.module.java.loback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Run2 {


    static Logger logger = LoggerFactory.getLogger(Run2.class);

    public static void main(String[] args) {

        logger.info("INFO信息");
        logger.debug("DEBUG信息");


        //打印 Logback 内部状态
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}
