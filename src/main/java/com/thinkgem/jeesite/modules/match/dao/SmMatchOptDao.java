/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.match.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.match.entity.SmMatchOpt;

/**
 * 方案配置选项DAO接口
 * @author louis
 * @version 2016-11-17
 */
@MyBatisDao
public interface SmMatchOptDao extends CrudDao<SmMatchOpt> {

	List<SmMatchOpt> findExistQuestList(SmMatchOpt smMatchOpt);

	List<SmMatchOpt> findListGroupBy(SmMatchOpt smMatchOpt);
	
}