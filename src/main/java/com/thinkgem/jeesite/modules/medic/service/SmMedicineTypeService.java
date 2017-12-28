/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.medic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.medic.entity.SmMedicineType;
import com.thinkgem.jeesite.modules.medic.dao.SmMedicineTypeDao;

/**
 * 药品类别Service
 * @author JackietLiu
 * @version 2017-07-23
 */
@Service
@Transactional(readOnly = true)
public class SmMedicineTypeService extends TreeService<SmMedicineTypeDao, SmMedicineType> {

	public SmMedicineType get(String id) {
		return super.get(id);
	}
	
	public List<SmMedicineType> findList(SmMedicineType smMedicineType) {
		if (StringUtils.isNotBlank(smMedicineType.getParentIds())){
			smMedicineType.setParentIds(","+smMedicineType.getParentIds()+",");
		}
		return super.findList(smMedicineType);
	}
	
	@Transactional(readOnly = false)
	public void save(SmMedicineType smMedicineType) {
		super.save(smMedicineType);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmMedicineType smMedicineType) {
		super.delete(smMedicineType);
	}
	
}