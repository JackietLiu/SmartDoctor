<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>配置病因管理</title>
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
		<li><a href="${ctx}/smrelation/smSymQuesIllreason/?smSymQues.id=${smSymQuesIllreason.smSymQues.id}">配置病因列表</a></li>
		<li class="active">
		<a href="${ctx}/smrelation/smSymQuesIllreason/form?id=${smSymQuesIllreason.id}&smSymQues.id=${smSymQuesIllreason.smSymQues.id}">配置病因<shiro:hasPermission name="smrelation:smSymQuesIllreason:edit">${not empty smSymQuesIllreason.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="smrelation:smSymQuesIllreason:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smSymQuesIllreason" action="${ctx}/smrelation/smSymQuesIllreason/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name ="smSymQues.id" value="${smSymQuesIllreason.smSymQues.id}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">选项：</label>
			<div class="controls">
				<select  id ="optId" name="option.id" style="width:290px;" onchange="changeQues();">  
					<option value="" >--请选择--</option>
					<c:forEach items="${optList }" var="opt" >
						<option value="${opt.id }" >${opt.name }</option>
					</c:forEach>
			    </select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">病因：</label>
			<div class="controls">
				<select name="illreaon.id" style="width:290px;">  
					<option value="" >--请选择--</option>
					<c:forEach items="${illreasonList }" var="ill" >
						<option value="${ill.id }" >${ill.name }</option>
					</c:forEach>
			    </select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="smrelation:smSymQuesIllreason:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>