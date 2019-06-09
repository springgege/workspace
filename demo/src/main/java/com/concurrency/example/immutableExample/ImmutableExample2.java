package com.concurrency.example.immutableExample;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class ImmutableExample2 {

    private final static ImmutableList<Integer> list = ImmutableList.of(1,2,3,4,5);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1,2,1,2);

    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder()
            .put(1,2).put(1,2).build();

    public static void main(String[] args) {
        //不可修改对象，调用会抛异常
        list.add(7);
    }
}
