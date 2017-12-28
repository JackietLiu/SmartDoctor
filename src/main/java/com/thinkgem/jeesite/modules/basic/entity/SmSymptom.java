/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 病症管理Entity
 * @author louis
 * @version 2016-11-11
 */
public class SmSymptom extends DataEntity<SmSymptom> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String description;		// description
	private String qyStatus;		// 启用状态
	private String sort ;
	
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public SmSymptom() {
		super();
	}

	public SmSymptom(String id){
		super(id);
	}

	@Length(min=0, max=200, message="name长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQyStatus() {
		return qyStatus;
	}

	public void setQyStatus(String qyStatus) {
		this.qyStatus = qyStatus;
	}
	
	
}