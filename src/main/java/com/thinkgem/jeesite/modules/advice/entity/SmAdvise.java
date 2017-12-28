/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.advice.entity;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.basic.entity.SmHealthy;
import com.thinkgem.jeesite.modules.medic.entity.SmMedicine;

/**
 * 治疗建议Entity
 * @author louis
 * @version 2016-11-16
 */
public class SmAdvise extends DataEntity<SmAdvise> {
	
	private static final long serialVersionUID = 1L;
	private String name;		  // 治疗建议名称
	private String description;	  // 治疗建议描述
	private String qyStatus;	  // 启用
	private SmMedicine med;       // 药品id
	private SmHealthy heath;      // 健康处方id
	private String dtNextvisit;   // 下次来诊
	private String period;        // 疗程
	private String periodUnit;    // 疗程单位（盒，支等）
	
	public SmAdvise() {
		super();
	}

	public SmAdvise(String id){
		super(id);
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
	
	@Length(min=0, max=1, message="启用长度必须介于 0 和 1 之间")
	public String getQyStatus() {
		return qyStatus;
	}

	public void setQyStatus(String qyStatus) {
		this.qyStatus = qyStatus;
	}

	public SmMedicine getMed() {
		return med;
	}

	public void setMed(SmMedicine med) {
		this.med = med;
	}

	public SmHealthy getHeath() {
		return heath;
	}

	public void setHeath(SmHealthy heath) {
		this.heath = heath;
	}

	public String getDtNextvisit() {
		return dtNextvisit;
	}

	public void setDtNextvisit(String dtNextvisit) {
		this.dtNextvisit = dtNextvisit;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPeriodUnit() {
		return periodUnit;
	}

	public void setPeriodUnit(String periodUnit) {
		this.periodUnit = periodUnit;
	}
	
}