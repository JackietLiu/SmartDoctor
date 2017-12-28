/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smrelation.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.smrelation.entity.SmRelation;

/**
 * 配置病因DAO接口
 * @author JackietLiu
 * @version 2017-04-20
 */
@MyBatisDao
public interface SmRelationDao extends CrudDao<SmRelation> {
	SmRelation findFirst(SmRelation smRelation);
	SmRelation getPercent(SmRelation smRelation);
	List<SmRelation> getQuesByIrId(SmRelation smRelation);
}