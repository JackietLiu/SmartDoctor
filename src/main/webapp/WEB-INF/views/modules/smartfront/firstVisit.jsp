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
    
    <title>初诊-智慧医生</title>
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
    
     function toSave(cardno,symId){
    	 $.ajax({
			 	type : 'post',
				url :  '${ctx}/inquiry/smInquiry/judgeFirstQuest',
				data : {
					symId : symId,state:1
				},
				success : function(data) {
					var msg = data.msg;
					if(msg==1){
						var sqId = data.result;//配置问题的ID
						window.location.href="${ctx}/inquiry/smInquiry/save?cardno="+cardno+"&sym.id="+symId+
								"&state=1"+"&sqId="+sqId;
					}else if(msg==2){
						//jBox.tip("该病症还没有配置问题！"); 
						swal({   title: "该病症还没有配置问题！",   
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
                    <a href="#" class="right">常见慢性病医疗建议查询窗口</a> 
                </div>
            </div>
        </div>
    </header>
    <section>
        <div class="breadcrumb-box">
            <div class="container">
                <div class="row">
                    <div class="col-xs-6">
                        
                         <c:forEach items="${list}" var="list">
                	<h3>卡号：${cardno}姓名：${list.name}</h3>
                	  <%--  ${list.name} --%>
                	
                </c:forEach>
                    </div>
                    <div class="col-xs-6">
                        <span>初诊</span>
                        <ul>
                            <li>
                               <!--  <a href="#" onclick="history.go(-1)" ><i class="iconfont">&#xe601;</i>返回</a> -->
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
            <div class="row">
                <div class="col-xs-12">
                    <div class="white-content">
                        <h3>请选择符合您症状的病情类型：</h3>
                    </div>
                </div>
            </div>
            <ul class="disease-list scrollbar">
                <c:forEach items="${smSymptomList}" var="smSymptom">
                	<li>
                	<a onclick="toSave('${cardno}','${smSymptom.id}');" href="#" title="${smSymptom.name }">${smSymptom.name }</a>
                	</li>
                </c:forEach>
            </ul>
        </div>
    </section>
    <footer>
        <div class="container">
            <p> Copyright &copy; 2016-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} <%-- - IDORP ${fns:getConfig('version')}&nbsp; --%>${fns:getConfig('frontName')}</p>
        </div>
    </footer>
</body>

</html>
