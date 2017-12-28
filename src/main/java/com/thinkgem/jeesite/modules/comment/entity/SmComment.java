/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.comment.entity;

import com.thinkgem.jeesite.modules.match.entity.SmIllreasonMatch;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 专家点评Entity
 * @author louis
 * @version 2016-11-18
 */
public class SmComment extends DataEntity<SmComment> {
	
	private static final long serialVersionUID = 1L;
	private SmIllreasonMatch match;		// 配置方案ID
	private String coType;		// 评论人类型
	private String auditStatus;		// 审核状态
	private String coStatusDoc;		// 医生点评
	private String coStatusSick;		// 病人点评
	
	public SmComment() {
		super();
	}

	public SmComment(String id){
		super(id);
	}

	public SmIllreasonMatch getMatch() {
		return match;
	}

	public void setMatch(SmIllreasonMatch match) {
		this.match = match;
	}
	
	@Length(min=0, max=1, message="评论人类型长度必须介于 0 和 1 之间")
	public String getCoType() {
		return coType;
	}

	public void setCoType(String coType) {
		this.coType = coType;
	}
	
	@Length(min=0, max=1, message="审核状态长度必须介于 0 和 1 之间")
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	@Length(min=0, max=2, message="医生点评长度必须介于 0 和 2 之间")
	public String getCoStatusDoc() {
		return coStatusDoc;
	}

	public void setCoStatusDoc(String coStatusDoc) {
		this.coStatusDoc = coStatusDoc;
	}
	
	@Length(min=0, max=2, message="病人点评长度必须介于 0 和 2 之间")
	public String getCoStatusSick() {
		return coStatusSick;
	}

	public void setCoStatusSick(String coStatusSick) {
		this.coStatusSick = coStatusSick;
	}
	
}