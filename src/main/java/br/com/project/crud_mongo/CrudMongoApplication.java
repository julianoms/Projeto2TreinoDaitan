package br.com.project.crud_mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrudMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudMongoApplication.class, args);
    }
}
