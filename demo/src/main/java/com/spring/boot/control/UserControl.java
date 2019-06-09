package com.spring.boot.control;

import com.spring.boot.dto.User;
import com.spring.boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@RestController
public class UserControl {

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/user/query/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    @GetMapping("/user/getUsers")
    public List<User> getUsers(){
        //IntStream.rangeClosed(1,4).collect(Collectors.toList())
        List<Long> ids = new ArrayList<Long>();

        List<Long> ids2 = LongStream.rangeClosed(0,3).boxed().collect(Collectors.toList());
        ids2.stream().forEach(i->{
            System.out.println(i);
        });
        ids2.stream().forEach(System.out::print);
        ids.add(1l);
        ids.add(2l);
        return userMapper.searchList(ids);
    }
}
