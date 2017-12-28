/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 健康处方Entity
 * @author JackietLiu
 * @version 2017-08-02
 */
public class SmHealthy extends DataEntity<SmHealthy> {
	
	private static final long serialVersionUID = 1L;
	private String food;		// 饮食
	private String suggest;		// 建议
	
	public SmHealthy() {
		super();
	}

	public SmHealthy(String id){
		super(id);
	}

	@Length(min=0, max=1000, message="饮食长度必须介于 0 和 1000 之间")
	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}
	
	@Length(min=0, max=1000, message="建议长度必须介于 0 和 1000 之间")
	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	
}