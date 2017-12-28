<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>方案配置选项管理</title>
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
		<li class="active"><a href="${ctx}/match/smMatchOpt/?matc.id=${smMatchOpt.matc.id}">问题选项列表</a></li>
		<shiro:hasPermission name="match:smMatchOpt:edit"><li><a href="${ctx}/match/smMatchOpt/form?matc.id=${smMatchOpt.matc.id}">问题选项添加</a></li></shiro:hasPermission>
	</ul>
	<%-- <form:form id="searchForm" modelAttribute="smMatchOpt" action="${ctx}/match/smMatchOpt/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>方案：</label>
				<form:input path="matc.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>症状：</label>
				<form:input path="sym.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>问题：</label>
				<form:input path="ques.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>选项：</label>
				<form:input path="opt.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>启用1正常2停用：</label>
				<form:radiobuttons path="qyStatus" items="${fns:getDictList('qy_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th class="span1" style="text-align: center;">序号</th>
				<!-- <th>方案</th>
				<th>症状</th> -->
				<th>问题</th>
				<th>选项</th>
				<th>启用状态</th>
				<th>创建日期</th>
				<th>备注</th>
				<shiro:hasPermission name="match:smMatchOpt:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smMatchOpt" varStatus="serial">
			<tr>
				<%-- <td><a href="${ctx}/match/smMatchOpt/form?id=${smMatchOpt.id}">
					${smMatchOpt.matc.id}
				</a></td>
				<td>
					${smMatchOpt.sym.id}
				</td> --%>
				<td style="text-align: center; ">
				${serial.count}
				</td>
				<td>
					${smMatchOpt.ques.name}
				</td>
				<td>
					[${smMatchOpt.opt.sort}]${smMatchOpt.opt.name}
				</td>
				<td>
					${fns:getDictLabel(smMatchOpt.qyStatus, 'qy_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${smMatchOpt.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${smMatchOpt.remarks}
				</td>
				<shiro:hasPermission name="match:smMatchOpt:edit"><td>
    				<a href="${ctx}/match/smMatchOpt/form?id=${smMatchOpt.id}&matc.id=${smMatchOpt.matc.id}">修改</a>
					<a href="${ctx}/match/smMatchOpt/delete?id=${smMatchOpt.id}&matc.id=${smMatchOpt.matc.id}" onclick="return confirmx('确认要删除该方案配置选项吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>