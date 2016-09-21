package com.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * Title:MainServer
 * Description:
 * @author    zwb
 * @date      2016年9月21日 上午11:55:09
 *
 */
@EnableAutoConfiguration
@ComponentScan
public class MainServer {
    public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }
}
