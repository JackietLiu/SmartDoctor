/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smrelation.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;
import com.thinkgem.jeesite.modules.config.entity.SmSymQues;

/**
 * 配置病因Entity
 * @author louis
 * @version 2017-03-24
 */
public class SmSymQuesIllreason extends DataEntity<SmSymQuesIllreason> {
	
	private static final long serialVersionUID = 1L;
	private SmSymQues smSymQues;		// sm_sym_ques的ID
	private SmQuestion question;		// 问题ID
	private SmIllreason illreaon;		// 病因ID
	private SmSymptom symptom;		// 病症ID
    private SmOption option;
	
	public SmSymQuesIllreason() {
		super();
	}

	public SmSymQuesIllreason(String id){
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

	public SmOption getOption() {
		return option;
	}

	public void setOption(SmOption option) {
		this.option = option;
	}


	
}