package org.records;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Server {
    public static void main(String[] args) {
        System.out.println("Hello humans!");
        SpringApplication.run(Server.class, args);
    }
}