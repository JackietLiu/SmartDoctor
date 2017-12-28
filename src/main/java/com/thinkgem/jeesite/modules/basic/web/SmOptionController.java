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

import com.thinkgem.jeesite.common.autocom.AjaxUtil;
import com.thinkgem.jeesite.common.autocom.ListResult;
import com.thinkgem.jeesite.common.autocom.ResultMgr;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import com.thinkgem.jeesite.modules.basic.service.SmOptionService;

/**
 * 问题选项Controller
 * @author louis
 * @version 2016-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/smOption")
public class SmOptionController extends BaseController {

	@Autowired
	private SmOptionService smOptionService;
	
	@ModelAttribute
	public SmOption get(@RequestParam(required=false) String id) {
		SmOption entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smOptionService.get(id);
		}
		if (entity == null){
			entity = new SmOption();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:smOption:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmOption smOption, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmOption> page = smOptionService.findPage(new Page<SmOption>(request, response), smOption); 
		model.addAttribute("page", page);
		model.addAttribute("questionId", smOption.getQuestion().getId());
		return "modules/basic/smOptionList";
	}

	@RequiresPermissions("basic:smOption:view")
	@RequestMapping(value = "form")
	public String form(SmOption smOption, Model model) {
		model.addAttribute("smOption", smOption);
		return "modules/basic/smOptionForm";
	}

	@RequiresPermissions("basic:smOption:edit")
	@RequestMapping(value = "save")
	public String save(SmOption smOption, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smOption)){
			return form(smOption, model);
		}
		smOptionService.save(smOption);
		addMessage(redirectAttributes, "保存问题选项成功");
		return "redirect:"+Global.getAdminPath()+"/basic/smOption/?repage&question.id="+smOption.getQuestion().getId();
	}
	
	@RequiresPermissions("basic:smOption:edit")
	@RequestMapping(value = "delete")
	public String delete(SmOption smOption, RedirectAttributes redirectAttributes) {
		smOptionService.delete(smOption);
		addMessage(redirectAttributes, "删除问题选项成功");
		return "redirect:"+Global.getAdminPath()+"/basic/smOption/?repage&question.id="+smOption.getQuestion().getId();
	}
	
	@RequestMapping(value = {"getOptList"})
	public void getOptList(HttpServletRequest request, HttpServletResponse response, Model model) {
		try
        {
			String quesid = request.getParameter("quesid");
			SmOption smOption = new SmOption();
			SmQuestion ques = new SmQuestion();
			ques.setId(quesid);
			smOption.setQuestion(ques);
			smOption.setQyStatus("1");
			List<SmOption> items= smOptionService.findList(smOption);
			ListResult lr = new ListResult();
	   		lr.setSuccess(true);
	   		lr.setItems(items);
	   	    ResultMgr rtMgr = new ResultMgr(lr, "json");
	   	    String re = rtMgr.convertResult();
            // 反馈到客户端
            AjaxUtil.sendResponseJsonText(response, re);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
	}

}