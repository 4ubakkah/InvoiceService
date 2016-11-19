package com.invoicing;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BootStrapper {
    public static void main(String[] args) {
        new SpringApplicationBuilder(BootStrapper.class).headless(false).run(args);
        //SpringApplication.run(BootStrapper.class, args);
    }
}