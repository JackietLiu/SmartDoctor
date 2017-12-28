/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.modules.basic.dao.SmIllreasonDao;

/**
 * 病因管理Service
 * @author louis
 * @version 2016-11-16
 */
@Service
@Transactional(readOnly = true)
public class SmIllreasonService extends CrudService<SmIllreasonDao, SmIllreason> {

	public SmIllreason get(String id) {
		return super.get(id);
	}
	
	public List<SmIllreason> findList(SmIllreason smIllreason) {
		return super.findList(smIllreason);
	}
	
	public Page<SmIllreason> findPage(Page<SmIllreason> page, SmIllreason smIllreason) {
		return super.findPage(page, smIllreason);
	}
	
	@Transactional(readOnly = false)
	public void save(SmIllreason smIllreason) {
		super.save(smIllreason);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmIllreason smIllreason) {
		super.delete(smIllreason);
	}
	
}