package uk.co.castlewater.myaccount.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Anatol Sialitski
 */
@SpringBootApplication
@ComponentScan(value = "uk.co.castlewater.myaccount")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
