<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病症管理管理</title>
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
		<li class="active"><a href="${ctx}/basic/smSymptom/">病症列表</a></li>
		<shiro:hasPermission name="basic:smSymptom:edit"><li><a href="${ctx}/basic/smSymptom/form">病症添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smSymptom" action="${ctx}/basic/smSymptom/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>病症名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
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
				<th  class="span1 " style="text-align: center; ">序号</th>
				<th>病症名称</th>
				<th>排序</th>
				<th>启用状态</th>
				<shiro:hasPermission name="basic:smSymptom:edit"><th >操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smSymptom" varStatus="serial">
			<tr>
				<td style="text-align: center; ">
				${serial.count}
				</td>
				<td><a href="${ctx}/basic/smSymptom/form?id=${smSymptom.id}">
					${smSymptom.name}
				</a></td>
				<td>
				${smSymptom.sort}
				</td>
				<td>
					${fns:getDictLabel(smSymptom.qyStatus, 'qy_status', '')}
				</td>
				<td>
					<shiro:hasPermission name="config:smSymQues:view">
	    				<%-- <a href="${ctx}/config/smSymQues/questionform?sym.id=${smSymptom.id}">第一个问题</a> --%>
	    				<a href="${ctx}/config/smSymQues/?sym.id=${smSymptom.id}">配置问题</a>
    				</shiro:hasPermission>
    				
    				<shiro:hasPermission name="basic:smIllreason:view">
    				<a href="${ctx}/basic/smIllreason/?sym.id=${smSymptom.id}">配置病因</a>
    				</shiro:hasPermission>
    				
    				<shiro:hasPermission name="basic:smSymptom:edit">
	    				<a href="${ctx}/basic/smSymptom/form?id=${smSymptom.id}">修改</a>
						<a href="${ctx}/basic/smSymptom/delete?id=${smSymptom.id}" onclick="return confirmx('确认要删除该病症管理吗？', this.href)">删除</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>