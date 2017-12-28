/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.advice.entity.SmAdvise;
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 询诊治疗建议Entity
 * @author louis
 * @version 2016-11-21
 */
public class SmInquiryAdvice extends DataEntity<SmInquiryAdvice> {
	
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

	public SmInquiryAdvice() {
		super();
	}

	public SmInquiryAdvice(String id){
		super(id);
	}

	
	public SmInquiry getInqu() {
		return inqu;
	}

	public void setInqu(SmInquiry inqu) {
		this.inqu = inqu;
	}

	@Length(min=0, max=1, message="结果长度必须介于 0 和 1 之间")
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
	
	@Length(min=0, max=255, message="治疗建议名称长度必须介于 0 和 255 之间")
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
	
}