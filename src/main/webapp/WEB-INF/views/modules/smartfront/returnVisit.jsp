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
    <script type="text/javascript" src="${ctxfront}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="${ctxfront}/js/bootstrap.min.js"></script>
     <script src="${ctxfront}/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="${ctxfront}/js/theme/zhys_script.js"></script>
    
     <script src="${ctxfront}/sweetalert/sweetalert-dev.js"></script>
	  <link rel="stylesheet" href="${ctxfront}/sweetalert/sweetalert.css">
    
    <title>复诊-智慧医生</title>
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
    
     function getLastResult(cardno){
    	 $.ajax({
			 	type : 'post',
				url :  '${ctx}/inquiry/smInquiryQues/judgeLastResult',
				data : {
					cardno : cardno
				},
				success : function(data) {
					var msg = data.msg;
					if(msg==1){
						window.location.href="${ctx}/inquiry/smInquiryQues/getLastResult?cardno="+cardno;
					}else if(msg==2){
						//jBox.tip("查询不到相关诊疗信息！"); 
						 swal({   title: "查询不到相关诊疗信息！",   
			    			 text: "",   
			    			 timer: 2000,   
			    			 showConfirmButton: false
			    			 });
					}else if(msg==3){
						//jBox.tip("请重新输入卡号！"); 
						swal({   title: "请重新输入卡号！",   
			    			 text: "",   
			    			 timer: 2000,   
			    			 showConfirmButton: false
			    			 });
					}else if(msg==4){
						//jBox.tip("对不起，没有查询到最新一次的诊疗结果！"); 
						swal({   title: "对不起，没有查询到最新一次的诊疗结果！",   
			    			 text: "",   
			    			 timer: 2000,   
			    			 showConfirmButton: false
			    			 });
					}else{
						//jBox.tip("对不起，没有查询到上次诊断结果和治疗建议！");
						swal({   title: "对不起，没有查询到上次诊断结果和治疗建议！",   
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
     //继续答题
     function goOnAnswer(id,gqState){
    	 if(gqState==1){
    		 //答题挂起，从sm_inquiry_ques表中查询该次询诊的最新一条数据，然后查询出下一题
    		 window.location.href="${ctx}/inquiry/smInquiryQues/goOnAnswerFromQues?inqu.id="+id;
    	 }else if(gqState==2){
    		//病因第一题挂起，从sm_inquiry_illreason查询出该次询诊的最新一条病因，然后查询出下一题
    		 window.location.href="${ctx}/inquiry/smInquiry/goOnAnswerFromIllRea?id="+id;
    	 }else{
    		 //没有挂起的题目
    		 swal({   title: "对不起，没有查询到挂起相关信息，请重新答题！",   
    			 text: "",   
    			 timer: 2000,   
    			 showConfirmButton: false
    			 });
    	 }
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
                        <h3>卡号：${cardno }</h3>
                    </div>
                    <div class="col-xs-6">
                        <span>复诊</span>
                        <ul>
                            <li>
                               <!--  <a href="#"  onclick="history.go(-1)" ><i class="iconfont">&#xe601;</i>返回</a> -->
                            </li>
                            <li>
                                <a href="${ctx}/index-doc.html">首页</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="container">
            <div class="middle-content">
                <div class="row">
                    <div class="col-xs-2"></div>
                    <div class="col-xs-3">
                        <a class="btn btn-lg btn-block blue" href="${ctx}/inquiry/smInquiry/restart?cardno=${cardno}" >重新诊断</a>
                    </div>
                    <div class="col-xs-2"></div>
                    <div class="col-xs-3">
                    	 <c:if test="${smInquiry.illStatus==2 }">
                    		<a class="btn btn-lg btn-block blue" 
                    		 onclick="goOnAnswer('${smInquiry.id }','${smInquiry.gqState }');"  href="#" >继续答题</a>
                    	</c:if> 
                    	<c:if test="${smInquiry.illStatus==3 }">
                    		<a class="btn btn-lg btn-block blue" onclick="getLastResult('${cardno}');" href="#" >重复开药</a>
                    	</c:if>
                    </div>
                    <div class="col-xs-2"></div>
                </div>
            </div>
        </div>
        </div>
    </section>
    <footer>
        <div class="container">
            <p> Copyright &copy; 2016-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - <%-- IDORP ${fns:getConfig('version')}&nbsp; --%>${fns:getConfig('frontName')}</p>
        </div>
    </footer>
</body>

</html>
