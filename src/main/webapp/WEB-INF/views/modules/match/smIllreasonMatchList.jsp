<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病因方案管理</title>
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
		
		function toMatchList(id){
			top.$.jBox.open(
					"iframe:${ctx}/match/smMatchOpt?matc.id="+id , 
					"问题选项列表",
					1000,
					$(top.document).height()-70,
					{
						top : "20px",
						iframeScrolling: "no",
						buttons: { '关闭': true} 
					}
				);
		}
		function toCommentList(id){
			top.$.jBox.open(
					"iframe:${ctx}/comment/smComment?match.id="+id , 
					"专家点评列表",
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
		<li ><a href="${ctx}/basic/smIllreason/listAll">病因列表</a></li>
		<li class="active"><a href="${ctx}/match/smIllreasonMatch/?illrea.id=${smIllreasonMatch.illrea.id}">方案列表</a></li>
		<shiro:hasPermission name="match:smIllreasonMatch:edit"><li><a href="${ctx}/match/smIllreasonMatch/form?illrea.id=${smIllreasonMatch.illrea.id}">方案添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smIllreasonMatch" action="${ctx}/match/smIllreasonMatch/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- 病因 -->
		<input type="hidden" name="illrea.id" value="${smIllreasonMatch.illrea.id}"/>
		<ul class="ul-form">
			<li><label>方案名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%-- <li><label>病因：</label>
				<form:input path="illrea.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
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
				<th>方案名称</th>
				<th>病因</th>
				<th>启用状态</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="match:smIllreasonMatch:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smIllreasonMatch" varStatus="serial">
			<tr>
			<td style="text-align: center; ">
				${serial.count}
				</td>
				<td><a href="${ctx}/match/smIllreasonMatch/form?id=${smIllreasonMatch.id}">
					${smIllreasonMatch.name}
				</a></td>
				<td>
					${smIllreasonMatch.illrea.name}
				</td>
				<td>
					${fns:getDictLabel(smIllreasonMatch.qyStatus, 'qy_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${smIllreasonMatch.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${smIllreasonMatch.remarks}
				</td>
				<td>
				
				<shiro:hasPermission name="match:smMatchOpt:edit">
				<a onclick="toMatchList('${smIllreasonMatch.id}');" href="#">配置问题选项</a>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="comment:smComment:edit">
				<a onclick="toCommentList('${smIllreasonMatch.id}');" href="#">专家点评</a>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="match:smIllreasonMatch:edit">
    				<a href="${ctx}/match/smIllreasonMatch/form?id=${smIllreasonMatch.id}&illrea.id=${smIllreasonMatch.illrea.id}">修改</a>
					<a href="${ctx}/match/smIllreasonMatch/delete?id=${smIllreasonMatch.id}&illrea.id=${smIllreasonMatch.illrea.id}" onclick="return confirmx('确认要删除该病因方案吗？', this.href)">删除</a>
				</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>