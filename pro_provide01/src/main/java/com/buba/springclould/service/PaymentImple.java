package com.buba.springclould.service;

import com.buba.springclould.dao.PaymentDao;
import com.buba.springclould.service.PaymentService;
import com.yxkj.pojo.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//com.buba.springcloud.pojo

@Service
public class PaymentImple implements PaymentService {

    @Autowired
    PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment queryById(long id) {
        return paymentDao.queryById(id);
    }

}
