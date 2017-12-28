/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.advice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.advice.entity.SmIllreasonAdvice;
import com.thinkgem.jeesite.modules.advice.dao.SmIllreasonAdviceDao;

/**
 * 病因和治疗建议Service
 * @author louis
 * @version 2016-11-16
 */
@Service
@Transactional(readOnly = true)
public class SmIllreasonAdviceService extends CrudService<SmIllreasonAdviceDao, SmIllreasonAdvice> {

	public SmIllreasonAdvice get(String id) {
		return super.get(id);
	}
	
	public List<SmIllreasonAdvice> findList(SmIllreasonAdvice smIllreasonAdvice) {
		return super.findList(smIllreasonAdvice);
	}
	
	public Page<SmIllreasonAdvice> findPage(Page<SmIllreasonAdvice> page, SmIllreasonAdvice smIllreasonAdvice) {
		return super.findPage(page, smIllreasonAdvice);
	}
	
	@Transactional(readOnly = false)
	public void save(SmIllreasonAdvice smIllreasonAdvice) {
		super.save(smIllreasonAdvice);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmIllreasonAdvice smIllreasonAdvice) {
		super.delete(smIllreasonAdvice);
	}
	
}