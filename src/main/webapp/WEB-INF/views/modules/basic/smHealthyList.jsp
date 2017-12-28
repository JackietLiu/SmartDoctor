<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>健康处方管理</title>
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
		<li class="active"><a href="${ctx}/basic/smHealthy/">健康处方列表</a></li>
		<shiro:hasPermission name="basic:smHealthy:edit"><li><a href="${ctx}/basic/smHealthy/form">健康处方添加</a></li></shiro:hasPermission>
	</ul>
<%-- 	<form:form id="searchForm" modelAttribute="smHealthy" action="${ctx}/basic/smHealthy/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>饮食</th>
				<th>建议</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="basic:smHealthy:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smHealthy">
			<tr>
				<td><a href="${ctx}/basic/smHealthy/form?id=${smHealthy.id}">
					${smHealthy.food}
				</a></td>
				<td>
					${smHealthy.suggest}
				</td>
				<td>
					<fmt:formatDate value="${smHealthy.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${smHealthy.remarks}
				</td>
				<shiro:hasPermission name="basic:smHealthy:edit"><td>
    				<a href="${ctx}/basic/smHealthy/form?id=${smHealthy.id}">修改</a>
					<a href="${ctx}/basic/smHealthy/delete?id=${smHealthy.id}" onclick="return confirmx('确认要删除该健康处方吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>