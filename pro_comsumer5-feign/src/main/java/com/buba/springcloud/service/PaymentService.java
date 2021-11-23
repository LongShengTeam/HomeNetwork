package com.buba.springcloud.service;

import com.yxkj.pojo.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);

    Payment queryById(@Param("id")long id);
}
