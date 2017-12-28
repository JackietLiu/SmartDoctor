/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.medic.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 药品类别Entity
 * @author JackietLiu
 * @version 2017-07-23
 */
public class SmMedicineType extends TreeEntity<SmMedicineType> {
	
	private static final long serialVersionUID = 1L;
	private String typeName;		// 类别名称

	
	public SmMedicineType() {
		super();
	}

	public SmMedicineType(String id){
		super(id);
	}

	@Length(min=0, max=255, message="类别名称长度必须介于 0 和 255 之间")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public SmMedicineType getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public void setParent(SmMedicineType parent) {
		// TODO Auto-generated method stub
		this.parent = parent;
	}
	

}