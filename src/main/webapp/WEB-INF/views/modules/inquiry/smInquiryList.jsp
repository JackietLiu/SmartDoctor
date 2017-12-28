<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>病人询诊管理</title>
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
					"iframe:${ctx}/inquiry/smInquiryQues?inqu.id="+id , 
					"答题明细列表",
					1000,
					$(top.document).height()-140,
					{
						top : "20px",
						iframeScrolling: "no",
						buttons: { '关闭': true} 
					}
				);
		}
		function toIllReasonList(id){
			top.$.jBox.open(
					"iframe:${ctx}/inquiry/smInquiryIllreason?inqu.id="+id , 
					"病因明细列表",
					1000,
					$(top.document).height()-140,
					{
						top : "20px",
						iframeScrolling: "no",
						buttons: { '关闭': true} 
					}
				);
		}
		function toAdviceList(id){
			top.$.jBox.open(
					"iframe:${ctx}/inquiry/smInquiryAdviceCopy?inqu.id="+id , 
					"治疗建议明细列表",
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
		<li class="active"><a href="${ctx}/inquiry/smInquiry/">病人询诊列表</a></li>
		<%-- <shiro:hasPermission name="inquiry:smInquiry:edit"><li><a href="${ctx}/inquiry/smInquiry/form">病人询诊添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="smInquiry" action="${ctx}/inquiry/smInquiry/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>卡号：</label>
				<form:input path="cardno" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%-- <li><label>病症：</label>
				<form:input path="sym.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>病症名称：</label>
				<form:input path="symName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>询证状态：</label>
				<form:radiobuttons path="illStatus" items="${fns:getDictList('ill_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>答题时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${smInquiry.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${smInquiry.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>卡号</th>
				<th>病症名称</th>
				<th>询证状态</th>
				<th>答题时间</th>
				<%-- <shiro:hasPermission name="inquiry:smInquiry:edit"><th>操作</th></shiro:hasPermission> --%>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smInquiry">
			<tr>
				<td>
					${smInquiry.cardno}
				</td>
				<td>
					${smInquiry.symName}
				</td>
				<td>
					${fns:getDictLabel(smInquiry.illStatus, 'ill_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${smInquiry.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="inquiry:smInquiry:edit"><td>
				<a onclick="toQuestionList('${smInquiry.id}');" href="#">答题明细</a>
				<a onclick="toIllReasonList('${smInquiry.id}');" href="#">病因明细</a>
				<a onclick="toAdviceList('${smInquiry.id}');" href="#">治疗建议明细</a>
    				<%-- <a href="${ctx}/inquiry/smInquiry/form?id=${smInquiry.id}">修改</a>
					<a href="${ctx}/inquiry/smInquiry/delete?id=${smInquiry.id}" onclick="return confirmx('确认要删除该病人询诊吗？', this.href)">删除</a> --%>
					
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>