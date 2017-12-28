/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smrelation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.smrelation.entity.SmSymQuesIllreason;
import com.thinkgem.jeesite.modules.smrelation.dao.SmSymQuesIllreasonDao;

/**
 * 配置病因Service
 * @author louis
 * @version 2017-03-24
 */
@Service
@Transactional(readOnly = true)
public class SmSymQuesIllreasonService extends CrudService<SmSymQuesIllreasonDao, SmSymQuesIllreason> {

	public SmSymQuesIllreason get(String id) {
		return super.get(id);
	}
	
	public List<SmSymQuesIllreason> findList(SmSymQuesIllreason smSymQuesIllreason) {
		return super.findList(smSymQuesIllreason);
	}
	
	public Page<SmSymQuesIllreason> findPage(Page<SmSymQuesIllreason> page, SmSymQuesIllreason smSymQuesIllreason) {
		return super.findPage(page, smSymQuesIllreason);
	}
	
	@Transactional(readOnly = false)
	public void save(SmSymQuesIllreason smSymQuesIllreason) {
		super.save(smSymQuesIllreason);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmSymQuesIllreason smSymQuesIllreason) {
		super.delete(smSymQuesIllreason);
	}

	public SmSymQuesIllreason findFirst(SmSymQuesIllreason sqIrea) {
		return dao.findFirst(sqIrea);
	}
	
}