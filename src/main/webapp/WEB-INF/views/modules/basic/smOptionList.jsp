<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>问题选项管理</title>
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
		<li class="active"><a href="${ctx}/basic/smOption/?question.id=${questionId}">选项列表</a></li>
		<shiro:hasPermission name="basic:smOption:edit"><li><a href="${ctx}/basic/smOption/form?question.id=${questionId}">选项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smOption" action="${ctx}/basic/smOption/?question.id=${questionId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>问题ID：</label>
				<form:select path="question.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li><label>选项标题：${question.id}</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>启用状态：</label>
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
				<!-- <th>问题ID</th> -->
				<th class="span1" style="text-align: center;">序号</th>
				<th>选项标题</th>
				<th>排序</th>
				<th>启用状态</th>
				<th>最后选项（特殊）</th>
				<shiro:hasPermission name="basic:smOption:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smOption" varStatus="serial">
			<tr>
				<%-- <td>
					${fns:getDictLabel(smOption.question.id, '', '')}
				</td> --%>
				<td style="text-align: center; ">
				${serial.count}
				</td>
				<td>
					${smOption.name}
				</td>
				<td>
					${smOption.sort}
				</td>
				<td>
					${fns:getDictLabel(smOption.qyStatus, 'qy_status', '')}
				</td>
				<td>
					${fns:getDictLabel(smOption.isLast, 'is_last', '')}
				</td>
				<shiro:hasPermission name="basic:smOption:edit"><td>
    				<a href="${ctx}/basic/smOption/form?id=${smOption.id}&question.id=${smOption.question.id}">修改</a>
					<a href="${ctx}/basic/smOption/delete?id=${smOption.id}&question.id=${smOption.question.id}" onclick="return confirmx('确认要删除该问题选项吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>