/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.entity;

import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiry;
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import com.thinkgem.jeesite.modules.config.entity.SmSymQues;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 病人答题明细Entity
 * @author louis
 * @version 2016-11-21
 */
public class SmInquiryQues extends DataEntity<SmInquiryQues> {
	
	private static final long serialVersionUID = 1L;
	private SmInquiry inqu;		// 询诊ID
	private SmQuestion question;		// 问题ID
	private String quesName;		// 问题名称
	private SmOption opt;		// 选项ID
	private String optName;		// 选项名称
	private SmSymptom sym;
	private String sort ;
	private int dept;
	private SmSymQues symQues;
	private int state ;
	private SmIllreason illrea;
	
	
	public SmIllreason getIllrea() {
		return illrea;
	}

	public void setIllrea(SmIllreason illrea) {
		this.illrea = illrea;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public SmSymQues getSymQues() {
		return symQues;
	}

	public void setSymQues(SmSymQues symQues) {
		this.symQues = symQues;
	}

	public int getDept() {
		return dept;
	}

	public void setDept(int dept) {
		this.dept = dept;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public SmSymptom getSym() {
		return sym;
	}

	public void setSym(SmSymptom sym) {
		this.sym = sym;
	}

	public SmInquiryQues() {
		super();
	}

	public SmInquiryQues(String id){
		super(id);
	}

	public SmInquiry getInqu() {
		return inqu;
	}

	public void setInqu(SmInquiry inqu) {
		this.inqu = inqu;
	}
	
	public SmQuestion getQuestion() {
		return question;
	}

	public void setQuestion(SmQuestion question) {
		this.question = question;
	}
	
	@Length(min=0, max=255, message="问题名称长度必须介于 0 和 255 之间")
	public String getQuesName() {
		return quesName;
	}

	public void setQuesName(String quesName) {
		this.quesName = quesName;
	}
	
	public SmOption getOpt() {
		return opt;
	}

	public void setOpt(SmOption opt) {
		this.opt = opt;
	}
	
	@Length(min=0, max=255, message="选项名称长度必须介于 0 和 255 之间")
	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}
	
}