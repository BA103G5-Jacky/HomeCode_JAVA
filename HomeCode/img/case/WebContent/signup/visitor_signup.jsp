<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>會員註冊</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">


<link rel="stylesheet" href="<%=request.getContextPath()%>bootstrap-lightbox/bootstrap-lightbox.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/signup/css/visitor_signup.css">


</head>
<body>


<!-- ======================header-start================================= -->
	
	<jsp:include page="/beforeLoginHeader.jsp" />

<!-- ======================header-end ================================== -->





	<div class="container login">
		<div class="col-sm-6-offset-4 row ">
			<div class="col-sm-5 f">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
					<strong class="t">註冊</strong> <br> <br>

					<!-- 姓名 -->
					<div class="col-sm-12">
						<input type="text" name="firstName" class="n" value="${memberVO.firstname}"
							placeholder="&nbsp;&nbsp;名字(必填)"> &nbsp; 
						<input type="text" name="lastName" class="n" value="${memberVO.lastname}" placeholder="&nbsp;&nbsp;姓氏(必填)">
					</div>
					
					
					
					<!-- 信箱,密碼,按鈕 -->
					<div class="col-sm-12">
						<br>
						<input type="text" name="mbUserName" class="inputtext" value="${memberVO.mbUserName}" placeholder="&nbsp;&nbsp;會員暱稱(必填)">
						<br><br>
						<input class="inputtext" type="email" name="email" value="${memberVO.email}" placeholder="  Email(必填)"> <br> <br>

						<input class="inputtext" type="password" name="password" placeholder="  password (需八碼以上!)" > <br><br> 
						<label class="lab">地區</label>
						<select name="location" class="d" >

							<option value="基隆市">基隆市</option>
							<option value="台北市">台北市</option>
							<option value="新北市">新北市</option>
							<option value="桃園市">桃園市</option>
							<option value="新竹市">新竹市</option>
							<option value="新竹縣">新竹縣</option>
							<option value="苗栗縣">苗栗縣</option>
							<option value="台中市">台中市</option>
							<option value="彰化縣">彰化縣</option>
							<option value="南投縣">南投縣</option>
							<option value="嘉義市">嘉義市</option>
							<option value="嘉義縣">嘉義縣</option>
							<option value="台南市">台南市</option>
							<option value="高雄市">高雄市</option>
							<option value="屏東市">屏東市</option>
							<option value="台東縣">台東縣</option>
							<option value="花蓮縣">花蓮縣</option>
							<option value="宜蘭縣">宜蘭縣</option>
							<option value="澎湖縣">澎湖縣</option>
							<option value="金門縣">金門縣</option>
							<option value="連江縣">連江縣</option>
							<option value="海外地區">海外地區</option>
						</select>

						<c:if test="${not empty errorMsgs}">
							<font color='red'>
								<br>
								<c:forEach var="message" items="${errorMsgs}">
									<div>${message}</div>
								</c:forEach>
							</font>
						</c:if>

						<input type="hidden" name="action" value="signup">
						<div class="col-sm-12">
							<button type="submit" class="btn btnlogin">立即上工!</button>
						</div>
						
					</div>
					
				</form>
			</div>
		</div>
	</div>

	<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
	<!-- footer-end   ============================================================= -->


	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>bootstrap-lightbox/bootstrap-lightbox.js"></script>
</body>
</html>