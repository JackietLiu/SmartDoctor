/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import com.thinkgem.jeesite.modules.basic.dao.SmOptionDao;

/**
 * 问题选项Service
 * @author louis
 * @version 2016-11-14
 */
@Service
@Transactional(readOnly = true)
public class SmOptionService extends CrudService<SmOptionDao, SmOption> {

	public SmOption get(String id) {
		return super.get(id);
	}
	
	public List<SmOption> findList(SmOption smOption) {
		return super.findList(smOption);
	}
	
	public Page<SmOption> findPage(Page<SmOption> page, SmOption smOption) {
		return super.findPage(page, smOption);
	}
	
	@Transactional(readOnly = false)
	public void save(SmOption smOption) {
		super.save(smOption);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmOption smOption) {
		super.delete(smOption);
	}
	
}