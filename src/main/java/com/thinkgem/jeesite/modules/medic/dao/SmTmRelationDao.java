/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.medic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.medic.entity.SmTmRelation;

/**
 * 药品和类别关系DAO接口
 * @author JackietLiu
 * @version 2017-07-20
 */
@MyBatisDao
public interface SmTmRelationDao extends CrudDao<SmTmRelation> {
	
}