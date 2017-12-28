/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.web;

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
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;
import com.thinkgem.jeesite.modules.basic.service.SmIllreasonService;
import com.thinkgem.jeesite.modules.basic.service.SmSymptomService;

/**
 * 病因管理Controller
 * @author louis
 * @version 2016-11-16
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/smIllreason")
public class SmIllreasonController extends BaseController {

	@Autowired
	private SmIllreasonService smIllreasonService;
	
	@Autowired
	private SmSymptomService smSymptomService;
	
	@ModelAttribute
	public SmIllreason get(@RequestParam(required=false) String id) {
		SmIllreason entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smIllreasonService.get(id);
		}
		if (entity == null){
			entity = new SmIllreason();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:smIllreason:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmIllreason smIllreason, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmIllreason> page = smIllreasonService.findPage(new Page<SmIllreason>(request, response), smIllreason); 
		model.addAttribute("page", page);
		return "modules/basic/smIllreasonList";
	}
	
	@RequiresPermissions("basic:smIllreason:view")
	@RequestMapping(value = {"listAll"})
	public String listAll(SmIllreason smIllreason, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmIllreason> page = smIllreasonService.findPage(new Page<SmIllreason>(request, response), smIllreason); 
		List<SmSymptom> list= smSymptomService.findAll(new SmSymptom());
		model.addAttribute("sympList", list);
		model.addAttribute("page", page);
		return "modules/basic/smIllreasonAllList";
	}

	@RequiresPermissions("basic:smIllreason:view")
	@RequestMapping(value = "form")
	public String form(SmIllreason smIllreason, Model model) {
		model.addAttribute("smIllreason", smIllreason);
		return "modules/basic/smIllreasonForm";
	}

	@RequiresPermissions("basic:smIllreason:edit")
	@RequestMapping(value = "save")
	public String save(SmIllreason smIllreason, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smIllreason)){
			return form(smIllreason, model);
		}
		smIllreasonService.save(smIllreason);
		addMessage(redirectAttributes, "保存病因管理成功");
		return "redirect:"+Global.getAdminPath()+"/basic/smIllreason/?repage&sym.id="+smIllreason.getSym().getId();
	}
	
	@RequiresPermissions("basic:smIllreason:edit")
	@RequestMapping(value = "delete")
	public String delete(SmIllreason smIllreason, RedirectAttributes redirectAttributes) {
		smIllreasonService.delete(smIllreason);
		addMessage(redirectAttributes, "删除病因管理成功");
		return "redirect:"+Global.getAdminPath()+"/basic/smIllreason/?repage&sym.id="+smIllreason.getSym().getId();
	}

}