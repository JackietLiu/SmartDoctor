<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病因管理管理</title>
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
		<li><a href="${ctx}/basic/smSymptom/">病症列表</a></li>
		<li class="active"><a href="${ctx}/basic/smIllreason/?sym.id=${smIllreason.sym.id}">病因列表</a></li>
		<shiro:hasPermission name="basic:smIllreason:edit"><li><a href="${ctx}/basic/smIllreason/form?sym.id=${smIllreason.sym.id}">病因添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smIllreason" action="${ctx}/basic/smIllreason/?sym.id=${smIllreason.sym.id}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>症状：</label>
				<form:input path="sym.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>启用：</label>
				<form:radiobuttons path="qyStatus" items="${fns:getDictList('qy_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th class="span1" style="text-align: center;">序号</th>
				<th>症状</th>
				<th>病因</th>
				<th style="width:1000px;">病因描述</th>
				<th>启用</th>
				<th>创建时间</th>
				<shiro:hasPermission name="basic:smIllreason:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smIllreason" varStatus="serial">
			<tr>
			<td style="text-align: center; ">
				${serial.count}
				</td>
				<td><a href="${ctx}/basic/smIllreason/form?id=${smIllreason.id}">
					${smIllreason.sym.name}
				</a></td>
				<td>
					${smIllreason.name}
				</td>
				<td>
					${smIllreason.description}
				</td>
				<td>
					${fns:getDictLabel(smIllreason.qyStatus, 'qy_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${smIllreason.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="basic:smIllreason:edit"><td>
    				<a href="${ctx}/basic/smIllreason/form?id=${smIllreason.id}">修改</a>
					<a href="${ctx}/basic/smIllreason/delete?id=${smIllreason.id}" onclick="return confirmx('确认要删除该病因管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>