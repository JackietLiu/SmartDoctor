/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import com.thinkgem.jeesite.modules.basic.service.SmQuestionService;

/**
 * 问题管理Controller
 * @author louis
 * @version 2016-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/smQuestion")
public class SmQuestionController extends BaseController {

	@Autowired
	private SmQuestionService smQuestionService;
	
	@ModelAttribute
	public SmQuestion get(@RequestParam(required=false) String id) {
		SmQuestion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smQuestionService.get(id);
		}
		if (entity == null){
			entity = new SmQuestion();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:smQuestion:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmQuestion smQuestion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmQuestion> page = smQuestionService.findPage(new Page<SmQuestion>(request, response), smQuestion); 
		model.addAttribute("page", page);
		return "modules/basic/smQuestionList";
	}

	@RequiresPermissions("basic:smQuestion:view")
	@RequestMapping(value = "form")
	public String form(SmQuestion smQuestion, Model model) {
		model.addAttribute("smQuestion", smQuestion);
		return "modules/basic/smQuestionForm";
	}

	@RequiresPermissions("basic:smQuestion:edit")
	@RequestMapping(value = "save")
	public String save(SmQuestion smQuestion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smQuestion)){
			return form(smQuestion, model);
		}
		smQuestionService.save(smQuestion);
		addMessage(redirectAttributes, "保存问题管理成功");
		return "redirect:"+Global.getAdminPath()+"/basic/smQuestion/?repage";
	}
	
	@RequiresPermissions("basic:smQuestion:edit")
	@RequestMapping(value = "delete")
	public String delete(SmQuestion smQuestion, RedirectAttributes redirectAttributes) {
		smQuestionService.delete(smQuestion);
		addMessage(redirectAttributes, "删除问题管理成功");
		return "redirect:"+Global.getAdminPath()+"/basic/smQuestion/?repage";
	}
	@RequiresPermissions("basic:smQuestion:edit")
	@RequestMapping(value = "toSaveForm")
	public String toSaveForm(Model model,HttpServletRequest request) {
		String tabPageId = request.getParameter("tabPageId");
		String frameName = "mainFrame";
		if(StringUtils.isNotBlank(tabPageId)){
			frameName = tabPageId;
		}
		model.addAttribute("frameName", frameName);
		return "modules/basic/smQuestionSaveForm";
	}

	@RequestMapping(value = "saveToQAndO")
	@ResponseBody
	public Map<String,Object> saveToQAndO(SmQuestion smQuestion,HttpServletRequest request) {
		smQuestion.setQyStatus("1");
		smQuestionService.saveToQAndO(smQuestion);
		
		Map<String,Object> result = Maps.newHashMap();
		result.put("success", true);
		return result;
	}
}