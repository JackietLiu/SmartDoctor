/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 病因管理Entity
 * @author louis
 * @version 2016-11-16
 */
public class SmIllreason extends DataEntity<SmIllreason> {
	
	private static final long serialVersionUID = 1L;
	private SmSymptom sym;		// 症状
	private String description;		// 病因描述
	private String qyStatus;		// 启用
	private String name ;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SmIllreason() {
		super();
	}

	public SmIllreason(String id){
		super(id);
	}

	public SmSymptom getSym() {
		return sym;
	}

	public void setSym(SmSymptom sym) {
		this.sym = sym;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=1, message="启用长度必须介于 0 和 1 之间")
	public String getQyStatus() {
		return qyStatus;
	}

	public void setQyStatus(String qyStatus) {
		this.qyStatus = qyStatus;
	}
	
}