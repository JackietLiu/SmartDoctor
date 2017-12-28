<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>关系管理</title>
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
		<li><a href="${ctx}/medic/smTmRelation/">关系列表</a></li>
		<li class="active"><a href="${ctx}/medic/smTmRelation/form?id=${smTmRelation.id}">关系<shiro:hasPermission name="medic:smTmRelation:edit">${not empty smTmRelation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="medic:smTmRelation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smTmRelation" action="${ctx}/medic/smTmRelation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">类别：</label>
			<div class="controls">
				<select  id ="type" name="type.id" style="width:290px;">  
					<option value="" >--请选择--</option>
					<c:forEach items="${typeList }" var="types" >
						<option value="${types.id }" >${types.typeName }</option>
					</c:forEach>
			    </select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">药品：</label>
			<div class="controls">
				<select  id ="med" name="med.id" style="width:290px;">  
					<option value="" >--请选择--</option>
					<c:forEach items="${medList }" var="med" >
						<option value="${med.id }" >${med.medName }</option>
					</c:forEach>
			    </select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="medic:smTmRelation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>