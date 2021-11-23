package com.buba.springcloud.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class proConsumer03Consul {
    public static void main(String[] args) {
        SpringApplication.run(proConsumer03Consul.class,args);
    }
}