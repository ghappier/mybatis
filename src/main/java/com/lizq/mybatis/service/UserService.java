package com.lizq.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lizq.mybatis.dao.UserDao;
import com.lizq.mybatis.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User get(String uuid) {
		User query = new User();
		query.setUuid(uuid);
		return userDao.selectOneSelective(query);
	}
	
	public PageList<User> page(User query, PageBounds pageBounds) {
		return userDao.selectWithPage(query, pageBounds);
	}
	
	public boolean add(User user) {
		return userDao.insertSelective(user);
	}
	
	public boolean delect(String uuid) {
		User u = get(uuid);
		if (u == null) {
			return false;
		}
		Integer id = u.getId();
		return userDao.deleteByPrimaryKey(id);
	}
	
	public boolean update(User user) {
		return userDao.updateByPrimaryKeySelective(user);
	}
}
