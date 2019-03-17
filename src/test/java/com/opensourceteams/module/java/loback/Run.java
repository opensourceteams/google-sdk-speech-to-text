package com.opensourceteams.module.java.loback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Run {


    static Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {

        logger.info("INFO信息");
        logger.debug("DEBUG信息");
       logger.error("ERROR信息");
    }
}
