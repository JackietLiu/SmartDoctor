/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.entity;

import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiry;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 询诊病因Entity
 * @author louis
 * @version 2016-11-21
 */
public class SmInquiryIllreason extends DataEntity<SmInquiryIllreason> {
	
	private static final long serialVersionUID = 1L;
	private SmInquiry inqu;		// 用户询诊表ID
	private String reStatus;		// 结果
	private SmIllreason illrea;		// 病因ID
	private String illreaName;		// 病因标题
	private String illreaDesc;		// 病因描述
	private int state;
	private SmIllreason answerIllrea;
	
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public SmIllreason getAnswerIllrea() {
		return answerIllrea;
	}

	public void setAnswerIllrea(SmIllreason answerIllrea) {
		this.answerIllrea = answerIllrea;
	}

	public SmInquiryIllreason() {
		super();
	}

	public SmInquiryIllreason(String id){
		super(id);
	}

	public SmInquiry getInqu() {
		return inqu;
	}

	public void setInqu(SmInquiry inqu) {
		this.inqu = inqu;
	}
	
	@Length(min=0, max=255, message="结果长度必须介于 0 和 255 之间")
	public String getReStatus() {
		return reStatus;
	}

	public void setReStatus(String reStatus) {
		this.reStatus = reStatus;
	}
	
	public SmIllreason getIllrea() {
		return illrea;
	}

	public void setIllrea(SmIllreason illrea) {
		this.illrea = illrea;
	}
	
	@Length(min=0, max=255, message="病因标题长度必须介于 0 和 255 之间")
	public String getIllreaName() {
		return illreaName;
	}

	public void setIllreaName(String illreaName) {
		this.illreaName = illreaName;
	}
	
	public String getIllreaDesc() {
		return illreaDesc;
	}

	public void setIllreaDesc(String illreaDesc) {
		this.illreaDesc = illreaDesc;
	}
	
}