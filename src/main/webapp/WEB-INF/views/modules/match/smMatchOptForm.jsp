<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>方案配置选项管理</title>
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
		function changeQues(){
			var quesid = $("#quesId").val();
			loading();
			 var optId = document.getElementById("optId");
			 optId.length=1;
			if(''!=quesid){
				$.ajax({
				 	type : 'post',
					url :  '${ctx}/basic/smOption/getOptList',
					data : {
						quesid : quesid
					},
					success : function(data) {
						var optList = data.items;
						 for(var i = 0; i < optList.length; i++){
							 optId.options.add(new Option(optList[i].name, optList[i].id));
			          	 }
						 closeLoading();
					},
					error:function(data){
						 closeLoading();
					}
				});
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/match/smMatchOpt/?matc.id=${smMatchOpt.matc.id}">问题选项列表</a></li>
		<li class="active"><a href="${ctx}/match/smMatchOpt/form?id=${smMatchOpt.id}&matc.id=${smMatchOpt.matc.id}">问题选项<shiro:hasPermission name="match:smMatchOpt:edit">${not empty smMatchOpt.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="match:smMatchOpt:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smMatchOpt" action="${ctx}/match/smMatchOpt/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="matc.id" value="${smMatchOpt.matc.id}"/>
		<sys:message content="${message}"/>		
		<%-- <div class="control-group">
			<label class="control-label">方案：</label>
			<div class="controls">
				<form:input path="matc.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">症状：</label>
			<div class="controls">
				<form:input path="sym.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">问题：</label>
			<div class="controls">
				<select name="ques.id" id="quesId" style="width:250px;" class="required" onchange="changeQues();">  
					<option value="" >--请选择--</option>
					<c:forEach items="${symQuesList }" var="symQuest1" varStatus="statuss">
						<option value="${symQuest1.question.id }" <c:if test="${symQuest1.question.id ==smMatchOpt.ques.id }">selected</c:if>  >[${symQuest1.sort}]${symQuest1.question.name }</option>
					</c:forEach>
			    </select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">选项：</label>
			<div class="controls">
				<select name="opt.id" id="optId" class="input-xlarge required" style="width:250px;"> 
				      <option value="">--请选择--</option>
				      <c:forEach items="${optionList }" var="option" varStatus="statuss">
						<option value="${option.id }" <c:if test="${option.id ==smMatchOpt.opt.id }">selected</c:if>  >[${option.sort}]${option.name }</option>
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
		<%-- <div class="control-group">
			<label class="control-label">最后一题：</label>
			<div class="controls">
				<form:radiobuttons path="isLast" items="${fns:getDictList('is_last')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="match:smMatchOpt:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>