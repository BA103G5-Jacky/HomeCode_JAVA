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


</head>
<body>




<!-- 	<!-- nav bar 開始======================================================================================= --> -->

<!-- 	<nav class="navbar navbar-default" role="navigation"> -->
<!-- 	<div class="container"> -->
<!-- 		<div class="navbar-header"> -->
<!-- 			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"> -->
<!-- 				<span class="sr-only">選單切換</span> -->
<!-- 				<span class="icon-bar"></span> -->
<!-- 				<span class="icon-bar"></span> <span class="icon-bar"></span> -->
<!-- 			</button> -->

<!-- 			<!-- # linking piont 首頁--> -->
<!-- 			<a class="navbar-brand" href="index.html"> -->
<!-- 				<img src="img/homecode2.png" height="35" class="img-va"> -->
<!-- 			</a> -->

<!-- 		</div> -->

<!-- 		<!-- 手機隱藏選單區 --> -->
<!-- 		<div class="collapse navbar-collapse navbar-ex1-collapse"> -->
<!-- 			<!-- 左選單 --> -->
<!-- 			<ul class="nav navbar-nav"> -->
<!-- 				<li class="dropdown"> -->
<!-- 					<a href="#" class="collapse-toggle" data-toggle="dropdown">接手案件<b class="caret"></b></a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li><a href="#">申請中案件</a></li> -->
<!-- 						<li><a href="member_case.html">進行中案件</a></li> -->
<!-- 						<li><a href="#">已完成案件</a></li> -->
<!-- 						<li><a href="#">接案評價</a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
<!-- 			</ul> -->

<!-- 			<ul class="nav navbar-nav"> -->
<!-- 				<li class="dropdown"> -->
<!-- 					<a href="#" class="collapse-toggle" data-toggle="dropdown">發包案件 <b class="caret"></b></a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li><a href="member_post.html">張貼案件</a></li> -->
<!-- 						<li><a href="#">等待中案件</a></li> -->
<!-- 						<li><a href="member_case.html">進行中案件</a></li> -->
<!-- 						<li><a href="#">已完成案件</a></li> -->
<!-- 						<li><a href="#">發案評價</a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
<!-- 			</ul> -->

<!-- 			<ul class="nav navbar-nav"> -->
<!-- 				<li class="dropdown"><a href="#" class="collapse-toggle" data-toggle="dropdown">訊息中心 <b class="caret"></b></a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li><a href="#">HomeCode! 訊息</a></li> -->
<!-- 						<li><a href="#">會員訊息</a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
<!-- 			</ul> -->

<!-- 			<form class="navbar-form navbar-left ng-pristine margin01"> -->
<!-- 				<div class="input-group"> -->
<!-- 					<div class="dropdown input-group-btn collapse-toggle"> -->
<!-- 						<label class="btn dropdown-toggle" data-toggle="dropdown"> -->
<!-- 							<span class="glyphicon glyphicon-search "></span> -->
<!-- 							<span class="glyphicon glyphicon-chevron-down"></span> -->
<!-- 						</label> -->
<!-- 						<ul class="dropdown-menu"> -->
<!-- 							<li><a href="#">尋找案件</a></li> -->
<!-- 							<li><a href="#">尋找人才</a></li> -->
<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 					<input type="text" class="form-control" placeholder=" 找設計人才/工作案件"> -->
<!-- 				</div> -->
<!-- 			</form> -->

<!-- 			<!-- 右選單 --> -->
<!-- 			<ul class="nav navbar-nav navbar-right"> -->
<!-- 				<ul class="nav navbar-nav"> -->
<!-- 					<li class="dropdown"> -->
<!-- 						<a href="#" class="collapse-toggle glyphicon glyphicon-question-sign navicon" data-toggle="dropdown"></a> -->
<!-- 						<ul class="dropdown-menu"> -->
<!-- 							<li><a href="#">如何接案</a></li> -->
<!-- 							<li><a href="#">如何發案</a></li> -->
<!-- 							<li><a href="#">客服中心</a></li> -->
<!-- 							<li><a href="#">合約範本下載</a></li> -->
<!-- 							<li><a href="#">App下載</a></li> -->
<!-- 						</ul> -->
<!-- 					</li> -->
<!-- 				</ul> -->
<!-- 				<li><a href="#" class="glyphicon glyphicon-bell navicon"></a></li> -->
<!-- 				<li class="dropdown"> -->
<!-- 					<a href="#" class="glyphicon glyphicon-user collapse-toggle" data-toggle="dropdown">  -->
<%-- 					<span class="font-18">${memberVO.getMbUserName()}</span>  --%>
<!-- 					<span class="glyphicon glyphicon-chevron-down"></span></a> -->
<!-- 					<ul class="dropdown-menu"> -->
<%-- 						<li><a href="member_profile.html">${username}</a></li> --%>
<!-- 						<li><a href="#">設定</a></li> -->
<%-- 						<li><a href="<%=request.getContextPath()%>/index.jsp">登出</a></li> --%>
<!-- 					</ul> -->
<!-- 				</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 		<!-- 手機隱藏選單區結束 --> -->
<!-- 	</div> -->
<!-- </nav> -->

