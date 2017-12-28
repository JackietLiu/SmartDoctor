/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryQues;
import com.thinkgem.jeesite.modules.inquiry.dao.SmInquiryQuesDao;

/**
 * 病人答题明细Service
 * @author louis
 * @version 2016-11-21
 */
@Service
@Transactional(readOnly = true)
public class SmInquiryQuesService extends CrudService<SmInquiryQuesDao, SmInquiryQues> {

	public SmInquiryQues get(String id) {
		return super.get(id);
	}
	
	public List<SmInquiryQues> findList(SmInquiryQues smInquiryQues) {
		return super.findList(smInquiryQues);
	}
	
	public Page<SmInquiryQues> findPage(Page<SmInquiryQues> page, SmInquiryQues smInquiryQues) {
		return super.findPage(page, smInquiryQues);
	}
	
	@Transactional(readOnly = false)
	public void save(SmInquiryQues smInquiryQues) {
		super.save(smInquiryQues);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmInquiryQues smInquiryQues) {
		super.delete(smInquiryQues);
	}

	@Transactional(readOnly = false)
	public void deleteBy(SmInquiryQues smInquiryQues) {
		dao.deleteBy(smInquiryQues);
	}

	public List<SmInquiryQues> findListOther(SmInquiryQues smInquiryQues) {
		return dao.findListOther(smInquiryQues);
	}

	public SmInquiryQues findFirstLast(SmInquiryQues smInquiryQues) {
		return dao.findFirstLast(smInquiryQues);
	}
	
}