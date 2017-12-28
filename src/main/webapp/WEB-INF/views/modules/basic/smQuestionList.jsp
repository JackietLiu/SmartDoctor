<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>问题管理管理</title>
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
		function toQuestionList(id){
			top.$.jBox.open(
					"iframe:${ctx}/basic/smOption?question.id="+id , 
					"选项列表",
					1000,
					$(top.document).height()-70,
					{
						top : "20px",
						iframeScrolling: "no",
						buttons: { '关闭': true} 
					}
				);
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/basic/smQuestion/">问题管理列表</a></li>
		<shiro:hasPermission name="basic:smQuestion:edit"><li><a href="${ctx}/basic/smQuestion/form">问题管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smQuestion" action="${ctx}/basic/smQuestion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>问题标题：</label>
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
				<th>问题标题</th>
				<th>创建日期</th>
				<th>启用状态</th>
				<shiro:hasPermission name="basic:smQuestion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smQuestion">
			<tr>
				<td><a href="${ctx}/basic/smQuestion/form?id=${smQuestion.id}">
					${smQuestion.name}
				</a></td>
				<td>
					<fmt:formatDate value="${smQuestion.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(smQuestion.qyStatus, 'qy_status', '')}
				</td>
				<shiro:hasPermission name="basic:smQuestion:edit"><td>
				    <a onClick="toQuestionList('${smQuestion.id}');" href="#">选项列表</a>
    				<a href="${ctx}/basic/smQuestion/form?id=${smQuestion.id}">修改</a>
					<a href="${ctx}/basic/smQuestion/delete?id=${smQuestion.id}" onclick="return confirmx('确认要删除该问题管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>