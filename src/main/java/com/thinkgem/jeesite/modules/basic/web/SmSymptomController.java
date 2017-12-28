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
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;
import com.thinkgem.jeesite.modules.basic.service.SmSymptomService;

/**
 * 病症管理Controller
 * @author louis
 * @version 2016-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/smSymptom")
public class SmSymptomController extends BaseController {

	@Autowired
	private SmSymptomService smSymptomService;
	
	@ModelAttribute
	public SmSymptom get(@RequestParam(required=false) String id) {
		SmSymptom entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smSymptomService.get(id);
		}
		if (entity == null){
			entity = new SmSymptom();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:smSymptom:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmSymptom smSymptom, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmSymptom> page = smSymptomService.findPage(new Page<SmSymptom>(request, response), smSymptom); 
		model.addAttribute("page", page);
		return "modules/basic/smSymptomList";
	}

	@RequiresPermissions("basic:smSymptom:view")
	@RequestMapping(value = "form")
	public String form(SmSymptom smSymptom, Model model) {
		model.addAttribute("smSymptom", smSymptom);
		return "modules/basic/smSymptomForm";
	}

	@RequiresPermissions("basic:smSymptom:edit")
	@RequestMapping(value = "save")
	public String save(SmSymptom smSymptom, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smSymptom)){
			return form(smSymptom, model);
		}
		smSymptomService.save(smSymptom);
		addMessage(redirectAttributes, "保存病症管理成功");
		return "redirect:"+Global.getAdminPath()+"/basic/smSymptom/?repage";
	}
	
	@RequiresPermissions("basic:smSymptom:edit")
	@RequestMapping(value = "delete")
	public String delete(SmSymptom smSymptom, RedirectAttributes redirectAttributes) {
		smSymptomService.delete(smSymptom);
		addMessage(redirectAttributes, "删除病症管理成功");
		return "redirect:"+Global.getAdminPath()+"/basic/smSymptom/?repage";
	}

}