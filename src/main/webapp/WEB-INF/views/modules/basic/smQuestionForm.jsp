<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>问题管理管理</title>
	<meta name="decorator" content="default"/>
		<script type="text/JavaScript" src="${pageContext.request.contextPath}/static/jquery-validation/1.11.1/lib/jquery.form.js"></script>
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
		
		$("#btnSubmit").click(function(){
			if($("#inputForm").valid()){
				loading();
				$("#inputForm").ajaxSubmit(ajax_option); 
			}else{
				validator.focusInvalid();
			}
		});
	});
	
	var ajax_option = {
			type: 'post', 
			url: '${ctx}/basic/smQuestion/saveToQAndO', 
			data: {},
			success: function(data) {
				closeLoading();
				if(data.success){
					jBox.tip("提交成功！");
					window.location.href="${ctx}/basic/smQuestion/list";
				}else{
					jBox.tip("提交失败！");
				}
			}
		}
	
	//增加选项行
  	function insertContentRow()
	{
  		var table = document.getElementById("optTable");
		var row = table.insertRow(-1);
       	var calname = row.insertCell(-1);
		calname.innerHTML = '<input type="text" name="optName" style="width:450px;" class="input-xlarge required" value="" />';
		var calsort = row.insertCell(-1);
		calsort.innerHTML = '<input type="text" name="optSort" style="width:100px;"  class="input-xlarge required number" value="" />';
		var calopera = row.insertCell(-1);
		calopera.innerHTML = '<a href="#" onclick="deleteCurrentRow(this)" title="删除" class="ico_del">删除</a>';
		resetContentId();
	}
	
	function resetContentId()
  	{
  		var optNames = document.getElementsByName("optName");
  		var optSorts = document.getElementsByName("optSort");
  		var lengths = optNames.length;
  		for(var i=0;i<lengths;i++)
  		{
  			optNames[i].id="optName"+i;
  			optSorts[i].id="optSort"+i;
  		}	
  	}
	//删除当前行 
  	function deleteCurrentRow(obj)
	{
		var tr=obj.parentNode.parentNode;
		var tbody=tr.parentNode;
		var submit = function (v, h, f) {
		    if (v == 'ok'){
		    	tbody.removeChild(tr);
		    	top.$.jBox.tip("删除成功！", 'info');
		    } else if (v == 'cancel'){
		    }
		    return true;
		};
		top.$.jBox.confirm("确认删除吗？", "删除确认", submit);
		
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/basic/smQuestion/">问题管理列表</a></li>
		<li class="active"><a href="${ctx}/basic/smQuestion/form?id=${smQuestion.id}">问题管理<shiro:hasPermission name="basic:smQuestion:edit">${not empty smQuestion.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="basic:smQuestion:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
<form:form id="inputForm" name="inputForm" modelAttribute="smQuestion" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">问题标题：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问题描述：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
	  <div class="control-group">
			<label class="control-label">启用状态：</label>
			<div class="controls">
				<form:radiobuttons path="qyStatus" items="${fns:getDictList('qy_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问题使用类型：</label>
			<div class="controls">
				<form:radiobuttons path="quesType" items="${fns:getDictList('sm_ques_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">问题适用年龄段：</label>
			<div class="controls">
				<form:radiobuttons path="ageRange.id" items="${fns:getDictList('age_range')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div> 
				<table>
			<tr>
				<td height="7px;">
				</td>	
				</tr>
				<tr>
				<td>
					<div style="text-align: center;">
					<span><input id="btn_addContent" type="button" class="btn-primary" value="增加选项"  onclick="insertContentRow();" /></span>
					</div>
					<table id="optTable" name="optTable" class="table table-striped table-bordered table-condensed">
						<tr>
							<th width="50%"><font class="f2">*</font>选项名称</th>
							<th width="30%"><font class="f2">*</font>排序</th>
							<th>操作</th>
						</tr>
					</table>
				</td>	
				</tr>
				
		
		</table>
	<div class="btn-toolbar breadcrumb">
		<div class="form-actions">
			<shiro:hasPermission name="basic:smQuestion:edit">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
			</shiro:hasPermission>
		</div>
		</div>
	</form:form>
</body>
</html>