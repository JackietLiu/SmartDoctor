/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.basic.entity.SmHealthy;
import com.thinkgem.jeesite.modules.basic.dao.SmHealthyDao;

/**
 * 健康处方Service
 * @author JackietLiu
 * @version 2017-08-02
 */
@Service
@Transactional(readOnly = true)
public class SmHealthyService extends CrudService<SmHealthyDao, SmHealthy> {

	public SmHealthy get(String id) {
		return super.get(id);
	}
	
	public List<SmHealthy> findList(SmHealthy smHealthy) {
		return super.findList(smHealthy);
	}
	
	public Page<SmHealthy> findPage(Page<SmHealthy> page, SmHealthy smHealthy) {
		return super.findPage(page, smHealthy);
	}
	
	@Transactional(readOnly = false)
	public void save(SmHealthy smHealthy) {
		super.save(smHealthy);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmHealthy smHealthy) {
		super.delete(smHealthy);
	}
	
}