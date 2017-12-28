/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.match.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 病因方案Entity
 * @author louis
 * @version 2016-11-17
 */
public class SmIllreasonMatch extends DataEntity<SmIllreasonMatch> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 方案名称
	private SmIllreason illrea;		// 病因
	private String qyStatus;		// 启用状态
	
	public SmIllreasonMatch() {
		super();
	}

	public SmIllreasonMatch(String id){
		super(id);
	}

	@Length(min=0, max=255, message="方案名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public SmIllreason getIllrea() {
		return illrea;
	}

	public void setIllrea(SmIllreason illrea) {
		this.illrea = illrea;
	}
	
	@Length(min=0, max=1, message="启用状态长度必须介于 0 和 1 之间")
	public String getQyStatus() {
		return qyStatus;
	}

	public void setQyStatus(String qyStatus) {
		this.qyStatus = qyStatus;
	}
	
}