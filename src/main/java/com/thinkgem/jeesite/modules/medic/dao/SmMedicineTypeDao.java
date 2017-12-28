/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.medic.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.medic.entity.SmMedicineType;

/**
 * 药品类别DAO接口
 * @author JackietLiu
 * @version 2017-07-23
 */
@MyBatisDao
public interface SmMedicineTypeDao extends TreeDao<SmMedicineType> {
	
}