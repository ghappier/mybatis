package com.lizq.mybatis.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// 姓名
    private String name;

    // 性别，0未知，1男，2女  默认：0
    private Byte gender;

    // 生日
    private Date birthday;
}