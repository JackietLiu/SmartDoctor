<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病因和治疗建议管理</title>
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
		<li ><a href="${ctx}/basic/smIllreason/listAll">病因列表</a></li>
		<li class="active"><a href="${ctx}/advice/smIllreasonAdvice/?illrea.id=${smIllreasonAdvice.illrea.id}">治疗建议列表</a></li>
		<shiro:hasPermission name="advice:smIllreasonAdvice:edit"><li><a href="${ctx}/advice/smIllreasonAdvice/form?illrea.id=${smIllreasonAdvice.illrea.id}">治疗建议添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smIllreasonAdvice" action="${ctx}/advice/smIllreasonAdvice/?illrea.id=${smIllreasonAdvice.illrea.id}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>病因：</label>
				<form:input path="illrea.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>治疗建议：</label>
				<select name="advise.id"  style="width:250px;">  
					<option value="" >--请选择--</option>
					<c:forEach items="${smAdviseList }" var="adv" >
						<option value="${adv.id }" >${adv.name }-${adv.description }</option>
					</c:forEach>
			    </select>
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
			<th class="span1" style="text-align: center;">序号</th>
				<th>病因</th>
				<th>治疗建议</th>
				<th>启用状态</th>
				<th>创建日期</th>
				<shiro:hasPermission name="advice:smIllreasonAdvice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smIllreasonAdvice" varStatus="serial">
			<tr>
			<td style="text-align: center; ">
				${serial.count}
				</td>
				<td><a href="${ctx}/advice/smIllreasonAdvice/form?id=${smIllreasonAdvice.id}">
					${smIllreasonAdvice.illrea.name}
				</a></td>
				<td>
					${smIllreasonAdvice.advise.name}
				</td>
				<td>
					${fns:getDictLabel(smIllreasonAdvice.qyStatus, 'qy_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${smIllreasonAdvice.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="advice:smIllreasonAdvice:edit"><td>
    				<a href="${ctx}/advice/smIllreasonAdvice/form?id=${smIllreasonAdvice.id}">修改</a>
					<a href="${ctx}/advice/smIllreasonAdvice/delete?id=${smIllreasonAdvice.id}" onclick="return confirmx('确认要删除该病因和治疗建议吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>