package org.geekhub.oleg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.support.JdbcUtils;

@SpringBootApplication
public class EventBookingApp {
    public static void main(String[] args) {
        SpringApplication.run(EventBookingApp.class, args);
    }
}
