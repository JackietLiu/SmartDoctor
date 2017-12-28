/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.config.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 年龄段Entity
 * @author JackietLiu
 * @version 2017-05-15
 */
public class SmAgeRange extends DataEntity<SmAgeRange> {
	
	private static final long serialVersionUID = 1L;
	private int maxAge;		// 最大值
	private int minAge;		// 最小值
	
	public SmAgeRange() {
		super();
	}

	public SmAgeRange(String id){
		super(id);
	}

	@Length(min=0, max=10, message="最大值长度必须介于 0 和 10 之间")
	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	
	@Length(min=0, max=10, message="最小值长度必须介于 0 和 10 之间")
	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	
}