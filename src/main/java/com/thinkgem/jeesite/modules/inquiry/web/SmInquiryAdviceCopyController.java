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
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryAdviceCopy;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryAdviceCopyService;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryAdviceService;

/**
 * 修改后的系统处方Controller
 * @author JackietLiu
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/inquiry/smInquiryAdviceCopy")
public class SmInquiryAdviceCopyController extends BaseController {

	@Autowired
	private SmInquiryAdviceCopyService smInquiryAdviceCopyService;
/*	@Autowired
	private SmInquiryAdviceService smInquiryAdviceService;
*/	
	@ModelAttribute
	public SmInquiryAdviceCopy get(@RequestParam(required=false) String id) {
		SmInquiryAdviceCopy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smInquiryAdviceCopyService.get(id);
		}
		if (entity == null){
			entity = new SmInquiryAdviceCopy();
		}
		return entity;
	}
	
	@RequiresPermissions("inquiry:smInquiryAdviceCopy:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmInquiryAdviceCopy smInquiryAdviceCopy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmInquiryAdviceCopy> page = smInquiryAdviceCopyService.findPage(new Page<SmInquiryAdviceCopy>(request, response), smInquiryAdviceCopy); 
		model.addAttribute("page", page);
		return "modules/inquiry/smInquiryAdviceCopyList";
	}

	@RequiresPermissions("inquiry:smInquiryAdviceCopy:view")
	@RequestMapping(value = "form")
	public String form(SmInquiryAdviceCopy smInquiryAdviceCopy, Model model) {
		model.addAttribute("smInquiryAdviceCopy", smInquiryAdviceCopy);
		return "modules/inquiry/smInquiryAdviceCopyForm";
	}

	@RequiresPermissions("inquiry:smInquiryAdviceCopy:edit")
	@RequestMapping(value = "save")
	public String save(SmInquiryAdviceCopy smInquiryAdviceCopy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smInquiryAdviceCopy)){
			return form(smInquiryAdviceCopy, model);
		}
		smInquiryAdviceCopyService.save(smInquiryAdviceCopy);
		addMessage(redirectAttributes, "保存医师处方成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiryAdviceCopy/?inqu.id="+smInquiryAdviceCopy.getInqu().getId();
	}
	@RequiresPermissions("inquiry:smInquiryAdviceCopy:edit")
	@RequestMapping(value = "updateByOldId")
	public String updateByOldId(SmInquiryAdviceCopy smInquiryAdviceCopy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smInquiryAdviceCopy)){
			return form(smInquiryAdviceCopy, model);
		}
		smInquiryAdviceCopyService.updateByOldId(smInquiryAdviceCopy);
		addMessage(redirectAttributes, "保存医师处方成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiryAdviceCopy/?repage";
	}
	@RequiresPermissions("inquiry:smInquiryAdviceCopy:edit")
	@RequestMapping(value = "delete")
	public String delete(SmInquiryAdviceCopy smInquiryAdviceCopy, RedirectAttributes redirectAttributes) {
		smInquiryAdviceCopyService.delete(smInquiryAdviceCopy);
		addMessage(redirectAttributes, "删除医师处方成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiryAdviceCopy/?repage";
	}

}