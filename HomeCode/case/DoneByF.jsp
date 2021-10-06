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

<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>

<% 
//Case
		CaseDAO csSvc = new CaseDAO();
		
		if(request.getAttribute("doneCsList") == null){
			List<CaseVO> doneCsList =csSvc.findByFreelancerNo((String)session.getAttribute("mbNo"),"已完成");
			pageContext.setAttribute("doneCsList",doneCsList);	
		}
		
		MemberService mbSvc = new MemberService();
 		pageContext.setAttribute("mbSvc",mbSvc);

%>

<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>接案者已完成案件管理</title>
		<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
		<!-- # linking piont bootstrap-lightbox-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<!-- # linking piont local style css-->	
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
		<!-- # linking piont tab-icon-->
		<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">	
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/stars.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/case/css/doneByFree.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/case/css/starability-slot.min.css">
		
	</head>
	<body>




	<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
	<!-- ======================header-end ================================== -->


<div style="padding-bottom:40px;">	

	
<!-- ========================================button three==========================================-->
<div class="container mrg-top-15 mb20">
	<div class="row">
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=applicant&actor=freelancer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">申請中案件</div></a>
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=onGoCase&actor=freelancer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">進行中案件</div></a>
		<a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=doneCase&actor=freelancer"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4 active">已完成案件</div></a>               
	</div> <!-- Row End -->
</div>
<!-- ========================================button three end=======================================-->


<!-- ========================================案件內容==========================================-->
<div class="case hiw">			
	<div class="container" style="padding-bottom:40px; min-height:75%;">
		<div class=" mrg-top-15  col-xs-12  ">
			<h2>已完成案件</h2>
	
			<div class="mrg-top-15 cs-content row">
			
				<c:forEach var="cs" items="${doneCsList}" varStatus="i">
					<div class="row img-va">
						<div class="col-xs-12 col-sm-8" >
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
										<td>正式結束時間</td><td>${cs.realEndDate}</td>
										<td>案件規模</td><td>${cs.csLevel}</td>
									</tr>
								</table>
							</div>
						</div>

						<div class="col-xs-12 col-sm-4" >
								<div class="col-xs-12 col-sm-6 col-sm-offset-6" >
									<c:if test="${cs.hirerScore==0}">
										<a href="#modal-id${cs.csNo}" data-toggle="modal">
										 	<div class="col-xs-12  btn btn-default central btn-tab-4">我要評價</div>
										</a>
									</c:if>
									<br>
									
									<div class="rating">
										您的評價 
										<div id="star_con" class="star-vote" >
										   	<span id="add_star" class="add-star"></span>
										    <span id="del_star" class="del-star"></span>
										</div>
									</div>
									<c:if test="${cs.freelancerScore==0}">
										 <script>
											 var raiting=document.getElementsByClassName("rating")[${i.index}];
											 raiting.style = "display:none";
										 </script>
										  <div>對方尚未給您評價</div>
									</c:if>
								</div>
						</div>
					</div>
					
					<hr class="hr-color">
					
					<!-- ======modal test======== -->
				<form method="post" action="<%=request.getContextPath() %>/case/case.do">
					<div class="modal fade" id="modal-id${cs.csNo}">
						<div class="modal-dialog">
							<div class="modal-content rate">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">評價發案人</h4>
								</div>
								<div class="modal-body">
									<legend>發案人 - ${mbSvc.getOneMember(cs.hirerNo).mbLstName}${mbSvc.getOneMember(cs.hirerNo).mbFstName}</legend>
									
									 <fieldset class="starability-basic">
									      <legend>評價分數</legend>
									
									      <input type="radio" id="rate1" name="hirerScore" value="1" />
									      <div class="glyphicon glyphicon-star"></div>
									
									      <input type="radio" id="rate2" name="hirerScore" value="2" />
									      <div class="glyphicon glyphicon-star"></div>
									      <div class="glyphicon glyphicon-star"></div>
									
									      <input type="radio" id="rate3" name="hirerScore" value="3" />
									      <div class="glyphicon glyphicon-star"></div>
									      <div class="glyphicon glyphicon-star"></div>
									      <div class="glyphicon glyphicon-star"></div>
									
									      <input type="radio" id="rate4" name="hirerScore" value="4" />
									      <div class="glyphicon glyphicon-star"></div>
									      <div class="glyphicon glyphicon-star"></div>
									      <div class="glyphicon glyphicon-star"></div>
									      <div class="glyphicon glyphicon-star"></div>
									
									      <input type="radio" id="rate5" name="hirerScore" value="5" />
									      <div class="glyphicon glyphicon-star"></div>
									      <div class="glyphicon glyphicon-star"></div>
									      <div class="glyphicon glyphicon-star"></div>
									      <div class="glyphicon glyphicon-star"></div>
									      <div class="glyphicon glyphicon-star"></div>
									    </fieldset>
									    
									    <legend>評價內容</legend>
									    <textarea class="form-control" id="comment" rows="5" cols="20" name="hirerComment" placeholder="請輸入50字以內的評論"></textarea>
								</div>
								<div class="modal-footer">
									<input type="hidden" name="csNo" value="${cs.csNo}">
									<input type="hidden" name="action" value="updateHirerScore">
									<!-- magic button -->
									<button type="button" class="btn btn-default" style="background-color: #eee; color: white;" onclick="inputInfo1();">free</button>
									<!-- magic button -->
									<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
									<button type="submit" class="btn btn-purple">送出</button>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!-- ======modal test======== -->
					
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
		<script src="<%=request.getContextPath()%>/js/stars.js"></script>
		<script>
			window.onload = function(){
				for(var i=0; i<${doneCsList.size()}; i++){
					<c:forEach var="vo" items="${doneCsList}" varStatus="i">
						showStarsList(${vo.freelancerScore}, ${i.index});
					</c:forEach>
				}
			}
			
			function rate(i){
				for(var j = 0; j<5;j++){
					document.getElementsByName("hirerScore")[j].removeAttribute("checked");
					if( j == (i-1)){
						document.getElementsByName("hirerScore")[j].setAttribute("checked","");
					}
				}
				
			}
		</script>
		
		<!-- magic button -->
		<script>
			function inputInfo1(){
				document.getElementById('rate5').checked = "checked";
				document.getElementById('comment').innerHTML = "謝謝乃哥給我工作機會還沒有幹爆我，我會努力尊敬前輩的";
				
			}
		</script>
		<!-- magic button -->
	</body>
</html>