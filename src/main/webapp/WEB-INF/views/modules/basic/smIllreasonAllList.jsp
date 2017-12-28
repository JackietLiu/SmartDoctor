<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病因管理管理</title>
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
		<li class="active"><a href="${ctx}/basic/smIllreason/listAll">病因列表</a></li>
		<li><a href="${ctx}/smrelation/smRelation/form">病因问题绑定</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="smIllreason" action="${ctx}/basic/smIllreason/listAll" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>症状：</label>
				<select name="sym.id"  style="width:250px;" >  
					<option value="" >--请选择--</option>
					<c:forEach items="${sympList }" var="symp" >
						<option value="${symp.id }" <c:if test="${symp.id ==smIllreason.sym.id }">selected</c:if>  >${symp.name }</option>
					</c:forEach>
			    </select>
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
				<th>症状</th>
				<th>病因</th>
				<th style="width:65%;">病因描述</th>
				<th>启用</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smIllreason" varStatus="serial">
			<tr>
			<td style="text-align: center; ">
				${serial.count}
				</td>
				<td>
					${smIllreason.sym.name}
				</td>
				<td>
					${smIllreason.name}
				</td>
				<td>
					${smIllreason.description}
				</td>
				<td>
					${fns:getDictLabel(smIllreason.qyStatus, 'qy_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${smIllreason.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				
				<%-- <shiro:hasPermission name="match:smIllreasonMatch:edit">
					<a href="${ctx}/match/smIllreasonMatch/?illrea.id=${smIllreason.id}">提问方案管理</a>
				</shiro:hasPermission> --%>
    				
   				<shiro:hasPermission name="advice:smIllreasonAdvice:edit">
   					<a href="${ctx}/advice/smIllreasonAdvice?illrea.id=${smIllreason.id}">治疗建议管理</a>
   				</shiro:hasPermission>
   				
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>