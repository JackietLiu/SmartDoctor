/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.advice.entity;

import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.modules.advice.entity.SmAdvise;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 病因和治疗建议Entity
 * @author louis
 * @version 2016-11-16
 */
public class SmIllreasonAdvice extends DataEntity<SmIllreasonAdvice> {
	
	private static final long serialVersionUID = 1L;
	private SmIllreason illrea;		// 病因
	private SmAdvise advise;		// 治疗建议
	private String qyStatus;		// 启用状态
	

	public SmIllreasonAdvice() {
		super();
	}

	public SmIllreasonAdvice(String id){
		super(id);
	}

	public SmIllreason getIllrea() {
		return illrea;
	}

	public void setIllrea(SmIllreason illrea) {
		this.illrea = illrea;
	}
	
	public SmAdvise getAdvise() {
		return advise;
	}

	public void setAdvise(SmAdvise advise) {
		this.advise = advise;
	}
	
	@Length(min=0, max=1, message="启用状态长度必须介于 0 和 1 之间")
	public String getQyStatus() {
		return qyStatus;
	}

	public void setQyStatus(String qyStatus) {
		this.qyStatus = qyStatus;
	}
	
}