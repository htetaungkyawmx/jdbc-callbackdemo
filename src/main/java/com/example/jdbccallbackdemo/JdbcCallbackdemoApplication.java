package com.example.jdbccallbackdemo;

import com.example.jdbccallbackdemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class JdbcCallbackdemoApplication {

    private final EmployeeService employeeService;

    @Bean
    public ApplicationRunner runner(){
        return runner ->{
           employeeService.doAction();
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(JdbcCallbackdemoApplication.class, args);
    }

}
