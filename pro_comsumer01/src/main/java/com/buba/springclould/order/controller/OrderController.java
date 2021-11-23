package com.buba.springclould.order.controller;


import com.yxkj.pojo.CommonResult;
import com.yxkj.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
@Slf4j
public class OrderController {
    //调用支付订单服务端的ip+端口号
    //public static final  String PAYMENT_URL = "http://localhost:8001";
    public static final  String PAYMENT_URL = "http://qlpro-provide-eureka" ;
    //public static final  String PAYMENT_URL = "http://payment8002" ;
    @Autowired
    private RestTemplate restTemplate;

    //创建支付订单的接口
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);
    }
    //获取id获取支付订单
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        Logger logger = Logger.getLogger("OrderController");
        logger.info("进入消费者Pro—comsumer01");
        System.out.println("-------url:"
                +restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class));
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        //return  null;
    }
}