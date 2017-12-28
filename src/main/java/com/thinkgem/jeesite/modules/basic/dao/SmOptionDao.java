/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.basic.entity.SmOption;

/**
 * 问题选项DAO接口
 * @author louis
 * @version 2016-11-14
 */
@MyBatisDao
public interface SmOptionDao extends CrudDao<SmOption> {
	
}