<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html lang="">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>HomeCode!</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
	<!-- # linking piont bootstrap-lightbox-->	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
	<!-- # linking piont tab-icon-->
	<link rel="icon" type="<%=request.getContextPath()%>/images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">			
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/login/css/visitor_login.css">

</head>
<body>


	<!-- ======================header-start================================= -->
	<jsp:include page="/beforeLoginHeader.jsp" />
	<!-- ======================header-end ================================== -->



	<div class="container login">
		<div class="row">
			<div class="col-xs-12 col-sm-12">



				<form METHOD="post" ACTION="member.do">
					<p class="t" ${signSuccess != null ? 'style="color:red;"': '' }>${signSuccess == null ? '登入，準備上工了!' : signSuccess }</p> <br>
					<div>
						<input class="inputtext" type="text" name="email"  placeholder=" email / username">					
					</div>
					<br><br>
					<div>
						<input class="inputtext" type="password" name="password"  placeholder=" password">
					</div>	
					<!-- errorMessages -->
					<c:if test="${not empty errorMsgs}">
						<font color='red'><br>
							<c:forEach var="message" items="${errorMsgs}">
								<div>${message}</div>
							</c:forEach>
						</font>
					</c:if>
<!-- ========================記住我跟忘記密碼===========================================-->


					<br>
					<div class="col-xs-12 col-sm-2 col-sm-offset-6">
						<a href="forgetPassword.jsp"><u>ForgetPassWord?</u></a>
					</div>


<!--========================================================================-->

					<br><br>
					<!-- # linking piont 登入 -->
					<!-- <div class="col-xs-12 col-sm-12"><button class="btnlogin" type="submit" width="40px">登入</button>
						</div> -->
					<div class="col-sm-12">
						<button class="btn btnlogin" tybe="submit" width="40px">登入</button>
					
						<input type="hidden" name="action" value="login">
					</div>
						
					<br><br><br>
				</form>	
						
				<div class="col-xs-12 col-sm-12 hint">
					還不是HomeCode!的會員嗎? &nbsp; 
					<a class="btnsignup" href="<%=request.getContextPath()%>/signup/visitor_signup.jsp" type="submit" >點擊註冊</a>
				</div>
				
				<div class="col-xs-12 col-sm-12 hint margin01">
						<button  type="button"   onclick="inputInfo1()">從從</button>
						<button  type="button"  onclick="inputInfo2()">乃乃</button>
						<button  type="button"  onclick="inputInfo3()">Shari</button>
						<button  type="button"  onclick="inputInfo4()">Peter</button>		
				</div>
				
			</div>
		</div>
	</div>


	<!-- footer-start ============================================================= -->
	<jsp:include page="/footer.jsp" />
	<!-- footer-end   ============================================================= -->

	

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- # linking piont bootstrap-lightbox-js-->
	<script src="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.js"></script> 
	
	<!-- magic button   ============================================================= -->
	<script>
    function inputInfo1(){

      document.getElementsByName("email")[0].value="chungchung@gmail.com";
      document.getElementsByName("password")[0].value="iiiiiiii";
    
    }
    
    function inputInfo2(){
        document.getElementsByName("email")[0].value="eggpower0@gmail.com";
        document.getElementsByName("password")[0].value="iiiiiiii";

      }
    
    function inputInfo3(){

        document.getElementsByName("email")[0].value="Shari@gmail.com";
        document.getElementsByName("password")[0].value="iii";
      
      }
      
     function inputInfo4(){
          document.getElementsByName("email")[0].value="Peter@gmail.com";
          document.getElementsByName("password")[0].value="iii";

        }
    </script>

<!-- magic button   ============================================================= -->
	
</body>
</html>