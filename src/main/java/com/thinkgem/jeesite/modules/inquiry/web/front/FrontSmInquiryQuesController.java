/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.web.front;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.mail.Flags.Flag;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.autocom.AjaxUtil;
import com.thinkgem.jeesite.common.autocom.BaseResult;
import com.thinkgem.jeesite.common.autocom.ListResult;
import com.thinkgem.jeesite.common.autocom.ResultMgr;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.advice.entity.SmAdvise;
import com.thinkgem.jeesite.modules.advice.entity.SmIllreasonAdvice;
import com.thinkgem.jeesite.modules.advice.service.SmIllreasonAdviceService;
import com.thinkgem.jeesite.modules.basic.entity.SmIllreason;
import com.thinkgem.jeesite.modules.basic.entity.SmOption;
import com.thinkgem.jeesite.modules.basic.entity.SmQuestion;
import com.thinkgem.jeesite.modules.basic.entity.SmSymptom;
import com.thinkgem.jeesite.modules.basic.service.SmIllreasonService;
import com.thinkgem.jeesite.modules.basic.service.SmOptionService;
import com.thinkgem.jeesite.modules.basic.service.SmQuestionService;
import com.thinkgem.jeesite.modules.config.entity.SmSymQues;
import com.thinkgem.jeesite.modules.config.service.SmSymQuesService;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiry;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryAdvice;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryAdviceCopy;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryIllreason;
import com.thinkgem.jeesite.modules.inquiry.entity.SmInquiryQues;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryAdviceCopyService;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryAdviceService;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryIllreasonService;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryQuesService;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryService;
import com.thinkgem.jeesite.modules.match.entity.SmIllreasonMatch;
import com.thinkgem.jeesite.modules.match.entity.SmMatchOpt;
import com.thinkgem.jeesite.modules.match.service.SmIllreasonMatchService;
import com.thinkgem.jeesite.modules.match.service.SmMatchOptService;
import com.thinkgem.jeesite.modules.smrelation.entity.SmRelation;
import com.thinkgem.jeesite.modules.smrelation.entity.SmSymQuesIllreason;
import com.thinkgem.jeesite.modules.smrelation.service.SmRelationService;
import com.thinkgem.jeesite.modules.smuser.entity.SmUser;
import com.thinkgem.jeesite.modules.smuser.service.SmUserService;


/**
 * 病人答题明细Controller
 * @author louis
 * @version 2016-11-21 
 */
@Controller
@RequestMapping(value = "${frontPath}/inquiry/smInquiryQues")
public class FrontSmInquiryQuesController extends BaseController {

	@Autowired
	private SmInquiryQuesService smInquiryQuesService;

	@Autowired
	private SmSymQuesService smSymQuesService;
	@Autowired
	private SmOptionService smOptionService;

	@Autowired
	private SmInquiryService smInquiryService;

	@Autowired
	private SmMatchOptService smMatchOptService;

	@Autowired
	private SmIllreasonMatchService smIllreasonMatchService;

	@Autowired
	private SmIllreasonService smIllreasonService;

	@Autowired
	private SmInquiryIllreasonService smInquiryIllreasonService;

	@Autowired
	private SmInquiryAdviceService smInquiryAdviceService;

	@Autowired
	private SmIllreasonAdviceService smIllreasonAdviceService;
	@Autowired
	private SmQuestionService smQuestionService;
	@Autowired
	private SmRelationService smRelationService;

	@Autowired
	private SmUserService smUserService;
	@Autowired
	private SmInquiryAdviceCopyService smInquiryAdviceCopyService;
    private int percents = 0;
    private String  pid = "";
    private List<SmQuestion> sqList = null;
    private String  fid = "";

