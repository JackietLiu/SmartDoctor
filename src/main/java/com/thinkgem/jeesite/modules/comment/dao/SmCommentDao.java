/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.comment.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.comment.entity.SmComment;

/**
 * 专家点评DAO接口
 * @author louis
 * @version 2016-11-18
 */
@MyBatisDao
public interface SmCommentDao extends CrudDao<SmComment> {
	
}