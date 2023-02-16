package com.adetola.bt.myguestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}, scanBasePackages = {"com.adetola.bt.myguestbook.repository"})
public class MyguestbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyguestbookApplication.class, args);
    }

}
