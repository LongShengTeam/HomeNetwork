package com.buba.springclould.dao;

import com.yxkj.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaymentDao  {

    int create(Payment payment);

    Payment queryById(@Param("id")long id);

}
