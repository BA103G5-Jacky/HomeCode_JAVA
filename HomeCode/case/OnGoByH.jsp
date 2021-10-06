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
<%@ page import="java.text.NumberFormat" %>

<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>
<% 

//Case
		CaseDAO csSvc = new CaseDAO();
// 		List<CaseVO> list4 = new ArrayList();
		if(request.getAttribute("onGoCsList") == null){
			List<CaseVO> onGoCsList = csSvc.findByHirerNo((String)session.getAttribute("mbNo"),"進行中");
			pageContext.setAttribute("onGoCsList",onGoCsList);	
		}
		NumberFormat nf = NumberFormat.getInstance();
		request.setAttribute("nf", nf);
%>




<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>發案-逕行中案件管理</title>
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
		<link rel="stylesheet" href="<%=request.getContextPath()%>/case/css/ongobyh.css">
		
	</head>
	<body>




	<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
	<!-- ======================header-end ================================== -->




<div style="padding-bottom:40px;">	


	
<div class="container mrg-top-15 mb20">
	<div class="row">
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=applicant&actor=hirer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">等待中案件</div></a>
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=onGoCase&actor=hirer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4 active">進行中案件</div></a>
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=doneCase&actor=hirer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">已完成案件</div></a>               
	</div> <!-- Row End -->
</div> <!-- container mrg-top-15 width-75per End -->



<!-- ========================================案件內容==========================================-->
<div class="case hiw">			
	<div class="container" style="padding-bottom:40px; min-height:75%;">
	<div class=" mrg-top-15  col-xs-12  ">
	<h2>進行中案件</h2>

			<div class="mrg-top-15 cs-content row">
			<jsp:useBean id="mbSvc" class="com.member.model.MemberService"/>
			<c:forEach var="cs" items="${onGoCsList}" >
			<div class="row img-va">
			
		 			
					<div class="col-xs-12 col-sm-8" >
					<div><h3><a href="<%=request.getContextPath()%>/case/case.do?csNo=${cs.csNo}&action=caseInfo" class="purple">${cs.csName}</a></h3></div>
					<div class="font-content">
						<p>接案人 - 
							<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${cs.freelancerNo}&action=getMbInfo" class="purple"> 
								${mbSvc.getOneMember(cs.freelancerNo).mbLstName}${mbSvc.getOneMember(cs.freelancerNo).mbFstName}
							</a>
						</p>
					</div>
							<div>
								<table class="font-content">	
									<tr>
										<td>開始時間 </td><td>${cs.startDate}</td>
										<td>結束時間</td><td>${cs.endDate}</td>
										<td>案件規模</td><td>${cs.csLevel}</td>
									</tr>
								</table>
							</div>
				
					</div>

					<div class="col-xs-12 col-sm-4  margin-right text-right" >
					<div ><p>${cs.csLoc}</p></div>
					<div class="font-content"><p >${cs.csPayState}</p></div>
					<div >
					<div class="font-content"><p >$${nf.format(cs.csPayment)}</p></div>
						</div>
					</div>
			</div>
			<hr class="hr-color">
			</c:forEach>

		</div>
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