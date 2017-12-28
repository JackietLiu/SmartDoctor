/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 问题选项Entity
 * @author louis
 * @version 2016-11-14
 */
public class SmOption extends DataEntity<SmOption> {
	
	private static final long serialVersionUID = 1L;
	private SmQuestion question;		// 问题ID
	private String name;		// 选项标题
	private String sort;		// 排序
	private String qyStatus;		// 启用状态
	private String isLast ;//是否最后个选项，为特殊服务
	
	
	
	public String getIsLast() {
		return isLast;
	}

	public void setIsLast(String isLast) {
		this.isLast = isLast;
	}

	public SmOption() {
		super();
	}

	public SmOption(String id){
		super(id);
	}

	public SmQuestion getQuestion() {
		return question;
	}

	public void setQuestion(SmQuestion question) {
		this.question = question;
	}
	
	@Length(min=0, max=255, message="选项标题长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="排序长度必须介于 0 和 2 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=1, message="启用状态长度必须介于 0 和 1 之间")
	public String getQyStatus() {
		return qyStatus;
	}

	public void setQyStatus(String qyStatus) {
		this.qyStatus = qyStatus;
	}
	
}