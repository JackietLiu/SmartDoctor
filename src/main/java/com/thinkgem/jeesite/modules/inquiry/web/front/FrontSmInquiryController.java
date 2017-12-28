/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.web.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.autocom.AjaxUtil;
import com.thinkgem.jeesite.common.autocom.BaseResult;
import com.thinkgem.jeesite.common.autocom.ResultMgr;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;
import com.thinkgem.jeesite.modules.basic.service.SmOptionService;
import com.thinkgem.jeesite.modules.basic.service.SmSymptomService;
import com.thinkgem.jeesite.modules.config.entity.SmSymQues;
import com.thinkgem.jeesite.modules.config.service.SmSymQuesService;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiry;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryIllreason;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryQues;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryIllreasonService;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryService;
import com.thinkgem.jeesite.modules.smuser.entity.SmUser;
import com.thinkgem.jeesite.modules.smuser.service.SmUserService;

/**
 * 病人询诊Controller
 * @author louis
 * @version 2016-11-21
 */
@Controller
@RequestMapping(value = "${frontPath}/inquiry/smInquiry")
public class FrontSmInquiryController extends BaseController {

	@Autowired
	private SmInquiryService smInquiryService;
	
	@Autowired
	private SmSymptomService smSymptomService;
	
	@Autowired
	private SmSymQuesService smSymQuesService;
	
	@Autowired
	private SmOptionService smOptionService;
	
	@Autowired
	private SmInquiryIllreasonService smInquiryIllreasonService;
	
	@Autowired
	private SmUserService smUserService;
	
