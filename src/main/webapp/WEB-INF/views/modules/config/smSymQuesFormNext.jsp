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
			changeQues();
		});
		
		function toSaveQuestion(){
			top.$.jBox.open(
					"iframe:${ctx}/basic/smQuestion/toSaveForm?tabPageId=mainFrame" , 
					"问题添加",
					1000,
					$(top.document).height()-140,
					{
						top : "20px",
						iframeScrolling: "yes",
						buttons: { '关闭': true} 
					}
				);
		}
		function refreshList(){
			window.location.href = "${ctx}/config/smSymQues/form?sym.id=${smSymQues.sym.id}&tabPageId=mainFrame";
		}
		
		function changeQues(){
			var quesid = $("#quesId").val();
			console.log("quesid:"+quesid);
			loading();
			if(quesid!=undefined){
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
			closeLoading();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/basic/smSymptom/">病症列表</a></li>
		<li><a href="${ctx}/config/smSymQues/?sym.id=${smSymQues.sym.id}">病症配置提问列表</a></li>
		<li class="active"><a href="${ctx}/config/smSymQues/toConfigNextQues?id=${smSymQues.id}&sym.id=${smSymQues.sym.id}">答案配置问题</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smSymQues" action="${ctx}/config/smSymQues/saveNextQues" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<%-- <input type="hidden" name="sym.id" value="${smSymQues.sym.id }"/> --%>
		<%--  <input type="hidden" name="illreason.id" value="${smSymQues.illreason.id }"/> --%>		
		 <input type="hidden" name="lastQuestion.id" id="quesId" value="${smSymQues.question.id }"/>
		<sys:message content="${message}"/>	
		
		<%-- <div class="control-group" id ="quesDivId">
			<label class="control-label">已配置问题：</label>
			<div class="controls">
				<select  id ="quesId" name="lastQuestion.id" style="width:290px;" onchange="changeQues();">  
					<option value="" >--请选择--</option>
					<c:forEach items="${useQList }" var="quest" >
						<option value="${quest.question.id }" >[${quest.lastQuestion.name }：${quest.option.name }]${quest.question.name }</option>
					</c:forEach>
			    </select>
			</div>
		</div>
		div>
		</div> --%>
		<div class="control-group"  id="optDivId">
			<label class="control-label">问题：</label>
			<div class="controls">
			${smSymQues.question.name} 
			</div>
		</div>
		<div class="control-group"  id="optDivId">
			<label class="control-label">问题的选项：</label>
			<div class="controls">
				<select name="option.id"  id="optId" class="input-xlarge required" style="width:250px;" > 
				      <option value="">--请选择--</option>
				      <c:forEach items="${optionList }" var="opt" varStatus="statuss">
						<option value="${opt.id }" >[${opt.sort}]${opt.name }</option>
					  </c:forEach>
				</select>
			</div>
		</div>
			<div class="control-group">
			<label class="control-label">下一题：</label>
			<div class="controls">
				<select name="question.id"  style="width:290px;" class="required">  
					<option value="" >--请选择--</option>
					<c:forEach items="${questList }" var="quest" >
						<option value="${quest.id }" >${quest.name }</option>
					</c:forEach>
			    </select>
			    <shiro:hasPermission name="basic:smQuestion:view">
			    <input id="btnCancel" class="btn-info" type="button" value="新增问题" onclick="toSaveQuestion();"/>
			    </shiro:hasPermission>
			</div>
		</div>
		
 		 <div class="control-group"  id="illId">
			<label class="control-label">问题所属病因：</label>
			<div class="controls">
				<select name="illreason.id" style="width:290px;">  
					<option value="" >--请选择--</option>
					<c:forEach items="${illreasonList }" var="ill" >
						<option value="${ill.id }">${ill.name }</option>
					</c:forEach>
			    </select>
			</div>
		</div>  		
	 		<div class="control-group">
	 		<label class="control-label">所占比重：</label>
			  <form:input path="percent" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
		   </div>
		<div class="control-group">
			<label class="control-label">问题适用年龄段：</label>
			<div class="controls">
				<form:radiobuttons path="ageRange.id" items="${fns:getDictList('age_range')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="2" class="input-xlarge required number"/>
				<font style="color:red;" >*排序必须严格按照1、2、3……顺序填写,不可重复填写。</font>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">启用状态：</label>
			<div class="controls">
				<form:radiobuttons path="qyStatus" items="${fns:getDictList('qy_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">最后问题：</label>
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
			<shiro:hasPermission name="config:smSymQues:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>