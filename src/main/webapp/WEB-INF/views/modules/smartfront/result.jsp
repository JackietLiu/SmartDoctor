<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/modules/smartfront/ftaglib.jsp"%>

<!DOCTYPE html>
<html lang="zh-cmn">

<head>
<meta charset="UTF-8">
<meta name="decorator" content="blank" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="Stylesheet" href="${ctxfront}/css/main.css" />
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/static/jquery-validation/1.11.1/lib/jquery.form.js"></script>
<script type="text/javascript" src="${ctxfront}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctxfront}/js/bootstrap.min.js"></script>
<script src="${ctxfront}/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${ctxfront}/js/theme/zhys_script.js"></script>
<script src="${ctxfront}/sweetalert/sweetalert-dev.js"></script>
<link rel="stylesheet" href="${ctxfront}/sweetalert/sweetalert.css">
<title>处方笺</title>
<!-- <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/bootstrap.min.js"></script> -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.hide {
	display: none;
}
</style>
<style>
#gggg ul {
	display: none;
}

.hhf_select {
	background-image: url(/img/aaa.png);
	background-repeat: no-repeat;
	background-position: left center;
}

.hhf_select.active {
	background-image: url(/img/bbb.png);
}
</style>
<script type="text/javascript">
    
  
    $(document).ready(function() {
    	
	});
    
    /**
    	*参数：卡号、病症ID、诊疗ID、病因ID
    */
    function submitNew(advId){
    	var adv = $("#adv"+advId).val();   	
      	 $.ajax({
   			 	type : 'post',
   				url :  '${ctx}/inquiry/smInquiryQues/updateByOldId',
   				data : {
   					old : advId,
   					adv : adv
				},
   				success : function(data) {
   					swal( data.result+data.msg,"","success");
   				},
   				error:function(data){
   					
   				}
   			});
       }
    function toSave(symId,inquId,illreaId){
   	 $.ajax({
			 	type : 'post',
				url :  '${ctx}/inquiry/smInquiry/judgeFirstQuest',
				data : {
					symId : symId,state:2,illreaId:illreaId
				},
				success : function(data) {
					var msg = data.msg;
					if(msg==1){
						var sqId = data.result;//配置问题的ID
						window.location.href="${ctx}/inquiry/smInquiry/illFirstQues?id="+inquId+
								"&state=2"+"&sqId="+sqId;
					}else if(msg==2){
						//jBox.tip("该病症还没有配置问题！"); 
						swal({   title: "已经是最终结果，无需继续答题！",   
			    			 text: "",   
			    			 timer: 2000,   
			    			 showConfirmButton: false
			    			 });
					}else if(msg==3){
						//jBox.tip("服务器忙，请点击【重新答题】！");
						swal({   title: "服务器忙，请点击【重新答题】！",   
			    			 text: "",   
			    			 timer: 2000,   
			    			 showConfirmButton: false
			    			 });
					}else{
						swal({   title: "服务器忙，请点击【重新答题】！",   
			    			 text: "",   
			    			 timer: 2000,   
			    			 showConfirmButton: false
			    			 });
					}
				},
				error:function(data){
					
				}
			});
    }
    </script>
</head>

