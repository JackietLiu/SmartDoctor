<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提问列表</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	table td{word-break: keep-all;}
	</style>
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
					$(top.document).height()-140,
					{
						top : "20px",
						iframeScrolling: "no",
						buttons: { '关闭': true} 
					}
				);
		}
	    //最后一题配置病因，一条答题线路可以有多个病因
		function configIllreason(id){
			top.$.jBox.open(
					"iframe:${ctx}/smrelation/smRelation?smSymQues.id="+id , 
					"病因列表",
					1000,
					$(top.document).height()-140,
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
		<li><a href="${ctx}/basic/smSymptom/">病症列表</a></li>
		<li class="active"><a href="${ctx}/config/smSymQues/?sym.id=${smSymQues.sym.id}">病症提问配置列表</a></li>
		<shiro:hasPermission name="config:smSymQues:edit"><li><a href="${ctx}/config/smSymQues/form?sym.id=${smSymQues.sym.id}">病症或病因第一题配置</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smSymQues" action="${ctx}/config/smSymQues/?sym.id=${smSymQues.sym.id}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>症状ID：</label>
				<form:input path="sym.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<%-- <li><label>问题ID：</label>
				<form:input path="question.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<%-- <li><label>排序：</label>
				<form:input path="sort" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li> --%>
			<li><label>启用状态：</label>
				<form:radiobuttons path="qyStatus" items="${fns:getDictList('qy_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<%-- <li><label>第一个问题：</label>
				<form:radiobuttons path="isFirst" items="${fns:getDictList('is_first')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="span1" style="text-align: center;width:30px;" >序号</th>
				<th>症状</th>
				<!-- <th>上级编号</th> -->
				<th>问题深度</th>
				<th>上题</th>
				<th>上题选项</th>
				<th>病因</th>
				<th>配置提问</th>
				<th>第一题</th>
				<th>所占比重</th> 
				<th>启用状态</th>
				<th>问题类型</th>
				<!-- <th>最后一个问题</th> -->
				<shiro:hasPermission name="config:smSymQues:edit"><th width="240">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smSymQues" varStatus="serial">
			<tr nowrap="nowrap">
				<td  style="text-align: center;">${serial.count }</td>
				<td title=""><%-- <a href="${ctx}/config/smSymQues/form?id=${smSymQues.id}"> --%>
					${smSymQues.sym.name}
				<!-- </a> --></td>
				<%-- <td>
				<a href="${ctx}/config/smSymQues/toConfigNextQues?id=${smSymQues.id}">
					${smSymQues.deptPid}
					</a>
				</td> --%>
				<td>
					${smSymQues.dept}
				</td>
				<td title="${smSymQues.lastQuestion.name}">
					${smSymQues.lastQuestion.name}
				</td>
				<td title="${smSymQues.option.name}">
					${smSymQues.option.name}
				</td>
				<td title="${smSymQues.illreason.name}">
					${smSymQues.illreason.name}
				</td>
				<td title="${smSymQues.question.name}">
					${smSymQues.question.name}
				</td>
				<td>
					${fns:getDictLabel(smSymQues.isFirst, 'is_first', '')}
				</td>
				
				 <td>
					${smSymQues.percent}
				</td> 
				<td>
					${fns:getDictLabel(smSymQues.qyStatus, 'qy_status', '')}
				</td>
				<td>
					${fns:getDictLabel(smSymQues.state, 'state', '')}
				</td>
				<%-- <td>
					${fns:getDictLabel(smSymQues.isLast, 'is_last', '')}
				</td> --%>
				<td>
					<a onclick="toQuestionList('${smSymQues.question.id}');" href="#">选项列表</a>
					<shiro:hasPermission name="config:smSymQues:edit">
	    				<a href="${ctx}/config/smSymQues/form?id=${smSymQues.id}">修改</a> 
	    				<a href="${ctx}/config/smSymQues/toConfigNextQues?id=${smSymQues.id}">添加下一题</a>
	    				<%-- <a onclick="configIllreason('${smSymQues.id}');" title="最后一题配置病因" href="#">配置病因</a> --%>
						<a href="${ctx}/config/smSymQues/delete?id=${smSymQues.id}" onclick="return confirmx('确认要删除该病症和问题关联表吗？', this.href)">删除</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>