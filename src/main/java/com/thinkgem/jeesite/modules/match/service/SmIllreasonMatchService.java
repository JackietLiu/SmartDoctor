/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.match.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.match.entity.SmIllreasonMatch;
import com.thinkgem.jeesite.modules.match.dao.SmIllreasonMatchDao;

/**
 * 病因方案Service
 * @author louis
 * @version 2016-11-17
 */
@Service
@Transactional(readOnly = true)
public class SmIllreasonMatchService extends CrudService<SmIllreasonMatchDao, SmIllreasonMatch> {

	public SmIllreasonMatch get(String id) {
		return super.get(id);
	}
	
	public List<SmIllreasonMatch> findList(SmIllreasonMatch smIllreasonMatch) {
		return super.findList(smIllreasonMatch);
	}
	
	public Page<SmIllreasonMatch> findPage(Page<SmIllreasonMatch> page, SmIllreasonMatch smIllreasonMatch) {
		return super.findPage(page, smIllreasonMatch);
	}
	
	@Transactional(readOnly = false)
	public void save(SmIllreasonMatch smIllreasonMatch) {
		super.save(smIllreasonMatch);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmIllreasonMatch smIllreasonMatch) {
		super.delete(smIllreasonMatch);
	}
	
}