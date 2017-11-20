package com.lizq.mybatis.mapper;

import java.io.Serializable;
import java.util.List;

import com.lizq.mybatis.entity.BaseEntity;

public interface BaseMapper<T extends BaseEntity> {

    int insertSelective(T t);

    T selectByPrimaryKey(Serializable id);

    int deleteByPrimaryKey(Serializable id);

    int updateByPrimaryKeySelective(T t);

    List<T> selectSelective(T t);
}
