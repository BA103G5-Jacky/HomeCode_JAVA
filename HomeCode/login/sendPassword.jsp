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
<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/login/css/sendPassword.css">		




</head>
<body>




<!-- ======================header-start================================= -->
	
	<jsp:include page="/beforeLoginHeader.jsp" />

<!-- ======================header-end ================================== -->



	<div class="container login">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<h2>已經將密碼傳至您的信箱!</h2> <br>
				<div>
					<a class="link"  href="vistor_login.jsp">回首頁</a>&nbsp&nbsp&nbsp&nbsp
					<a class="link" href="vistor_login.jsp">回到登入畫面</a>
				</div>
	
				<br><br>
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
    <script src='https://www.google.com/recaptcha/api.js'></script>
</body>
</html>