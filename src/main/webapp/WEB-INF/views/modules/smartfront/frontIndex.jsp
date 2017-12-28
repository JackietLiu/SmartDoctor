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
    
    <script src="${ctxfront}/sweetalert/sweetalert-dev.js"></script>
	  <link rel="stylesheet" href="${ctxfront}/sweetalert/sweetalert.css">
    
    <title>查询-智慧医生</title>
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
    
     function toSubmit(){
    	 var cardno = $("#cardno").val();
    	 if(''==cardno){
    		// jBox.tip("请输入卡号后查询！"); 
    		 swal({   title: "请输入卡号后查询！",   
    			 text: "",   
    			 timer: 2000,   
    			 showConfirmButton: false
    			 });
    	 }else{
    		 $("#sercher").submit();   		 
    	 }
    	 
     }
     window.onload=function()
 	{ 
 
 			$("#cardno").focus(); 
 		
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
    <form id="sercher" action="${ctx}/inquiry/smInquiry/list" method="post" modelAttribute="SmInquiry">
    <section>
        <div class="container">
            <div class="middle-content">
                <h3 class="form-title">请输入您的查询卡号</h3>
                <div class="form-horizontal">
                    <div class="form-group">
                        <div class="col-xs-3"></div>
                        <div class="col-xs-6">
                            <input type="text" id="cardno" name="cardno" autocomplete="off" 
                             onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" class="form-control required form-search" placeholder="">
                        </div>
                       
                    </div>
                    <div class="form-group">
                        <div class="col-xs-5"></div>
                        <div class="col-xs-2">
                            <a class="btn btn-lg btn-block btn-search" href="#" onclick="toSubmit();"><i class="iconfont">&#xe622;</i>查 询</a> 
                        </div>
                        <div class="col-xs-5"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    </form>
    <footer>
        <div class="container">
            <p> Copyright &copy; 2016-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - <%-- IDORP ${fns:getConfig('version')}&nbsp; --%> ${fns:getConfig('frontName')}</p>
        </div>
    </footer>
</body>

</html>
