<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>HomeCode!</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
		
		
		<!-- # linking piont bootstrap-lightbox-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
		<!-- # linking piont tab-icon-->
		<link rel="icon" type="<%=request.getContextPath()%>/images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png"
			sizes="16x16">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/stars.css">
<style>
	.star-vote>.del-star{
		    background-color:#eee;
	}
	.carousel .item {
	  height: 650px;
	}
	
	.item{
	    position: absolute;
	    top: 0;
	    left: 0;
	    min-height: 650px;
	}
</style>
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







	<!-- 廣告區 =========================================================== 
<div class="ad">
<div class="row">
		<div class="col-xs-12 col-sm-12 aa">
			<img class="img" src="img/ad05.jpeg" > 
		</div>
		</div>
</div>

-->

	<!-- 廣告區結束 =========================================================== -->




	<div id="myCarousel" class="carousel slide ad" data-ride="carousel">

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<img src="<%=request.getContextPath() %>/img/ad05.png" alt="ad05">
			</div>

			<div class="item">
				<img src="<%=request.getContextPath() %>/img/ad06.png" alt="ad06">
			</div>

			<div class="item">
				<img src="<%=request.getContextPath() %>/img/ad07.png" alt="ad07">
			</div>

			<div class="item">
				<img src="<%=request.getContextPath() %>/img/ad03.png" alt="ad03">
			</div>

			<div class="item">
				<img src="<%=request.getContextPath() %>/img/ad04.png" alt="ad04">
			</div>
		</div>


	</div>



	<!-- 燈箱廣告結束=================================================================== -->








	<!-- 搜尋區 =========================================================== -->
	<div class="container search-area">
		<div class="row">
			<div class="col-xs-12 col-sm-6 central">
				<a href="#" class="btn btn-default search-btn btn-color"> <b>尋找人才</b>
				</a>
			</div>
			<div class="col-xs-12 col-sm-6 central">
				<a href="<%=request.getContextPath()%>/case/Case_Search.jsp" class="btn btn-default search-btn btn-color	"> <b>尋找案件</b>
				</a>
			</div>
		</div>
	</div>









	<!-- How it Work區 =========================================================== -->
	<div class="hiw">
		<div class="col-xs-12 col-sm-12 central search-area">
			<h1>
				<b>How It Works</b>
			</h1>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-lg-3 central mb20">
					<img src="<%=request.getContextPath() %>/img/find.svg" class="hiw-icon">
					<h4 class="mb20">
						<b>FIND</b>
					</h4>
					<p class="mb20">Post a job to tell us about your project. We'll
						quickly match you with the right freelancers.</p>
				</div>
				<div class="col-xs-12 col-sm-6 col-lg-3 central mb20">
					<img src="<%=request.getContextPath() %>/img/hire.svg" class="hiw-icon">
					<h4 class="mb20">
						<b>HIRE</b>
					</h4>
					<p class="mb20">Browse profiles, reviews, and proposals then
						interview top candidates. Hire a favorite and begin your project.</p>
				</div>
				<div class="col-xs-12 col-sm-6 col-lg-3 central mb20">
					<img src="<%=request.getContextPath() %>/img/work.svg" class="hiw-icon">
					<h4 class="mb20">
						<b>WORK</b>
					</h4>
					<p class="mb20">Use the Upwork platform to chat, share files,
						and collaborate from your desktop or on the go.</p>
				</div>
				<div class="col-xs-12 col-sm-6 col-lg-3 central mb20">
					<img src="<%=request.getContextPath() %>/img/pay.svg" class="hiw-icon">
					<h4 class="mb20">
						<b>PAY</b>
					</h4>
					<p class="mb20">Invoicing and payments happen through Upwork.
						With Upwork Protection, only pay for work you authorize.</p>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- rank-start ============================================================= -->
	
		<jsp:include page="/rank/rank.jsp" />
	
	<!-- rank-start ============================================================= -->

	<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
	<!-- footer-end   ============================================================= -->







	<script src="https://code.jquery.com/jquery.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>
	<script src="<%=request.getContextPath()%>/js/stars.js"></script>
	
</body>
</html>