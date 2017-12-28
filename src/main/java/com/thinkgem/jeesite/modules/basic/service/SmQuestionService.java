/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import com.thinkgem.jeesite.modules.basic.dao.SmQuestionDao;

/**
 * 问题管理Service
 * @author louis
 * @version 2016-11-14
 */
@Service
@Transactional(readOnly = true)
public class SmQuestionService extends CrudService<SmQuestionDao, SmQuestion> {
	
	@Autowired
	private SmOptionService smOptionService;

	public SmQuestion get(String id) {
		return super.get(id);
	}
	
	public List<SmQuestion> findList(SmQuestion smQuestion) {
		return super.findList(smQuestion);
	}
	
	public Page<SmQuestion> findPage(Page<SmQuestion> page, SmQuestion smQuestion) {
		return super.findPage(page, smQuestion);
	}
	
	@Transactional(readOnly = false)
	public void save(SmQuestion smQuestion) {
		super.save(smQuestion);
	}
	
	@Transactional(readOnly = false)
	public void delete(SmQuestion smQuestion) {
		super.delete(smQuestion);
	}

	@Transactional(readOnly = false)
	public void saveToQAndO(SmQuestion smQuestion) {
		super.save(smQuestion);
		if(StringUtils.isNotBlank(smQuestion.getOptName())){
			 String  [] optNames = smQuestion.getOptName().split(",");
			 String  [] optSorts = smQuestion.getOptSort().split(",");
			 int length = optNames.length;
			 for(int i = 0;i<length;i++){
				 SmOption opt = new SmOption();
				 opt.setQuestion(smQuestion);
				 opt.setName(optNames[i]);
				 opt.setQyStatus("1");
				 opt.setIsLast("1");
				 if(optSorts.length>0&&StringUtils.isNoneBlank(optSorts[i])){
					 opt.setSort(optSorts[i]);
				 }
				 smOptionService.save(opt);
			 }
		}
	}
}