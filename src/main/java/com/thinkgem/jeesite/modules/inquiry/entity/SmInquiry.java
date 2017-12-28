/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 病人询诊Entity
 * @author louis
 * @version 2016-11-21
 */
public class SmInquiry extends DataEntity<SmInquiry> {
	
	private static final long serialVersionUID = 1L;
	private String cardno;		// 卡号
	private SmSymptom sym;		// 病症
	private String symName;		// 病症名称
	private String illStatus;		// 询证状态
	private Date beginCreateDate;		// 开始 create_date
	private Date endCreateDate;		// 结束 create_date
	private int state ;
	private SmIllreason illrea;		// 病因ID 
	private int gqState ;
	
	public int getGqState() {
		return gqState;
	}

	public void setGqState(int gqState) {
		this.gqState = gqState;
	}

	public SmInquiry() {
		super();
	}

	public SmInquiry(String id){
		super(id);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public SmIllreason getIllrea() {
		return illrea;
	}

	public void setIllrea(SmIllreason illrea) {
		this.illrea = illrea;
	}

	@Length(min=0, max=255, message="卡号长度必须介于 0 和 255 之间")
	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	
	public SmSymptom getSym() {
		return sym;
	}

	public void setSym(SmSymptom sym) {
		this.sym = sym;
	}
	
	@Length(min=0, max=255, message="病症名称长度必须介于 0 和 255 之间")
	public String getSymName() {
		return symName;
	}

	public void setSymName(String symName) {
		this.symName = symName;
	}
	
	@Length(min=0, max=1, message="询证状态长度必须介于 0 和 1 之间")
	public String getIllStatus() {
		return illStatus;
	}

	public void setIllStatus(String illStatus) {
		this.illStatus = illStatus;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}