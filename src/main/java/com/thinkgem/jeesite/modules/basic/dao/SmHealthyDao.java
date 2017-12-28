/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.basic.entity.SmHealthy;

/**
 * 健康处方DAO接口
 * @author JackietLiu
 * @version 2017-08-02
 */
@MyBatisDao
public interface SmHealthyDao extends CrudDao<SmHealthy> {
	
}