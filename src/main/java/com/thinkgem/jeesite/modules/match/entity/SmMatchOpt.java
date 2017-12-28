/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.match.entity;

import com.thinkgem.jeesite.modules.match.entity.SmIllreasonMatch;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 方案配置选项Entity
 * @author louis
 * @version 2016-11-17
 */
public class SmMatchOpt extends DataEntity<SmMatchOpt> {
	
	private static final long serialVersionUID = 1L;
	private SmIllreasonMatch matc;		// 方案
	private SmSymptom sym;		// 症状
	private SmQuestion ques;		// 问题
	private SmOption opt;		// 选项
	private String qyStatus;		// 启用1正常2停用
	private String isLast;//是否最后一条
	
	public SmMatchOpt() {
		super();
	}

	public SmMatchOpt(String id){
		super(id);
	}
	
	

	public String getIsLast() {
		return isLast;
	}

	public void setIsLast(String isLast) {
		this.isLast = isLast;
	}

	public SmIllreasonMatch getMatc() {
		return matc;
	}

	public void setMatc(SmIllreasonMatch matc) {
		this.matc = matc;
	}
	
	public SmSymptom getSym() {
		return sym;
	}

	public void setSym(SmSymptom sym) {
		this.sym = sym;
	}
	
	public SmQuestion getQues() {
		return ques;
	}

	public void setQues(SmQuestion ques) {
		this.ques = ques;
	}
	
	public SmOption getOpt() {
		return opt;
	}

	public void setOpt(SmOption opt) {
		this.opt = opt;
	}
	
	@Length(min=0, max=1, message="启用1正常2停用长度必须介于 0 和 1 之间")
	public String getQyStatus() {
		return qyStatus;
	}

	public void setQyStatus(String qyStatus) {
		this.qyStatus = qyStatus;
	}
	
}