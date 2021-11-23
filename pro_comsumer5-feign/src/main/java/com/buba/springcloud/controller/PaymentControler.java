package com.buba.springcloud.controller;


import com.buba.springcloud.service.PaymentService;
import com.yxkj.pojo.CommonResult;
import com.yxkj.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/*
 * 提供restful服务  供其他服务调用
 *
 * */
@RestController
@Slf4j
public class PaymentControler {
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment= null;
                //paymentService.queryById(id);
        return new CommonResult(200,"right",payment,"1");
    }

}
