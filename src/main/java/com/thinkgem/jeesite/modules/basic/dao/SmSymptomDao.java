/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;

/**
 * 病症管理DAO接口
 * @author louis
 * @version 2016-11-11
 */
@MyBatisDao
public interface SmSymptomDao extends CrudDao<SmSymptom> {
	
}