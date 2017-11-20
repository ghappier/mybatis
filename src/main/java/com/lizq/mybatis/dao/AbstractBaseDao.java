package com.lizq.mybatis.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.lizq.mybatis.entity.BaseEntity;
import com.lizq.mybatis.mapper.BaseMapper;

/**
 * @author lizq
 * @desc 默认的实现类
 */
public abstract class AbstractBaseDao<M extends BaseMapper<T>,T extends BaseEntity>  implements BaseDao<T>{

    @Autowired
    protected M baseMapper;

    @Override
    public boolean insertSelective(T t){
        if(StringUtils.isEmpty(t.getUuid())){
            t.setUuid(generatePrimaryKey());
        }
        return baseMapper.insertSelective(t) == 1 ? true : false;
    }

    @Override
    public boolean updateByPrimaryKeySelective(T t){
        return baseMapper.updateByPrimaryKeySelective(t) == 1 ? true : false;
    }

    @Override
    public boolean deleteByPrimaryKey(Serializable id) {
        return baseMapper.deleteByPrimaryKey(id) == 1 ? true : false;
    }


    @Override
    public T selectByPrimaryKey(Serializable id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public T selectOneSelective(T t) {
        List<T> list = selectManySelective(t);
        return (list == null || list.size()==0) ? null : list.get(0);
    }

    @Override
    public List<T> selectManySelective(T t) {
        return baseMapper.selectSelective(t);
    }
}
