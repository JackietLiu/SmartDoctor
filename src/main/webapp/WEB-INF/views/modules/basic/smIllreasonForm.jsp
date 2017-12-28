<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病因管理管理</title>
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
		<li><a href="${ctx}/basic/smSymptom/">病症列表</a></li>
		<li><a href="${ctx}/basic/smIllreason/?sym.id=${smIllreason.sym.id}">病因列表</a></li>
		<li class="active"><a href="${ctx}/basic/smIllreason/form?id=${smIllreason.id}&sym.id=${smIllreason.sym.id}">病因<shiro:hasPermission name="basic:smIllreason:edit">${not empty smIllreason.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="basic:smIllreason:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smIllreason" action="${ctx}/basic/smIllreason/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="sym.id" value="${smIllreason.sym.id }"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">病因：</label>
			<div class="controls">
				<form:textarea path="name" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">病因描述：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用：</label>
			<div class="controls">
				<form:radiobuttons path="qyStatus" items="${fns:getDictList('qy_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="basic:smIllreason:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>