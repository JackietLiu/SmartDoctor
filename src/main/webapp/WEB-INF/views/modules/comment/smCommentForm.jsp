<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专家点评管理</title>
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
		<li><a href="${ctx}/comment/smComment/?match.id=${smComment.match.id}">专家点评列表</a></li>
		<li class="active"><a href="${ctx}/comment/smComment/form?id=${smComment.id}&match.id=${smComment.match.id}">专家点评<shiro:hasPermission name="comment:smComment:edit">${not empty smComment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="comment:smComment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smComment" action="${ctx}/comment/smComment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="match.id" value="${smComment.match.id }"/>
		<input type="hidden" name="coType" value="2"/><!-- 医生 -->
		<input type="hidden" name="auditStatus" value="2"/><!-- 只有医生评论，都是通过 -->
		<sys:message content="${message}"/>		
		<%-- <div class="control-group">
			<label class="control-label">评论人类型：</label>
			<div class="controls">
				<form:radiobuttons path="coType" items="${fns:getDictList('co_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">审核状态：</label>
			<div class="controls">
				<form:radiobuttons path="auditStatus" items="${fns:getDictList('audit_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">医生点评：</label>
			<div class="controls">
				<form:radiobuttons path="coStatusDoc" items="${fns:getDictList('co_status_doc')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">病人点评：</label>
			<div class="controls">
				<form:radiobuttons path="coStatusSick" items="${fns:getDictList('co_status_sick')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="comment:smComment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>