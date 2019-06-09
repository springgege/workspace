package com.spring.boot.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
//@Data 等同与上面四个
public class User {
    private Long id;

    private String name;

    private Short sex;

    private Integer courseid;
}