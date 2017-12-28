/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.config.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.config.entity.SmAgeRange;

/**
 * 年龄段DAO接口
 * @author JackietLiu
 * @version 2017-05-15
 */
@MyBatisDao
public interface SmAgeRangeDao extends CrudDao<SmAgeRange> {
	
}