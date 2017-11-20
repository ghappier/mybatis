package com.lizq.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lizq.mybatis.entity.User;

public interface UserMapper extends BaseMapper<User> {

	PageList<User> selectWithPage(@Param("condition") User condition, PageBounds pageBounds);
}