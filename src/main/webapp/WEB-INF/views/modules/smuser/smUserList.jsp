<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>患者管理</title>
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
		<li class="active"><a href="${ctx}/smuser/smUser/">患者列表</a></li>
		<shiro:hasPermission name="smuser:smUser:edit"><li><a href="${ctx}/smuser/smUser/form">患者添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smUser" action="${ctx}/smuser/smUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>卡号：</label>
				<form:input path="cardNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:select path="sex" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>卡号</th>
				<th>性别</th>
				<th>姓名</th>
				<th>出生日期</th>
				<shiro:hasPermission name="smuser:smUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smUser">
			<tr>
				<td><a href="${ctx}/smuser/smUser/form?id=${smUser.id}">
					${smUser.cardNo}
				</a></td>
				<td>
					${fns:getDictLabel(smUser.sex, 'sex', '')}
				</td>
				<td>
					${smUser.name}
				</td>
				<td>
					<fmt:formatDate value="${smUser.birth}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="smuser:smUser:edit"><td>
    				<a href="${ctx}/smuser/smUser/form?id=${smUser.id}">修改</a>
					<a href="${ctx}/smuser/smUser/delete?id=${smUser.id}" onclick="return confirmx('确认要删除该患者吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>