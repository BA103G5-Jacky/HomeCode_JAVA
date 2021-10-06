<%@page import="com.member_skill.model.Member_skillService"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.cs.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reported_case.model.*"%>
<%@ page import= "java.util.*"%>
<%@ page import="com.skill_type.model.*"%>
<%@ page import="com.member_skill.model.*"%>
<%@ page import="com.work.model.*"%>
<%@ page import="com.applicant.model.*"%>
<%@ page import="java.text.NumberFormat"%>

<% 
response.setHeader("Cache-Control","no-store");
 	

		NumberFormat nf = NumberFormat.getInstance();
		request.setAttribute("nf", nf);
		
		MemberService mbSvc = new MemberService();
 		pageContext.setAttribute("mbSvc",mbSvc);
%>




<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>收藏案件列表</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<!-- # linking piont local style css-->	
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">		
		<!-- # linking piont bootstrap-lightbox-->	
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
		<!-- # linking piont tab-icon-->
		<link rel="icon" type="<%=request.getContextPath()%>/images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/favoriteCase/css/favoriteCase.css">
			
		
	</head>
<body>




	<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
	<!-- ======================header-end ================================== -->
	
	<!-- ========================================button three==========================================-->
	<div class="container mrg-top-15 mb20">
		<div class="row">
			<a href="<%=request.getContextPath()%>/favoriteCase/favoriteCase.do?mbNo=${mbNo}&action=favoriteCaseList"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4 active">收藏中案件</div></a>
			              
		</div> <!-- Row End -->
	</div>
	<!-- ========================================button three end=======================================-->


<!-- ========================================案件內容==========================================-->
<div class="case hiw">				
	<div class="container mb20">
		<div class=" mrg-top-15  col-xs-12">
			<h2>收藏中案件</h2>
			<div class="mrg-top-15 cs-content row">
				<c:forEach var="cs" items="${fcList}" >
				
					<div class="row img-va ">
						<div class="col-xs-12 col-sm-8 " >
							<div><h3><a href="<%=request.getContextPath()%>/case/case.do?csNo=${cs.csNo}&action=caseInfo" class="purple">${cs.csName}</a></h3></div>
							<div class="font-content">
								<p>發案人 - 
									<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${cs.hirerNo}&action=getMbInfo" class="purple">
										${mbSvc.getOneMember(cs.hirerNo).mbLstName}${mbSvc.getOneMember(cs.hirerNo).mbFstName}
									</a>
								</p>
							</div>
							<div class="font-content">
								<table class="font-content">	
									<tr>
										<td>開始時間 </td><td>${cs.startDate.substring(0, 10)}</td>
										<td>結束時間</td><td>${cs.endDate.substring(0, 10)}</td>
										<td>案件金額</td><td>$${nf.format(cs.csPayment)}</td>
										<td>案件規模</td><td> ${cs.csLevel}</td>
									</tr>
								</table>
							</div>
						</div>

						<div class="col-xs-12 col-sm-4" >
							<div class="col-xs-12 col-sm-6 col-sm-offset-6" >
								<form action="<%=request.getContextPath()%>/favoriteCase/favoriteCase.do" method="post">
									<input type="hidden" name="csNo" value="${cs.csNo}">
									<input type="hidden" name="mbNo" value="${mbNo}">
									<input type="hidden" name="action" value="removeFavorite">
									<button type="submit" class="col-xs-12  btn btn-default central btn-tab-4">取消追蹤&nbsp&nbsp&nbsp&nbsp<div class="glyphicon glyphicon-heart-empty"></div></button>
								</form>
							</div>
						</div>
					</div>
					
					<hr class="hr-color">
				</c:forEach>
			</div>
		</div>		
	</div>
</div>				

<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
<!-- footer-end   ============================================================= -->

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/script.js"></script>
		
	</body>
</html>