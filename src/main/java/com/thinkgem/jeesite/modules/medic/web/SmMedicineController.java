/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.medic.web;

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
import com.thinkgem.jeesite.modules.medic.entity.SmMedicine;
import com.thinkgem.jeesite.modules.medic.service.SmMedicineService;

/**
 * 药品表Controller
 * @author JackietLiu
 * @version 2017-07-20
 */
@Controller
@RequestMapping(value = "${adminPath}/medic/smMedicine")
public class SmMedicineController extends BaseController {

	@Autowired
	private SmMedicineService smMedicineService;
	
	@ModelAttribute
	public SmMedicine get(@RequestParam(required=false) String id) {
		SmMedicine entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smMedicineService.get(id);
		}
		if (entity == null){
			entity = new SmMedicine();
		}
		return entity;
	}
	
	@RequiresPermissions("medic:smMedicine:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmMedicine smMedicine, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmMedicine> page = smMedicineService.findPage(new Page<SmMedicine>(request, response), smMedicine); 
		model.addAttribute("page", page);
		return "modules/medic/smMedicineList";
	}

	@RequiresPermissions("medic:smMedicine:view")
	@RequestMapping(value = "form")
	public String form(SmMedicine smMedicine, Model model) {
		model.addAttribute("smMedicine", smMedicine);
		return "modules/medic/smMedicineForm";
	}

	@RequiresPermissions("medic:smMedicine:edit")
	@RequestMapping(value = "save")
	public String save(SmMedicine smMedicine, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smMedicine)){
			return form(smMedicine, model);
		}
		if(smMedicine.getDoseUnit() == null || "".equals(smMedicine.getDoseUnit())) {
			smMedicine.setDoseUnit("/");//剂量单位‘mg/d’
		}
		if(smMedicine.getMedication() == null || "".equals(smMedicine.getMedication())) {
			smMedicine.setMedication("/");//服药
		}
		if(smMedicine.getMedicationUnit() == null || "".equals(smMedicine.getMedicationUnit())) {
			smMedicine.setMedicationUnit("/");//服药单位‘次/d’
		}
		smMedicineService.save(smMedicine);
		addMessage(redirectAttributes, "保存药品成功");
		return "redirect:"+Global.getAdminPath()+"/medic/smMedicine/?repage";
	}
	
	@RequiresPermissions("medic:smMedicine:edit")
	@RequestMapping(value = "delete")
	public String delete(SmMedicine smMedicine, RedirectAttributes redirectAttributes) {
		smMedicineService.delete(smMedicine);
		addMessage(redirectAttributes, "删除药品成功");
		return "redirect:"+Global.getAdminPath()+"/medic/smMedicine/?repage";
	}

}