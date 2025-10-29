package com.edulend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EduLendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduLendApplication.class, args);
        System.out.println("🚀 Servidor EduLend corriendo en http://localhost:8080");
    }
}