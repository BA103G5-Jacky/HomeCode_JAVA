<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rank.model.*"%>


<%
	
%>
<jsp:useBean id="memberSvc" scope="page"
	class="com.member.model.MemberService" />

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>HomeCode!</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- # linking piont bootstrap-lightbox-->
<link rel="stylesheet" href="bootstrap-lightbox/bootstrap-lightbox.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<!-- # linking piont tab-icon-->
<link rel="icon" type="images/gif"
	href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">




</head>
<body>



	<!-- 燈箱排行榜=================================================================== -->

	<div id="myCarousel" class="carousel slide rank" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target=".rank" data-slide-to="0" class="active"></li>
			<li data-target="myCarousel" data-slide-to="1"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-12 central search-area">
							<h2>Web 人才排行</h2>
						</div>
					</div>
				</div>
				<div class="container search-area">
					<div class="row">
						<div class="col-xs-12 col-sm-2 col-sm-offset-1 central rank-card">
							<img src="img/profile01.jpg"
								class="img-circle photo-size margin01"> <br> <br>
							<div class="central">
								<b> <jsp:useBean id="rankSvc" scope="page"
										class="com.rank.model.RankService" /> <c:forEach
										var="memberVO" items="${memberSvc.all}">
										<c:if
											test="${rankSvc.getOneRankNo(\"1\").getMbno()==memberVO.mbNo }">
	     ${memberVO.mbUserName }
 
 
 </c:if>


									</c:forEach>

								</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="img/profile02.jpg"
								class="img-circle photo-size margin01"> <br> <br>
							<div class="central">
								
								<b>
							 <c:forEach
										var="memberVO" items="${memberSvc.all}">
										<c:if
											test="${rankSvc.getOneRankNo(\"2\").getMbno()==memberVO.mbNo }">
	     ${memberVO.mbUserName }
 
 
 </c:if>


									</c:forEach>





								</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="img/profile03.jpg"
								class="img-circle photo-size margin01"> <br> <br>
							<div class="central">
								<b>	 <c:forEach
										var="memberVO" items="${memberSvc.all}">
										<c:if
											test="${rankSvc.getOneRankNo(\"3\").getMbno()==memberVO.mbNo }">
	     ${memberVO.mbUserName }
 
 
 </c:if>


									</c:forEach>
</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="img/profile04.jpg"
								class="img-circle photo-size margin01"> <br> <br>
							<div class="central">
								<b>	 <c:forEach
										var="memberVO" items="${memberSvc.all}">
										<c:if
											test="${rankSvc.getOneRankNo(\"4\").getMbno()==memberVO.mbNo }">
	     ${memberVO.mbUserName }
 
 
 </c:if>


									</c:forEach>
</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="img/profile05.jpg"
								class="img-circle photo-size margin01"> <br> <br>
							<div class="central">
								<b>	 <c:forEach
										var="memberVO" items="${memberSvc.all}">
										<c:if
											test="${rankSvc.getOneRankNo(\"5\").getMbno()==memberVO.mbNo }">
	     ${memberVO.mbUserName }
 
 
 </c:if>


									</c:forEach>
</b> <br> Job Success
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="item">
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-12 central search-area">
							<h2>App 人才排行</h2>
						</div>
					</div>
				</div>
				<div class="container search-area">
					<div class="row">
						<div class="col-xs-12 col-sm-1 hv-central"></div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="img/profile01.jpg"
								class="img-circle photo-size margin01"> <br> <br>
							<div class="central">
								<b><c:forEach
										var="memberVO" items="${memberSvc.all}">
										<c:if
											test="${rankSvc.getOneRankNo(\"1\").getMbno()==memberVO.mbNo }">
	     ${memberVO.mbUserName }
 
 
 </c:if>


									</c:forEach></b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="img/profile02.jpg"
								class="img-circle photo-size margin01"> <br> <br>
							<div class="central">
								<b><c:forEach
										var="memberVO" items="${memberSvc.all}">
										<c:if
											test="${rankSvc.getOneRankNo(\"2\").getMbno()==memberVO.mbNo }">
	     ${memberVO.mbUserName }
 
 
 </c:if>


									</c:forEach></b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="img/profile03.jpg"
								class="img-circle photo-size margin01"> <br> <br>
							<div class="central">
								<b><c:forEach
										var="memberVO" items="${memberSvc.all}">
										<c:if
											test="${rankSvc.getOneRankNo(\"3\").getMbno()==memberVO.mbNo }">
	     ${memberVO.mbUserName }
 
 
 </c:if>


									</c:forEach></b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="img/profile04.jpg"
								class="img-circle photo-size margin01"> <br> <br>
							<div class="central">
								<b><c:forEach
										var="memberVO" items="${memberSvc.all}">
										<c:if
											test="${rankSvc.getOneRankNo(\"4\").getMbno()==memberVO.mbNo }">
	     ${memberVO.mbUserName }
 
 
 </c:if>


									</c:forEach></b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="img/profile05.jpg"
								class="img-circle photo-size margin01"> <br> <br>
							<div class="central">
								<b><c:forEach
										var="memberVO" items="${memberSvc.all}">
										<c:if
											test="${rankSvc.getOneRankNo(\"5\").getMbno()==memberVO.mbNo }">
	     ${memberVO.mbUserName }
 
 
 </c:if>


									</c:forEach></b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-1 hv-central"></div>
					</div>
				</div>
			</div>


			<!-- Left and right controls -->
			<a class="left carousel-control" href=".rank" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href=".rank" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</div>



	<!-- 燈箱排行榜結束=================================================================== -->





	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="bootstrap-lightbox/bootstrap-lightbox.js"></script>
	<script src="js/script.js"></script>
</body>
</html>