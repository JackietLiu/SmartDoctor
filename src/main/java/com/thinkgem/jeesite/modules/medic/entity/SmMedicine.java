/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.medic.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品表Entity
 * @author JackietLiu
 * @version 2017-07-20
 */
public class SmMedicine extends DataEntity<SmMedicine> {
	
	private static final long serialVersionUID = 1L;
	private String medName;		// 药品名称
	private String dose;		// 剂量
	private String medication;		// 服药
	private String doseUnit;		// 剂量单位
	private String medicationUnit;		// 服药单位（次/d）
	private String effects;		// 副作用
	private String workTime;		// 起作用时间
	private String durationTime;		// 持续时间
	
	public SmMedicine() {
		super();
	}

	public SmMedicine(String id){
		super(id);
	}

	@Length(min=0, max=255, message="药品名称长度必须介于 0 和 255 之间")
	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}
	
	@Length(min=0, max=255, message="剂量长度必须介于 0 和 255 之间")
	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}
	
	@Length(min=0, max=20, message="服药长度必须介于 0 和 20 之间")
	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}
	
	@Length(min=0, max=20, message="剂量单位长度必须介于 0 和 20 之间")
	public String getDoseUnit() {
		return doseUnit;
	}

	public void setDoseUnit(String doseUnit) {
		this.doseUnit = doseUnit;
	}
	
	@Length(min=0, max=20, message="服药单位（次/d）长度必须介于 0 和 20 之间")
	public String getMedicationUnit() {
		return medicationUnit;
	}

	public void setMedicationUnit(String medicationUnit) {
		this.medicationUnit = medicationUnit;
	}
	
	@Length(min=0, max=255, message="副作用长度必须介于 0 和 255 之间")
	public String getEffects() {
		return effects;
	}

	public void setEffects(String effects) {
		this.effects = effects;
	}
	
	@Length(min=0, max=10, message="起作用时间长度必须介于 0 和 10 之间")
	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	
	@Length(min=0, max=10, message="持续时间长度必须介于 0 和 10 之间")
	public String getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}
	
}