<body>

	<header>
		<div class="container">
			<div class="row">
				<div class="col-xs-6">
					<a class="logo-box"> <img
						src="${ctxfront}/assets/images/logo.png" alt="">
					</a>
				</div>
				<div class="col-xs-6">
					<a href="" class="right">常见慢性病医疗建议查询窗口</a>
				</div>
			</div>
		</div>
	</header>
	<section>
		<div class="breadcrumb-box">
			<div class="container">
				<div class="row">
					<div class="col-xs-6">
						<h3>卡号：${user.cardNo }</h3>
					</div>
					<div class="col-xs-6">
						<span>结果</span>
						<ul>
							<c:if test="${isNextQues==2 }">
								<!-- 病因有下一题，则显示继续答题按钮 -->
								<li><a
									onclick="toSave('${smInquiry.sym.id}','${smInquiry.id }','${si.illrea.id }');">继续答题</a>
								</li>
							</c:if>
							<c:if test="${isNextQues==1 }">
								<li><a href="${ctx}/index-doc.html">完成</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="left-box scrollbar">
				<h2 Style="text-align: center;">中山医院徐汇医院</h2>
				<h1 Style="text-align: center;">处方笺</h1>
				<br />
				<div Style="text-align: center;">
					<%  

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");  

               java.util.Date currentTime = new java.util.Date();//得到当前系统时间  

                String str_date1 = formatter.format(currentTime); //将日期时间格式化  

                String str_date2 = str_date1.toString(); //将Date型日期时间转换成字符串形式  
					
                %>
			   <%=str_date2 %>
				</div>
				<br /> <span
					style="padding-right: 30px; margin-left: 40px; font-size: 20px;">姓名：${user.name}</span><span
					style="padding-right: 30px; font-size: 20px;">年龄:${user.age}</span><span
					style="font-size: 20px;">性别：${fns:getDictLabel(user.sex, 'sex', '')}</span>
				<h4 class="title">血压：</h4>
				<br />
				<hr width="98%" style="margin-top: -5px; border: 1px solid red;" />
				<c:if test="${si.reStatus==2 }">
					<h4 class="title">病情描述：</h4>
					<div class="white-content">
						<c:forEach items="${sqList}" var="sq" varStatus="s">

							<p>${sq.description }</p>

						</c:forEach>
					</div>
					<h4 class="title">初步诊断：</h4>
					<div class="white-content">
						<h4>您好，根据您的选项，检查不出相关病因！</h4>
					</div>
					<h4 class="title">建议处方：</h4>
					<div class="answer-content">
						<p>暂无治疗建议提供！谢谢使用！</p>
					</div>
				</c:if>
				<c:if test="${si.reStatus!=2 }">
					<h4 class="title">病情描述：</h4>
					<div class="white-content">
						<c:forEach items="${sqList}" var="sq" varStatus="s">

							<p>${sq.description }</p>

						</c:forEach>
					</div>
					<h4 class="title">初步诊断：</h4>
					<div class="white-content">
						<h4>${sirs.name }</h4>
					</div>

				</c:if>
				<h4 class="title">处方:</h4>
				<div class="yf">
					<table style="width: 100%;" class="white-content">
						<tbody>
							<tr>
								<th colspan="1">序号</th>
								<th colspan="5">药名</th>
								<th colspan="3">剂量</th>
								<th colspan="3">疗程</th>
							</tr>

							<c:forEach items="${advList}" var="advlist" varStatus="s">
								<tr>
									<%-- <c:if test="${adv.illrea.id==si.illrea.id }">   --%>
									<td colspan="1">${s.count}</td>
									<td colspan="5">${advlist.adv.med.medName}</td>
									<td colspan="3">${advlist.adv.med.dose}${advlist.adv.med.doseUnit}</td>
									<td colspan="3">${advlist.adv.period}${advlist.adv.periodUnit}</td>
									<%-- </c:if> --%>
								</tr>

							</c:forEach>

						</tbody>

					</table>
				</div>
				<h4 class="title">下次来诊:</h4>
				<div class="white-content">
					<c:forEach items="${advList}" var="advlist2">
						<h4>${advlist2.adv.dtNextvisit}</h4>
					</c:forEach>

				</div>
				<h4 class="title">健康处方:</h4>
				<div class="white-content">
					<c:forEach items="${advList}" var="advlist1">
						<font color="red">饮食建议：</font>
						<h4>${advlist1.adv.heath.food}</h4>
						<font color="red">健康建议：</font>
						<h4>${advlist1.adv.heath.suggest}</h4>
					</c:forEach>

				</div>
		<%--  <h4 class="title">系统建议处方：</h4>
	                <a onclick="$('#chufang').removeClass('hide');$('#cft').removeClass('hide');" href="#" style="margin-left:200px;float:right;display:block;margin-top: -30px;margin-right: 20px;">修改系统建议处方</a>
	                <div class="answer-content">
	                	<c:forEach items="${advList}" var="adv" varStatus="s">	
	                    	<p>${s.count}、${adv.description }</p>
	                    	<c:if test="${adv.illrea.id==si.illrea.id }">
	                    	</c:if>
	                    </c:forEach>
	                </div>
	                
         	
         		       <h4 id="cft" class="title hide">医师处方：</h4>
         			   <div id="chufang" class="answer-content hide">
         			   
	                	<c:forEach items="${newAdv}" var="advs" varStatus="ss">	
	                    	<p>${ss.count}、<input id="adv${advs.old.id }" style="background:rgb(0,89,179);border:2px solid #fff;width:300px" value="${advs.description }" onblur=""/>
	                    	<input type="button" style="border:1px solid rgb(0,89,179);color:rgb(0,89,179);background:#fff;" value="提交修改" onclick="submitNew('${advs.old.id }');">
	                    	</p>
	                    	<input type="hidden" value="${advs.old.id }"  name="advId" />
	 
	                    </c:forEach>
	                </div> --%>
			</div>

			<div class="disease-box scrollbar">
				<div class="disease-title">
					<h4>病症：${smInquiry.symName }</h4>
					<p>症状历史选项如下：</p>
				</div>
				<div class="disease-answer-list" id="parent">
					<span id="jzz"> 加载中……</span>
				</div>
			</div>
			<div class="white-shadow"></div>
		</div>
	</section>
	<footer>
		<div class="container">
			<p>Copyright &copy; 2012-${fns:getConfig('copyrightYear')}
				${fns:getConfig('productName')} - ${fns:getConfig('frontName')}</p>
		</div>
	</footer>
	<input type="hidden" name="inqu.id" id="inquId"
		value="${smInquiry.id }" />

	<script type="text/javascript">
    	function autoGetList(){
    		var inquId = $("#inquId").val();
    		 $.ajax({
 			 	type : 'post',
 				url :  '${ctx}/inquiry/smInquiryQues/findListByInquId',
 				data : {
 					inquId : inquId
 				},
 				success : function(data) {
 					var list = data.items;
 					if(null!=list&&list.length>0){
 						var parent = document.getElementById("parent");
 						$("#jzz").text("");
 						for(var i =0 ;i<list.length;i++){
 							// <p><span>Q1</span>A. 颈部肌肉痉挛和疲劳，下肢麻木、疼痛、跛行，有的患者在走路时有如踏棉花的感觉。</p>
 							if(list[i].illrea.id!=''&&list[i].illrea.id!=null){
 								/* if(illname==''){ */
 									illname = list[i].illrea.name ;
 									//添加标签
 									var p0 = document.createElement("p");
 		 							var span0 = document.createElement("span");
 		 							span0.innerHTML = "R";
 		 							p0.innerHTML = list[i].illrea.name;
 		 							p0.appendChild(span0);
 		 							parent.appendChild(p0);
 								/* }else{
 									if(illname!=list[i].illrea.name){
 										//添加标签
 										var p0 = document.createElement("p");
 	 		 							var span0 = document.createElement("span");
 	 		 							span0.innerHTML = "R";
 	 		 							p0.innerHTML = list[i].illrea.name;
 	 		 							p0.appendChild(span0);
 	 		 							parent.appendChild(p0);
 									}
 								} */
 							}
 							var p = document.createElement("p");
 							var span = document.createElement("span");
 							span.innerHTML = "Q"+list[i].dept;
 							p.innerHTML = list[i].quesName;
 							
 							p.appendChild(span);
 							parent.appendChild(p);
 							
 							var p2 = document.createElement("p");
 							var span2 = document.createElement("span");
 							span2.innerHTML = "A";
 							p2.innerHTML = list[i].optName;
 							
 							p2.appendChild(span2);
 							parent.appendChild(p2);
 	 					}
 					}else{
 						$("#jzz").text("开始答题吧O(∩_∩)O~");
 					}
 				},
 				error:function(data){
 					jBox.tip("加载失败！"); 
 				}
 			});
    	}
    	autoGetList();
    </script>
</body>

</html>
