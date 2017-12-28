/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smuser.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 模拟患者表Entity
 * @author Jackiet
 * @version 2017-04-13
 */
public class SmUser extends DataEntity<SmUser> {
	
	private static final long serialVersionUID = 1L;
	private String cardNo;		// 卡号
	private String sex;		// 性别
	private String name;		// 姓名
	private Date birth;		// 出生日期
	
	private String age;
	
	public SmUser() {
		super();
	}

	public SmUser(String id){
		super(id);
	}

	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Length(min=0, max=50, message="卡号长度必须介于 0 和 50 之间")
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=25, message="姓名长度必须介于 0 和 25 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
}