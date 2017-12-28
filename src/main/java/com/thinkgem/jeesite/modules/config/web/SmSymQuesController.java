/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.config.web;

import java.util.Date;
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
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import com.thinkgem.jeesite.modules.basic.service.SmIllreasonService;
import com.thinkgem.jeesite.modules.basic.service.SmQuestionService;
import com.thinkgem.jeesite.modules.config.entity.SmSymQues;
import com.thinkgem.jeesite.modules.config.service.SmSymQuesService;
import com.thinkgem.jeesite.modules.smrelation.entity.SmRelation;
import com.thinkgem.jeesite.modules.smrelation.service.SmRelationService;

/**
 * 病症和问题关联表Controller
 * @author louis
 * @version 2016-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/config/smSymQues")
public class SmSymQuesController extends BaseController {

	@Autowired
	private SmSymQuesService smSymQuesService;
	
	@Autowired
	private SmQuestionService smQuestionService;
	@Autowired
	private SmIllreasonService smIllreasonService;
	@Autowired
	private SmRelationService  smRelationService;
	
	@ModelAttribute
	public SmSymQues get(@RequestParam(required=false) String id) {
		SmSymQues entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smSymQuesService.get(id);
		}
		if (entity == null){
			entity = new SmSymQues();
		}
		return entity;
	}
	
	@RequiresPermissions("config:smSymQues:view")
	@RequestMapping(value = {"list", ""})
	public String list(SmSymQues smSymQues, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmSymQues> page = smSymQuesService.findPage(new Page<SmSymQues>(request, response), smSymQues); 
		model.addAttribute("page", page);
		return "modules/config/smSymQuesList";
	}

	@RequiresPermissions("config:smSymQues:view")
	@RequestMapping(value = "form")
	public String form(SmSymQues smSymQues, Model model) {
		//所有问题列表
		SmQuestion q = new SmQuestion();
		q.setQyStatus("1");
		List<SmQuestion> list = smQuestionService.findList(q);
		//所有病因列表
		SmIllreason r = new SmIllreason();
		r.setQyStatus("1");
		List<SmIllreason> lis2t = smIllreasonService.findList(r);
		System.out.println(lis2t);
		//已经配置给病症的问题列表
		SmSymQues sq = new SmSymQues();
		sq.setSym(smSymQues.getSym());
		sq.setQyStatus("1");
		List<SmSymQues> sqList = smSymQuesService.findList(sq);
		
		model.addAttribute("questList", list);
		model.addAttribute("illreasonList", lis2t);
		model.addAttribute("useQList", sqList);
		model.addAttribute("smSymQues", smSymQues);
		return "modules/config/smSymQuesForm";
	}

	//添加第一题
	@RequiresPermissions("config:smSymQues:edit")
	@RequestMapping(value = "save")
	public String save(SmSymQues smSymQues, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, smSymQues)){
			return form(smSymQues, model);
		}
		//只能绑定一个问题
		int state = smSymQues.getState();//类型
		SmSymQues sq = null ;
		if(StringUtils.isNotBlank(state+"")){
			int s = state ;
			if(s==1) {//如果是病症配置的问题，需要判断唯一性
				sq = new SmSymQues();
				sq.setState(state);
				sq.setQyStatus(smSymQues.getQyStatus());
				sq.setSym(smSymQues.getSym());
				List<SmSymQues> list = smSymQuesService.findList(sq);
				if(list!=null&&list.size()>0){
					addMessage(redirectAttributes, "对不起！病症已经配置了第一题，不可重复配置");
					return "redirect:"+Global.getAdminPath()+"/config/smSymQues/form?sym.id="+smSymQues.getSym().getId();
				}else{
					smSymQues.setLastQuestion(new SmQuestion());
					smSymQues.setOption(new SmOption());
					smSymQues.setIllreason(smSymQues.getIllreason());
					smSymQues.setIsFirst("2");
					smSymQues.setDept(1);
					smSymQues.setDeptPid("1");
				}
				
			}else if(s==2){//如果是病因配置的问题，需要判断唯一性。
				sq = new SmSymQues();
				sq.setState(state);
				sq.setQyStatus(smSymQues.getQyStatus());
				sq.setSym(smSymQues.getSym());
				sq.setIllreason(smSymQues.getIllreason());
				sq.setPercent(smSymQues.getPercent());
				List<SmSymQues> list = smSymQuesService.findList(sq);
				if(list!=null&&list.size()>0){
					addMessage(redirectAttributes, "对不起！病因已经配置了第一题，不可重复配置");
					return "redirect:"+Global.getAdminPath()+"/config/smSymQues/form?sym.id="+smSymQues.getSym().getId();
				}else{
					smSymQues.setLastQuestion(new SmQuestion());
					smSymQues.setOption(new SmOption());
					smSymQues.setIsFirst("2");
					smSymQues.setDept(1);
					smSymQues.setDeptPid("1");
				}
			}
		}
		smSymQues.setDeptFid("0");
		smSymQuesService.save(smSymQues);
		//保存到关系表中
		SmRelation smRelation = new SmRelation();
		smRelation.setIllreaon(smSymQues.getIllreason());
		smRelation.setQuestion(smSymQues.getQuestion());
		smRelation.setPercent(smSymQues.getPercent());
		smRelation.setCreateDate(new Date());
		smRelation.setSmSymQues(smSymQues);
		smRelation.setSymptom(smSymQues.getSym());	
		smRelation.setDelFlag("0");
		smRelationService.save(smRelation);
		
		addMessage(redirectAttributes, "保存病症和问题关联表成功");
		return "redirect:"+Global.getAdminPath()+"/config/smSymQues/?repage&sym.id="+smSymQues.getSym().getId();
	}
	
	//进入页面。给选项配问题
	@RequiresPermissions("config:smSymQues:edit")
	@RequestMapping(value = "toConfigNextQues")
	public String toConfigNextQues(SmSymQues smSymQues, Model model, RedirectAttributes redirectAttributes) {
//		int state = smSymQues.getState();
		//所有问题列表
		SmQuestion q = new SmQuestion();
		q.setQyStatus("1");
		List<SmQuestion> list = smQuestionService.findList(q);
		//所有病因列表
		SmIllreason r = new SmIllreason();
		r.setQyStatus("1");
		List<SmIllreason> sirList = smIllreasonService.findList(r);
		System.out.println(sirList);
		model.addAttribute("illreasonList", sirList);
//		SmSymQues sq = new SmSymQues();
//		sq.setSym(smSymQues.getSym());
//		sq.setState(state);
//		sq.setQyStatus("1");
//		
//		if(state==2){
//			sq.setIllreason(smSymQues.getIllreason());
//		}
		
		//已经配置给病症的问题列表
//		List<SmSymQues> sqList = smSymQuesService.findList(sq);
		
		model.addAttribute("questList", list);
//		model.addAttribute("useQList", sqList);
		model.addAttribute("smSymQues", smSymQues);
		
		return "modules/config/smSymQuesFormNext";
	}
	
	@RequiresPermissions("config:smSymQues:edit")
	@RequestMapping(value = "saveNextQues")
	public String saveNextQues(SmSymQues smSymQues, Model model, RedirectAttributes redirectAttributes) {
		String symId = smSymQues.getSym().getId() ;
		//配置病症答题列表，配置病因答题列表
		//通过病症ID和state=1决定这个是病症开始答题流程。
		//通过病症ID和病因ID和state=2决定这个是哪个病因的答题流程
		//病症和病因的第一个问题都是唯一的，通过if_first决定唯一性
		//通过dept和dept_pid深度和深度唯一标识位，获取答案对应的问题
		//通过上面，可以完成系列答题流程
		//下面，需要添加配置问题
		SmSymQues symQ = new SmSymQues();
		
		int state = smSymQues.getState();
		
		//获取最大的唯一值
		SmSymQues sq = new SmSymQues();
		sq.setSym(smSymQues.getSym());
		sq.setState(state);
		sq.setQyStatus("1");
		sq.setDept(smSymQues.getDept()+1);
		sq.setDeptFid(smSymQues.getDeptPid());
		//state=2病因配题
		sq.setIllreason(smSymQues.getIllreason());
		sq.setPercent(smSymQues.getPercent());
		sq.setAgeRange(smSymQues.getAgeRange());
		/*if(state==2){
			sq.setIllreason(smSymQues.getIllreason());
			symQ.setIllreason(smSymQues.getIllreason());
		}*/
		List<SmSymQues> sqList = smSymQuesService.findList(sq);//获取该深度的下一深度，最大唯一标志
		if(sqList!=null&&sqList.size()>0){
			int d= 0 ;
			int[] arr = new int[sqList.size()]  ;
			for(int i = 0;i<sqList.size();i++){
				String ss = sqList.get(i).getDeptPid().split(smSymQues.getDeptPid()+"-")[1];
				if(StringUtils.isNotBlank(ss)){
					arr[i] = Integer.parseInt(ss) ;
				}else{
					arr[i] = 0 ;
				}
			}
			d = getMax(arr)+1;
			symQ.setDeptPid(smSymQues.getDeptPid()+"-"+d);
		}else{
			symQ.setDeptPid(smSymQues.getDeptPid()+"-1");
		}
		//判断该线下答案是否配置了重复问题
		sq.setOption(smSymQues.getOption());
		List<SmSymQues> exitList = smSymQuesService.findList(sq);
		if(exitList!=null&&exitList.size()>0){
			addMessage(redirectAttributes, "该选项已经配置了问题");
			return "redirect:"+Global.getAdminPath()+"/config/smSymQues/toConfigNextQues?id="+smSymQues.getId();
		}
		
		symQ.setDeptFid(smSymQues.getDeptPid());
		symQ.setState(state);
		symQ.setSym(smSymQues.getSym());
		symQ.setQuestion(smSymQues.getQuestion());
		symQ.setOption(smSymQues.getOption());
		symQ.setQyStatus(smSymQues.getQyStatus());
		symQ.setIsFirst("1");
		symQ.setIllreason(smSymQues.getIllreason());
		symQ.setRemarks(smSymQues.getRemarks());
		symQ.setLastQuestion(smSymQues.getLastQuestion());
		symQ.setDept(smSymQues.getDept()+1);
		symQ.setPercent(smSymQues.getPercent());
		symQ.setQuesType(smSymQues.getQuestion().getQuesType());
		symQ.setAgeRange(smSymQues.getAgeRange());
		smSymQuesService.save(symQ);
		//保存到关系表中
		
		SmRelation smRelation = new SmRelation();
		smRelation.setIllreaon(smSymQues.getIllreason());
		smRelation.setQuestion(smSymQues.getQuestion());
		
		List<SmRelation> smList = smRelationService.findList(smRelation);
	   if (smList.size() == 0) {
		    smRelation.setPercent(smSymQues.getPercent());
			smRelation.setCreateDate(new Date());
			smRelation.setSmSymQues(smSymQues);
			smRelation.setSymptom(smSymQues.getSym());	
			smRelation.setDelFlag("0");
			smRelationService.save(smRelation);
	} else {
		//addMessage(redirectAttributes, "添加下题成功,关系表中已存在该记录");
	}
		
		
		addMessage(redirectAttributes, "添加下题成功");
		return "redirect:"+Global.getAdminPath()+"/config/smSymQues/?repage&sym.id="+symId;
	}
	
	/**		
      * 取出数组中的最大值
      * @param arr
      * @return
      */
	public int getMax(int[] arr){
	   int max=arr[0];
	    for(int i=1;i<arr.length;i++){
	        if(arr[i]>max){
	            max=arr[i];
	        }
	     }
	    return max;
	}
	
	@RequiresPermissions("config:smSymQues:edit")
	@RequestMapping(value = "delete")
	public String delete(SmSymQues smSymQues, RedirectAttributes redirectAttributes) {
		smSymQuesService.delete(smSymQues);
		addMessage(redirectAttributes, "删除病症和问题关联表成功");
		return "redirect:"+Global.getAdminPath()+"/config/smSymQues/?repage";
	}
	
	@RequiresPermissions("config:smSymQues:view")
	@RequestMapping(value = "questionform")
	public String questionform(SmSymQues smSymQues, Model model) {
		List<SmQuestion> list = smQuestionService.findList(new SmQuestion());
		model.addAttribute("questList", list);
		model.addAttribute("smSymQues", smSymQues);
		return "modules/config/smFirstQuest";
	}

}