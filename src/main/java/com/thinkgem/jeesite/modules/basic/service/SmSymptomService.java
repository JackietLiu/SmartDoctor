/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;
import com.thinkgem.jeesite.modules.basic.dao.SmSymptomDao;

/**
 * 病症管理Service
 * @author louis
 * @version 2016-11-11
 */
@Service
@Transactional(readOnly = true)
public class SmSymptomService extends CrudService<SmSymptomDao, SmSymptom> {

	public SmSymptom get(String id) {
		return super.get(id);
	}
	
	public List<SmSymptom> findList(SmSymptom smSymptom) {
		return super.findList(smSymptom);
	}
	
	public Page<SmSymptom> findPage(Page<SmSymptom> page, SmSymptom smSymptom) {
		return super.findPage(page, smSymptom);
	}
	
	@Transactional(readOnly = false)
	public void save(SmSymptom smSymptom) {
		super.save(smSymptom);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmSymptom smSymptom) {
		super.delete(smSymptom);
	}

	public List<SmSymptom> findAll(SmSymptom smSymptom) {
		return dao.findAllList(smSymptom);
	}
	
}