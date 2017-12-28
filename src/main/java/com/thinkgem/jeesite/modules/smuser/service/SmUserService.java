/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smuser.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.smuser.entity.SmUser;
import com.thinkgem.jeesite.modules.smuser.dao.SmUserDao;

/**
 * 模拟患者表Service
 * @author Jackiet
 * @version 2017-04-13
 */
@Service
@Transactional(readOnly = true)
public class SmUserService extends CrudService<SmUserDao, SmUser> {

	public SmUser get(String id) {
		return super.get(id);
	}
	
	public List<SmUser> findList(SmUser smUser) {
		return super.findList(smUser);
	}
	
	public Page<SmUser> findPage(Page<SmUser> page, SmUser smUser) {
		return super.findPage(page, smUser);
	}
	
	@Transactional(readOnly = false)
	public void save(SmUser smUser) {
		super.save(smUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmUser smUser) {
		super.delete(smUser);
	}
	
}