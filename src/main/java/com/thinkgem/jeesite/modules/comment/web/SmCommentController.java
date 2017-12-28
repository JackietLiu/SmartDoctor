/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.comment.web;

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
import com.thinkgem.jeesite.modules.comment.entity.SmComment;
import com.thinkgem.jeesite.modules.comment.service.SmCommentService;

/**
 * 专家点评Controller
 * @author louis
 * @version 2016-11-18
 */
@Controller
@RequestMapping(value = "${adminPath}/comment/smComment")
public class SmCommentController extends BaseController {

	@Autowired
	private SmCommentService smCommentService;
	
	@ModelAttribute
	public SmComment get(@RequestParam(required=false) String id) {
		SmComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smCommentService.get(id);
		}
		if (entity == null){
			entity = new SmComment();
		}
		return entity;
	}
	
	@RequiresPermissions("comment:smComment:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmComment smComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmComment> page = smCommentService.findPage(new Page<SmComment>(request, response), smComment); 
		model.addAttribute("page", page);
		return "modules/comment/smCommentList";
	}

	@RequiresPermissions("comment:smComment:view")
	@RequestMapping(value = "form")
	public String form(SmComment smComment, Model model) {
		model.addAttribute("smComment", smComment);
		return "modules/comment/smCommentForm";
	}

	@RequiresPermissions("comment:smComment:edit")
	@RequestMapping(value = "save")
	public String save(SmComment smComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smComment)){
			return form(smComment, model);
		}
		smCommentService.save(smComment);
		addMessage(redirectAttributes, "保存专家点评成功");
		return "redirect:"+Global.getAdminPath()+"/comment/smComment/?repage&match.id="+smComment.getMatch().getId();
	}
	
	@RequiresPermissions("comment:smComment:edit")
	@RequestMapping(value = "delete")
	public String delete(SmComment smComment, RedirectAttributes redirectAttributes) {
		smCommentService.delete(smComment);
		addMessage(redirectAttributes, "删除专家点评成功");
		return "redirect:"+Global.getAdminPath()+"/comment/smComment/?repage&match.id="+smComment.getMatch().getId();
	}

}