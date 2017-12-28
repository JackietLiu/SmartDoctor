/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryAdviceCopy;

/**
 * 修改后的系统处方DAO接口
 * @author JackietLiu
 * @version 2017-07-03
 */
@MyBatisDao
public interface SmInquiryAdviceCopyDao extends CrudDao<SmInquiryAdviceCopy> {
	void updateByOldId(SmInquiryAdviceCopy smInquiryAdviceCopy);
}