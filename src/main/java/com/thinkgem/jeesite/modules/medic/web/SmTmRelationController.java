/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.medic.web;

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
import com.thinkgem.jeesite.modules.medic.entity.SmMedicine;
import com.thinkgem.jeesite.modules.medic.entity.SmMedicineType;
import com.thinkgem.jeesite.modules.medic.entity.SmTmRelation;
import com.thinkgem.jeesite.modules.medic.service.SmMedicineService;
import com.thinkgem.jeesite.modules.medic.service.SmMedicineTypeService;
import com.thinkgem.jeesite.modules.medic.service.SmTmRelationService;


/**
 * 药品和类别关系Controller
 * @author JackietLiu
 * @version 2017-07-20
 */
@Controller
@RequestMapping(value = "${adminPath}/medic/smTmRelation")
public class SmTmRelationController extends BaseController {

	@Autowired
	private SmTmRelationService smTmRelationService;
	@Autowired
	private SmMedicineService smMedicineService;
	@Autowired
	private SmMedicineTypeService smMedicineTypeService;
	
	@ModelAttribute
	public SmTmRelation get(@RequestParam(required=false) String id) {
		SmTmRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smTmRelationService.get(id);
		}
		if (entity == null){
			entity = new SmTmRelation();
		}
		return entity;
	}
	
	@RequiresPermissions("medic:smTmRelation:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmTmRelation smTmRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmTmRelation> page = smTmRelationService.findPage(new Page<SmTmRelation>(request, response), smTmRelation); 
		model.addAttribute("page", page);
		return "modules/medic/smTmRelationList";
	}
	@RequiresPermissions("medic:smTmRelation:view")
	@RequestMapping(value = "list2")
	public String list2(SmTmRelation smTmRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		//Page<SmTmRelation> page = smTmRelationService.findPage(new Page<SmTmRelation>(request, response), smTmRelation); 
		
		List<SmTmRelation> list = smTmRelationService.findList(smTmRelation);
	    model.addAttribute("list", list);
		return "modules/medic/smTmRelationList2";
	}
	@RequiresPermissions("medic:smTmRelation:view")
	@RequestMapping(value = "form")
	public String form(SmTmRelation smTmRelation, Model model) {
		SmMedicine smMedicine = new SmMedicine();
		SmMedicineType smMedicineType = new SmMedicineType();
		List<SmMedicine> medList = smMedicineService.findList(smMedicine);
		List<SmMedicineType> typeList = smMedicineTypeService.findList(smMedicineType);
		
		
		model.addAttribute("smTmRelation", smTmRelation);
		model.addAttribute("typeList", typeList);
		model.addAttribute("medList", medList);
		return "modules/medic/smTmRelationForm";
	}

	@RequiresPermissions("medic:smTmRelation:edit")
	@RequestMapping(value = "save")
	public String save(SmTmRelation smTmRelation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smTmRelation)){
			return form(smTmRelation, model);
		}
		smTmRelationService.save(smTmRelation);
		addMessage(redirectAttributes, "保存关系成功");
		return "redirect:"+Global.getAdminPath()+"/medic/smTmRelation/?repage";
	}
	
	@RequiresPermissions("medic:smTmRelation:edit")
	@RequestMapping(value = "delete")
	public String delete(SmTmRelation smTmRelation, RedirectAttributes redirectAttributes) {
		smTmRelationService.delete(smTmRelation);
		addMessage(redirectAttributes, "删除关系成功");
		return "redirect:"+Global.getAdminPath()+"/medic/smTmRelation/?repage";
	}

}