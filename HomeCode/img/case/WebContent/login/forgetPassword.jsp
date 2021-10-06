<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">			
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/login/css/forgetPassword.css">



</head>
<body>


<!-- ======================header-start================================= -->
	
	<jsp:include page="/beforeLoginHeader.jsp" />

<!-- ======================header-end ================================== -->



	<div class="container login">
		<div class="row">
			<div class="col-xs-12 col-sm-12">



				<form METHOD="post" ACTION="<%=request.getContextPath() %>/member/member.do">
					<p class="t">忘記密碼</p> <br>
					<div>
						<input class="inputtext" type="email" name="email"  placeholder="請輸入email">
					
					</div>

					<br><br>


					<div class="g-recaptcha check" data-sitekey="6Lf-0DEUAAAAADeUmnhRHKDO4PdN8T4G_ArTFB41"></div>

					<c:if test="${not empty errorMsgs}">
						<font color='red'><br>
							<c:forEach var="message" items="${errorMsgs}">
								<div>${message}</div>
							</c:forEach>
								
						</font>
					</c:if>
<!-- ========================記住我跟忘記密碼===========================================-->


				
					<br><br>
<!-- # linking piont 登入 -->
				
					<div class="col-sm-12">
						<button class="btn btnlogin" type="submit" >寄送密碼</button>
						
						<input type="hidden" name="action" value="forgetpassword">
					</div>
								
					<br><br><br>
				</form>	
						
				<div class="col-xs-12 col-sm-12 hint">
					還不是HomeCode!的會員嗎? &nbsp; 
					<a class="btnsignup" href="<%=request.getContextPath() %>/signup/visitor_signup.jsp" type="submit" >點擊註冊</a>
				</div>
						
						


			</div>
		</div>
	</div>


	<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
	<!-- footer-end   ============================================================= -->



	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src='https://www.google.com/recaptcha/api.js'></script>
	<!-- # linking piont bootstrap-lightbox-js-->
	<script src="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.js"></script> 
</body>
</html>