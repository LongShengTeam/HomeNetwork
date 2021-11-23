package com.buba.springclould.qitest;

import org.omg.PortableServer.ForwardRequestHelper;

import java.util.ArrayList;
import java.util.List;

public class qlCommonTest {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("b");
        list.add("b");
        list.add("e");
        list.add("c");
        List<Integer> listRemove=new ArrayList<>();
        while (true){
            int i = 1;
            if (list.indexOf("b") != -1){
                list.remove("b");
            }
            i++;
            if(i==list.size()) break;
        }
        for(int j=0;j<list.size();j++){
            System.out.println("剩余的元素："+list.get(j));
        }
    }
}
