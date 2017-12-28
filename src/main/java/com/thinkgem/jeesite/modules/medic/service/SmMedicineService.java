/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.medic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.medic.entity.SmMedicine;
import com.thinkgem.jeesite.modules.medic.dao.SmMedicineDao;

/**
 * 药品表Service
 * @author JackietLiu
 * @version 2017-07-20
 */
@Service
@Transactional(readOnly = true)
public class SmMedicineService extends CrudService<SmMedicineDao, SmMedicine> {

	public SmMedicine get(String id) {
		return super.get(id);
	}
	
	public List<SmMedicine> findList(SmMedicine smMedicine) {
		return super.findList(smMedicine);
	}
	
	public Page<SmMedicine> findPage(Page<SmMedicine> page, SmMedicine smMedicine) {
		return super.findPage(page, smMedicine);
	}
	
	@Transactional(readOnly = false)
	public void save(SmMedicine smMedicine) {
		super.save(smMedicine);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmMedicine smMedicine) {
		super.delete(smMedicine);
	}
	
}