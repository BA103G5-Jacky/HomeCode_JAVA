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
<%@ page import="java.text.NumberFormat"%>

<% 
response.setHeader("Cache-Control","no-store");

//Case
		CaseDAO csSvc = new CaseDAO();
		if(request.getAttribute("onGoCsList") == null){
			List<CaseVO> onGoCsList = csSvc.findByFreelancerNo((String)session.getAttribute("mbNo"),"進行中");
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
		<title>會員資訊</title>
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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/case/css/ongobyf.css">
			

	</head>
	<body>




	<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
	<!-- ======================header-end ================================== -->
	
<!-- ========================================button three==========================================-->
<div class="container mrg-top-15 mb20">
	<div class="row">
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=applicant&actor=freelancer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">申請中案件</div></a>
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=onGoCase&actor=freelancer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4 active">進行中案件</div></a>
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=doneCase&actor=freelancer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">已完成案件</div></a>               
	</div> <!-- Row End -->
</div>
<!-- ========================================button three end=======================================-->


<!-- ========================================案件內容==========================================-->
<div class="case hiw">				
	<div class="container mb20">
	<div class=" mrg-top-15  col-xs-12  ">
	<h2>進行中案件</h2>

			<div class="mrg-top-15 cs-content row">
			<jsp:useBean id="mbSvc" class="com.member.model.MemberService"/>
			<c:forEach var="cs" items="${onGoCsList}" >
			<div class="row img-va">
			
		 
					<div class="col-xs-12 col-sm-8 " >
					<div><h3><a href="<%=request.getContextPath()%>/case/case.do?csNo=${cs.csNo}&action=caseInfo" class="purple">${cs.csName}</a></h3></div>
					<div class="font-content">
						<p>發案人 - 
							<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${cs.hirerNo}&action=getMbInfo" class="purple"> 
								${mbSvc.getOneMember(cs.hirerNo).mbLstName}${mbSvc.getOneMember(cs.hirerNo).mbFstName}
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
					<div class="font-content"><p>$${nf.format(cs.csPayment)}</p></div>
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