/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smrelation.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.smrelation.entity.SmSymQuesIllreason;

/**
 * 配置病因DAO接口
 * @author louis
 * @version 2017-03-24
 */
@MyBatisDao
public interface SmSymQuesIllreasonDao extends CrudDao<SmSymQuesIllreason> {

	SmSymQuesIllreason findFirst(SmSymQuesIllreason sqIrea);
	
}