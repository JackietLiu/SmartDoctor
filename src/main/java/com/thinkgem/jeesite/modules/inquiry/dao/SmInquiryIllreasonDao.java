/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryIllreason;

/**
 * 询诊病因DAO接口
 * @author louis
 * @version 2016-11-21
 */
@MyBatisDao
public interface SmInquiryIllreasonDao extends CrudDao<SmInquiryIllreason> {

	SmInquiryIllreason findFirst(SmInquiryIllreason illrea);

	
}