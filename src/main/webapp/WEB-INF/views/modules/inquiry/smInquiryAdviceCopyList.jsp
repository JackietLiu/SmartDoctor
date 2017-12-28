<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医师处方管理</title>
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
<%--  	 <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inquiry/smInquiryAdviceCopy/">医师处方列表</a></li>
		<shiro:hasPermission name="inquiry:smInquiryAdviceCopy:edit"><li><a href="${ctx}/inquiry/smInquiryAdviceCopy/form">医师处方添加</a></li></shiro:hasPermission>	</ul>
	<form:form id="searchForm" modelAttribute="smInquiryAdviceCopy" action="${ctx}/inquiry/smInquiryAdviceCopy/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>治疗建议名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> 
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>治疗建议名称</th>
				<th>描述</th>
				<shiro:hasPermission name="inquiry:smInquiryAdviceCopy:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smInquiryAdviceCopy">
			<tr>
				<td><a href="${ctx}/inquiry/smInquiryAdviceCopy/form?id=${smInquiryAdviceCopy.id}">
					${smInquiryAdviceCopy.name}
				</a></td>
				<td>
				${smInquiryAdviceCopy.description}
				</td>
				<shiro:hasPermission name="inquiry:smInquiryAdviceCopy:edit"><td>
    				<a href="${ctx}/inquiry/smInquiryAdviceCopy/form?id=${smInquiryAdviceCopy.id}">修改</a>
					<a href="${ctx}/inquiry/smInquiryAdviceCopy/delete?id=${smInquiryAdviceCopy.id}" onclick="return confirmx('确认要删除该医师处方吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
<%-- 	<div class="pagination">${page}</div> --%>
</body>
</html>