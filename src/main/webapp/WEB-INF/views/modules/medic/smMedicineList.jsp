<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品管理</title>
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
		<li class="active"><a href="${ctx}/medic/smMedicine/">药品列表</a></li>
		<shiro:hasPermission name="medic:smMedicine:edit"><li><a href="${ctx}/medic/smMedicine/form">药品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smMedicine" action="${ctx}/medic/smMedicine/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>药品名称：</label>
				<form:input path="medName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>药品名称</th>
				<th>剂量</th>				
				<th>剂量单位</th>
				<th>服药</th>
				<th>服药单位</th>
				<th>不良反应</th>
				<th>起效时间(min)</th>
				<th>持续时间</th>				
				<th>备注</th>
				<shiro:hasPermission name="medic:smMedicine:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smMedicine">
			<tr>
				<td><a href="${ctx}/medic/smMedicine/form?id=${smMedicine.id}">
					${smMedicine.medName}
				</a></td>
				<td>
					${smMedicine.dose}
				</td>
				<td>
					${smMedicine.doseUnit}
				</td>
				<td>
					${smMedicine.medication}
				</td>
				
				<td>
					${smMedicine.medicationUnit}
				</td>
				<td>
					${smMedicine.effects}
				</td>
				<td>
					${smMedicine.workTime}
				</td>
				<td>
					${smMedicine.durationTime}
				</td>
			<%-- 	<td>
					<fmt:formatDate value="${smMedicine.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<td>
					${smMedicine.remarks}
				</td>
				<shiro:hasPermission name="medic:smMedicine:edit"><td>
    				<a href="${ctx}/medic/smMedicine/form?id=${smMedicine.id}">修改</a>
					<a href="${ctx}/medic/smMedicine/delete?id=${smMedicine.id}" onclick="return confirmx('确认要删除该药品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>