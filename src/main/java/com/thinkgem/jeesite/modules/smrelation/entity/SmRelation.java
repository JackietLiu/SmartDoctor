/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smrelation.entity;

import com.thinkgem.jeesite.modules.config.entity.SmSymQues;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 配置病因Entity
 * @author JackietLiu
 * @version 2017-04-20
 */
public class SmRelation extends DataEntity<SmRelation> {
	
	private static final long serialVersionUID = 1L;
	private SmSymQues smSymQues;		// sm_sym_ques的ID
	private SmQuestion question;		// 问题ID
	private SmIllreason illreaon;		// 病因ID
	private SmSymptom symptom;		// 病症ID
	private Integer percent;		// 每个问题在该病因中所占比重
	public SmOption option;
	
	public SmRelation() {
		super();
	}

	public SmRelation(String id){
		super(id);
	}

	public SmSymQues getSmSymQues() {
		return smSymQues;
	}

	public void setSmSymQues(SmSymQues smSymQues) {
		this.smSymQues = smSymQues;
	}
	
	public SmQuestion getQuestion() {
		return question;
	}

	public void setQuestion(SmQuestion question) {
		this.question = question;
	}
	
	public SmIllreason getIllreaon() {
		return illreaon;
	}

	public void setIllreaon(SmIllreason illreaon) {
		this.illreaon = illreaon;
	}
	
	public SmSymptom getSymptom() {
		return symptom;
	}

	public void setSymptom(SmSymptom symptom) {
		this.symptom = symptom;
	}
	
	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public SmOption getOption() {
		return option;
	}

	public void setOption(SmOption option) {
		this.option = option;
	}
	
}