	@ModelAttribute
	public SmInquiry get(@RequestParam(required=false) String id) {
		SmInquiry entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smInquiryService.get(id);
		}
		if (entity == null){
			entity = new SmInquiry();
		}
		return entity;
	}
	
	/**
	 * 卡号查询操作
	 * @param cardno
	 * @param smInquiry
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list"})
	public String list(String cardno,SmInquiry smInquiry, HttpServletRequest request, HttpServletResponse response, Model model) {
		SmInquiry inqu = smInquiryService.findListExcept(smInquiry);
		model.addAttribute("cardno", cardno);//卡号
		SmUser smUser = new SmUser();
		smUser.setCardNo(cardno);
		List<SmUser> list = smUserService.findList(smUser);
		System.out.println(list);
		model.addAttribute("list", list);
		
		if(inqu!=null){
			//查询最新一条记录的状态，进入不同操纵
			//1上次没有答题		2 上一次答题中，挂起	 3上次完成答题，复诊
			model.addAttribute("smInquiry", inqu);
			return "modules/smartfront/returnVisit";//复诊页面（需要加一个[继续答题]）
		}
		//初诊，进入询诊页面
		//查询病症列表传递到页面
		SmSymptom smSymptom = new SmSymptom();
		smSymptom.setQyStatus("1");//启用中
		List<SmSymptom> smSymptomList = smSymptomService.findList(smSymptom);
		model.addAttribute("smSymptomList", smSymptomList);
		return "modules/smartfront/firstVisit";//初诊页面
	}
	
	/**
	 * 重新答题
	 */
	@RequestMapping(value = {"restart"})
	public String restart(String cardno,SmInquiry smInquiry, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		//初诊，进入询诊页面
		//查询病症列表传递到页面
		SmSymptom smSymptom = new SmSymptom();
		smSymptom.setQyStatus("1");//启用中
		List<SmSymptom> smSymptomList = smSymptomService.findList(smSymptom);
		model.addAttribute("smSymptomList", smSymptomList);
		model.addAttribute("cardno", smInquiry.getCardno());
		//删除该条会诊信息
		smInquiryService.delete(smInquiry);
		return "modules/smartfront/firstVisit";//初诊页面
	}
	
	@RequestMapping(value = "delete")
	public String delete(SmInquiry smInquiry, Model model) {
		String cardno = smInquiry.getCardno();
		smInquiryService.delete(smInquiry);
		return "redirect:"+Global.getFrontPath()+"/inquiry/smInquiry/list?cardno="+cardno;
	}

	@RequestMapping(value = "form")
	public String form(SmInquiry smInquiry, Model model) {
		model.addAttribute("smInquiry", smInquiry);
		return "modules/inquiry/smInquiryForm";
	}

	/**
	 * 点击病症，进入答题页面
	 * @param smInquiry
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(SmInquiry smInquiry, Model model,HttpServletRequest request) {
		String sqId = request.getParameter("sqId");
		if(smInquiry.getSym()!=null){
			//保存
			SmSymptom smSymptom = smSymptomService.get(smInquiry.getSym().getId());
			smInquiry.setIllStatus("1");//新建
			smInquiry.setSymName(smSymptom.getName());
			smInquiryService.save(smInquiry);
			//获取第一题
			SmSymQues smSymQues = smSymQuesService.get(sqId);//查询病症第一题或者病因第一题
			if(null!=smSymQues){
				model.addAttribute("smSymQues", smSymQues);//问题名称
				//获取选项列表
				SmOption smOption = new SmOption();
				smOption.setQyStatus("1");
				smOption.setQuestion(smSymQues.getQuestion());
				List<SmOption> optList= smOptionService.findList(smOption);
				model.addAttribute("optList", optList);//选项列表
			}
			
			model.addAttribute("smInquiry", smInquiry);//病症名称、卡号
			//下面这个 是防止报错的
			SmInquiryQues sq = new SmInquiryQues();
			model.addAttribute("smInquiryQues", sq);//已经选择的下一题信息带到页面中
		}
		return "modules/smartfront/answer";//进入答题页面
	}
	
	/**
	 * 判断是不是有第一题
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = {"judgeFirstQuest"})
	public void judgeFirstQuest(HttpServletRequest request, HttpServletResponse response, Model model) {
		try
        {
			String symId = request.getParameter("symId");
			String state = request.getParameter("state");
			int sta = Integer.parseInt(state) ;//1病症答题2病因答题
			BaseResult lr = new BaseResult();
	   		lr.setSuccess(true);
			if(symId!=null){
				//获取题目相关信息
				SmSymQues conf = new SmSymQues();
				conf.setState(sta);//查病症答题
				SmSymptom sp = new SmSymptom();
				sp.setId(symId);
				conf.setSym(sp);
				conf.setIsFirst("2");//是第一题
				conf.setQyStatus("1");
				if(sta==2){
					String illreaId = request.getParameter("illreaId");
					SmIllreason ir = new SmIllreason();
					ir.setId(illreaId);
					conf.setIllreason(ir);
				}
				SmSymQues smSymQues = smSymQuesService.getQuestionBySort(conf);//查询病症第一题或者病因第一题
				if(null!=smSymQues){
					lr.setMsg("1");//有第一题
					lr.setResult(smSymQues.getId());//配置问题的ID
				}else{
					lr.setMsg("2");//没有第一题
				}
			}else{
				lr.setMsg("3");//需要重新答题
			}
	   		
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
	
	/**
	 * 病因继续答题
	 * 点击继续答题，获取第一题，进入答题页面
	 * @param smInquiry
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "illFirstQues")
	public String illFirstQues(SmInquiry smInquiry, Model model,HttpServletRequest request) {
		String sqId = request.getParameter("sqId");//后台配置问题sm_sym_ques的ID
		//获取第一题
		SmSymQues smSymQues = smSymQuesService.get(sqId);//查询病症第一题或者病因第一题
		if(null!=smSymQues){
			model.addAttribute("smSymQues", smSymQues);//问题名称
			//获取选项列表
			SmOption smOption = new SmOption();
			smOption.setQyStatus("1");
			smOption.setQuestion(smSymQues.getQuestion());
			List<SmOption> optList= smOptionService.findList(smOption);
			model.addAttribute("optList", optList);//选项列表
		}
		
		model.addAttribute("smInquiry", smInquiry);//病症名称、卡号
		//下面这个 是防止报错的
		SmInquiryQues sq = new SmInquiryQues();
		model.addAttribute("smInquiryQues", sq);//已经选择的下一题信息带到页面中
		return "modules/smartfront/answer";//进入答题页面
	}
	
	/** 挂起
	 * @param smInquiry
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "hangUp")
	public String hangUp(SmInquiry smInquiry, Model model,HttpServletRequest request) {
		smInquiryService.save(smInquiry);
		return "redirect:"+Global.getFrontPath()+"/index-doc.html";
	}
	
	/**
	 * 病因挂起继续答题
	 * 病因第一题挂起，从sm_inquiry_illreason查询出该次询诊的最新一条病因，然后查询出下一题，进入answer.jsp页面
	 * @param smInquiry
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goOnAnswerFromIllRea")
	public String goOnAnswerFromIllRea(SmInquiry smInquiry, Model model,HttpServletRequest request) {
		//询诊挂起状态修改为正常流程
		smInquiry.setGqState(0);
		smInquiryService.save(smInquiry);
		//查询询诊最后一次病因
		SmInquiryIllreason	smInquiryIllreason = new SmInquiryIllreason();
		smInquiryIllreason.setInqu(smInquiry);
		smInquiryIllreason = smInquiryIllreasonService.findFirst(smInquiryIllreason);
		//获取下一题
		String symId = smInquiry.getSym().getId();
   		SmSymQues smSymQues = null ;
		if(symId!=null){
			//获取题目相关信息
			SmSymQues conf = new SmSymQues();
			conf.setState(2);//查病症答题
			SmSymptom sp = new SmSymptom();
			sp.setId(symId);
			conf.setSym(sp);
			conf.setIsFirst("2");//是第一题
			conf.setQyStatus("1");
			conf.setIllreason(smInquiryIllreason.getIllrea());//病因ID
			smSymQues = smSymQuesService.getQuestionBySort(conf);//查询病症第一题或者病因第一题
		}
		
		if(null!=smSymQues){
			model.addAttribute("smSymQues", smSymQues);//问题名称
			//获取选项列表
			SmOption smOption = new SmOption();
			smOption.setQyStatus("1");
			smOption.setQuestion(smSymQues.getQuestion());
			List<SmOption> optList= smOptionService.findList(smOption);
			model.addAttribute("optList", optList);//选项列表
		}else{
			model.addAttribute("cardno", smInquiry.getCardno());//选项列表
			model.addAttribute("smInquiry", smInquiry);
			return "modules/smartfront/returnVisit";
		}
		
		model.addAttribute("smInquiry", smInquiry);//病症名称、卡号
		//下面这个 是防止报错的
		SmInquiryQues sq = new SmInquiryQues();
		model.addAttribute("smInquiryQues", sq);//已经选择的下一题信息带到页面中
		return "modules/smartfront/answer";//进入答题页面
	}
	
}