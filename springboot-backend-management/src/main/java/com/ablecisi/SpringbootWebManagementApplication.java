package com.ablecisi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Repository;

@ServletComponentScan
@SpringBootApplication
public class SpringbootWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebManagementApplication.class, args);
    }

}
