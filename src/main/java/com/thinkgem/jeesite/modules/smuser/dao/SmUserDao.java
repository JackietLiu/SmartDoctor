/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smuser.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.smuser.entity.SmUser;

/**
 * 模拟患者表DAO接口
 * @author Jackiet
 * @version 2017-04-13
 */
@MyBatisDao
public interface SmUserDao extends CrudDao<SmUser> {
	
}