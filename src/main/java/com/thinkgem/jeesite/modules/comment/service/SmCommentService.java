/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.comment.entity.SmComment;
import com.thinkgem.jeesite.modules.comment.dao.SmCommentDao;

/**
 * 专家点评Service
 * @author louis
 * @version 2016-11-18
 */
@Service
@Transactional(readOnly = true)
public class SmCommentService extends CrudService<SmCommentDao, SmComment> {

	public SmComment get(String id) {
		return super.get(id);
	}
	
	public List<SmComment> findList(SmComment smComment) {
		return super.findList(smComment);
	}
	
	public Page<SmComment> findPage(Page<SmComment> page, SmComment smComment) {
		return super.findPage(page, smComment);
	}
	
	@Transactional(readOnly = false)
	public void save(SmComment smComment) {
		super.save(smComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmComment smComment) {
		super.delete(smComment);
	}
	
}