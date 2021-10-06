<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>HomeCode!</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
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
	
	.item{
	    position: absolute;
	    top: 0;
	    left: 0;
	}
	.pb40{
		padding-bottom:40px;
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





	<!-- 燈箱廣告結束=================================================================== -->

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
				<a href="<%=request.getContextPath()%>/search/Case_Search.jsp" class="btn btn-default search-btn btn-color	"> <b>尋找案件</b>
				</a>
			</div>
			<div class="col-xs-12 col-sm-6 central">
				<a href="<%=request.getContextPath()%>/search/People_Search.jsp" class="btn btn-default search-btn btn-color"> <b>尋找人才</b>
				</a>
			</div>
		</div>
	</div>









	<!-- How it Work區 =========================================================== -->
	<div class="hiw">
		<div class="col-xs-12 col-sm-12 central search-area">
			<h1>
				<b>如何發案</b>
			</h1>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-lg-3 central mb20">
					<img src="<%=request.getContextPath() %>/img/find.svg" class="hiw-icon">
					<h4 class="mb20">
						<b>張貼</b>
					</h4>
					<p class="mb20" style="text-align:left;">登入會員後，點擊發包案件內的<strong>張貼案件</strong></p>
				</div>
				<div class="col-xs-12 col-sm-6 col-lg-3 central mb20">
					<img src="<%=request.getContextPath() %>/img/hire.svg" class="hiw-icon">
					<h4 class="mb20">
						<b>確認</b>
					</h4>
					<p class="mb20" style="text-align:left;">對於來申請案件的會員，若對該會員感興趣可<strong>先聯繫</strong>，確定人選後再<strong>同意</strong>申請</p>
				</div>
				<div class="col-xs-12 col-sm-6 col-lg-3 central mb20">
					<img src="<%=request.getContextPath() %>/img/pay.svg" class="hiw-icon">
					<h4 class="mb20">
						<b>付款</b>
					</h4>
					<p class="mb20" style="text-align:left;">確認接案者後，雙方選擇是否制訂進度檢查點，並且<strong>發案者先行付款</strong>，將由HomeCode!進行第三方收款動作，等待<strong>案件結束才撥款</strong>給接案者</p>
				</div>
				<div class="col-xs-12 col-sm-6 col-lg-3 central mb20">
					<img src="<%=request.getContextPath() %>/img/work.svg" class="hiw-icon">
					<h4 class="mb20">
						<b>上工</b>
					</h4>
					<p class="mb20" style="text-align:left;">在工作期間Homecode!提供了兩樣強大的功能<br><strong>✔案件管理</strong>與<strong>✔時間軸紀錄簿功能-Timeline</strong>讓專案開發過程更透明化！</p>
				</div>
			</div>
		</div>
	</div>

<div class="pb40">

	<!-- rank-start ============================================================= -->
	
		<jsp:include page="/rank/rank.jsp" />
	
	<!-- rank-start ============================================================= -->
	
</div>	
	

	<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
	<!-- footer-end   ============================================================= -->







	<script src="https://code.jquery.com/jquery.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>
	<script src="<%=request.getContextPath()%>/js/stars.js"></script>
	
</body>
</html>