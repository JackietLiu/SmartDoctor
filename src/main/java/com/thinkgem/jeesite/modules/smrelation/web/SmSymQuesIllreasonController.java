/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smrelation.web;

import java.util.ArrayList;
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
import com.thinkgem.jeesite.modules.smrelation.entity.SmSymQuesIllreason;
import com.thinkgem.jeesite.modules.smrelation.service.SmSymQuesIllreasonService;

/**
 * 配置病因Controller
 * @author louis
 * @version 2017-03-24
 */
@Controller
@RequestMapping(value = "${adminPath}/smrelation/smSymQuesIllreason")
public class SmSymQuesIllreasonController extends BaseController {

	@Autowired
	private SmSymQuesIllreasonService smSymQuesIllreasonService;
	
	@Autowired
	private SmSymQuesService smSymQuesService;
	
	@Autowired
	private SmOptionService smOptionService;
	@Autowired
	private SmIllreasonService smIllreasonService;
	
	@ModelAttribute
	public SmSymQuesIllreason get(@RequestParam(required=false) String id) {
		SmSymQuesIllreason entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smSymQuesIllreasonService.get(id);
		}
		if (entity == null){
			entity = new SmSymQuesIllreason();
		}
		return entity;
	}
	
	@RequiresPermissions("smrelation:smSymQuesIllreason:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmSymQuesIllreason smSymQuesIllreason, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmSymQuesIllreason> page = smSymQuesIllreasonService.findPage(new Page<SmSymQuesIllreason>(request, response), smSymQuesIllreason); 
		model.addAttribute("page", page);
		model.addAttribute("smSymQuesIllreason", smSymQuesIllreason);
		return "modules/smrelation/smSymQuesIllreasonList";
	}

	@RequiresPermissions("smrelation:smSymQuesIllreason:view")
	@RequestMapping(value = "form")
	public String form(SmSymQuesIllreason smSymQuesIllreason, Model model) {
		String sqId = smSymQuesIllreason.getSmSymQues().getId();
		SmSymQues smSymQues = smSymQuesService.get(sqId);//最后一题配置
		//查询问题选项
		List<SmOption> items= new ArrayList<SmOption>();
		if(null!=smSymQues){
			String quesid = smSymQues.getQuestion().getId();
			if(StringUtils.isNotBlank(quesid)){
				SmOption smOption = new SmOption();
				SmQuestion ques = new SmQuestion();
				ques.setId(quesid);
				smOption.setQuestion(ques);
				smOption.setQyStatus("1");
				items= smOptionService.findList(smOption);
			}
		}
		model.addAttribute("optList", items);//选项list
		//所有病因列表
		SmIllreason r = new SmIllreason();
		r.setQyStatus("1");
		List<SmIllreason> lis2t = smIllreasonService.findList(r);
		model.addAttribute("illreasonList", lis2t);
		model.addAttribute("smSymQuesIllreason", smSymQuesIllreason);
		return "modules/smrelation/smSymQuesIllreasonForm";
	}

	@RequiresPermissions("smrelation:smSymQuesIllreason:edit")
	@RequestMapping(value = "save")
	public String save(SmSymQuesIllreason smSymQuesIllreason, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smSymQuesIllreason)){
			return form(smSymQuesIllreason, model);
		}
		String sqId = smSymQuesIllreason.getSmSymQues().getId();
		SmSymQues smSymQues = smSymQuesService.get(sqId);//最后一题配置
		if(null!=smSymQues){
			SmSymQues sq = new SmSymQues();
			sq.setState(smSymQues.getState());
			sq.setSym(smSymQues.getSym());
			sq.setDeptFid(smSymQues.getDeptPid());
			sq.setQyStatus("1");
			if(2==smSymQues.getState()){
				sq.setIllreason(smSymQues.getIllreason());
			}
/*			List<SmSymQues> list = smSymQuesService.findList(sq);
			if(list!=null&&list.size()>0){
				addMessage(redirectAttributes, "不能配置，只有最后一题才能配置病因");
				return "redirect:"+Global.getAdminPath()+"/smrelation/smSymQuesIllreason/form?smSymQues.id="+sqId;
			}*/ 
			smSymQuesIllreason.setSymptom(smSymQues.getSym());
			smSymQuesIllreason.setQuestion(smSymQues.getQuestion());
		}
		smSymQuesIllreasonService.save(smSymQuesIllreason);
		addMessage(redirectAttributes, "保存配置病因成功");
		return "redirect:"+Global.getAdminPath()+"/smrelation/smSymQuesIllreason/?repage&smSymQues.id="+sqId;
	}
	
	@RequiresPermissions("smrelation:smSymQuesIllreason:edit")
	@RequestMapping(value = "delete")
	public String delete(SmSymQuesIllreason smSymQuesIllreason, RedirectAttributes redirectAttributes) {
		String sqId = smSymQuesIllreason.getSmSymQues().getId();
		smSymQuesIllreasonService.delete(smSymQuesIllreason);
		addMessage(redirectAttributes, "删除配置病因成功");
		return "redirect:"+Global.getAdminPath()+"/smrelation/smSymQuesIllreason/?repage&smSymQues.id="+sqId;
	}

}