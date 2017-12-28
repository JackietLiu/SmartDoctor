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
import com.thinkgem.jeesite.modules.advice.service.SmAdviseService;
import com.thinkgem.jeesite.modules.basic.entity.SmHealthy;
import com.thinkgem.jeesite.modules.basic.service.SmHealthyService;
import com.thinkgem.jeesite.modules.medic.entity.SmMedicine;
import com.thinkgem.jeesite.modules.medic.service.SmMedicineService;

/**
 * 治疗建议Controller
 * @author louis
 * @version 2016-11-16
 */
@Controller
@RequestMapping(value = "${adminPath}/advice/smAdvise")
public class SmAdviseController extends BaseController {

	@Autowired
	private SmAdviseService smAdviseService;
	
	@Autowired
	private SmMedicineService smMedicineService;
	
	@Autowired
	private SmHealthyService smHealthyService;
	
	@ModelAttribute
	public SmAdvise get(@RequestParam(required=false) String id) {
		SmAdvise entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smAdviseService.get(id);
		}
		if (entity == null){
			entity = new SmAdvise();
		}
		return entity;
	}
	
	@RequiresPermissions("advice:smAdvise:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmAdvise smAdvise, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmAdvise> page = smAdviseService.findPage(new Page<SmAdvise>(request, response), smAdvise); 
		model.addAttribute("page", page);
		return "modules/advice/smAdviseList";
	}

	@RequiresPermissions("advice:smAdvise:view")
	@RequestMapping(value = "form")
	public String form(SmAdvise smAdvise, Model model) {
        SmMedicine smMedicine = new SmMedicine();        
		List<SmMedicine> medList = smMedicineService.findList(smMedicine);
		SmHealthy smHealthy = new SmHealthy();
		List<SmHealthy> heathList = smHealthyService.findList(smHealthy);
		
		model.addAttribute("smAdvise", smAdvise);	
		model.addAttribute("medList", medList);	
		model.addAttribute("heathList", heathList);	
		return "modules/advice/smAdviseForm";
	}

	@RequiresPermissions("advice:smAdvise:edit")
	@RequestMapping(value = "save")
	public String save(SmAdvise smAdvise, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smAdvise)){
			return form(smAdvise, model);
		}
		smAdviseService.save(smAdvise);
		addMessage(redirectAttributes, "保存治疗建议成功");
		return "redirect:"+Global.getAdminPath()+"/advice/smAdvise/?repage";
	}
	
	@RequiresPermissions("advice:smAdvise:edit")
	@RequestMapping(value = "delete")
	public String delete(SmAdvise smAdvise, RedirectAttributes redirectAttributes) {
		smAdviseService.delete(smAdvise);
		addMessage(redirectAttributes, "删除治疗建议成功");
		return "redirect:"+Global.getAdminPath()+"/advice/smAdvise/?repage";
	}

}