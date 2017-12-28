<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>治疗建议管理</title>
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
		<li><a href="${ctx}/advice/smAdvise/">治疗建议列表</a></li>
		<li class="active"><a href="${ctx}/advice/smAdvise/form?id=${smAdvise.id}">治疗建议<shiro:hasPermission name="advice:smAdvise:edit">${not empty smAdvise.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="advice:smAdvise:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smAdvise" action="${ctx}/advice/smAdvise/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">治疗建议名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">治疗建议描述：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">药品处方：</label>
			<div class="controls">
			<select name="med.id"  id="med" class="input-xlarge required" style="width:250px;" > 
				      <option value="">--请选择--</option>
				      <c:forEach items="${medList }" var="med" varStatus="statuss">
						<option value="${med.id }" >${med.medName }</option>
					  </c:forEach>
			</select>
			</div>
			</div>
			<div class="control-group">
			<label class="control-label">疗程：</label>
			<div class="controls">
				<form:input path="period" htmlEscape="false" rows="1" class="input-small" />
				<form:input path="periodUnit" htmlEscape="false" rows="1" class="input-small" placeholder="单位" />			
			</div>
		</div>

			<div class="control-group">
			<label class="control-label">健康处方：</label>
			<div class="controls">
			<select name="heath.id"  id="heath" class="input-xlarge required" style="width:250px;" > 
				      <option value="">--请选择--</option>
				      <c:forEach items="${heathList }" var="heath" varStatus="statuss">
						<option value="${heath.id }" >${heath.food }</option>
					  </c:forEach>
			</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下次来诊时间：</label>
			<div class="controls">
                 <input name="dtNextvisit" type="text" readonly="readonly" class="input-medium Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> 
				<%-- <form:input path="dtNextvisit" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			 --%></div>
		</div>
		
		<div class="control-group">
			<label class="control-label">启用：</label>
			<div class="controls">
				<form:radiobuttons path="qyStatus" items="${fns:getDictList('qy_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="advice:smAdvise:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>