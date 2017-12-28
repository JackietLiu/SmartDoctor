/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.medic.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.medic.entity.SmMedicineType;
import com.thinkgem.jeesite.modules.medic.service.SmMedicineTypeService;

/**
 * 药品类别Controller
 * @author JackietLiu
 * @version 2017-07-23
 */
@Controller
@RequestMapping(value = "${adminPath}/medic/smMedicineType")
public class SmMedicineTypeController extends BaseController {

	@Autowired
	private SmMedicineTypeService smMedicineTypeService;
	
	@ModelAttribute
	public SmMedicineType get(@RequestParam(required=false) String id) {
		SmMedicineType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smMedicineTypeService.get(id);
		}
		if (entity == null){
			entity = new SmMedicineType();
		}
		return entity;
	}
	
	@RequiresPermissions("medic:smMedicineType:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmMedicineType smMedicineType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SmMedicineType> list = smMedicineTypeService.findList(smMedicineType); 
		model.addAttribute("list", list);
		return "modules/medic/smMedicineTypeList";
	}

	@RequiresPermissions("medic:smMedicineType:view")
	@RequestMapping(value = "form")
	public String form(SmMedicineType smMedicineType, Model model) {
		if (smMedicineType.getParent()!=null && StringUtils.isNotBlank(smMedicineType.getParent().getId())){
			smMedicineType.setParent(smMedicineTypeService.get(smMedicineType.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(smMedicineType.getId())){
				SmMedicineType smMedicineTypeChild = new SmMedicineType();
				smMedicineTypeChild.setParent(new SmMedicineType(smMedicineType.getParent().getId()));
				List<SmMedicineType> list = smMedicineTypeService.findList(smMedicineType); 
				if (list.size() > 0){
					smMedicineType.setSort(list.get(list.size()-1).getSort());
					if (smMedicineType.getSort() != null){
						smMedicineType.setSort(smMedicineType.getSort() + 30);
					}
				}
			}
		}
		if (smMedicineType.getSort() == null){
			smMedicineType.setSort(30);
		}
		model.addAttribute("smMedicineType", smMedicineType);
		return "modules/medic/smMedicineTypeForm";
	}

	@RequiresPermissions("medic:smMedicineType:edit")
	@RequestMapping(value = "save")
	public String save(SmMedicineType smMedicineType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smMedicineType)){
			return form(smMedicineType, model);
		}
		smMedicineTypeService.save(smMedicineType);
		addMessage(redirectAttributes, "保存药品类别成功");
		return "redirect:"+Global.getAdminPath()+"/medic/smMedicineType/?repage";
	}
	
	@RequiresPermissions("medic:smMedicineType:edit")
	@RequestMapping(value = "delete")
	public String delete(SmMedicineType smMedicineType, RedirectAttributes redirectAttributes) {
		smMedicineTypeService.delete(smMedicineType);
		addMessage(redirectAttributes, "删除药品类别成功");
		return "redirect:"+Global.getAdminPath()+"/medic/smMedicineType/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SmMedicineType> list = smMedicineTypeService.findList(new SmMedicineType());
		for (int i=0; i<list.size(); i++){
			SmMedicineType e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getTypeName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}