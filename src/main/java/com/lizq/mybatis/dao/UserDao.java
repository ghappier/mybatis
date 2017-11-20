package com.lizq.mybatis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lizq.mybatis.entity.User;
import com.lizq.mybatis.mapper.UserMapper;

@Repository
public class UserDao extends AbstractBaseDao<UserMapper, User> {

	@Autowired
	private UserMapper userMapper;
	
	public PageList<User> selectWithPage(User condition, PageBounds pageBounds) {
		return userMapper.selectWithPage(condition, pageBounds);
	}
}