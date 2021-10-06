<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
				<span class="sr-only">選單切換</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<!-- # linking piont 首頁-->
			<a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">
				<img src="<%=request.getContextPath()%>/img/homecode2.png" height="35" class="img-va">
			</a>

		</div>
		<!-- 手機隱藏選單區 -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<form class="navbar-form navbar-left ng-pristine margin01">
				<div class="input-group">
					<div class="dropdown input-group-btn collapse-toggle">
							<label class="btn dropdown-toggle" data-toggle="dropdown">
								<span class="glyphicon glyphicon-search "></span>
								<span class="glyphicon glyphicon-chevron-down "></span>
							</label>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/case/Case_Search.jsp">尋找案件</a></li>
							<li><a href="#">尋找人才</a></li>
						</ul>
					</div>
					<input type="text" class="form-control" placeholder=" 找設計人才/工作案件">
				</div>
			</form>
					
			<!-- 左選單 -->
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="collapse-toggle" data-toggle="dropdown">瀏覽分類 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<%=request.getContextPath()%>/search/People_Search.jsp">瀏覽開發人才</a></li>
						<li><a href="<%=request.getContextPath()%>/search/Case_Search.jsp">瀏覽工作案件</a></li>
					</ul>
				</li>
			</ul>

			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="collapse-toggle" data-toggle="dropdown">新手上路 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="NewPeopleFirstComeToHomecode!.html">如何接案</a></li>
						<li><a href="NewPeopleFirstComeToHomecode!.html">如何發案</a></li>
						<li><a href="NewPeopleFirstComeToHomecode!.html">客服中心</a></li>
						<li><a href="NewPeopleFirstComeToHomecode!.html">合約範本下載</a></li>
					</ul>
				</li>
			</ul>
				
			<!-- 右選單 -->
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="<%=request.getContextPath() %>/signup/visitor_signup.jsp" class="glyphicon glyphicon-pencil">
						<span class="font">註冊</span>
					</a>
				</li>
				<!-- # linking piont 進入登入畫面 -->
				<li>
					<a href="<%=request.getContextPath()%>/login/vistor_login.jsp" class="glyphicon glyphicon-log-in">
						<span class="font">登入</span>
					</a>
				</li>
				<a href="<%=request.getContextPath()%>/signup/visitor_signup.jsp" class="btn btn-default btn-right btn-color"> 成為開發接案者</a>
			</ul>
		</div>
		<!-- 手機隱藏選單區結束 -->
	</div>
</nav>
