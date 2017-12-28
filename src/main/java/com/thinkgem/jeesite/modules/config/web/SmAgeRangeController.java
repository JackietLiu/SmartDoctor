/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.config.web;

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
import com.thinkgem.jeesite.modules.config.entity.SmAgeRange;
import com.thinkgem.jeesite.modules.config.service.SmAgeRangeService;

/**
 * 年龄段Controller
 * @author JackietLiu
 * @version 2017-05-15
 */
@Controller
@RequestMapping(value = "${adminPath}/config/smAgeRange")
public class SmAgeRangeController extends BaseController {

	@Autowired
	private SmAgeRangeService smAgeRangeService;
	
	@ModelAttribute
	public SmAgeRange get(@RequestParam(required=false) String id) {
		SmAgeRange entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smAgeRangeService.get(id);
		}
		if (entity == null){
			entity = new SmAgeRange();
		}
		return entity;
	}
	
	@RequiresPermissions("config:smAgeRange:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmAgeRange smAgeRange, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmAgeRange> page = smAgeRangeService.findPage(new Page<SmAgeRange>(request, response), smAgeRange); 
		model.addAttribute("page", page);
		return "modules/config/smAgeRangeList";
	}

	@RequiresPermissions("config:smAgeRange:view")
	@RequestMapping(value = "form")
	public String form(SmAgeRange smAgeRange, Model model) {
		model.addAttribute("smAgeRange", smAgeRange);
		return "modules/config/smAgeRangeForm";
	}

	@RequiresPermissions("config:smAgeRange:edit")
	@RequestMapping(value = "save")
	public String save(SmAgeRange smAgeRange, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smAgeRange)){
			return form(smAgeRange, model);
		}
		smAgeRangeService.save(smAgeRange);
		addMessage(redirectAttributes, "保存年龄段成功");
		return "redirect:"+Global.getAdminPath()+"/config/smAgeRange/?repage";
	}
	
	@RequiresPermissions("config:smAgeRange:edit")
	@RequestMapping(value = "delete")
	public String delete(SmAgeRange smAgeRange, RedirectAttributes redirectAttributes) {
		smAgeRangeService.delete(smAgeRange);
		addMessage(redirectAttributes, "删除年龄段成功");
		return "redirect:"+Global.getAdminPath()+"/config/smAgeRange/?repage";
	}

}