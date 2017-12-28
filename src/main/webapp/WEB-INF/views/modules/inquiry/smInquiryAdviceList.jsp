<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>询诊治疗建议管理</title>
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
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inquiry/smInquiryAdvice/">询诊治疗建议列表</a></li>
		<shiro:hasPermission name="inquiry:smInquiryAdvice:edit"><li><a href="${ctx}/inquiry/smInquiryAdvice/form">询诊治疗建议添加</a></li></shiro:hasPermission>
	</ul> --%>
	<form:form id="searchForm" modelAttribute="smInquiryAdvice" action="${ctx}/inquiry/smInquiryAdvice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<%-- 	<li><label>询诊ID：</label>
				<form:input path="inqu.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>结果：</label>
				<form:radiobuttons path="reStatus" items="${fns:getDictList('re_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>建议ID：</label>
				<form:input path="adv.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>治疗建议名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li> --%>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>询诊ID</th>
				<th>结果</th>
				<th>建议ID</th> -->
				<th>治疗建议名称</th>
				<th>描述</th>
				<%-- <shiro:hasPermission name="inquiry:smInquiryAdvice:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smInquiryAdvice">
			<tr>
				<%-- <td><a href="${ctx}/inquiry/smInquiryAdvice/form?id=${smInquiryAdvice.id}">
					${smInquiryAdvice.inqu.id}
				</a></td>
				<td>
					${fns:getDictLabel(smInquiryAdvice.reStatus, 're_status', '')}
				</td>
				<td>
					${smInquiryAdvice.adv.id}
				</td> --%>
				<td>
					${smInquiryAdvice.name}
				</td>
				<td>
				${smInquiryAdvice.description}
				</td>
				<%-- <shiro:hasPermission name="inquiry:smInquiryAdvice:edit"><td>
    				<a href="${ctx}/inquiry/smInquiryAdvice/form?id=${smInquiryAdvice.id}">修改</a>
					<a href="${ctx}/inquiry/smInquiryAdvice/delete?id=${smInquiryAdvice.id}" onclick="return confirmx('确认要删除该询诊治疗建议吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>