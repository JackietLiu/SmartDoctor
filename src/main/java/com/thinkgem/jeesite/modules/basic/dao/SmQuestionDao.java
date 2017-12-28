/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;

/**
 * 问题管理DAO接口
 * @author JackietLiu
 * @version 2017-04-20
 */
@MyBatisDao
public interface SmQuestionDao extends CrudDao<SmQuestion> {
	
}