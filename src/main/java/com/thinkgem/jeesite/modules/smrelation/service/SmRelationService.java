/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smrelation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.smrelation.entity.SmRelation;
import com.thinkgem.jeesite.modules.smrelation.dao.SmRelationDao;

/**
 * 配置病因Service
 * @author JackietLiu
 * @version 2017-04-20
 */
@Service
@Transactional(readOnly = true)
public class SmRelationService extends CrudService<SmRelationDao, SmRelation> {

	public SmRelation get(String id) {
		return super.get(id);
	}
	
	public List<SmRelation> findList(SmRelation smRelation) {
		return super.findList(smRelation);
	}
	
	public Page<SmRelation> findPage(Page<SmRelation> page, SmRelation smRelation) {
		return super.findPage(page, smRelation);
	}
	
	@Transactional(readOnly = false)
	public void save(SmRelation smRelation) {
		super.save(smRelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmRelation smRelation) {
		super.delete(smRelation);
	}
	@Transactional(readOnly = false)
	public SmRelation findFirst(SmRelation smRelation) {
		return dao.findFirst(smRelation);
	}
	@Transactional(readOnly = false)
	public SmRelation getPercent(SmRelation smRelation) {
		return dao.getPercent(smRelation);
	}
	@Transactional(readOnly = false)
	public List<SmRelation> getQuesByIrId(SmRelation smRelation) {
		return dao.getQuesByIrId(smRelation);
	}
}