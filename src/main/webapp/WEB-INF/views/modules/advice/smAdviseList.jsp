<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>治疗建议管理</title>
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
		<li class="active"><a href="${ctx}/advice/smAdvise/">治疗建议列表</a></li>
		<shiro:hasPermission name="advice:smAdvise:edit"><li><a href="${ctx}/advice/smAdvise/form">治疗建议添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smAdvise" action="${ctx}/advice/smAdvise/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
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
				<th>治疗建议名称</th>
				<th>治疗建议描述</th>
				<th>药名</th>
				<th>剂量</th>
				<th>疗程</th>
				<th>健康饮食</th>
				<th>健康建议</th>
				<th>下次来诊</th>
				<th>启用</th>
				<th>创建日期</th>
				<shiro:hasPermission name="advice:smAdvise:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smAdvise" varStatus="serial">
			<tr>
			<td style="text-align: center; ">
				${serial.count}
				</td>
				<td><a href="${ctx}/advice/smAdvise/form?id=${smAdvise.id}">
					${smAdvise.name}
				</a></td>
				<td>
					${smAdvise.description}
				</td>
				<td>
					${smAdvise.med.medName} 
				</td>
				<td>
					${smAdvise.med.dose}(单位：${smAdvise.med.doseUnit})
				</td>
				 <td>
				   ${smAdvise.period} (单位：${smAdvise.periodUnit})
				 </td>
                <td>${smAdvise.heath.food}</td>
                <td>${smAdvise.heath.suggest}</td>
                <td>
                ${smAdvise.dtNextvisit}
                </td>
                
				<td>
					${fns:getDictLabel(smAdvise.qyStatus, 'qy_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${smAdvise.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="advice:smAdvise:edit"><td>
    				<a href="${ctx}/advice/smAdvise/form?id=${smAdvise.id}">修改</a>
					<a href="${ctx}/advice/smAdvise/delete?id=${smAdvise.id}" onclick="return confirmx('确认要删除该治疗建议吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>