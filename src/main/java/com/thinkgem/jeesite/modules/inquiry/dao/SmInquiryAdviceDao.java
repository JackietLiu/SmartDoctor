/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryAdvice;

/**
 * 询诊治疗建议DAO接口
 * @author louis
 * @version 2016-11-21
 */
@MyBatisDao
public interface SmInquiryAdviceDao extends CrudDao<SmInquiryAdvice> {
	
	public int insert(SmInquiryAdvice smInquiryAdvice);
	
}