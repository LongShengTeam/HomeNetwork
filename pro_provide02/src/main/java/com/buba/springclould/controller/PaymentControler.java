package com.buba.springclould.controller;

import com.buba.springclould.service.PaymentService;
import com.yxkj.pojo.CommonResult;
import com.yxkj.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String port;
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment dept){
        int i = paymentService.create(dept);
        Logger logger = Logger.getLogger("PaymentControler");
        logger.info("***************插入成功*******"+i);
        if(i>0){
            return new CommonResult(200,"pro_provide02 插入数据库成功,端口為："+port,null);
        }else{
            return new CommonResult(444,"pro_provide02 插入数据库失败,端口為："+port,null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult queryById(@PathVariable("id") Long id){
        Payment payment = paymentService.queryById(id);
        Logger logger = Logger.getLogger("PaymentControler");
        logger.info("***************进入pro_provide02*********");
        logger.info("***************查询成功*********"+payment.toString());
        CommonResult commonResult = null;
        if(payment!=null){
            commonResult = new CommonResult(200, "pro_provide02 查询成功,端口為："+port, payment);

        }else{
            commonResult = new CommonResult(444,"pro_provide02 查询失败,端口為："+port,null);
        }
        System.out.println("----------------commonResult:"+commonResult);
        return commonResult;
    }

    //获取服务信息    GetMapping("/payment/discovery")
    @GetMapping("/payment/discovery")
    public  Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String s : services){
            log.info("********注册到eureka中的服务中有:"+s);
        }
        List <ServiceInstance> instances = discoveryClient.getInstances("qlpro-comsumer01-eruka");
        for (ServiceInstance s: instances) {
            log.info("当前服务的实例有"+s.getServiceId()+"\t"+s.getHost()+"\t"+s.getPort()+"\t"+s.getUri());
        }
        return this.discoveryClient;
    }
}
