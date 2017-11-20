package com.lizq.mybatis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lizq.mybatis.entity.User;
import com.lizq.mybatis.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{uuid}")
	public User get(@PathVariable("uuid") String uuid) {
		return userService.get(uuid);
	}
	
	@GetMapping("/") 
	public PageList<User> page(User query, PageBounds pageBounds) {
		return userService.page(query, pageBounds);
	}
	
	@PostMapping("/") 
	public boolean add(User user) {
		return userService.add(user);
	}
	
	@PutMapping("/")
	public boolean update(User user) {
		return userService.update(user);
	}
	
	@DeleteMapping("/{uuid}")
	public boolean delete(@PathVariable("uuid") String uuid) {
		return userService.delect(uuid);
	}
}
