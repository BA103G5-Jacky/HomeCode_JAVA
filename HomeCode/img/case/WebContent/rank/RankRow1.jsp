<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.rank.model.*"%>


<%
    RankService rankSvc = new RankService();
    List<RankVO> list = rankSvc.getAll();
    pageContext.setAttribute("list",list);
%>

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


<link rel="stylesheet" href="bootstrap-lightbox/bootstrap-lightbox.css">

<script src="bootstrap-lightbox/bootstrap-lightbox.js"></script>


<style type="text/css" media="screen">

#rank {
	text-align:center;
	margin-left:350px;
}


</style>
</head>
<body>
	<!-- ======================================================================================= -->
	<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="<%=request.getContextPath()%>/index.jsp"><img
					src="<%=request.getContextPath()%>/img/homecode2.png" height="35"
					class="img-va"></a>

			</div>

			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">

				<form class="navbar-form navbar-left ng-pristine margin01">
					<div class="input-group">
						<div class="input-group-btn">
							<label class="btn  unstyled-button"> <span
								class="glyphicon glyphicon-search "></span> <span
								class="glyphicon glyphicon-chevron-down "></span>
							</label>
						</div>
						<input type="text" class="form-control" placeholder=" 找設計人才/工作案件">
					</div>
				</form>

				<!-- 左選單 -->
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="collapse-toggle"
						data-toggle="dropdown">瀏覽分類 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">瀏覽開發人才</a></li>
							<li><a href="#">瀏覽工作案件</a></li>
						</ul></li>
				</ul>

				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="collapse-toggle"
						data-toggle="dropdown">新手上路 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">如何接案</a></li>
							<li><a href="#">如何發案</a></li>
							<li><a href="#">客服中心</a></li>
							<li><a href="#">合約範本下載</a></li>
							<li><a href="#">App下載</a></li>
						</ul></li>
				</ul>

				<!-- 右選單 -->
				<ul class="nav navbar-nav navbar-right">
					<li><a href="visitor_signup.jsp"
						class="glyphicon glyphicon-pencil"> <span class="font">註冊</span></a></li>

					<li><a
						href="<%=request.getContextPath()%>/login/vistor_login.jsp"
						class="glyphicon glyphicon-log-in"> <span class="font">登入</span></a></li>
					<a
						href="<%=request.getContextPath()%>/login/vistor_login.jsp
					"
						class="btn btn-default btn-right btn-color"> 成為開發接案者</a>

				</ul>

			</div>
			<!-- 手機隱藏選單區結束 -->
		</div>
	</nav>
	<!-- TEST區 ============================================================================================== -->


<div class="container" id="rank">
	<div class="row">
		


	<table  border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有排行榜資料 - ListAllrank.jsp</h3>
		<a href="rankHomePage.jsp"><img src="<%=request.getContextPath()%>/img/1.JPG" width="100" height="100" border="0">回首頁</a>
		</td>
	</tr>
</table>



<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>會員編號</th>
		<th>會員排名</th>
		
	</tr>
	
	<c:forEach var="rankVO" items="${list}" >
		<tr align='center' valign='middle'>
			<td>${rankVO.mbno}</td>
			<td>${rankVO.rankno}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rank.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mbno" value="${rankVO.mbno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>rank.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="mbno" value="${rankVO.mbno}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>



	</div>
</div>



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
</body>
</html>