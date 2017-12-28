/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.medic.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 药品和类别关系Entity
 * @author JackietLiu
 * @version 2017-07-20
 */
public class SmTmRelation extends DataEntity<SmTmRelation> {
	
	private static final long serialVersionUID = 1L;
	private SmMedicineType type;		// 类别id
	private SmMedicine med;		// 药品id
	
	private String typeName;
	private String medName;
	
	public SmTmRelation() {
		super();
	}

	public SmTmRelation(String id){
		super(id);
	}

	public SmMedicineType getType() {
		return type;
	}

	public void setType(SmMedicineType type) {
		this.type = type;
	}
	
	public SmMedicine getMed() {
		return med;
	}

	public void setMed(SmMedicine med) {
		this.med = med;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}
	
}