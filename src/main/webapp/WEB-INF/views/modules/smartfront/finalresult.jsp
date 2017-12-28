<%@ page contentType="text/html;charset=UTF-8"%>
 <%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%> 
<%@ include file="/WEB-INF/views/modules/smartfront/ftaglib.jsp"%>

<!DOCTYPE html>
<html lang="zh-cmn">

<head>
    <meta charset="UTF-8">
    <meta name="decorator" content="blank"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <link rel="Stylesheet" href="${ctxfront}/css/main.css" />
    <script type="text/JavaScript" src="${pageContext.request.contextPath}/static/jquery-validation/1.11.1/lib/jquery.form.js"></script>
    <script type="text/javascript" src="${ctxfront}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="${ctxfront}/js/bootstrap.min.js"></script>
    <script src="${ctxfront}/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="${ctxfront}/js/theme/zhys_script.js"></script>
    <script src="${ctxfront}/sweetalert/sweetalert-dev.js"></script>
	  <link rel="stylesheet" href="${ctxfront}/sweetalert/sweetalert.css">
    <title>结果-智慧医生</title>
    <!-- <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/bootstrap.min.js"></script> -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    $(document).ready(function() {
    	
	});
    
    /**
    	*参数：卡号、病症ID、诊疗ID、病因ID
    */
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
                    <a class="logo-box">
                        <img src="${ctxfront}/assets/images/logo.png" alt="">
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
                        <h3>卡号：${smInquiry.cardno }</h3>
                    </div>
                    <div class="col-xs-6">
                        <span>结果</span>
                        <ul>
                            <li>
                                <a href="${ctx}/index-doc.html">完成</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
        	<div class="left-box scrollbar">
         	<c:forEach items="${illreaList}" var="reason">
         		<c:if test="${reason.reStatus==2 }">
         			<h4 class="title">病因：</h4>
	                <div class="white-content">
	                    <h4>您好，根据您的选项，检查不出相关病因！</h4>
	                </div>
	                <h4 class="title">治疗建议：</h4>
	                <div class="answer-content">
	                	<p>暂无治疗建议提供！谢谢使用！</p>
	                </div>
         		</c:if>
         		<c:if test="${reason.reStatus!=2 }">
         			<h4 class="title">病因：</h4>
	                <div class="white-content">
	                    <h4>${reason.illreaDesc }</h4>
	                </div>
	                <h4 class="title">治疗建议：</h4>
	                <div class="answer-content">
	                	<c:forEach items="${advList}" var="adv" varStatus="s">
	                		<c:if test="${adv.illrea.id==reason.illrea.id }">
	                    	<p>${s.count}、${adv.description }</p>
	                    	</c:if>
	                    </c:forEach>
	                </div>
         		</c:if>
               </c:forEach>
            </div>
            
            <div class="disease-box scrollbar">
                <div class="disease-title">
                    <h4>病症：${smInquiry.symName }</h4>
                    <p>症状历史选项如下：</p>
                </div>
                <div class="disease-answer-list"  id="parent">
                   <span id="jzz"> 加载中……</span>
                </div>
            </div>
            <div class="white-shadow"></div>
            </div>
    </section>
    <footer>
        <div class="container">
            <p> Copyright &copy; 2012-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - IDORP ${fns:getConfig('version')}&nbsp;${fns:getConfig('frontName')}</p>
        </div>
    </footer>
     <input type="hidden" name="inqu.id" id="inquId" value="${smInquiry.id }"/>
    
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
 					var illname = '';
 					if(null!=list&&list.length>0){
 						var parent = document.getElementById("parent");
 						$("#jzz").text("");
 						for(var i =0 ;i<list.length;i++){
 							// <p><span>Q1</span>A. 颈部肌肉痉挛和疲劳，下肢麻木、疼痛、跛行，有的患者在走路时有如踏棉花的感觉。</p>
 							/* var p = document.createElement("p");
 							var span = document.createElement("span");
 							span.innerHTML = "Q"+list[i].dept;
 							p.innerHTML = list[i].optName;
 							
 							p.appendChild(span);
 							parent.appendChild(p); */
 							if(list[i].illrea.id!=''&&list[i].illrea.id!=null){
 								if(illname==''){
 									illname = list[i].illrea.name ;
 									//添加标签
 									var p0 = document.createElement("p");
 		 							var span0 = document.createElement("span");
 		 							span0.innerHTML = "R";
 		 							p0.innerHTML = list[i].illrea.name;
 		 							p0.appendChild(span0);
 		 							parent.appendChild(p0);
 								}else{
 									if(illname!=list[i].illrea.name){
 										//添加标签
 										var p0 = document.createElement("p");
 	 		 							var span0 = document.createElement("span");
 	 		 							span0.innerHTML = "R";
 	 		 							p0.innerHTML = list[i].illrea.name;
 	 		 							p0.appendChild(span0);
 	 		 							parent.appendChild(p0);
 									}
 								}
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
