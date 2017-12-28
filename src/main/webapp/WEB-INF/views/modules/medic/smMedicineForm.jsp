<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品管理</title>
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
		<li><a href="${ctx}/medic/smMedicine/">药品列表</a></li>
		<li class="active"><a href="${ctx}/medic/smMedicine/form?id=${smMedicine.id}">药品<shiro:hasPermission name="medic:smMedicine:edit">${not empty smMedicine.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="medic:smMedicine:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smMedicine" action="${ctx}/medic/smMedicine/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">药品名称：</label>
			<div class="controls">
				<form:input path="medName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">剂量：</label>
			<div class="controls">
				<form:input path="dose" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">剂量单位：</label>
			<div class="controls" >
				<form:input path="doseUnit" htmlEscape="false" maxlength="20" class="input-xlarge " placeholder=" 默认单位 ‘mg/d’ " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服药：</label>
			<div class="controls">
				<form:input path="medication" htmlEscape="false" maxlength="20" class="input-xlarge "  />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">服药单位：</label>
			<div class="controls">
				<form:input path="medicationUnit" htmlEscape="false" maxlength="20" class="input-xlarge " placeholder="默认单位 ‘次/d’" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不良反应：</label>
			<div class="controls">
				<%-- <form:input path="effects" htmlEscape="false" maxlength="255" class="input-xlarge "/> --%>
				<form:textarea path="effects" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">起效时间(min)：</label>
			<div class="controls">
				<form:input path="workTime" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">持续时间：</label>
			<div class="controls">
				<form:input path="durationTime" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="medic:smMedicine:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>