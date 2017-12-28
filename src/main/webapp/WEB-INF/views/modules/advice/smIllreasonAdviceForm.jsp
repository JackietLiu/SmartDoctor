<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病因和治疗建议管理</title>
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
		<li ><a href="${ctx}/basic/smIllreason/listAll">病因列表</a></li>
		<li><a href="${ctx}/advice/smIllreasonAdvice/?illrea.id=${smIllreasonAdvice.illrea.id}">治疗建议列表</a></li>
		<li class="active"><a href="${ctx}/advice/smIllreasonAdvice/form?id=${smIllreasonAdvice.id}&illrea.id=${smIllreasonAdvice.illrea.id}">治疗建议<shiro:hasPermission name="advice:smIllreasonAdvice:edit">${not empty smIllreasonAdvice.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="advice:smIllreasonAdvice:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smIllreasonAdvice" action="${ctx}/advice/smIllreasonAdvice/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="illrea.id" value="${smIllreasonAdvice.illrea.id }"/>
		<sys:message content="${message}"/>		
		<%-- <div class="control-group">
			<label class="control-label">病因：</label>
			<div class="controls">
				<form:input path="illrea.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">治疗建议：</label>
			<div class="controls">
				<select name="advise.id"  style="width:250px;" class="required">  
					<option value="" >--请选择--</option>
					<c:forEach items="${smAdviseList }" var="adv" >
						<option value="${adv.id }" <c:if test="${adv.id ==smIllreasonAdvice.advise.id }">selected</c:if>  >${adv.name }-${adv.description }</option>
					</c:forEach>
			    </select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用状态：</label>
			<div class="controls">
				<form:radiobuttons path="qyStatus" items="${fns:getDictList('qy_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="advice:smIllreasonAdvice:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>