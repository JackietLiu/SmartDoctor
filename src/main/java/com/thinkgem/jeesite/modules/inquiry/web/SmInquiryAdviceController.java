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
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryAdvice;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryAdviceCopy;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryAdviceCopyService;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryAdviceService;

/**
 * 询诊治疗建议Controller
 * @author louis
 * @version 2016-11-21
 */
@Controller
@RequestMapping(value = "${adminPath}/inquiry/smInquiryAdvice")
public class SmInquiryAdviceController extends BaseController {

	@Autowired
	private SmInquiryAdviceService smInquiryAdviceService;
/*	@Autowired
	private SmInquiryAdviceCopyService adviceCopyService;
	*/
	@ModelAttribute
	public SmInquiryAdvice get(@RequestParam(required=false) String id) {
		SmInquiryAdvice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smInquiryAdviceService.get(id);
		}
		if (entity == null){
			entity = new SmInquiryAdvice();
		}
		return entity;
	}
	
	@RequiresPermissions("inquiry:smInquiryAdvice:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmInquiryAdvice smInquiryAdvice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmInquiryAdvice> page = smInquiryAdviceService.findPage(new Page<SmInquiryAdvice>(request, response), smInquiryAdvice); 
		model.addAttribute("page", page);
		return "modules/inquiry/smInquiryAdviceList";
	}

	@RequiresPermissions("inquiry:smInquiryAdvice:view")
	@RequestMapping(value = "form")
	public String form(SmInquiryAdvice smInquiryAdvice, Model model) {
		model.addAttribute("smInquiryAdvice", smInquiryAdvice);
		return "modules/inquiry/smInquiryAdviceForm";
	}

	@RequiresPermissions("inquiry:smInquiryAdvice:edit")
	@RequestMapping(value = "save")
	public String save(SmInquiryAdvice smInquiryAdvice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smInquiryAdvice)){
			return form(smInquiryAdvice, model);
		}
		smInquiryAdviceService.save(smInquiryAdvice);
		addMessage(redirectAttributes, "保存询诊治疗建议成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiryAdvice/?repage";
	}
	
	@RequiresPermissions("inquiry:smInquiryAdvice:edit")
	@RequestMapping(value = "delete")
	public String delete(SmInquiryAdvice smInquiryAdvice, RedirectAttributes redirectAttributes) {
		smInquiryAdviceService.delete(smInquiryAdvice);
/*		SmInquiryAdviceCopy smInquiryAdviceCopy = new SmInquiryAdviceCopy();
		smInquiryAdviceCopy.setOld(smInquiryAdvice);
		adviceCopyService.delete(smInquiryAdviceCopy);*/
		addMessage(redirectAttributes, "删除询诊治疗建议成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiryAdvice/?repage";
	}

}