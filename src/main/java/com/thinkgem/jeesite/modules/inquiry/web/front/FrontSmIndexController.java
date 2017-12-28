/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inquiry.web.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.inquiry.service.SmInquiryService;

/**
 * 病人询诊Controller
 * @author louis
 * @version 2016-11-21
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class FrontSmIndexController extends BaseController {

	@Autowired
	private SmInquiryService smInquiryService;
	
	/**
	 * 网站首页
	 */
	@RequestMapping(value = "index-doc${urlSuffix}")
	public String index(Model model) {
		return "modules/smartfront/frontIndex";
	}

}