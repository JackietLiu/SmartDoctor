<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病因方案管理</title>
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
		<li><a href="${ctx}/basic/smIllreason/listAll">病因列表</a></li>
		<li><a href="${ctx}/match/smIllreasonMatch/?illrea.id=${smIllreasonMatch.illrea.id}">方案列表</a></li>
		<li class="active"><a href="${ctx}/match/smIllreasonMatch/form?id=${smIllreasonMatch.id}&illrea.id=${smIllreasonMatch.illrea.id}">方案<shiro:hasPermission name="match:smIllreasonMatch:edit">${not empty smIllreasonMatch.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="match:smIllreasonMatch:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smIllreasonMatch" action="${ctx}/match/smIllreasonMatch/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input type="hidden" name="illrea.id" value="${smIllreasonMatch.illrea.id}"/>
		<div class="control-group">
			<label class="control-label">方案名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">病因：</label>
			<div class="controls">
				<form:input path="illrea.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">启用状态：</label>
			<div class="controls">
				<form:radiobuttons path="qyStatus" items="${fns:getDictList('qy_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="match:smIllreasonMatch:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>