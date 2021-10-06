<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
<!-- # linking piont bootstrap-lightbox-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
<!--[if lt IE 9]>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<!-- # linking piont tab-icon-->
<link rel="icon" type="images/gif"
	href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">

<style type="text/css">
.whole-content {
	margin-top: 50px;
	margin-bottom: 100px;
}
.loginPlz {
    font-weight: bolder;
    color: #7e55ab;
}

.tab-content {
	background-color: #fafafa;
}

#title {
	background-color: #f5f5f5;
	font-size: 30px;
	text-align: center;
	margin-top: 50px;
}

#homecode {
	font-family: cursive;
}

.btn-download {
	text-align: center;
	border: 1px solid;
	font-size: 50px;
	border-radius: 5px;
	margin-top: 10px;
}

.titlepic {
	height: 40px;
}

.tab-pane {
	font-size: 20px;
}

.downloadspace{
height:500px;
}
.picicon{
height:200px;
width:200px;
}

.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover {
    color: #fff;
    cursor: default;
    background-color: #5e2b97;
    border: 1px solid #ddd;
    border-bottom-color: transparent;
}
.loginPlz:focus, .loginPlz:active, .loginPlz:hover, .loginPlz:active:focus {
    color: #5e2b97;
    border-color: #5E2B97;
}
.pad-tb-30{
	padding-top:30px;
	padding-bottom:30px;
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
<%
	String tab = request.getParameter("tab");
	pageContext.setAttribute("tab", tab);
%>

<div style="padding-bottom:40px;">	
	<!-- 新手上路ㄉ內容 -->

	<div class="container whole-content">
		<div class="row">

			<div role="tabpanel">
				<!-- 標籤面板：標籤區 -->

				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="${ (tab eq 1) or (tab eq null) ? 'active' : '' }">
						<a href="#tab1" aria-controls="tab1" rol="tab" data-toggle="tab" class="loginPlz">如何接案</a>
					</li>
					<li role="presentation" class="${ tab eq 2 ? 'active' : '' }">
						<a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" class="loginPlz">如何發案</a>
					</li>
					<li role="presentation" class="${ tab eq 3 ? 'active' : '' }">
						<a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab" class="loginPlz">合約範本下載</a>
					</li>
				</ul>
				

				<!-- 標籤面板：內容區 -->


				<!-- 如何接案 -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane ${ (tab eq 1) or (tab eq null) ? 'active' : '' } central" id="tab1">
						<div class="pad-tb-30 col-sm-12 img-va">
							<div class="col-xs-12 col-sm-5">
								<img src="<%=request.getContextPath()%>/img/search.svg" class="picicon">
							</div>
							<div class="col-xs-12 col-sm-7" style="text-align: left;">
								在Homecode!上依據您<strong>能力</strong>和<strong>喜好</strong>來搜尋案件
							</div>
						</div>
						<div class="pad-tb-30 col-sm-12 img-va" style="background-color:#eee;">
							<div class="col-xs-12 col-sm-5 col-sm-push-7">
								<img src="<%=request.getContextPath()%>/img/click.svg" class="picicon">
							</div>
							<div class="col-xs-12 col-sm-7 col-sm-pull-5" style="text-align: right;">
								對心動的案件<strong>點擊申請</strong>
							</div>
						</div>
						<div class="pad-tb-30 col-sm-12 img-va">
							<div class="col-xs-12 col-sm-5">
								<img src="<%=request.getContextPath()%>/img/tasks.svg" class="picicon">
							</div>
							<div class="col-xs-12 col-sm-7" style="text-align: left;">
								開始上工！在工作期間Homecode!提供了兩樣強大的功能<strong>✔案件管理</strong>與<strong>✔時間軸紀錄簿功能-Timeline</strong>讓專案開發過程更透明化！
							</div>
						</div>
						<div class="pad-tb-30 col-sm-12 img-va" style="background-color:#eee;">
							<div class="col-xs-12 col-sm-5 col-sm-push-7">
								<img src="<%=request.getContextPath()%>/img/buy.svg" class="picicon">
							</div>
							<div class="col-xs-12 col-sm-7 col-sm-pull-5" style="text-align: right;">
								<strong>案件完成後</strong>，立即<strong>領取</strong>您所應得的報酬，無須煩惱任何交易問題！
							</div>
						</div>
					</div>
					
					<!-- 如何發案 -->
					<div role="tabpanel" class="tab-pane central ${ tab eq 2 ? 'active' : '' }" id="tab2">
						<div class="pad-tb-30 col-sm-12 img-va">
							<div class="col-xs-12 col-sm-5">
								<img src="<%=request.getContextPath()%>/img/find.svg"  class="picicon">
							</div>
							<div class="col-xs-12 col-sm-7" style="text-align: left;">
								登入會員後，點擊發包案件內的<strong>張貼案件</strong>
							</div>
						</div>
						<div class="pad-tb-30 col-sm-12 img-va" style="background-color:#eee;">
							<div class="col-xs-12 col-sm-5 col-sm-push-7">
								<img src="<%=request.getContextPath()%>/img/hire.svg"  class="picicon">
							</div>
							<div class="col-xs-12 col-sm-7 col-sm-pull-5" style="text-align: right;">
								對於來申請案件的會員，若對該會員感興趣可<strong>先聯繫</strong>，確定人選後再<strong>同意</strong>申請
							</div>
						</div>
						<div class="pad-tb-30 col-sm-12 img-va">
							<div class="col-xs-12 col-sm-5">
								<img src="<%=request.getContextPath()%>/img/searchP.svg"  class="picicon">
							</div>
							<div class="col-xs-12 col-sm-7" style="text-align: left;">
								若沒有滿意的申請人，也可在Homecode!上依據人才的<strong>能力</strong>和<strong>喜好</strong>來搜尋人才，在發出訊息邀請該會員申請
							</div>
						</div>
						<div class="pad-tb-30 col-sm-12 img-va"  style="background-color:#eee;">
							<div class="col-xs-12 col-sm-5 col-sm-push-7">
								<img src="<%=request.getContextPath()%>/img/pay.svg"   class="picicon">
							</div>
							<div class="col-xs-12 col-sm-7 col-sm-pull-5" style="text-align: right;">
								確認接案者後，雙方選擇是否制訂進度檢查點，並且<strong>發案者先行付款</strong>，將由HomeCode!進行第三方收款動作，等待<strong>案件結束才撥款</strong>給接案者
							</div>
						</div>
						<div class="pad-tb-30 col-sm-12 img-va">
							<div class="col-xs-12 col-sm-5">
								<img src="<%=request.getContextPath()%>/img/work.svg"  class="picicon">
							</div>
							<div class="col-xs-12 col-sm-7" style="text-align: left;">
								在工作期間Homecode!提供了兩樣強大的功能<strong>✔案件管理</strong>與<strong>✔時間軸紀錄簿功能-Timeline</strong>讓專案開發過程更透明化！
							</div>
						</div>
					</div>
					
					
					<!-- 合約部分 -->
					<div role="tabpanel" class="tab-pane central ${ tab eq 3 ? 'active' : '' }" id="tab3">
						<div class="pad-tb-30 col-sm-12 img-va"  style="background-color:#eee;">
							<div class="col-xs-12 col-sm-8">
								HomeCode!提供了交易合約，讓初出茅廬，沒有頭緒的您提供一盞光明燈！
							</div>
							<div class="col-xs-12 col-sm-4">
								<a class="btn-download loginPlz"
									href="<%=request.getContextPath()%>/FileDownload/contract_download/contract.doc">點我下載
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>


	<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
	<!-- footer-end   ============================================================= -->












	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="bootstrap-lightbox/bootstrap-lightbox.js"></script>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>

	<script type="text/javascript">
		if ($("a.attr('aria-expanded')") == "ture") {
			$(".btn-download").css("display", "none");

		}
		// else{
		// 	$(".btn-download").css("display","none");
		// }
	</script>
</body>
</html>