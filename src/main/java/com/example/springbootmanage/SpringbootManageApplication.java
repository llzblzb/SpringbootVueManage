package com.example.springbootmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;

@RestController
@SpringBootApplication
@EnableOpenApi
public class SpringbootManageApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpringbootManageApplication.class, args);
    }




}
