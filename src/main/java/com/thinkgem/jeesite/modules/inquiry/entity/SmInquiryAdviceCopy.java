/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.entity;

import com.thinkgem.jeesite.modules.advice.entity.SmAdvise;
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 修改后的系统处方Entity
 * @author JackietLiu
 * @version 2017-07-03
 */
public class SmInquiryAdviceCopy extends DataEntity<SmInquiryAdviceCopy> {
	
	private static final long serialVersionUID = 1L;
	private SmInquiry inqu;		// 询诊ID
	private String reStatus;		// 结果
	private SmAdvise adv;		// 建议ID
	private String name;		// 治疗建议名称
	private String description;		// 治疗建议描述
	private SmIllreason illrea;
	private String illreaname ;
	private String illreadesc ;
	private int state;
	private SmIllreason answerIllrea;
	private SmInquiryAdvice old;		// 系统建议处方id
	
	public SmInquiryAdviceCopy() {
		super();
	}

	public SmInquiryAdviceCopy(String id){
		super(id);
	}


	
	public SmInquiry getInqu() {
		return inqu;
	}

	public void setInqu(SmInquiry inqu) {
		this.inqu = inqu;
	}

	public String getReStatus() {
		return reStatus;
	}

	public void setReStatus(String reStatus) {
		this.reStatus = reStatus;
	}

	public SmAdvise getAdv() {
		return adv;
	}

	public void setAdv(SmAdvise adv) {
		this.adv = adv;
	}

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

	public SmIllreason getIllrea() {
		return illrea;
	}

	public void setIllrea(SmIllreason illrea) {
		this.illrea = illrea;
	}

	public String getIllreaname() {
		return illreaname;
	}

	public void setIllreaname(String illreaname) {
		this.illreaname = illreaname;
	}

	public String getIllreadesc() {
		return illreadesc;
	}

	public void setIllreadesc(String illreadesc) {
		this.illreadesc = illreadesc;
	}

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

	public SmInquiryAdvice getOld() {
		return old;
	}

	public void setOld(SmInquiryAdvice old) {
		this.old = old;
	}

	
	
}