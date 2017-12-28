/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.config.entity;


import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 病症和问题关联表Entity
 * @author louis
 * @version 2016-11-14
 */
public class SmSymQues extends DataEntity<SmSymQues> {
	
	private static final long serialVersionUID = 1L;
	private SmSymptom sym;		// 症状ID
	private SmQuestion question;		// 问题ID
	private SmOption option;		// 选项
	private String sort;		// 排序
	private String qyStatus;		// 启用状态
	private String isFirst;		// 第一个问题
	private String isLast;		// 最后一题
	private SmIllreason illreason ;//病因
	private int state ;//是什么类型的问题。1病症2病因
	private SmQuestion lastQuestion;		// 问题ID
	private int dept;//深度
	private String deptPid ;//深度唯一指标
	private String deptFid ;//父深度指标
	private Integer percent; //问题在病因中所占比重
	private String quesType;
	private SmAgeRange ageRange;
	
	
	
	
	
	public SmAgeRange getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(SmAgeRange ageRange) {
		this.ageRange = ageRange;
	}

	public String getQuesType() {
		return quesType;
	}

	public void setQuesType(String quesType) {
		this.quesType = quesType;
	}

	public String getDeptFid() {
		return deptFid;
	}

	public void setDeptFid(String deptFid) {
		this.deptFid = deptFid;
	}

	public int getDept() {
		return dept;
	}

	public void setDept(int dept) {
		this.dept = dept;
	}

	public String getDeptPid() {
		return deptPid;
	}

	public void setDeptPid(String deptPid) {
		this.deptPid = deptPid;
	}

	public SmQuestion getLastQuestion() {
		return lastQuestion;
	}

	public void setLastQuestion(SmQuestion lastQuestion) {
		this.lastQuestion = lastQuestion;
	}

	public SmIllreason getIllreason() {
		return illreason;
	}

	public void setIllreason(SmIllreason illreason) {
		this.illreason = illreason;
	}


	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIsLast() {
		return isLast;
	}

	public void setIsLast(String isLast) {
		this.isLast = isLast;
	}

	public SmOption getOption() {
		return option;
	}

	public void setOption(SmOption option) {
		this.option = option;
	}

	public SmSymQues() {
		super();
	}

	public SmSymQues(String id){
		super(id);
	}

	public SmSymptom getSym() {
		return sym;
	}

	public void setSym(SmSymptom sym) {
		this.sym = sym;
	}
	
	public SmQuestion getQuestion() {
		return question;
	}

	public void setQuestion(SmQuestion question) {
		this.question = question;
	}
	
	@Length(min=0, max=2, message="排序长度必须介于 0 和 2 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=1, message="启用状态长度必须介于 0 和 1 之间")
	public String getQyStatus() {
		return qyStatus;
	}

	public void setQyStatus(String qyStatus) {
		this.qyStatus = qyStatus;
	}
	
	@Length(min=0, max=1, message="第一个问题长度必须介于 0 和 1 之间")
	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}
	
}