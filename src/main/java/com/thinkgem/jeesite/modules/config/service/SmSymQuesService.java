/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.config.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.config.entity.SmSymQues;
import com.thinkgem.jeesite.modules.config.dao.SmSymQuesDao;

/**
 * 病症和问题关联表Service
 * @author louis
 * @version 2016-11-14
 */
@Service
@Transactional(readOnly = true)
public class SmSymQuesService extends CrudService<SmSymQuesDao, SmSymQues> {

	public SmSymQues get(String id) {
		return super.get(id);
	}
	
	public List<SmSymQues> findList(SmSymQues smSymQues) {
		return super.findList(smSymQues);
	}
	
	public Page<SmSymQues> findPage(Page<SmSymQues> page, SmSymQues smSymQues) {
		return super.findPage(page, smSymQues);
	}
	
	@Transactional(readOnly = false)
	public void save(SmSymQues smSymQues) {
		super.save(smSymQues);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmSymQues smSymQues) {
		super.delete(smSymQues);
	}

	public SmSymQues getQuestionBySort(SmSymQues smSymQues) {
		return dao.getQuestionBySort(smSymQues);
	}
	
}