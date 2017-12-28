<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病人答题明细管理</title>
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
	<%--<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inquiry/smInquiryQues/">病人答题明细列表</a></li>
		 <shiro:hasPermission name="inquiry:smInquiryQues:edit"><li><a href="${ctx}/inquiry/smInquiryQues/form">病人答题明细添加</a></li></shiro:hasPermission> 
	</ul>--%>
	<form:form id="searchForm" modelAttribute="smInquiryQues" action="${ctx}/inquiry/smInquiryQues/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>询诊ID：</label>
				<form:input path="inqu.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>问题ID：</label>
				<form:input path="question.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>问题名称：</label>
				<form:input path="quesName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>选项ID：</label>
				<form:input path="opt.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>选项名称：</label>
				<form:input path="optName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li> --%>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>询诊ID</th> -->
				<th>排序</th>
				<th>问题名称</th>
				<th>选项名称</th>
				<th>答题时间</th>
				<%-- <shiro:hasPermission name="inquiry:smInquiryQues:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smInquiryQues">
			<tr>
				<%-- <td><a href="${ctx}/inquiry/smInquiryQues/form?id=${smInquiryQues.id}">
					${smInquiryQues.inqu.id}
				</a></td> --%>
				<td>
					${smInquiryQues.sort}
				</td>
				<td>
					${smInquiryQues.quesName}
				</td>
				<td>
					${smInquiryQues.optName}
				</td>
				<td>
					<fmt:formatDate value="${smInquiryQues.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <shiro:hasPermission name="inquiry:smInquiryQues:edit"><td>
    				<a href="${ctx}/inquiry/smInquiryQues/form?id=${smInquiryQues.id}">修改</a>
					<a href="${ctx}/inquiry/smInquiryQues/delete?id=${smInquiryQues.id}" onclick="return confirmx('确认要删除该病人答题明细吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>