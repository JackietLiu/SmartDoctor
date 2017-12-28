/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiry;
import com.thinkgem.jeesite.modules.inquiry.dao.SmInquiryDao;

/**
 * 病人询诊Service
 * @author louis
 * @version 2016-11-21
 */
@Service
@Transactional(readOnly = true)
public class SmInquiryService extends CrudService<SmInquiryDao, SmInquiry> {

	public SmInquiry get(String id) {
		return super.get(id);
	}
	
	public List<SmInquiry> findList(SmInquiry smInquiry) {
		return super.findList(smInquiry);
	}
	
	public Page<SmInquiry> findPage(Page<SmInquiry> page, SmInquiry smInquiry) {
		return super.findPage(page, smInquiry);
	}
	
	@Transactional(readOnly = false)
	public void save(SmInquiry smInquiry) {
		super.save(smInquiry);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmInquiry smInquiry) {
		super.delete(smInquiry);
	}

	public SmInquiry getLastInqu(String cardno) {
		return dao.getLastInqu(cardno);
	}

	public SmInquiry findListExcept(SmInquiry smInquiry) {
		return dao.findListExcept(smInquiry);
	}
	
}