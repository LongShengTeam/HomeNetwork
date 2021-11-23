package com.yxkj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommonResult<T> {
    private Integer code;//返回状态码
    private String  message;//返回是否调用成功
    private Payment  Payment;//返回是否调用成功
    private  T data; //返回的数据


    public CommonResult(Integer code, String message,Payment  Payment) {

       // this(code,message,Payment,1);
        this.message = message;
        this.code = code;
        this.Payment = Payment;
        System.out.println("构造方法1");

    }

    public CommonResult(Integer code,String message ,Payment  Payment,Integer d){
        this.message = message;
        this.code = code;
        this.Payment = Payment;
        System.out.println("构造方法2");
    }

    public CommonResult(Integer code,String message ,Payment  Payment,Integer d,Integer e){
        System.out.println("构造方法3");
    }

/*
    public static void main(String[] args) {
        CommonResult commonResult1 = new CommonResult(1,"");
        CommonResult commonResult = new CommonResult(1,"","");

        CommonResult commonResult3 = new CommonResult(1,"","",1);
}
*/

}