/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryIllreason;
import com.thinkgem.jeesite.modules.inquiry.dao.SmInquiryIllreasonDao;

/**
 * 询诊病因Service
 * @author louis
 * @version 2016-11-21
 */
@Service
@Transactional(readOnly = true)
public class SmInquiryIllreasonService extends CrudService<SmInquiryIllreasonDao, SmInquiryIllreason> {

	public SmInquiryIllreason get(String id) {
		return super.get(id);
	}
	
	public List<SmInquiryIllreason> findList(SmInquiryIllreason smInquiryIllreason) {
		return super.findList(smInquiryIllreason);
	}
	
	public Page<SmInquiryIllreason> findPage(Page<SmInquiryIllreason> page, SmInquiryIllreason smInquiryIllreason) {
		return super.findPage(page, smInquiryIllreason);
	}
	
	@Transactional(readOnly = false)
	public void save(SmInquiryIllreason smInquiryIllreason) {
		super.save(smInquiryIllreason);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmInquiryIllreason smInquiryIllreason) {
		super.delete(smInquiryIllreason);
	}

	public SmInquiryIllreason findFirst(SmInquiryIllreason illrea) {
		return dao.findFirst(illrea);
	}
	
}