<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>询诊病因管理</title>
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
		<li class="active"><a href="${ctx}/inquiry/smInquiryIllreason/">询诊病因列表</a></li>
		<shiro:hasPermission name="inquiry:smInquiryIllreason:edit"><li><a href="${ctx}/inquiry/smInquiryIllreason/form">询诊病因添加</a></li></shiro:hasPermission>
	</ul> --%>
	<form:form id="searchForm" modelAttribute="smInquiryIllreason" action="${ctx}/inquiry/smInquiryIllreason/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>用户询诊表ID：</label>
				<form:input path="inqu.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>结果：</label>
				<form:radiobuttons path="reStatus" items="${fns:getDictList('re_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>病因ID：</label>
				<form:input path="illrea.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>病因标题：</label>
				<form:input path="illreaName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
 --%>		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>用户询诊表ID</th> -->
				<!-- th>结果</th>
				<th>病因ID</th> -->
				<th>病因标题</th>
				<th>病因描述</th>
				<!-- <th>update_date</th> -->
				<%-- <shiro:hasPermission name="inquiry:smInquiryIllreason:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smInquiryIllreason">
			<tr>
				<%-- <td><a href="${ctx}/inquiry/smInquiryIllreason/form?id=${smInquiryIllreason.id}">
					${smInquiryIllreason.inqu.id}
				</a></td> --%>
				<%-- <td>
					${fns:getDictLabel(smInquiryIllreason.reStatus, 're_status', '')}
				</td>
				<td>
					${smInquiryIllreason.illrea.id}
				</td> --%>
				<td>
					${smInquiryIllreason.illreaName}
				</td>
				<td>
					${smInquiryIllreason.illreaDesc}
				</td>
				<%-- <td>
					<fmt:formatDate value="${smInquiryIllreason.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<%-- <shiro:hasPermission name="inquiry:smInquiryIllreason:edit"><td>
    				<a href="${ctx}/inquiry/smInquiryIllreason/form?id=${smInquiryIllreason.id}">修改</a>
					<a href="${ctx}/inquiry/smInquiryIllreason/delete?id=${smInquiryIllreason.id}" onclick="return confirmx('确认要删除该询诊病因吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>