<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病因症状关系管理</title>
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
		<li class="active"><a href="${ctx}/smrelation/smRelation/">病因列表</a></li>
		 <shiro:hasPermission name="smrelation:smRelation:edit"><li><a href="${ctx}/smrelation/smRelation/form">病因问题绑定</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smRelation" action="${ctx}/smrelation/smRelation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>问题：</label>
				<form:input path="question.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>病因：</label>
				<form:input path="illreaon.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>病症：</label>
				<form:input path="symptom.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>病症</th>
				<th>病因</th>
				<th>对应问题</th>
				<th>所占比重</th>
				<th>更新日期</th>
				<th>备注</th>
				<shiro:hasPermission name="smrelation:smRelation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smRelation">
			<tr>
			    <td>
					${smRelation.symptom.name}
				</td>
				<td>
					${smRelation.illreaon.name}
				</td>
				<td>
					${smRelation.question.name}
				</td>
				<td>
					${smRelation.percent}
				</td>
				<td>
					<fmt:formatDate value="${smRelation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${smRelation.remarks}
				</td>
				<shiro:hasPermission name="smrelation:smRelation:edit"><td>
    				<a href="${ctx}/smrelation/smRelation/form?id=${smRelation.id}">修改</a>
					
					<a href="${ctx}/smrelation/smRelation/delete?id=${smRelation.id}" onclick="return confirmx('确认要删除该病因吗？', this.href)">删除</a>
			        <a href="${ctx}/basic/smIllreason/listAll">绑定建议处方</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>