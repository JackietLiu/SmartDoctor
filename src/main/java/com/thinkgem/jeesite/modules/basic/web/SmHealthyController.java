/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.web;

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
import com.thinkgem.jeesite.modules.basic.entity.SmHealthy;
import com.thinkgem.jeesite.modules.basic.service.SmHealthyService;

/**
 * 健康处方Controller
 * @author JackietLiu
 * @version 2017-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/smHealthy")
public class SmHealthyController extends BaseController {

	@Autowired
	private SmHealthyService smHealthyService;
	
	@ModelAttribute
	public SmHealthy get(@RequestParam(required=false) String id) {
		SmHealthy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smHealthyService.get(id);
		}
		if (entity == null){
			entity = new SmHealthy();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:smHealthy:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmHealthy smHealthy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmHealthy> page = smHealthyService.findPage(new Page<SmHealthy>(request, response), smHealthy); 
		model.addAttribute("page", page);
		return "modules/basic/smHealthyList";
	}

	@RequiresPermissions("basic:smHealthy:view")
	@RequestMapping(value = "form")
	public String form(SmHealthy smHealthy, Model model) {
		model.addAttribute("smHealthy", smHealthy);
		return "modules/basic/smHealthyForm";
	}

	@RequiresPermissions("basic:smHealthy:edit")
	@RequestMapping(value = "save")
	public String save(SmHealthy smHealthy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smHealthy)){
			return form(smHealthy, model);
		}
		smHealthyService.save(smHealthy);
		addMessage(redirectAttributes, "保存健康处方成功");
		return "redirect:"+Global.getAdminPath()+"/basic/smHealthy/?repage";
	}
	
	@RequiresPermissions("basic:smHealthy:edit")
	@RequestMapping(value = "delete")
	public String delete(SmHealthy smHealthy, RedirectAttributes redirectAttributes) {
		smHealthyService.delete(smHealthy);
		addMessage(redirectAttributes, "删除健康处方成功");
		return "redirect:"+Global.getAdminPath()+"/basic/smHealthy/?repage";
	}

}