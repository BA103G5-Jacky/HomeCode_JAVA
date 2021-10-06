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

//Case
		
		CaseDAO csSvc = new CaseDAO();
		
		if(request.getAttribute("applicantCaseList") == null){
			
			List<CaseVO> applicantCaseList = csSvc.findByHirerNo((String)session.getAttribute("mbNo"),"接洽中");
			request.setAttribute("applicantCaseList", applicantCaseList);
		}
		
		NumberFormat nf = NumberFormat.getInstance();
		request.setAttribute("nf", nf);
	

%>




<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>發案-等待中案件管理</title>
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
		<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/case/css/applicant.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/stars.css">

		
	</head>
	<body>




<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
<!-- ======================header-end ================================== -->
	
	
<!-- ========================================process==========================================-->
<div class="container mrg-top-15 mb20">
	<div class="row">
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=applicant&actor=hirer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4 active">等待中案件</div></a>
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=onGoCase&actor=hirer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">進行中案件</div></a>
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=doneCase&actor=hirer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">已完成案件</div></a>               
	</div> <!-- Row End -->
</div> <!-- container mrg-top-15 width-75per End -->


<!-- ========================================案件內容==========================================-->

<div class="case hiw">		
	<div class="container mb20">
		<div class=" mrg-top-15  col-xs-12">
			<h2>等待中案件</h2>

			<div class="mrg-top-15 cs-content row oneCase">
<%-- 			<%@ include file="page1.file" %>  --%>
				<jsp:useBean id="appSvc" class="com.applicant.model.ApplicantService"/>
				<c:forEach var="cs" items="${applicantCaseList}" >
					<div class="row img-va">
						<div class="col-xs-12 col-sm-8">
							<div><h3><a href="<%=request.getContextPath()%>/case/case.do?csNo=${cs.csNo}&action=caseInfo" class="purple" ${(newPost eq cs.csNo) ? 'id="new"':''}>${cs.csName}</a></h3></div>
							<div class="font-content"><p><b>目前申請人數</b> - <span id="applicantSize">${appSvc.getOneCaseWhoApply(cs.csNo).size()}</span>人</p></div>
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
							<div class="col-xs-12 col-sm-6 col-sm-offset-6">
								
								<c:choose>
									<c:when test="${cs.freelancerNo == null }">
										<form action="<%=request.getContextPath() %>/case/case.do" method="post">
											<input type="hidden" name="csNo" value="${cs.csNo}">
											<input type="hidden" name="mbNo" value="${mbNo}">
											<input type="hidden" name="action" value="deleteCase">
											<div class="font-content"><button type="submit" class="col-xs-12  btn btn-default central btn-tab-4">刪除案件</button></div>
										</form>
										
										<!-- 按下之後觀看申請人列表MODAL 開始 -->
										<a href='#modal-id${cs.csNo}' data-toggle="modal">
											<button type="button" class="col-xs-12  btn btn-default central btn-tab-4">觀看申請人</button>
										</a>									
										<!-- 按下之後觀看申請人列表MODAL 結束 -->
									</c:when>
									<c:otherwise>
										<button type="button" class="col-xs-12  btn btn-default central btn-tab-4">確認合約並付款</button>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				
				<hr class="hr-color">
				<jsp:useBean id="mbScv" class="com.member.model.MemberService"/>
				<jsp:useBean id="csScv" class="com.cs.model.CaseService"/>
				<!-- ======modal test======== -->
					<div class="modal fade" id="modal-id${cs.csNo}">
						<div class="modal-dialog">
							<div class="modal-content rate">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h3 class="modal-title">${cs.csName}</h3>
								</div>
								<div class="">
									<c:choose>
											<c:when test="${appSvc.getOneCaseWhoApply(cs.csNo).size() == 0}">
												<h3 style="color:#FF0000">目前無人申請接案</h3>
											</c:when>
											<c:otherwise>
												
												<c:forEach var="mbNoList" items="${appSvc.getOneCaseWhoApply(cs.csNo)}">
												
														<div class="applicants col-sm-12 marginTB15 ${cs.csNo}${mbNoList}">
															<div class="col-xs-12 col-sm-3 img-va">
																<img class="portrait img-responsive" src="<%=request.getContextPath() %>/Image/Image.do?index=0&MBNO=${mbNoList}">
															</div>
															<div class="col-xs-12 col-sm-9">
																<div class="col-xs-12 col-sm-8">
																	<div class="mbName">
																		<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${mbNoList}&action=getMbInfo" class="purple"> 
																			${mbScv.getOneMemberNoImg(mbNoList).mbLstName}${mbScv.getOneMemberNoImg(mbNoList).mbFstName}
																		</a>
																	</div>
																	<br>
																	<div class="csSuccessTimes">接案成功次數&nbsp&nbsp&nbsp&nbsp${mbScv.getOneMemberNoImg(mbNoList).csSuccessTimes}&nbsp(${mbScv.getOneMemberNoImg(mbNoList).csTimes})</div>
																	<div class="freeScoreAvg">
																		接案平均分數&nbsp&nbsp&nbsp
																		${csScv.getMbFreelancerScoreAvg(mbNoList).toString().substring(0,3)}
																	</div>
																	
																</div>
																<div class="col-xs-12 col-sm-4 ">
																	<div class="col-xs-12 col-sm-6">
																		<form action="<%=request.getContextPath()%>/Applicant/ApplicantServlet" method="post">
																			<input type="hidden" name="action" value="checkApplicant">
																			<input type="hidden" name="freelancerNo" value="${mbNoList}">
																			<input type="hidden" name="hirerNo" value="${mbNo}">
																			<input type="hidden" name="csNo" value="${cs.csNo}">
																			<button type="submit" class="btn btn-default central btn-tab-4">同意</button>
																		</form>
																	</div>
																	<div class="col-xs-12 col-sm-6">
																		<form action="" method="post">
																			<button type="button" class="btn btn-default central btn-tab-4" onclick='refuse("${cs.csNo}","${mbNoList}",${appSvc.getOneCaseWhoApply(cs.csNo).size()});'>取消</button>
																		</form>
																	</div>							
																</div>
															</div>
														</div>
														
												</c:forEach>
																						
											</c:otherwise>
										</c:choose>

								</div>
								<div class="modal-footer">
									
								</div>
							</div>
						</div>
					</div>
				<!-- ======modal test======== -->
				
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
		<script src="<%=request.getContextPath()%>/js/stars.js"></script>
		<script>
		
			function refuse(csNo, mbNo, applicantSize){
				console.log(csNo);
				console.log(mbNo);
				var xhr = new XMLHttpRequest();
				var applicant =  "."+csNo+mbNo;
				var app = applicantSize-1;
				 xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200) {
							$(applicant).remove();
							$("#applicantSize").text(app);
						}//xhr.status == 200
					}//xhr.readyState == 4
				};//onreadystatechange 
		
				//建立好Get連接http://localhost:8081/Local-BA103G5_Jacky/message/sysbox.jsp#mesg-content0
				var url = "<%=request.getContextPath()%>/Applicant/ApplicantServlet?action=deleteByFree&from=hirer&mbNo="+mbNo+"&csNo="+csNo;
				xhr.open("Get", url, true);
				//送出請求 
				xhr.send(null);
				console.log(url);
			}
			
		</script>
		
	</body>
<%-- 	<%@ include file="page2.file" %> --%>
</html>