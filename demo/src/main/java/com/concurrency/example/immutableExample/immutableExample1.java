package com.concurrency.example.immutableExample;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;
public class immutableExample1 {
    private final static Integer a = 1; //基础类型不可修改
    private final static String b = "2"; //String类型不可修改
    //Maps类简化写法
    private final static Map<Integer, Integer> map = Maps.newHashMap();// 不可重新指向其他对象

    private static Map<Integer, Integer> unmodifiableMap = Maps.newHashMap();
    static {
        map.put(1,2);
        unmodifiableMap = Collections.unmodifiableMap(unmodifiableMap);
    }

    public static void main(String[] args) {
        //Collections.unmodifiableMap方法处理过之后不可以再修改，会抛出异常
        //此方法定义了一个新的Map，将类中更新的方法定义为抛出异常
        unmodifiableMap.put(1,1);
    }

    private void test(final int a){
        //a = 1;  修饰入参类型也不许修改值
    }
}
