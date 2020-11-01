package com.yeyangshu.servicepassengeruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yeyangshu.servicepassengeruser.mapper")
public class ServicePassengerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePassengerUserApplication.class, args);
    }

}
