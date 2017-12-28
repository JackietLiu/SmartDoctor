<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title></title>
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
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>药名</th>
				<th>剂量</th>				
				<th>剂量单位</th>
				<th>服药</th>
				<th>服药单位</th>
				<th>不良反应</th>
				<th>起作用时间</th>
				<th>持续时间</th>	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="smTmRelation">
			<tr>

				<td>
					${smTmRelation.med.medName}
				</td>
				<td>
					${smTmRelation.med.dose}
				</td>
				<td>
					${smTmRelation.med.doseUnit}
				</td>
				<td>
					${smTmRelation.med.medication}
				</td>
				
				<td>
					${smTmRelation.med.medicationUnit}
				</td>
				<td>
					${smTmRelation.med.effects}
				</td>
				<td>
					${smTmRelation.med.workTime}
				</td>
				<td>
					${smTmRelation.med.durationTime}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>