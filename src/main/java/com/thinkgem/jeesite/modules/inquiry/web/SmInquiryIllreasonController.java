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
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryIllreason;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryIllreasonService;

/**
 * 询诊病因Controller
 * @author louis
 * @version 2016-11-21
 */
@Controller
@RequestMapping(value = "${adminPath}/inquiry/smInquiryIllreason")
public class SmInquiryIllreasonController extends BaseController {

	@Autowired
	private SmInquiryIllreasonService smInquiryIllreasonService;
	
	@ModelAttribute
	public SmInquiryIllreason get(@RequestParam(required=false) String id) {
		SmInquiryIllreason entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smInquiryIllreasonService.get(id);
		}
		if (entity == null){
			entity = new SmInquiryIllreason();
		}
		return entity;
	}
	
	@RequiresPermissions("inquiry:smInquiryIllreason:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmInquiryIllreason smInquiryIllreason, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmInquiryIllreason> page = smInquiryIllreasonService.findPage(new Page<SmInquiryIllreason>(request, response), smInquiryIllreason); 
		model.addAttribute("page", page);
		return "modules/inquiry/smInquiryIllreasonList";
	}

	@RequiresPermissions("inquiry:smInquiryIllreason:view")
	@RequestMapping(value = "form")
	public String form(SmInquiryIllreason smInquiryIllreason, Model model) {
		model.addAttribute("smInquiryIllreason", smInquiryIllreason);
		return "modules/inquiry/smInquiryIllreasonForm";
	}

	@RequiresPermissions("inquiry:smInquiryIllreason:edit")
	@RequestMapping(value = "save")
	public String save(SmInquiryIllreason smInquiryIllreason, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smInquiryIllreason)){
			return form(smInquiryIllreason, model);
		}
		smInquiryIllreasonService.save(smInquiryIllreason);
		addMessage(redirectAttributes, "保存询诊病因成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiryIllreason/?repage";
	}
	
	@RequiresPermissions("inquiry:smInquiryIllreason:edit")
	@RequestMapping(value = "delete")
	public String delete(SmInquiryIllreason smInquiryIllreason, RedirectAttributes redirectAttributes) {
		smInquiryIllreasonService.delete(smInquiryIllreason);
		addMessage(redirectAttributes, "删除询诊病因成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiryIllreason/?repage";
	}

}