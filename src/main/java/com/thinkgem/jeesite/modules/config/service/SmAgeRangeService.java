/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.config.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.config.entity.SmAgeRange;
import com.thinkgem.jeesite.modules.config.dao.SmAgeRangeDao;

/**
 * 年龄段Service
 * @author JackietLiu
 * @version 2017-05-15
 */
@Service
@Transactional(readOnly = true)
public class SmAgeRangeService extends CrudService<SmAgeRangeDao, SmAgeRange> {

	public SmAgeRange get(String id) {
		return super.get(id);
	}
	
	public List<SmAgeRange> findList(SmAgeRange smAgeRange) {
		return super.findList(smAgeRange);
	}
	
	public Page<SmAgeRange> findPage(Page<SmAgeRange> page, SmAgeRange smAgeRange) {
		return super.findPage(page, smAgeRange);
	}
	
	@Transactional(readOnly = false)
	public void save(SmAgeRange smAgeRange) {
		super.save(smAgeRange);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmAgeRange smAgeRange) {
		super.delete(smAgeRange);
	}
	
}