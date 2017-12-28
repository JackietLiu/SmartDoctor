/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.match.web;

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
import com.thinkgem.jeesite.modules.match.entity.SmIllreasonMatch;
import com.thinkgem.jeesite.modules.match.service.SmIllreasonMatchService;

/**
 * 病因方案Controller
 * @author louis
 * @version 2016-11-17
 */
@Controller
@RequestMapping(value = "${adminPath}/match/smIllreasonMatch")
public class SmIllreasonMatchController extends BaseController {

	@Autowired
	private SmIllreasonMatchService smIllreasonMatchService;
	
	@ModelAttribute
	public SmIllreasonMatch get(@RequestParam(required=false) String id) {
		SmIllreasonMatch entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smIllreasonMatchService.get(id);
		}
		if (entity == null){
			entity = new SmIllreasonMatch();
		}
		return entity;
	}
	
	@RequiresPermissions("match:smIllreasonMatch:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmIllreasonMatch smIllreasonMatch, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmIllreasonMatch> page = smIllreasonMatchService.findPage(new Page<SmIllreasonMatch>(request, response), smIllreasonMatch); 
		model.addAttribute("page", page);
		return "modules/match/smIllreasonMatchList";
	}

	@RequiresPermissions("match:smIllreasonMatch:view")
	@RequestMapping(value = "form")
	public String form(SmIllreasonMatch smIllreasonMatch, Model model) {
		model.addAttribute("smIllreasonMatch", smIllreasonMatch);
		return "modules/match/smIllreasonMatchForm";
	}

	@RequiresPermissions("match:smIllreasonMatch:edit")
	@RequestMapping(value = "save")
	public String save(SmIllreasonMatch smIllreasonMatch, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smIllreasonMatch)){
			return form(smIllreasonMatch, model);
		}
		smIllreasonMatchService.save(smIllreasonMatch);
		addMessage(redirectAttributes, "保存病因方案成功");
		return "redirect:"+Global.getAdminPath()+"/match/smIllreasonMatch/?repage&illrea.id="+smIllreasonMatch.getIllrea().getId();
	}
	
	@RequiresPermissions("match:smIllreasonMatch:edit")
	@RequestMapping(value = "delete")
	public String delete(SmIllreasonMatch smIllreasonMatch, RedirectAttributes redirectAttributes) {
		smIllreasonMatchService.delete(smIllreasonMatch);
		addMessage(redirectAttributes, "删除病因方案成功");
		return "redirect:"+Global.getAdminPath()+"/match/smIllreasonMatch/?repage&illrea.id="+smIllreasonMatch.getIllrea().getId();
	}

}