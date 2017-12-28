/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryAdvice;
import com.thinkgem.jeesite.modules.inquiry.dao.SmInquiryAdviceDao;

/**
 * 询诊治疗建议Service
 * @author louis
 * @version 2016-11-21
 */
@Service
@Transactional(readOnly = true)
public class SmInquiryAdviceService extends CrudService<SmInquiryAdviceDao, SmInquiryAdvice> {

	public SmInquiryAdvice get(String id) {
		return super.get(id);
	}
	
	public List<SmInquiryAdvice> findList(SmInquiryAdvice smInquiryAdvice) {
		return super.findList(smInquiryAdvice);
	}
	
	public Page<SmInquiryAdvice> findPage(Page<SmInquiryAdvice> page, SmInquiryAdvice smInquiryAdvice) {
		return super.findPage(page, smInquiryAdvice);
	}
	
	@Transactional(readOnly = false)
	public void save(SmInquiryAdvice smInquiryAdvice) {
		super.save(smInquiryAdvice);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmInquiryAdvice smInquiryAdvice) {
		super.delete(smInquiryAdvice);
	}
	@Transactional(readOnly = false)
	public int insert(SmInquiryAdvice smInquiryAdvice){
		return dao.insert(smInquiryAdvice);
		
	}
	
}