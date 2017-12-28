/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.advice.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.advice.entity.SmAdvise;

/**
 * 治疗建议DAO接口
 * @author louis
 * @version 2016-11-16
 */
@MyBatisDao
public interface SmAdviseDao extends CrudDao<SmAdvise> {
	
}