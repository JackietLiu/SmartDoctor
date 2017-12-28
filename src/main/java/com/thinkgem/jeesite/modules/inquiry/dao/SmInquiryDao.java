/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiry;

/**
 * 病人询诊DAO接口
 * @author louis
 * @version 2016-11-21
 */
@MyBatisDao
public interface SmInquiryDao extends CrudDao<SmInquiry> {

	SmInquiry getLastInqu(String cardno);

	SmInquiry findListExcept(SmInquiry smInquiry);
	
}