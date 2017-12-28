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
    
    <title>答题-智慧医生</title>
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
    
      function toChange(optName,sort){
    	 $("#optName").val(optName);  
    /* 	 var sort = sort;
    	 $.ajax({
 				type: 'post', 
 				url: '${ctx}/inquiry/smInquiryQues/getSort', 
 				data: {sort:sort},
 				success: function(data) {
		
 				}
 			});  */
     }
     
     function toSubmitNext(){
    	 var optName = $("#optName").val();
    	 //var symId = $("#symId").val();
    	// var optId = $("#optId").val();
    	
    	var optId = $("[name='opt.id']:checked").val();
    	
    	// var optSort = $("#optSort").val();
    	 var symQuesId = $("#symQuesId").val();
    	 
    	 if(''==optName){
    		 swal({   title: "请先选择选项，再答下一题！",   
    			 text: "",   
    			 timer: 2000,    
    			 showConfirmButton: false
    			 });
    	 }else{
    		 $("#inputForm").submit();
           	 //先判断是不是有下一题
 /*           	   $.ajax({
       			 	type : 'post',
       				url :  '${ctx}/inquiry/smInquiryQues/judgeNextQuest',
       				data : {
       					symQuesId : symQuesId,
       					optId : optId
       				},
       				success : function(data) {
       					var msg = data.msg;
       					if(msg==1){
       						 //$("#inputForm").ajaxSubmit(ajax_option); //有下一题
       						$("#inputForm").submit();
       					}else if(msg==2){//没有下一提，则需要判断判断是不是有病因。
       						$("#inputForm").submit();
       					}else if(msg==3){
       						//jBox.tip("服务器忙，请点击【重新答题】！"); 
       						swal({   title: "服务器忙，请点击【重新答题】！",   
       			    			 text: "",   
       			    			 timer: 2000,   
       			    			 showConfirmButton: false
       			    			 });
       					}else{
       						//jBox.tip("服务器忙，请点击【重新答题】！"); 
       						swal({   title: "服务器忙，请点击【重新答题】！",   
      			    			 text: "",   
      			    			 timer: 2000,   
      			    			 showConfirmButton: false
      			    			 });
       					}
       				},
       				error:function(data){
       					//jBox.tip("服务器忙，请点击【重新答题】！"); 
       					swal({   title: "服务器忙，请点击【重新答题】！",   
  			    			 text: "",   
  			    			 timer: 2000,   
  			    			 showConfirmButton: false
  			    			 });
       				}
       			}); */ 
    	 }
     }
     
   /*   var ajax_option = {
				type: 'post', 
				url: '${ctx}/inquiry/smInquiryQues/nextQuest', 
				data: {},
				success: function(data) {
					closeLoading();
					top.frames["${frameName}"].refreshList();
					if(data.success){
						jBox.tip("提交成功！");
						top.$.jBox.close();
					}else{
						jBox.tip("提交失败！");
					}
				}
			} */
			
	function completeQuest(){
		$("#subtype").val("2");//完成答题
		 $("#inputForm").submit();
   }
   
	//重新答题
  	function restart(id)
	{
  		swal({
				title: "确认重新答题吗？",  
				text: "重新答题后选择的问题将会丢失!",   
				type: "warning",   
				showCancelButton: true,   
				confirmButtonColor: "#DD6B55",   
				confirmButtonText: "重新答题",   
				cancelButtonText: "再考虑一下",
				closeOnConfirm: false 
			}, 
				
			function(isConfirm){   
				if (isConfirm){
					window.location.href="${ctx}/inquiry/smInquiry/restart?id="+id;
				    } else {
				    	
				    }
			});
		
	}
	
	function toIndex(id){
		swal({
			title: "确认返回挂起吗？",  
			text: "执行挂起返回首页!",   
			type: "warning",   
			showCancelButton: true,   
			confirmButtonColor: "#DD6B55",   
			confirmButtonText: "返回首页",   
			cancelButtonText: "再考虑一下",
			closeOnConfirm: false 
		}, 
			
		function(isConfirm){   
			if (isConfirm){
				var dept = $('#dept').val();
				var state = $('#state').val();
				var gqState = 0;
				//病因第一题挂起
				if(state==2&&dept==1) {
					gqState = 2 ;
				}else {
					//答题挂起
					gqState = 1 ;
				}
				console.log(gqState);
				//window.location.href="${ctx}/index-doc.html";
				window.location.href="${ctx}/inquiry/smInquiry/hangUp?id="+id+"&gqState="+gqState;
			    } else {
			    	
			    }
		});
	}
	
	function returnUp(id){
		swal({
			title: "确认返回上一页吗？",
			text: "返回上一页后选择的问题将会丢失!",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "返 回",
			cancelButtonText: "再考虑一下",
			closeOnConfirm: false 
		}, 
			
		function(isConfirm){   
			if (isConfirm){
				window.location.href="${ctx}/inquiry/smInquiry/delete?id="+id;
			    } else {
			    	
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
                    <a href="${ctx}/index-doc.html" class="right">常见慢性病医疗建议查询窗口</a>
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
                        <span>答题</span>
                        <ul>
                            <li>
                            <a href="#" onclick="returnUp('${smInquiry.id}');"><i class="iconfont">&#xe601;</i>返回</a>
                            </li>
                            <!-- 如果是病症开始答题，并且第一题，则不需要显示挂起按钮 -->
                            <c:choose>
	                            <c:when test="${smSymQues.state==1&&smSymQues.dept==1 }"></c:when>
	                            <c:otherwise><li><a href="#" onclick="toIndex('${smInquiry.id }');">挂起</a></li></c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
        
         <form id="inputForm"  method="post" modelAttribute="SmInquiryQues" action="${ctx}/inquiry/smInquiryQues/save">
         
         <input type="hidden" name="inqu.id" id="inquId" value="${smInquiry.id }"/>
         <input type="hidden" name="sym.id" id="symId" value="${smInquiry.sym.id }"/>
         
         <input type="hidden" name="question.id" value="${smSymQues.question.id }"/>
         <input type="hidden" name="question.quesType" value="${smSymQues.question.quesType }"/>
         <input type="hidden" name="smRelation.id" value="${smRelation.id }"/>
         <input type="hidden" name="quesName" value="${smSymQues.question.name }"/>
         <input type="hidden" name="symQues.id" id="symQuesId" value="${smSymQues.id }"/>
         <input type="hidden" name="dept" id="dept" value="${smSymQues.dept }"/>
         <input type="hidden" name="state" id="state" value="${smSymQues.state }"/>
          <input type="hidden" name="illrea.id" id="illreaId" value="${smSymQues.illreason.id }"/>
         
         <input type="hidden" name="optName" id="optName" value="${siq.optName }"/>
   
            <div class="left-box scrollbar">
                <div class="white-content">
                    <h3>Q${smSymQues.dept }, ${smSymQues.question.name }</h3>
                </div>
                 <c:forEach items="${optList}" var="opt">
                 	<c:if test="${siq.opt.id ==''}">
	                 	<div class="radio">
	                    <label>
	                        <input type="radio" name="opt.id" id="optId" value="${opt.id }"  
	                        	 onclick="toChange('${opt.name }','${opt.sort }');"   >${opt.name }
	                        	 
	                    </label>
	                    <label>1</label>
	                    <i class="iconfont">&#xe609;</i>
	                	</div>
                	</c:if>
                	
                	<c:if test="${siq.opt.id !=''}">
                		<c:if test="${siq.opt.id ==opt.id }">
	                		<div class="radio checked">
		                    <label>
		                        <input type="radio" name="opt.id" id="optId" value="${opt.id }"  
		                        	 onclick="toChange('${opt.name }','${opt.sort }');" checked="checked" />${opt.name }
		                        	 
		                    </label>
		                     <label>2</label>
		                    <i class="iconfont">&#xe609;</i>
		                	</div>
                		</c:if>
                		
                		<c:if test="${siq.opt.id !=opt.id }">
	                		<div class="radio">
		                    <label>
		                        <input type="radio" name="opt.id" id="optId" value="${opt.id }"  
		                        	 onclick="toChange('${opt.name }','${opt.sort }');"  >${opt.name }
		                       	
		                    </label>
		                      
		                    <i class="iconfont">&#xe609;</i>
		                	</div>
                		</c:if>
                		
                	</c:if>
                 </c:forEach>
                
                <ul class="pager">
                    <li class="next" id="next">
                    	<a href="#" onclick="toSubmitNext();" >下一题</a>
                    </li>
                </ul>
                <c:if test="${smSymQues.isFirst==1 }">
               		 <a href="#" onclick="restart('${smInquiry.id }')" class="answer-again">重新答题</a>
                </c:if>
            </div>
            </form>
            
            <div class="disease-box scrollbar">
                <div class="disease-title">
                    <h4>病症：${smInquiry.symName }</h4>
                    <p>症状历史选项如下：</p>
                </div>
                <div class="disease-answer-list" id="parent">
                   <!--  <p><span>Q1</span>A. 颈部肌肉痉挛和疲劳，下肢麻木、疼痛、跛行，有的患者在走路时有如踏棉花的感觉。</p> -->
                  <span id="jzz"> 加载中……</span>
                </div>
            </div>
            <div class="white-shadow"></div>
            </div>
    </section>
    <footer>
        <div class="container">
            <p> Copyright &copy; 2016-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} <%-- - IDORP ${fns:getConfig('version')}&nbsp; --%>${fns:getConfig('frontName')}</p>
        </div>
    </footer>
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
 								/* } *//* else{
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
