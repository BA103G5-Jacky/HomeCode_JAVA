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
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/member/css/resetPassword.css">



</head>
<body>


<!-- ======================header-start================================= -->
	<c:choose>
		<c:when test="${mbNo == null}">
			<jsp:include page="/beforeLoginHeader.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/loginHeader.jsp" />
		</c:otherwise>
	</c:choose>

	<!-- ======================header-end ================================== -->



	<div class="container login">
		<div class="row">
			<div class="col-xs-12 col-sm-12">



				<form METHOD="post" ACTION="<%=request.getContextPath() %>/member/member.do">
					<p class="t">重設密碼</p> <br>
					<div>
						<input class="inputtext" type="text" name="oldPsw"  placeholder="舊密碼">
					
					</div>

					<br><br>
					<div>
						<input class="inputtext" type="text" name="newPsw"  placeholder="新密碼">
					
					</div>
					
					<br><br>
					<div>
						<input class="inputtext" type="text" name="checkNewPsw"  placeholder="確認新密碼">
					
					</div>


					<c:if test="${not empty errorMsgs}">
						<font color='red'><br>
							<c:forEach var="message" items="${errorMsgs}">
								<div>${message}</div>
							</c:forEach>
								
						</font>
					</c:if>
					<c:if test="${not empty resetSuccess}">
						<font color='blue'><br>
							<div>${resetSuccess}</div>
						</font>
					</c:if>
<!-- ========================記住我跟忘記密碼===========================================-->


				
					<br><br>
				
					<div class="col-sm-12">
						<button class="btn btnlogin" type="submit" >重設密碼</button>
						
						<input type="hidden" name="action" value="resetPassword">
					</div>
								
					<br><br><br>
				</form>	


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