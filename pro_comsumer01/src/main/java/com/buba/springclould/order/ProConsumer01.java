package com.buba.springclould.order;

import com.yxkj.ql.myribbonrule.MyselfRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//name为生产者服务的服务名称  configuration为配置类的类名  qlpro-provide01-eureka
@RibbonClient(name = "qlpro-provider-eureka",configuration = MyselfRibbonRule.class)
public class ProConsumer01 {

    public static void main(String[] args) {
        SpringApplication.run(ProConsumer01.class,args);
    }
}