	@ModelAttribute
	public SmInquiryQues get(@RequestParam(required=false) String id) {
		SmInquiryQues entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = smInquiryQuesService.get(id);
		}
		if (entity == null){
			entity = new SmInquiryQues();
		}
		return entity;
	}

	@RequestMapping(value = {"list", ""})
	public String list(SmInquiryQues smInquiryQues, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SmInquiryQues> page = smInquiryQuesService.findPage(new Page<SmInquiryQues>(request, response), smInquiryQues); 
		model.addAttribute("page", page);
		return "modules/inquiry/smInquiryQuesList";
	}

	@RequestMapping(value = "form")
	public String form(SmInquiryQues smInquiryQues, Model model) {
		model.addAttribute("smInquiryQues", smInquiryQues);
		return "modules/inquiry/smInquiryQuesForm";
	}

	@RequestMapping(value = "delete")
	public String delete(SmInquiryQues smInquiryQues, RedirectAttributes redirectAttributes) {
		smInquiryQuesService.delete(smInquiryQues);
		addMessage(redirectAttributes, "删除病人答题明细成功");
		return "redirect:"+Global.getAdminPath()+"/inquiry/smInquiryQues/?repage";
	}

	@RequestMapping(value = "save")
	public String save(SmInquiryQues smInquiryQues, Model model, HttpServletRequest request) {
		//获取询诊，修改询诊状态为2
		SmInquiry smInquiry = smInquiryService.get(smInquiryQues.getInqu().getId());
		smInquiry.setIllStatus("2");
		smInquiryService.save(smInquiry);
		//先删除，后保存 
		//		SmInquiryQues iqdel = new SmInquiryQues();
		//		iqdel.setInqu(smInquiryQues.getInqu());
		//		iqdel.setQuestion(smInquiryQues.getQuestion());
		//		if(!"1".equals(subtype)){//如果完成答题的，该题以及后面的题目都需要删除
		//			iqdel.setSort(smInquiryQues.getSort());
		//			iqdel.setQuestion(null);
		//		}
		//		smInquiryQuesService.deleteBy(iqdel);
		//保存当前答题
		smInquiryQuesService.save(smInquiryQues);
		//进入下一题
		return "redirect:"+Global.getFrontPath()+"/inquiry/smInquiryQues/nextQuest?id="+smInquiryQues.getId();
	}
	@RequestMapping(value = "getSort")
	public String getSort(SmInquiryQues smInquiryQues, Model model, HttpServletRequest request) {
		String sort = request.getParameter("sort");
		return sort;
	}
	@RequestMapping(value = "totalPercent")
	public int totalPercent(List<Integer> list) {
		int percent = 0;
		
		return percent;
	}
	/**
	 * 进入下一题
	 */
	@RequestMapping(value = "nextQuest")
	public String  nextQuest(SmInquiryQues smInquiryQues,Model model,HttpServletRequest request) {
		if(!smInquiryQues.getIllrea().getId().equals(pid) || !smInquiryQues.getSymQues().getIsFirst().equals("1")){			
			percents = 0;
		}	
		fid = smInquiryQues.getInqu().getId();
		if(!smInquiryQues.getInqu().getId().equals(fid) || !smInquiryQues.getSymQues().getIsFirst().equals("1")){
			sqList = new ArrayList<SmQuestion>();
		}
		//获取询诊
		SmInquiry smInquiry = smInquiryService.get(smInquiryQues.getInqu().getId());
		//点击下一题传入的问题
		SmSymQues sqes = smSymQuesService.get(smInquiryQues.getSymQues().getId());	
		//病因列表
		SmIllreason smIllreasons = new SmIllreason();		
		List<SmIllreason> sirList = smIllreasonService.findList(smIllreasons);//病因列表
		
		int percent;	
		
		for (int i = 0; i < sirList.size(); i++) {			
			SmOption smo = smOptionService.get(smInquiryQues.getOpt());
			//System.out.println(smo.getSort());
			if(sirList.get(i).getId().equals(smInquiryQues.getIllrea().getId())){
				pid=smInquiryQues.getIllrea().getId();
				if (smo.getSort().equals("1")) {					
					SmQuestion smQuestion = smQuestionService.get(sqes.getQuestion().getId());							
					sqList.add(smQuestion);					
					percent = sqes.getPercent();				
					percents += percent;					
					System.out.println(percents);
				} 
					if(percents > 90){
						advList(smInquiryQues);
						//修改询诊状态为完成,修改询诊状态为3
						smInquiry.setIllStatus("3");
						smInquiryService.save(smInquiry);
						SmInquiryIllreason illrea = new SmInquiryIllreason();
						illrea.setInqu(smInquiryQues.getInqu());
						illrea.setState(smInquiryQues.getState());
						illrea.setAnswerIllrea(smInquiryQues.getIllrea());
						//		List<SmInquiryIllreason> illreaList = smInquiryIllreasonService.findList(illrea);
						SmInquiryIllreason illreason = smInquiryIllreasonService.findFirst(illrea);

						SmIllreason sir = new SmIllreason();
						sir.setId(sirList.get(i).getId());
						SmIllreason sirs = smIllreasonService.get(sir);

						//系统处方
						SmInquiryAdvice smAdvise = new SmInquiryAdvice();			
						smAdvise.setInqu(smInquiryQues.getInqu());
						smAdvise.setState(smInquiryQues.getState());
						smAdvise.setAnswerIllrea(smInquiryQues.getIllrea());
						//医师建议处方
						SmInquiryAdviceCopy smAdvises = new SmInquiryAdviceCopy();
						smAdvises.setInqu(smInquiryQues.getInqu());
						smAdvises.setState(smInquiryQues.getState());
						smAdvises.setAnswerIllrea(smInquiryQues.getIllrea());
						List<SmInquiryAdvice> advices = smInquiryAdviceService.findList(smAdvise);	
						List<SmInquiryAdviceCopy> advicess = smInquiryAdviceCopyService.findList(smAdvises);	
						SmInquiry smInquirys = smInquiryService.get(smInquiryQues.getInqu().getId());
						SmUser su = smUserService.get(smInquirys.getCardno());                                                           
						model.addAttribute("smInquiry", smInquirys);//病症、卡号														
						model.addAttribute("si",illreason);
						model.addAttribute("advList",advices);
						model.addAttribute("newAdv",advicess);
						model.addAttribute("sirs",sirs);
						model.addAttribute("user",su);
						model.addAttribute("sqList",sqList);
						return "modules/smartfront/result";		
					}
				}
			}																				
		/*}*/
		SmInquiry smInquiryss = smInquiryService.get(smInquiryQues.getInqu().getId());
		SmUser sus = smUserService.get(smInquiryss.getCardno());
		String sex = sus.getSex();
		int age = Integer.parseInt((sus.getAge()));  
		// SmAgeRange ageRange = new SmAgeRange();
		//获取题目相关信息
		SmSymQues conf = new SmSymQues(); 
		//conf.setOption(smInquiryQues.getOpt());
		conf.setSym(sqes.getSym());
		//conf.setDeptFid(sqes.getDeptPid());
		conf.setState(sqes.getState());
		conf.setQyStatus("1");
		conf.setIsFirst("1");
		conf.setDept(sqes.getDept());
		//暂时设定为病症答题。		
		/*if(sqes.getState()==2){
			conf.setIllreason(sqes.getIllreason());
		}*/
		if (sex.equals("1")) {
			conf.setQuesType("1");
		}else if (sex.equals("2")) {
			conf.setQuesType("2");
		}
/*		if (age < sqes.getQuestion().getAgeRange().getMaxAge() || age > sqes.getQuestion().getAgeRange().getMinAge()) {

		}else if (age < 18) {
			SmInquiry smInquirys = smInquiryService.get(smInquiryQues.getInqu().getId());
            SmUser su = smUserService.get(smInquirys.getCardno());

			model.addAttribute("smInquiry", smInquirys);//病症、卡号														
			model.addAttribute("message","小于18岁的患者，请到医院进行诊疗");

			model.addAttribute("user",su);
			return "modules/smartfront/result";	
		}*/
		SmSymQues smSymQues = smSymQuesService.getQuestionBySort(conf);				
		//获取下题选项列表
		if(null!=smSymQues){
			model.addAttribute("smSymQues", smSymQues);//问题名称
			//获取选项列表
			SmOption smOption = new SmOption();
			smOption.setQyStatus("1");
			smOption.setQuestion(smSymQues.getQuestion());
			List<SmOption> optList= smOptionService.findList(smOption);
			model.addAttribute("optList", optList);//选项列表
		}else{						
			//没有下一题，则查询相关病因
			advList(smInquiryQues);
			//修改询诊状态为完成,修改询诊状态为3
			smInquiry.setIllStatus("3");
			smInquiryService.save(smInquiry);
			SmInquiryIllreason illrea = new SmInquiryIllreason();
			illrea.setInqu(smInquiryQues.getInqu());
			illrea.setState(smInquiryQues.getState());
			illrea.setAnswerIllrea(smInquiryQues.getIllrea());
//			List<SmInquiryIllreason> illreaList = smInquiryIllreasonService.findList(illrea);
			SmInquiryIllreason illreason = smInquiryIllreasonService.findFirst(illrea);


			SmIllreason sir = new SmIllreason();
			sir.setId(smInquiryQues.getIllrea().getId());
			SmIllreason sirs = smIllreasonService.get(sir);
			//系统处方
			SmInquiryAdvice smAdvise = new SmInquiryAdvice();			
			smAdvise.setInqu(smInquiryQues.getInqu());
			smAdvise.setState(smInquiryQues.getState());
			smAdvise.setAnswerIllrea(smInquiryQues.getIllrea());
			//医师建议处方
			SmInquiryAdviceCopy smAdvises = new SmInquiryAdviceCopy();
			smAdvises.setInqu(smInquiryQues.getInqu());
			smAdvises.setState(smInquiryQues.getState());
			smAdvises.setAnswerIllrea(smInquiryQues.getIllrea());


			List<SmInquiryAdvice> advices = smInquiryAdviceService.findList(smAdvise);	
			List<SmInquiryAdviceCopy> advicess = smInquiryAdviceCopyService.findList(smAdvises);			
			SmInquiry smInquirys = smInquiryService.get(smInquiryQues.getInqu().getId());

			SmUser su = smUserService.get(smInquirys.getCardno());

			model.addAttribute("smInquiry", smInquirys);//病症、卡号														
			model.addAttribute("si",illreason);
			model.addAttribute("message","没发现您有任何病因");
			model.addAttribute("advList",advices);
			model.addAttribute("newAdv",advicess);
			model.addAttribute("sirs",sirs);
			model.addAttribute("user",su);
			return "modules/smartfront/result";	
		}
		//判断下题是否被选择了，如果选择了，则把相关数据带到下个页面
		SmInquiryQues iq = new SmInquiryQues();
		iq.setInqu(smInquiryQues.getInqu());
		iq.setQuestion(smSymQues.getQuestion());
		iq.setDept(smSymQues.getDept());
		iq.setState(smSymQues.getState());
		iq.setIllrea(smSymQues.getIllreason());
		List<SmInquiryQues> list1 = smInquiryQuesService.findList(iq);
		if(list1!=null&&list1.size()>0){
			model.addAttribute("siq", list1.get(0));//已经选择的下一题信息带到页面中
		}else{
			SmInquiryQues sq = new SmInquiryQues();
			model.addAttribute("siq", sq);//已经选择的下一题信息带到页面中
		}
		model.addAttribute("smInquiry", smInquiry);//病症名称、卡号


		return "modules/smartfront/answer";

	}

	public void advList(SmInquiryQues smInquiryQues){
		SmRelation smRelation = new SmRelation();
		smRelation.setSmSymQues(smInquiryQues.getSymQues());
		smRelation.setOption(smInquiryQues.getOpt());
		SmRelation sRelation = smRelationService.findFirst(smRelation);
		//查询到配置的病因
		if(null!=sRelation){
			String irId = smInquiryQues.getIllrea().getId();//病因ID
			SmIllreason illreason = smIllreasonService.get(irId);
			//有病因且开启
			if(null!=illreason&&"1".equals(illreason.getQyStatus())){
				//保存病因
				SmInquiryIllreason smInquiryIllreason = new SmInquiryIllreason();
				smInquiryIllreason.setIllrea(illreason);
				smInquiryIllreason.setIllreaName(illreason.getName());
				smInquiryIllreason.setIllreaDesc(illreason.getDescription());
				smInquiryIllreason.setReStatus("1");
				smInquiryIllreason.setInqu(smInquiryQues.getInqu());
				smInquiryIllreason.setAnswerIllrea(smInquiryQues.getIllrea());
				smInquiryIllreason.setState(smInquiryQues.getState());
				smInquiryIllreasonService.save(smInquiryIllreason);
				//保存治疗建议
				//1.根据病因查询配置的建议
				SmIllreasonAdvice reaAdv =new SmIllreasonAdvice();
				reaAdv.setIllrea(illreason);
				reaAdv.setQyStatus("1");
				List<SmIllreasonAdvice> reaAdvList = smIllreasonAdviceService.findList(reaAdv);
				for(SmIllreasonAdvice illreaadv : reaAdvList){
					SmInquiryAdvice adv = new SmInquiryAdvice();
					SmInquiryAdviceCopy newAdv = new SmInquiryAdviceCopy();
					adv.setIllrea(illreason);
					adv.setIllreaname(illreason.getName());
					adv.setIllreadesc(illreason.getDescription());
					adv.setInqu(smInquiryQues.getInqu());
					adv.setReStatus("1");
					adv.setAdv(illreaadv.getAdvise());
					adv.setName(illreaadv.getAdvise().getName());
					adv.setDescription(illreaadv.getAdvise().getDescription());
					adv.setState(smInquiryQues.getState());
					adv.setAnswerIllrea(smInquiryQues.getIllrea());					
					String s = UUID.randomUUID().toString().replaceAll("\\-", "");
					adv.setId(s);
					smInquiryAdviceService.insert(adv);

					SmInquiryAdvice sia = smInquiryAdviceService.get(s);
					newAdv.setOld(sia);
					newAdv.setIllrea(illreason);
					newAdv.setIllreaname(illreason.getName());
					newAdv.setIllreadesc(illreason.getDescription());
					newAdv.setInqu(smInquiryQues.getInqu());
					newAdv.setReStatus("1");
					newAdv.setAdv(illreaadv.getAdvise());
					newAdv.setName(illreaadv.getAdvise().getName());
					newAdv.setDescription(illreaadv.getAdvise().getDescription());
					newAdv.setState(smInquiryQues.getState());
					newAdv.setAnswerIllrea(smInquiryQues.getIllrea());	
					smInquiryAdviceCopyService.save(newAdv);
				}
			}
		}else {
			//查询不到配置的病因
			SmInquiryIllreason smInquiryIllreason = new SmInquiryIllreason();
			smInquiryIllreason.setReStatus("2");
			smInquiryIllreason.setInqu(smInquiryQues.getInqu());
			smInquiryIllreason.setAnswerIllrea(smInquiryQues.getIllrea());
			smInquiryIllreason.setState(smInquiryQues.getState());
			smInquiryIllreasonService.save(smInquiryIllreason);
			//保存建议
			SmInquiryAdvice adv = new SmInquiryAdvice();
			adv.setInqu(smInquiryQues.getInqu());
			adv.setReStatus("2");
			adv.setState(smInquiryQues.getState());
			adv.setAnswerIllrea(smInquiryQues.getIllrea());
			smInquiryAdviceService.save(adv);
		}




	}
	/**
	 * 判断选项下是否有下一题
	 */
	@RequestMapping(value = "judgeNextQuest")
	public void judgeNextQuest(SmInquiryQues smInquiryQues,HttpServletRequest request, HttpServletResponse response, Model model) {

		try
		{
			String symQuesId = request.getParameter("symQuesId");//sm_sym_ques的id
			String optId = request.getParameter("optId");//选项ID，判断该选项还有没有下一提
			BaseResult lr = new BaseResult();
			lr.setSuccess(true);
			if(symQuesId!=null){
				SmSymQues sq = smSymQuesService.get(symQuesId);
				//获取题目相关信息
				SmSymQues conf = new SmSymQues();
				SmOption opt = new SmOption();
				opt.setId(optId);
				conf.setOption(opt);
				conf.setSym(sq.getSym());
				conf.setDeptFid(sq.getDeptPid());
				conf.setState(sq.getState());
				conf.setQyStatus("1");
				conf.setIsFirst("1");
				if(sq.getState()==2){
					conf.setIllreason(sq.getIllreason());
				}
				SmSymQues smSymQues = smSymQuesService.getQuestionBySort(conf);
				if(null!=smSymQues){
					lr.setMsg("1");//有第一题
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
	 * 完成答题（不用了）
	 */
	@RequestMapping(value = "completeQuest")
	public String completeQuest(SmInquiryQues smInquiryQues,Model model) {
		//修改询诊状态为完成//修改询诊状态为3
		//获取询诊
		SmInquiry smInquiry = smInquiryService.get(smInquiryQues.getInqu().getId());
		smInquiry.setIllStatus("3");
		smInquiryService.save(smInquiry);
		//根据询诊ID获取用户答题明细
		List<SmInquiryQues> qnQuestList = smInquiryQuesService.findList(smInquiryQues);
		int length = qnQuestList.size();
		//根据病症ID获取方案列表
		SmMatchOpt smMatchOpt = new SmMatchOpt();
		smMatchOpt.setSym(smInquiryQues.getSym());
		smMatchOpt.setQyStatus("1");
		List<SmMatchOpt> matchList = smMatchOptService.findListGroupBy(smMatchOpt);
		int c = 0;
		int f = 0;
		if(matchList!=null&&matchList.size()>0){
			for(SmMatchOpt match:matchList){//方案list，一个个方案遍历比较

				//根据方案获取配置的问答列表
				SmMatchOpt matopt = new SmMatchOpt();
				matopt.setQyStatus("1");//启用的
				matopt.setMatc(match.getMatc());
				List<SmMatchOpt> matchOptList = smMatchOptService.findList(matopt);

				if(qnQuestList.size()==matchOptList.size()){

					//list与list比较是否相同
					for(SmMatchOpt mqo : matchOptList){
						for(SmInquiryQues inquQues:qnQuestList){
							if(mqo.getQues().getId().equals(inquQues.getQuestion().getId())
									&&mqo.getOpt().getId().equals(inquQues.getOpt().getId())){
								c++;
							}
						}
					}


				}

				if(length==c){
					//如果该方案是的，则保存
					//1根据方案ID获取病因
					SmIllreasonMatch illreaMatch = smIllreasonMatchService.get(match.getMatc().getId());
					if(illreaMatch!=null){
						if("1".equals(illreaMatch.getQyStatus())){//启用的
							if(null!=illreaMatch.getIllrea()&&StringUtils.isNotBlank(illreaMatch.getIllrea().getId())){
								//存在病因ID,获取病因
								SmIllreason illreason = smIllreasonService.get(illreaMatch.getIllrea().getId());
								if(null!=illreason){
									if("1".equals(illreason.getQyStatus())){//启用
										f++;//用于判断是否有病因
										//保存病因
										SmInquiryIllreason smInquiryIllreason = new SmInquiryIllreason();
										smInquiryIllreason.setIllrea(illreason);
										smInquiryIllreason.setIllreaName(illreason.getName());
										smInquiryIllreason.setIllreaDesc(illreason.getDescription());
										smInquiryIllreason.setReStatus("1");
										smInquiryIllreason.setInqu(smInquiryQues.getInqu());
										smInquiryIllreasonService.save(smInquiryIllreason);
										//保存治疗建议
										//1.根据病因查询配置的建议
										SmIllreasonAdvice reaAdv =new SmIllreasonAdvice();
										reaAdv.setIllrea(illreason);
										reaAdv.setQyStatus("1");
										List<SmIllreasonAdvice> reaAdvList = smIllreasonAdviceService.findList(reaAdv);
										for(SmIllreasonAdvice illreaadv : reaAdvList){
											SmInquiryAdvice adv = new SmInquiryAdvice();
											adv.setIllrea(illreason);
											adv.setIllreaname(illreason.getName());
											adv.setIllreadesc(illreason.getDescription());
											adv.setInqu(smInquiryQues.getInqu());
											adv.setReStatus("1");
											adv.setAdv(illreaadv.getAdvise());
											adv.setName(illreaadv.getAdvise().getName());
											adv.setDescription(illreaadv.getAdvise().getDescription());
											smInquiryAdviceService.save(adv);
										}
									}
								}
							}
						}
					}
				}
				c = 0;
			}
			if(f==0){
				//如果有方案，但是没有病因，保存后，直接返回
				//保存病因
				SmInquiryIllreason smInquiryIllreason = new SmInquiryIllreason();
				smInquiryIllreason.setReStatus("2");
				smInquiryIllreason.setInqu(smInquiryQues.getInqu());
				smInquiryIllreasonService.save(smInquiryIllreason);
				//保存建议
				SmInquiryAdvice adv = new SmInquiryAdvice();
				adv.setInqu(smInquiryQues.getInqu());
				adv.setReStatus("2");
				smInquiryAdviceService.save(adv);
			}
		}else{
			//如果没有方案，保存后，直接返回
			//如果有方案，但是没有病因，保存后，直接返回
			//保存病因
			SmInquiryIllreason smInquiryIllreason = new SmInquiryIllreason();
			smInquiryIllreason.setReStatus("2");
			smInquiryIllreason.setInqu(smInquiryQues.getInqu());
			smInquiryIllreasonService.save(smInquiryIllreason);
			//保存建议
			SmInquiryAdvice adv = new SmInquiryAdvice();
			adv.setInqu(smInquiryQues.getInqu());
			adv.setReStatus("2");
			smInquiryAdviceService.save(adv);
		}
		return "redirect:"+Global.getFrontPath()+"/inquiry/smInquiryQues/getResult?inqu.id="+smInquiryQues.getInqu().getId();//进入病因页面
	}

	//查询结果根据询诊ID，查询治疗病因和治疗结果
	@RequestMapping(value = "getResult")
	public String getResult(SmInquiryQues smInquiryQues,Model model) {
		SmInquiry smInquiry = smInquiryService.get(smInquiryQues.getInqu().getId());
		//查询病因列表		
		SmInquiryIllreason illrea = new SmInquiryIllreason();
		illrea.setInqu(smInquiryQues.getInqu());
		illrea.setState(smInquiryQues.getState());
		illrea.setAnswerIllrea(smInquiryQues.getIllrea());
		//		List<SmInquiryIllreason> illreaList = smInquiryIllreasonService.findList(illrea);
		SmInquiryIllreason illreason = smInquiryIllreasonService.findFirst(illrea);
		//查询建议列表
		SmInquiryAdvice avd = new SmInquiryAdvice();
		avd.setInqu(smInquiryQues.getInqu());
		avd.setState(smInquiryQues.getState());
		avd.setAnswerIllrea(smInquiryQues.getIllrea());
		List<SmInquiryAdvice> advList = smInquiryAdviceService.findList(avd);

		model.addAttribute("reason", illreason);
		model.addAttribute("advList", advList);
		model.addAttribute("smInquiry", smInquiry);//病症、卡号

		if(illreason.getReStatus().equals("2")){
			model.addAttribute("isNextQues", 1);
			smInquiry.setIllStatus("3");
			smInquiryService.save(smInquiry);
			return "modules/smartfront/result";
		}
		//判断病因是不是有下一题，改操作，只是为了修改sm_inquiry表的ill_status状态
		SmSymQues conf = new SmSymQues();
		conf.setState(smInquiryQues.getState());//查病症答题
		SmSymptom sp = new SmSymptom();
		sp.setId(smInquiry.getSym().getId());
		conf.setSym(sp);
		conf.setIsFirst("2");//是第一题
		conf.setQyStatus("1");
		if(smInquiryQues.getState()==2){
			conf.setIllreason(smInquiryQues.getIllrea());
		}
		SmSymQues smSymQues = smSymQuesService.getQuestionBySort(conf);//查询病症第一题或者病因第一题
		if(null==smSymQues){//这里查询没有下一题了。则修改状态
			//这里用一个状态带入页面。1表示没有下一题，2表示有下一题
			model.addAttribute("isNextQues", 1);
			smInquiry.setIllStatus("3");
			smInquiryService.save(smInquiry);
		}else{
			model.addAttribute("isNextQues", 2);
		}


		return "modules/smartfront/result";
	}


	/**
	 * 查询重复开药
	 * @param smInquiryQues
	 * @param model
	 * @param cardno
	 * @return
	 */
	@RequestMapping(value = "getLastResult")
	public String getLastResult(SmInquiryQues smInquiryQues,Model model,String cardno) {
		SmInquiry	smInquiry = smInquiryService.getLastInqu(cardno);//根据卡号查询最新一次询诊
		//查询病因列表
		SmInquiryIllreason illrea = new SmInquiryIllreason();
		illrea.setInqu(smInquiry);
		List<SmInquiryIllreason> illreaList = smInquiryIllreasonService.findList(illrea);
		//查询建议列表
		SmInquiryAdvice avd = new SmInquiryAdvice();
		avd.setInqu(smInquiry);
		List<SmInquiryAdvice> advList = smInquiryAdviceService.findList(avd);

		model.addAttribute("illreaList", illreaList);
		model.addAttribute("advList", advList);
		model.addAttribute("smInquiry", smInquiry);//病症、卡号
		return "modules/smartfront/finalresult";
	}

	/**
	 * 判断最后一次询诊是不是完整进行
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = {"judgeLastResult"})
	public void judgeLastResult(HttpServletRequest request, HttpServletResponse response, Model model) {
		try
		{
			String cardno = request.getParameter("cardno");
			BaseResult lr = new BaseResult();
			lr.setSuccess(true);
			if(cardno!=null){
				SmInquiry	smInquiry = smInquiryService.getLastInqu(cardno);//根据卡号查询最新一次询诊
				if(smInquiry==null){
					lr.setMsg("2");//查询不到相关诊疗信息
				}else{
					SmInquiryIllreason smInquiryIllreason = new SmInquiryIllreason();
					smInquiryIllreason.setInqu(smInquiry);
					List<SmInquiryIllreason> list = smInquiryIllreasonService.findList(smInquiryIllreason);
					if(list!=null&&list.size()>0){
						lr.setMsg("1");//有结果，可以执行下去
					}else{
						lr.setMsg("4");//查询不到上次诊疗的结果
					}
				}
			}else{
				lr.setMsg("3");//重新输入卡号查询
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
	 * 上一题
	 * @param smInquiryQues
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "upQuest")
	public String upQuest(SmInquiryQues smInquiryQues,Model model){
		//获取询诊
		SmInquiry smInquiry = smInquiryService.get(smInquiryQues.getInqu().getId());

		//获取当前题目的排序，拿到上题序号
		int nextSort =Integer.parseInt(smInquiryQues.getSort())-1 ;//上一题序号

		//根据序号和病症ID，获取下一题信息
		SmSymQues conf = new SmSymQues();
		conf.setSort(nextSort+"");//查询上一题
		conf.setSym(smInquiryQues.getSym());//病症ID
		SmSymQues smSymQues = smSymQuesService.getQuestionBySort(conf);//上一题
		//获取下题选项列表
		if(null!=smSymQues){
			model.addAttribute("smSymQues", smSymQues);//问题名称
			//获取选项列表
			SmOption smOption = new SmOption();
			smOption.setQyStatus("1");
			smOption.setQuestion(smSymQues.getQuestion());
			List<SmOption> optList= smOptionService.findList(smOption);
			model.addAttribute("optList", optList);//选项列表
		}
		//判断上题是否被选择了，如果选择了，则把相关数据带到下个页面
		SmInquiryQues iq = new SmInquiryQues();
		iq.setInqu(smInquiryQues.getInqu());
		iq.setQuestion(smSymQues.getQuestion());
		List<SmInquiryQues> list = smInquiryQuesService.findList(iq);
		if(list!=null&&list.size()>0){
			model.addAttribute("siq", list.get(0));//已经选择的下一题信息带到页面中
		}else{
			SmInquiryQues sq = new SmInquiryQues();
			model.addAttribute("siq", sq);//已经选择的下一题信息带到页面中
		}

		model.addAttribute("smInquiry", smInquiry);//病症名称、卡号

		return "modules/smartfront/answer";
	}

	/**
	 * 自动加载已经选择的问题和选项
	 */
	@RequestMapping(value = "findListByInquId")
	public void findListByInquId(HttpServletRequest request, HttpServletResponse response, Model model) {
		try
		{
			String inquId = request.getParameter("inquId");
			ListResult lr = new ListResult();
			lr.setSuccess(true);
			if(inquId!=null){
				//获取题目相关信息
				SmInquiryQues iq = new SmInquiryQues();
				SmInquiry in = new SmInquiry();
				in.setId(inquId);
				iq.setInqu(in);
				List<SmInquiryQues> list = smInquiryQuesService.findListOther(iq);
				lr.setItems(list);
			}else{
				lr.setItems(null);
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
	 * 继续答题1
	 * 答题挂起，从sm_inquiry_ques表中查询该次询诊的最新一条数据，然后查询出下一题，进入answer.jsp页面
	 * @param smInquiryQues
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goOnAnswerFromQues")
	public String goOnAnswerFromQues(SmInquiryQues smInquiryQues,Model model){
		//获取询诊
		SmInquiry smInquiry = smInquiryService.get(smInquiryQues.getInqu().getId());
		//询诊挂起状态修改为正常流程
		smInquiry.setGqState(0);
		smInquiryService.save(smInquiry);
		//获取最新一条病人答题问题
		smInquiryQues = smInquiryQuesService.findFirstLast(smInquiryQues);

		return "redirect:"+Global.getFrontPath()+"/inquiry/smInquiryQues/nextQuest?id="+smInquiryQues.getId();
	}
	@RequestMapping(value = "updateByOldId")
	public void updateByOldId(SmInquiryQues smInquiryQues,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
		String id = request.getParameter("old");
		String adv = request.getParameter("adv");
		BaseResult lr = new BaseResult();

		SmInquiryAdvice smInquiryAdvice = smInquiryAdviceService.get(id); 
		SmInquiryAdviceCopy smInquiryAdviceCopy = new SmInquiryAdviceCopy();
		smInquiryAdviceCopy.setOld(smInquiryAdvice);
		smInquiryAdviceCopy.setDescription(adv);
		smInquiryAdviceCopyService.updateByOldId(smInquiryAdviceCopy);
		lr.setSuccess(true);
		lr.setMsg("修改成功");
		lr.setResult(smInquiryAdvice.getName());
		ResultMgr rtMgr = new ResultMgr(lr, "json");
		String re = rtMgr.convertResult();
		// 反馈到客户端
		AjaxUtil.sendResponseJsonText(response, re);
		addMessage(redirectAttributes, "保存成功");

	}
}