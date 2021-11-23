package com.buba.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProProvide03Consul {
    public static void main(String[] args) {
        SpringApplication.run(ProProvide03Consul.class,args);
    }
}