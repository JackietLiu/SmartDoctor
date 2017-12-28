/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryQues;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryQuesService;

/**
 * 病人答题明细Controller
 * @author louis
 * @version 2016-11-21
 */
@Controller
@RequestMapping(value = "${adminPath}/inquiry/smInquiryQues")
public class SmInquiryQuesController extends BaseController {

	@Autowired
	private SmInquiryQuesService smInquiryQuesService;
	
	@ModelAttribute
	public SmInquiryQues get(@RequestParam(required=false) String id) {
		SmInquiryQues entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smInquiryQuesService.get(id);
		}
		if (entity == null){
			entity = new SmInquiryQues();
		}
		return entity;
	}
	
	@RequiresPermissions("inquiry:smInquiryQues:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmInquiryQues smInquiryQues, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmInquiryQues> page = smInquiryQuesService.findPage(new Page<SmInquiryQues>(request, response), smInquiryQues); 
		model.addAttribute("page", page);
		return "modules/inquiry/smInquiryQuesList";
	}

	@RequiresPermissions("inquiry:smInquiryQues:view")
	@RequestMapping(value = "form")
	public String form(SmInquiryQues smInquiryQues, Model model) {
		model.addAttribute("smInquiryQues", smInquiryQues);
		return "modules/inquiry/smInquiryQuesForm";
	}

	@RequiresPermissions("inquiry:smInquiryQues:edit")
	@RequestMapping(value = "save")
	public String save(SmInquiryQues smInquiryQues, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smInquiryQues)){
			return form(smInquiryQues, model);
		}
		smInquiryQuesService.save(smInquiryQues);
		addMessage(redirectAttributes, "保存病人答题明细成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiryQues/?repage";
	}
	
	@RequiresPermissions("inquiry:smInquiryQues:edit")
	@RequestMapping(value = "delete")
	public String delete(SmInquiryQues smInquiryQues, RedirectAttributes redirectAttributes) {
		smInquiryQuesService.delete(smInquiryQues);
		addMessage(redirectAttributes, "删除病人答题明细成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiryQues/?repage";
	}

}