<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>配置病因管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/smrelation/smSymQuesIllreason/?smSymQues.id=${smSymQuesIllreason.smSymQues.id}">配置病因列表</a></li>
		<shiro:hasPermission name="smrelation:smSymQuesIllreason:edit">
		<li><a href="${ctx}/smrelation/smSymQuesIllreason/form?smSymQues.id=${smSymQuesIllreason.smSymQues.id}">配置病因添加</a>
		</li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smSymQuesIllreason" action="${ctx}/smrelation/smSymQuesIllreason/?smSymQues.id=${smSymQuesIllreason.smSymQues.id}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>sm_inquiry_ques的ID：</label>
				<form:input path="smInquiryQues.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>问题ID：</label>
				<form:input path="question.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>选项ID：</label>
				<form:input path="option.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>病因ID：</label>
				<form:input path="illreaon.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>病症ID：</label>
				<form:input path="symptom.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> --%>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>问题</th>
				<th>选项</th>
				<th>病因</th>
				<shiro:hasPermission name="smrelation:smSymQuesIllreason:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smSymQuesIllreason">
			<tr>
				<td>
					${smSymQuesIllreason.question.name}
				</td>
				<td>
					${smSymQuesIllreason.option.name}
				</td>
				<td>
					${smSymQuesIllreason.illreaon.name}
				</td>
				<shiro:hasPermission name="smrelation:smSymQuesIllreason:edit"><td>
    				<%-- <a href="${ctx}/smrelation/smSymQuesIllreason/form?id=${smSymQuesIllreason.id}">修改</a> --%>
					<a href="${ctx}/smrelation/smSymQuesIllreason/delete?id=${smSymQuesIllreason.id}" onclick="return confirmx('确认要删除该配置病因吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>