<!-- 	<!-- ==nav bar 結束======================================================================================= --> -->






	<!-- ======================header-start================================= -->
	<c:choose>
		<c:when test="${mbNo == null}">
			<jsp:include page="/beforeLoginHeader.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/loginHeader.jsp" />
		</c:otherwise>
	</c:choose>









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
				<a href="#" class="btn btn-default search-btn btn-color	"> <b>尋找案件</b>
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
								class="img-circle photo-size margin01"> <br>
							<br>
							<div class="central">
								<b>Name</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="<%=request.getContextPath() %>/img/profile02.jpg"
								class="img-circle photo-size margin01"> <br>
							<br>
							<div class="central">
								<b>Name</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="<%=request.getContextPath() %>/img/profile03.jpg"
								class="img-circle photo-size margin01"> <br>
							<br>
							<div class="central">
								<b>Name</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="<%=request.getContextPath() %>/img/profile04.jpg"
								class="img-circle photo-size margin01"> <br>
							<br>
							<div class="central">
								<b>Name</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="<%=request.getContextPath() %>/img/profile05.jpg"
								class="img-circle photo-size margin01"> <br>
							<br>
							<div class="central">
								<b>Name</b> <br> Job Success
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
							<img src="<%=request.getContextPath() %>/img/profile01.jpg"
								class="img-circle photo-size margin01"> <br>
							<br>
							<div class="central">
								<b>Name</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="<%=request.getContextPath() %>/img/profile02.jpg"
								class="img-circle photo-size margin01"> <br>
							<br>
							<div class="central">
								<b>Name</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="<%=request.getContextPath() %>/img/profile03.jpg"
								class="img-circle photo-size margin01"> <br>
							<br>
							<div class="central">
								<b>Name</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="<%=request.getContextPath() %>/img/profile04.jpg"
								class="img-circle photo-size margin01"> <br>
							<br>
							<div class="central">
								<b>Name</b> <br> Job Success
							</div>
						</div>
						<div class="col-xs-12 col-sm-2 central rank-card">
							<img src="<%=request.getContextPath() %>/img/profile05.jpg"
								class="img-circle photo-size margin01"> <br>
							<br>
							<div class="central">
								<b>Name</b> <br> Job Success
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













	<!-- footer區 =========================================================== -->
	<div class="footer">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-3">
					<h3>title</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Rem provident dolorum veniam, iure voluptate veritatis a debitis
						architecto iste deserunt aperiam consequatur, officia sequi
						dolore. Odio, quam ratione magnam fuga itaque? Dicta libero
						mollitia voluptates neque molestias, harum magnam amet.</p>
				</div>
				<div class="col-xs-12 col-sm-3">
					<h3>title</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Deleniti sapiente doloribus repellendus exercitationem optio
						veniam quidem porro quis eaque, hic corrupti nobis voluptatum
						praesentium nemo cum est expedita minus temporibus, commodi fuga
						nisi. Quis velit, enim earum, provident ab odit!</p>
				</div>
				<div class="col-xs-12 col-sm-3">
					<h3>title</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Excepturi illo distinctio possimus quas et fuga ad enim quod eius
						iure inventore perspiciatis obcaecati eveniet at consequatur sed
						animi, itaque sapiente necessitatibus vel quam illum esse
						exercitationem culpa. Beatae, nisi inventore?</p>
				</div>
				<div class="col-xs-12 col-sm-3">
					<h3>title</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Quia voluptatem necessitatibus quaerat iste ad aliquam earum
						soluta vitae corporis, nulla sed iusto odit, sunt molestias
						exercitationem quibusdam ex veniam deserunt beatae? Possimus
						fugiat cum omnis blanditiis ab ipsum, deleniti facilis!</p>
				</div>
			</div>
		</div>
	</div>
	<!-- footer區結束 =========================================================== -->







	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>
</body>
</html>