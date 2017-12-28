/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.smrelation.web;

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
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;
import com.thinkgem.jeesite.modules.basic.service.SmIllreasonService;
import com.thinkgem.jeesite.modules.basic.service.SmQuestionService;
import com.thinkgem.jeesite.modules.basic.service.SmSymptomService;
import com.thinkgem.jeesite.modules.config.entity.SmSymQues;
import com.thinkgem.jeesite.modules.config.service.SmSymQuesService;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryQues;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryQuesService;
import com.thinkgem.jeesite.modules.smrelation.entity.SmRelation;
import com.thinkgem.jeesite.modules.smrelation.service.SmRelationService;

/**
 * 配置病因Controller
 * @author JackietLiu
 * @version 2017-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/smrelation/smRelation")
public class SmRelationController extends BaseController {

	@Autowired
	private SmRelationService smRelationService;
	@Autowired
	private SmIllreasonService smIllreasonService;
	@Autowired
	private SmQuestionService smQuestionService;
	@Autowired
	private SmSymptomService smSymptomService;
	@Autowired
	private SmSymQuesService smSymQuesService;
	@Autowired
	private SmInquiryQuesService smInquiryQuesService;
	
	@ModelAttribute
	public SmRelation get(@RequestParam(required=false) String id) {
		SmRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smRelationService.get(id);
		}
		if (entity == null){
			entity = new SmRelation();
		}
		return entity;
	}
	
	@RequiresPermissions("smrelation:smRelation:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmRelation smRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
	    Page<SmRelation> page = smRelationService.findPage(new Page<SmRelation>(request, response), smRelation); 
		model.addAttribute("page", page);
		return "modules/smrelation/smRelationList";
	}

	@RequiresPermissions("smrelation:smRelation:view")
	@RequestMapping(value = "form")
	public String form(SmRelation smRelation, Model model) {
		model.addAttribute("smRelation", smRelation);
		SmQuestion smQuestion = new SmQuestion();
		SmIllreason smIllreason = new SmIllreason();
		SmSymptom symptom = new SmSymptom();
		List<SmQuestion> quesList = smQuestionService.findList(smQuestion);
		List<SmIllreason> reasonsList = smIllreasonService.findList(smIllreason);
		List<SmSymptom> symptomsList = smSymptomService.findList(symptom);
		model.addAttribute("quesList", quesList);
		model.addAttribute("reasonsList", reasonsList);
		model.addAttribute("symptomsList", symptomsList);

		
		return "modules/smrelation/smRelationForm";
	}

	@RequiresPermissions("smrelation:smRelation:edit")
	@RequestMapping(value = "save")
	public String save(SmRelation smRelation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smRelation)){
			return form(smRelation, model);
		}
	/*	String sqId = smRelation.getSmSymQues().getId();
		System.out.println(sqId);
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
			smRelation.setSymptom(smSymQues.getSym());
			smRelation.setQuestion(smSymQues.getQuestion());
		}*/
		SmInquiryQues smInquiryQues = new SmInquiryQues();
		smInquiryQues.setIllrea(smRelation.getIllreaon());
		
		
		smRelationService.save(smRelation);
		addMessage(redirectAttributes, "保存病因关系成功");
		return "redirect:"+Global.getAdminPath()+"/smrelation/smRelation/?repage";
	}
	
	@RequiresPermissions("smrelation:smRelation:edit")
	@RequestMapping(value = "delete")
	public String delete(SmRelation smRelation, RedirectAttributes redirectAttributes) {
		//String sqId = smRelation.getSmSymQues().getId();
		smRelationService.delete(smRelation);
		addMessage(redirectAttributes, "删除病因关系成功");
		return "redirect:"+Global.getAdminPath()+"/smrelation/smRelation/?repage";
	}

}