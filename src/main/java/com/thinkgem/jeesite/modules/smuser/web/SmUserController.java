/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smuser.web;

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
import com.thinkgem.jeesite.modules.smuser.entity.SmUser;
import com.thinkgem.jeesite.modules.smuser.service.SmUserService;

/**
 * 模拟患者表Controller
 * @author Jackiet
 * @version 2017-04-13
 */
@Controller
@RequestMapping(value = "${adminPath}/smuser/smUser")
public class SmUserController extends BaseController {

	@Autowired
	private SmUserService smUserService;
	
	@ModelAttribute
	public SmUser get(@RequestParam(required=false) String id) {
		SmUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smUserService.get(id);
		}
		if (entity == null){
			entity = new SmUser();
		}
		return entity;
	}
	
	@RequiresPermissions("smuser:smUser:view")
	@RequestMapping(value = "list")
	public String list(String cardNo,SmUser smUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		//Page<SmUser> page = smUserService.findPage(new Page<SmUser>(request, response), smUser); 
		model.addAttribute("cardNo", cardNo);
		smUser.setCardNo(cardNo);
		List<SmUser> list = smUserService.findList(smUser);
		model.addAttribute("list", list);
		return "modules/smuser/smUserList";
	}

	@RequiresPermissions("smuser:smUser:view")
	@RequestMapping(value = "form")
	public String form(SmUser smUser, Model model) {
		model.addAttribute("smUser", smUser);
		return "modules/smuser/smUserForm";
	}

	@RequiresPermissions("smuser:smUser:edit")
	@RequestMapping(value = "save")
	public String save(SmUser smUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smUser)){
			return form(smUser, model);
		}
		smUserService.save(smUser);
		addMessage(redirectAttributes, "保存患者成功");
		return "redirect:"+Global.getAdminPath()+"/smuser/smUser/?repage";
	}
	
	@RequiresPermissions("smuser:smUser:edit")
	@RequestMapping(value = "delete")
	public String delete(SmUser smUser, RedirectAttributes redirectAttributes) {
		smUserService.delete(smUser);
		addMessage(redirectAttributes, "删除患者成功");
		return "redirect:"+Global.getAdminPath()+"/smuser/smUser/?repage";
	}

}