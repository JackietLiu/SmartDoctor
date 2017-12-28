/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.advice.web;

import java.util.List;

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
import com.thinkgem.jeesite.modules.advice.entity.SmAdvise;
import com.thinkgem.jeesite.modules.advice.entity.SmIllreasonAdvice;
import com.thinkgem.jeesite.modules.advice.service.SmAdviseService;
import com.thinkgem.jeesite.modules.advice.service.SmIllreasonAdviceService;

/**
 * 病因和治疗建议Controller
 * @author louis
 * @version 2016-11-16
 */
@Controller
@RequestMapping(value = "${adminPath}/advice/smIllreasonAdvice")
public class SmIllreasonAdviceController extends BaseController {

	@Autowired
	private SmIllreasonAdviceService smIllreasonAdviceService;
	
	@Autowired
	private SmAdviseService smAdviseService;
	
	@ModelAttribute
	public SmIllreasonAdvice get(@RequestParam(required=false) String id) {
		SmIllreasonAdvice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smIllreasonAdviceService.get(id);
		}
		if (entity == null){
			entity = new SmIllreasonAdvice();
		}
		return entity;
	}
	
	@RequiresPermissions("advice:smIllreasonAdvice:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmIllreasonAdvice smIllreasonAdvice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmIllreasonAdvice> page = smIllreasonAdviceService.findPage(new Page<SmIllreasonAdvice>(request, response), smIllreasonAdvice); 
		List<SmAdvise> list = smAdviseService.findAllList(new SmAdvise());
		model.addAttribute("smAdviseList", list);
		model.addAttribute("page", page);
		return "modules/advice/smIllreasonAdviceList";
	}

	@RequiresPermissions("advice:smIllreasonAdvice:view")
	@RequestMapping(value = "form")
	public String form(SmIllreasonAdvice smIllreasonAdvice, Model model) {
		List<SmAdvise> list = smAdviseService.findAllList(new SmAdvise());
		model.addAttribute("smAdviseList", list);
		model.addAttribute("smIllreasonAdvice", smIllreasonAdvice);
		return "modules/advice/smIllreasonAdviceForm";
	}

	@RequiresPermissions("advice:smIllreasonAdvice:edit")
	@RequestMapping(value = "save")
	public String save(SmIllreasonAdvice smIllreasonAdvice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smIllreasonAdvice)){
			return form(smIllreasonAdvice, model);
		}
		smIllreasonAdviceService.save(smIllreasonAdvice);
		addMessage(redirectAttributes, "保存病因和治疗建议成功");
		return "redirect:"+Global.getAdminPath()+"/advice/smIllreasonAdvice/?repage&illrea.id="+smIllreasonAdvice.getIllrea().getId();
	}
	
	@RequiresPermissions("advice:smIllreasonAdvice:edit")
	@RequestMapping(value = "delete")
	public String delete(SmIllreasonAdvice smIllreasonAdvice, RedirectAttributes redirectAttributes) {
		smIllreasonAdviceService.delete(smIllreasonAdvice);
		addMessage(redirectAttributes, "删除病因和治疗建议成功");
		return "redirect:"+Global.getAdminPath()+"/advice/smIllreasonAdvice/?repage&illrea.id="+smIllreasonAdvice.getIllrea().getId();
	}

}