/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.config.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.config.entity.SmSymQues;

/**
 * 病症和问题关联表DAO接口
 * @author louis
 * @version 2016-11-14
 */
@MyBatisDao
public interface SmSymQuesDao extends CrudDao<SmSymQues> {

	SmSymQues getQuestionBySort(SmSymQues smSymQues);
	
}