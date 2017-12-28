/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.match.web;

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
import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import com.thinkgem.jeesite.modules.basic.service.SmIllreasonService;
import com.thinkgem.jeesite.modules.basic.service.SmOptionService;
import com.thinkgem.jeesite.modules.config.entity.SmSymQues;
import com.thinkgem.jeesite.modules.config.service.SmSymQuesService;
import com.thinkgem.jeesite.modules.match.entity.SmIllreasonMatch;
import com.thinkgem.jeesite.modules.match.entity.SmMatchOpt;
import com.thinkgem.jeesite.modules.match.service.SmIllreasonMatchService;
import com.thinkgem.jeesite.modules.match.service.SmMatchOptService;

/**
 * 方案配置选项Controller
 * @author louis
 * @version 2016-11-17
 */
@Controller
@RequestMapping(value = "${adminPath}/match/smMatchOpt")
public class SmMatchOptController extends BaseController {

	@Autowired
	private SmMatchOptService smMatchOptService;
	
	@Autowired
	private SmIllreasonMatchService smIllreasonMatchService;
	
	@Autowired
	private SmIllreasonService smIllreasonService;
	
	@Autowired
	private SmSymQuesService smSymQuesService;
	
	@Autowired
	private SmOptionService smOptionService;
	
	@ModelAttribute
	public SmMatchOpt get(@RequestParam(required=false) String id) {
		SmMatchOpt entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smMatchOptService.get(id);
		}
		if (entity == null){
			entity = new SmMatchOpt();
		}
		return entity;
	}
	
	@RequiresPermissions("match:smMatchOpt:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmMatchOpt smMatchOpt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmMatchOpt> page = smMatchOptService.findPage(new Page<SmMatchOpt>(request, response), smMatchOpt); 
		model.addAttribute("page", page);
		return "modules/match/smMatchOptList";
	}

	@RequiresPermissions("match:smMatchOpt:view")
	@RequestMapping(value = "form")
	public String form(SmMatchOpt smMatchOpt, Model model) {
		
		List<SmSymQues> symQuesList = null ;
		//查询方案-查询病因-找到症状ID，通过症状ID查询问题列表
		if(StringUtils.isNotBlank(smMatchOpt.getMatc().getId())){
			SmIllreasonMatch match=	smIllreasonMatchService.get(smMatchOpt.getMatc().getId());
			if(null!=match&&null!=match.getIllrea()&&StringUtils.isNoneBlank(match.getIllrea().getId())){
				SmIllreason illReaseon = smIllreasonService.get(match.getIllrea().getId());
				if(null!=illReaseon){
					SmSymQues smSymQues = new SmSymQues();
					smSymQues.setSym(illReaseon.getSym());
					smSymQues.setQyStatus("1");//启用的问题
					symQuesList = smSymQuesService.findList(smSymQues);
				}
			}
		}
		if(!smMatchOpt.getIsNewRecord()){//修改时，option列表需要传递过去
			if(smMatchOpt.getQues()!=null&&StringUtils.isNoneBlank(smMatchOpt.getQues().getId())){
				SmOption smOption = new SmOption();
				smOption.setQuestion(smMatchOpt.getQues());
				smOption.setQyStatus("1");
				List<SmOption> items= smOptionService.findList(smOption);
				model.addAttribute("optionList", items);
			}
		}
		model.addAttribute("symQuesList", symQuesList);
		model.addAttribute("smMatchOpt", smMatchOpt);
		return "modules/match/smMatchOptForm";
	}

	@RequiresPermissions("match:smMatchOpt:edit")
	@RequestMapping(value = "save")
	public String save(SmMatchOpt smMatchOpt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smMatchOpt)){
			return form(smMatchOpt, model);
		}
		if(smMatchOpt.getIsNewRecord()){//新增时
			//查询方案-查询病因-找到症状ID
			if(StringUtils.isNotBlank(smMatchOpt.getMatc().getId())){
				SmIllreasonMatch match=	smIllreasonMatchService.get(smMatchOpt.getMatc().getId());
				if(null!=match&&null!=match.getIllrea()&&StringUtils.isNoneBlank(match.getIllrea().getId())){
					SmIllreason illReaseon = smIllreasonService.get(match.getIllrea().getId());
					if(null!=illReaseon){
						smMatchOpt.setSym(illReaseon.getSym());//保存症状ID
					}
				}
			}
		}
		//查询选择的问题是不是已经被选择过了
		List<SmMatchOpt> list = smMatchOptService.findExistQuestList(smMatchOpt);
		if(list!=null&&list.size()>0){
			addMessage(redirectAttributes, "配置失败，【"+list.get(0).getQues().getName()+"】已经配置过");
		}else{
			smMatchOptService.save(smMatchOpt);
			addMessage(redirectAttributes, "保存方案配置选项成功");
		}
		return "redirect:"+Global.getAdminPath()+"/match/smMatchOpt/?repage&matc.id="+smMatchOpt.getMatc().getId();
	}
	
	@RequiresPermissions("match:smMatchOpt:edit")
	@RequestMapping(value = "delete")
	public String delete(SmMatchOpt smMatchOpt, RedirectAttributes redirectAttributes) {
		smMatchOptService.delete(smMatchOpt);
		addMessage(redirectAttributes, "删除方案配置选项成功");
		return "redirect:"+Global.getAdminPath()+"/match/smMatchOpt/?repage&matc.id="+smMatchOpt.getMatc().getId();
	}

}