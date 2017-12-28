/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.match.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.match.entity.SmMatchOpt;
import com.thinkgem.jeesite.modules.match.dao.SmMatchOptDao;

/**
 * 方案配置选项Service
 * @author louis
 * @version 2016-11-17
 */
@Service
@Transactional(readOnly = true)
public class SmMatchOptService extends CrudService<SmMatchOptDao, SmMatchOpt> {

	public SmMatchOpt get(String id) {
		return super.get(id);
	}
	
	public List<SmMatchOpt> findList(SmMatchOpt smMatchOpt) {
		return super.findList(smMatchOpt);
	}
	
	public Page<SmMatchOpt> findPage(Page<SmMatchOpt> page, SmMatchOpt smMatchOpt) {
		return super.findPage(page, smMatchOpt);
	}
	
	@Transactional(readOnly = false)
	public void save(SmMatchOpt smMatchOpt) {
		
		super.save(smMatchOpt);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmMatchOpt smMatchOpt) {
		super.delete(smMatchOpt);
	}

	public List<SmMatchOpt> findExistQuestList(SmMatchOpt smMatchOpt) {
		return dao.findExistQuestList(smMatchOpt);
	}

	public List<SmMatchOpt> findListGroupBy(SmMatchOpt smMatchOpt) {
		return dao.findListGroupBy(smMatchOpt);
	}
	
}