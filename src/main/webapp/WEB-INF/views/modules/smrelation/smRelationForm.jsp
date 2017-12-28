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
		<%-- <li><a href="${ctx}/smrelation/smRelation/">病因列表</a></li> --%>
		<li ><a href="${ctx}/basic/smIllreason/listAll">病因列表</a></li>
		<li class="active"><a href="${ctx}/smrelation/smRelation/form?id=${smRelation.id}">病因问题绑定<shiro:hasPermission name="smrelation:smRelation:edit">${not empty smRelation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="smrelation:smRelation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smRelation" action="${ctx}/smrelation/smRelation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
<%-- 		<div class="control-group">
			<label class="control-label">sm_sym_ques的ID：</label>
			<div class="controls">
				<form:input path="smSymQues.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">问题：</label>
			<select  id ="question" name="question.id" style="width:290px;">  
					<option value="" >--请选择--</option>
					<c:forEach items="${quesList }" var="ques" >
						<option value="${ques.id }" >${ques.name }</option>
					</c:forEach>
			    </select>
		</div>
		<div class="control-group">
			<label class="control-label">病因：</label>
			<select  id ="illReason" name="illreaon.id" style="width:290px;">  
					<option value="" >--请选择--</option>
					<c:forEach items="${reasonsList }" var="reasons" >
						<option value="${reasons.id }" >${reasons.name }</option>
					</c:forEach>
			 </select>
		</div>
		<div class="control-group">
			<label class="control-label">病症：</label>
			<select  id ="illReason" name="symptom.id" style="width:290px;">  
					<option value="" >--请选择--</option>
					<c:forEach items="${symptomsList }" var="symptoms" >
						<option value="${symptoms.id }" >${symptoms.name }</option>
					</c:forEach>
			 </select>
		</div>
		<div class="control-group">
			<label class="control-label">所占比重：</label>
			<div class="controls">
				<form:input path="percent" htmlEscape="false" maxlength="20" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">特殊备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="smrelation:smRelation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>