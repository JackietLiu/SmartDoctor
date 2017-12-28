/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.match.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.match.entity.SmIllreasonMatch;

/**
 * 病因方案DAO接口
 * @author louis
 * @version 2016-11-17
 */
@MyBatisDao
public interface SmIllreasonMatchDao extends CrudDao<SmIllreasonMatch> {
	
}