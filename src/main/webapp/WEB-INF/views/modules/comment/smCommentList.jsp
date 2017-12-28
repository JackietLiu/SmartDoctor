<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专家点评管理</title>
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
		<li class="active"><a href="${ctx}/comment/smComment/?match.id=${smComment.match.id}">专家点评列表</a></li>
		<shiro:hasPermission name="comment:smComment:edit"><li><a href="${ctx}/comment/smComment/form?match.id=${smComment.match.id}">专家点评添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smComment" action="${ctx}/comment/smComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" name="match.id" value="${smComment.match.id }"/>
		<ul class="ul-form">
			<%-- <li><label>配置方案ID：</label>
				<form:input path="match.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> 
			<li><label>评论人类型：</label>
				<form:radiobuttons path="coType" items="${fns:getDictList('co_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>审核状态：</label>
				<form:radiobuttons path="auditStatus" items="${fns:getDictList('audit_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>医生点评：</label>
				<form:radiobuttons path="coStatusDoc" items="${fns:getDictList('co_status_doc')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>病人点评：</label>
				<form:radiobuttons path="coStatusSick" items="${fns:getDictList('co_status_sick')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>--%>
			<li><label>评论人：</label>
				<form:input path="createBy.name" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>配置方案</th>
				<th>评论人类型</th>
				<!-- <th>审核状态</th> -->
				<th>医生点评</th>
				<!-- <th>病人点评</th> -->
				<th>评论时间</th>
				<th>评论人</th>
				<shiro:hasPermission name="comment:smComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smComment" varStatus="serial">
			<tr>
			<td style="text-align: center;">${serial.count }</td>
				<td><a href="${ctx}/comment/smComment/form?id=${smComment.id}&match.id=${smComment.match.id}">
					${smComment.match.name}
				</a></td>
				<td>
					${fns:getDictLabel(smComment.coType, 'co_type', '')}
				</td>
				<%-- <td>
					${fns:getDictLabel(smComment.auditStatus, 'audit_status', '')}
				</td> --%>
				<td>
					${fns:getDictLabel(smComment.coStatusDoc, 'co_status_doc', '')}
				</td>
			<%-- 	<td>
					${fns:getDictLabel(smComment.coStatusSick, 'co_status_sick', '')}
				</td> --%>
				<td>
					<fmt:formatDate value="${smComment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${smComment.createBy.name}
				</td>
				<shiro:hasPermission name="comment:smComment:edit"><td>
    				<a href="${ctx}/comment/smComment/form?id=${smComment.id}&match.id=${smComment.match.id}">修改</a>
					<a href="${ctx}/comment/smComment/delete?id=${smComment.id}&match.id=${smComment.match.id}" onclick="return confirmx('确认要删除该专家点评吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>