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
/* 			$("input[name='state']").click(function () {
                if($(this).val()==1){
                	//$("#quesDivId").hide();
                	//$("#optDivId").hide();
                	$("#illId").hide();
                }else if($(this).val()==2){
                	//$("#quesDivId").hide();
                	//$("#optDivId").hide();
                	$("#illId").show();
                }
           });   */
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
			closeLoading();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/basic/smSymptom/">病症列表</a></li>
		<li><a href="${ctx}/config/smSymQues/?sym.id=${smSymQues.sym.id}">病症配置提问列表</a></li>
		<li class="active"><a href="${ctx}/config/smSymQues/form?id=${smSymQues.id}&sym.id=${smSymQues.sym.id}">病症或病因第一题配置<shiro:hasPermission name="config:smSymQues:edit">${not empty smSymQues.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="config:smSymQues:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="smSymQues" action="${ctx}/config/smSymQues/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="sym.id" value="${smSymQues.sym.id }"/>
		<!-- <input type="hidden" name="state" value = "1"> --><!-- 配置病症问题的类型，1代表病症开始配置2代表病因开始配置3代表由答案配置问题 -->
		<sys:message content="${message}"/>	
		<%-- <sys:message content="问题类型决定配置，选择病症第一题，则只需选择【对应问题】，选择病因第一题，则需选择【对应问题】、【病因】。选择第三个，则需选择【答案】、【对应问题】"/>	 --%>
		
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>问题配置类型：</label>
			<div class="controls">
				<form:radiobuttons path="state" id="state" items="${fns:getDictList('state')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		
			
		<div class="control-group">
			<label class="control-label">第一题：</label>
			<div class="controls">
				<select name="question.id"  style="width:290px;" class="required">  
					<option value="" >--请选择--</option>
					<c:forEach items="${questList }" var="quest" >
						<option value="${quest.id }" <c:if test="${quest.id ==smSymQues.question.id }">selected</c:if>  >${quest.name }</option>
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
						<option value="${ill.id }" <c:if test="${ill.id ==smSymQues.illreason.id }">selected</c:if>  >${ill.name }</option>
					</c:forEach>
			    </select>
			</div>
		</div>
	  <div class="control-group">
	 		<label class="control-label">所占比重：</label>
			  <form:input path="percent" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
		   </div>
		
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
			<shiro:hasPermission name="config:smSymQues:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>