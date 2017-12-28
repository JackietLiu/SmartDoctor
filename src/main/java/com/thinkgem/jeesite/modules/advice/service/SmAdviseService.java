/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.advice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.advice.entity.SmAdvise;
import com.thinkgem.jeesite.modules.advice.dao.SmAdviseDao;

/**
 * 治疗建议Service
 * @author louis
 * @version 2016-11-16
 */
@Service
@Transactional(readOnly = true)
public class SmAdviseService extends CrudService<SmAdviseDao, SmAdvise> {

	public SmAdvise get(String id) {
		return super.get(id);
	}
	
	public List<SmAdvise> findList(SmAdvise smAdvise) {
		return super.findList(smAdvise);
	}
	
	public Page<SmAdvise> findPage(Page<SmAdvise> page, SmAdvise smAdvise) {
		return super.findPage(page, smAdvise);
	}
	
	@Transactional(readOnly = false)
	public void save(SmAdvise smAdvise) {
		super.save(smAdvise);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmAdvise smAdvise) {
		super.delete(smAdvise);
	}

	public List<SmAdvise> findAllList(SmAdvise smAdvise) {
		return dao.findAllList(smAdvise);
	}
	
}