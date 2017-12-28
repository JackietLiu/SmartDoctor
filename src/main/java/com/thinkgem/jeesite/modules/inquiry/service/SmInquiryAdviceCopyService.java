/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryAdviceCopy;
import com.thinkgem.jeesite.modules.inquiry.dao.SmInquiryAdviceCopyDao;

/**
 * 修改后的系统处方Service
 * @author JackietLiu
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class SmInquiryAdviceCopyService extends CrudService<SmInquiryAdviceCopyDao, SmInquiryAdviceCopy> {

	public SmInquiryAdviceCopy get(String id) {
		return super.get(id);
	}
	
	public List<SmInquiryAdviceCopy> findList(SmInquiryAdviceCopy smInquiryAdviceCopy) {
		return super.findList(smInquiryAdviceCopy);
	}
	
	public Page<SmInquiryAdviceCopy> findPage(Page<SmInquiryAdviceCopy> page, SmInquiryAdviceCopy smInquiryAdviceCopy) {
		return super.findPage(page, smInquiryAdviceCopy);
	}
	
	@Transactional(readOnly = false)
	public void save(SmInquiryAdviceCopy smInquiryAdviceCopy) {
		super.save(smInquiryAdviceCopy);
	}
	@Transactional(readOnly = false)
	public void updateByOldId(SmInquiryAdviceCopy smInquiryAdviceCopy) {
		dao.updateByOldId(smInquiryAdviceCopy);
	}
	@Transactional(readOnly = false)
	public void delete(SmInquiryAdviceCopy smInquiryAdviceCopy) {
		super.delete(smInquiryAdviceCopy);
	}
	
}