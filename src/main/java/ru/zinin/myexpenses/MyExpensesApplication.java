package ru.zinin.myexpenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyExpensesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyExpensesApplication.class, args);
    }

}
