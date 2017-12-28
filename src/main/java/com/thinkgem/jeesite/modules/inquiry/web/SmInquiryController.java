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
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiry;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryService;

/**
 * 病人询诊Controller
 * @author louis
 * @version 2016-11-21
 */
@Controller
@RequestMapping(value = "${adminPath}/inquiry/smInquiry")
public class SmInquiryController extends BaseController {

	@Autowired
	private SmInquiryService smInquiryService;
	
	@ModelAttribute
	public SmInquiry get(@RequestParam(required=false) String id) {
		SmInquiry entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smInquiryService.get(id);
		}
		if (entity == null){
			entity = new SmInquiry();
		}
		return entity;
	}
	
	@RequiresPermissions("inquiry:smInquiry:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmInquiry smInquiry, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isBlank(smInquiry.getIllStatus())){
			smInquiry.setIllStatus("3");
		}
		Page<SmInquiry> page = smInquiryService.findPage(new Page<SmInquiry>(request, response), smInquiry); 
		model.addAttribute("page", page);
		return "modules/inquiry/smInquiryList";
	}

	@RequiresPermissions("inquiry:smInquiry:view")
	@RequestMapping(value = "form")
	public String form(SmInquiry smInquiry, Model model) {
		model.addAttribute("smInquiry", smInquiry);
		return "modules/inquiry/smInquiryForm";
	}

	@RequiresPermissions("inquiry:smInquiry:edit")
	@RequestMapping(value = "save")
	public String save(SmInquiry smInquiry, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smInquiry)){
			return form(smInquiry, model);
		}
		smInquiryService.save(smInquiry);
		addMessage(redirectAttributes, "保存病人询诊成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiry/?repage";
	}
	
	@RequiresPermissions("inquiry:smInquiry:edit")
	@RequestMapping(value = "delete")
	public String delete(SmInquiry smInquiry, RedirectAttributes redirectAttributes) {
		smInquiryService.delete(smInquiry);
		addMessage(redirectAttributes, "删除病人询诊成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiry/?repage";
	}

}