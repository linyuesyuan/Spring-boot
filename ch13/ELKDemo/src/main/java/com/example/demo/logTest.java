package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class logTest {
    private Logger logger = LoggerFactory.getLogger(logTest.class);
    @Scheduled(fixedRate = 1000)
    public void logtest(){
        logger.trace("trace記錄檔");
        logger.debug("debug記錄檔");
        logger.info("info記錄檔");
        logger.warn("warn記錄檔");
        logger.error("error記錄檔");
    }
}
