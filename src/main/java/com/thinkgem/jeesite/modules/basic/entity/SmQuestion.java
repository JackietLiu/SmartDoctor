/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.config.entity.SmAgeRange;

/**
 * 问题管理Entity
 * @author JackietLiu
 * @version 2017-04-20
 */
public class SmQuestion extends DataEntity<SmQuestion> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 问题标题
	private String description;		// 问题描述
	private String qyStatus;		// 启用状态
	private String quesType;		// 问题使用类型
	private String optName ;//选项名称
	private String optSort ;//选项排序
	private SmAgeRange ageRange; //年龄范围
	
	
	
	public SmAgeRange getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(SmAgeRange ageRange) {
		this.ageRange = ageRange;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getOptSort() {
		return optSort;
	}

	public void setOptSort(String optSort) {
		this.optSort = optSort;
	}


	public SmQuestion() {
		super();
	}

	public SmQuestion(String id){
		super(id);
	}

	@Length(min=0, max=200, message="问题标题长度必须介于 0 和 200 之间")
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
	
	@Length(min=0, max=1, message="启用状态长度必须介于 0 和 1 之间")
	public String getQyStatus() {
		return qyStatus;
	}

	public void setQyStatus(String qyStatus) {
		this.qyStatus = qyStatus;
	}
	
	@Length(min=0, max=1, message="问题使用类型长度必须介于 0 和 1 之间")
	public String getQuesType() {
		return quesType;
	}

	public void setQuesType(String quesType) {
		this.quesType = quesType;
	}
	
}