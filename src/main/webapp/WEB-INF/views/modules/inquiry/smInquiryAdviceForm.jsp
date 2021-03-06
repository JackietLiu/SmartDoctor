<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>询诊治疗建议管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inquiry/smInquiryAdvice/">询诊治疗建议列表</a></li>
		<li class="active"><a href="${ctx}/inquiry/smInquiryAdvice/form?id=${smInquiryAdvice.id}">询诊治疗建议<shiro:hasPermission name="inquiry:smInquiryAdvice:edit">${not empty smInquiryAdvice.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="inquiry:smInquiryAdvice:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smInquiryAdvice" action="${ctx}/inquiry/smInquiryAdvice/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">询诊ID：</label>
			<div class="controls">
				<form:input path="inqu.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结果：</label>
			<div class="controls">
				<form:radiobuttons path="reStatus" items="${fns:getDictList('re_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">建议ID：</label>
			<div class="controls">
				<form:input path="adv.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">治疗建议名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">治疗建议描述：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="inquiry:smInquiryAdvice:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>