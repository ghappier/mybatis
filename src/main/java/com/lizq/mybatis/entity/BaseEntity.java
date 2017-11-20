package com.lizq.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 数据库自增ID **/
	private Integer id;
	
	/** 业务主键 **/
	private String uuid;

	/** 创建时间 **/
	private Date createTime;

	/** 创建人ID **/
	private String createUser;

	/** 更新时间 **/
	private Date updateTime;

	/** 更新人ID **/
	private String updateUser;
	
	/** 删除标识(0:未删除 1:已删除) **/
    private Byte isDelete;
}
