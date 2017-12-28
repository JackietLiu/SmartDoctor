<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病症和问题关联表管理</title>
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
		<li><a href="${ctx}/config/smSymQues/">病症和问题关联表列表</a></li>
		<li class="active"><a href="${ctx}/config/smSymQues/form?id=${smSymQues.id}">病症和问题关联表<shiro:hasPermission name="config:smSymQues:edit">${not empty smSymQues.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="config:smSymQues:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smSymQues" action="${ctx}/config/smSymQues/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="sym.id" value="${smSymQues.sym.id }"/> 
		<sys:message content="${message}"/>		
		<%-- <div class="control-group">
			<label class="control-label">症状ID：</label>
			<div class="controls">
				<form:input path="sym.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">问题ID：</label>
			<div class="controls">
				<select name="question.id"  style="width:150px;"   >  
					<option value="" >--请选择--</option>
					<c:forEach items="${questList }" var="quest" >
						<option value="${quest.id }" >${quest.name }</option>
					</c:forEach>
			    </select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用状态：</label>
			<div class="controls">
				<form:input path="qyStatus" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第一个问题：</label>
			<div class="controls">
				<form:radiobuttons path="isFirst" items="${fns:getDictList('is_first')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="config:smSymQues:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>