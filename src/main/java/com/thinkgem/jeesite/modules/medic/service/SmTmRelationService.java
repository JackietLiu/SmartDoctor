/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.medic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.medic.entity.SmTmRelation;
import com.thinkgem.jeesite.modules.medic.dao.SmTmRelationDao;

/**
 * 药品和类别关系Service
 * @author JackietLiu
 * @version 2017-07-20
 */
@Service
@Transactional(readOnly = true)
public class SmTmRelationService extends CrudService<SmTmRelationDao, SmTmRelation> {

	public SmTmRelation get(String id) {
		return super.get(id);
	}
	
	public List<SmTmRelation> findList(SmTmRelation smTmRelation) {
		return super.findList(smTmRelation);
	}
	
	public Page<SmTmRelation> findPage(Page<SmTmRelation> page, SmTmRelation smTmRelation) {
		return super.findPage(page, smTmRelation);
	}
	
	@Transactional(readOnly = false)
	public void save(SmTmRelation smTmRelation) {
		super.save(smTmRelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmTmRelation smTmRelation) {
		super.delete(smTmRelation);
	}
	
}