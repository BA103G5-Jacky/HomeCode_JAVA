<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cs.model.*"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.sql.Date" %>
<%@ page import="com.case_type.model.*" %>
<%@ page import="com.skill_type.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="com.applicant.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>

<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>
<%		
	CaseVO caseVO = null;
	String mbNo = (String)session.getAttribute("mbNo");
	System.out.println(request.getAttribute("caseVO"));
	if(request.getAttribute("caseVO") == null){
		caseVO = new CaseService().getOneCase("CS0000007");
		request.setAttribute("caseVO", caseVO);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	} else {
		caseVO = (CaseVO) request.getAttribute("caseVO");
	}
	
	NumberFormat nf = NumberFormat.getInstance();
	request.setAttribute("nf", nf);
	
	String state = "接洽中";
	request.setAttribute("state", state);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CaseInfomation</title>
		
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/materialize.css"	media="screen,projection" />
		<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
		<!-- # linking piont bootstrap-lightbox-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
		<!--[if lt IE 9]>
					<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
					<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
		
		<!-- # linking piont tab-icon-->
		<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/case/css/case_info.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/stars.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pre.css">
		<style>
			.purple{
				background-color: rgb(249,249,249) !important;
			}
			pre{
				background-color:white;
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
	
	
	

	<jsp:useBean id="mbSvc" class="com.member.model.MemberService"/>
	<div class="container mrg-top-15 mb20">
		<div class="row">
			<a href="<%=request.getContextPath()%>/case/case.do?csNo=${caseVO.csNo}&action=caseInfo">
				<button type="button" class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4 active" id="caseInfo">案件詳情</button>
			</a>
			<c:if test="${mbNo.equals(caseVO.hirerNo) || mbNo.equals(caseVO.freelancerNo)}">
				<a href="<%=request.getContextPath()%>/milestone/milestone.do?action=getCase_milestone&csNo=${caseVO.csNo}">
					<button type="button" class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4" id="caseMilestone">案件進度</button>
				</a>
				<a href="<%=request.getContextPath()%>/timeline/timeline.do?csNo=${caseVO.csNo}&action=getCaseTimeline">
					<button type="button" class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4" id="caseTimeline">Timeline</button>
				</a>
			</c:if>
		</div>
		<!-- Row End -->
	</div>
	<!-- container mrg-top-15 width-75per End -->

<div class="hiw" style="padding-bottom:40px; min-height:80%;">
		<!--=====================================Header======================================================================= -->
		<div class="container mb20">
			<div class="img-va">
				<div class="col-xs-12 col-sm-9">
					<h2>${caseVO.csName}</h2>
				</div>
				<div class="col-xs-12 col-sm-3 vh_center_right">
					<c:if test="${mbNo != null && mbNo != caseVO.hirerNo && mbSvc.getOneMemberNoImg(mbNo).suspensionStatus eq '否'}">
						<span>
							<form action="<%=request.getContextPath()%>/rpcase/rpcase.do" method="post">
								<input type="hidden" name="action" value="rpCase">
								<input type="hidden" name="csNo" value="${caseVO.csNo}">
								<input type="hidden" name="url" value="<%=request.getQueryString()%>">
								<button type="submit" class="btn btn-default btn-square">
									<span class="glyphicon glyphicon-flag"></span> 檢舉案件
								</button>
							</form>
						</span>
					</c:if>
				</div>
			</div>
		</div>

		<!--=====================================Content===================================================================== -->
		<div class="container">
			<div class="row">

				<!--=====================================LeftContent===================================================================== -->
				<div class="col-xs-12 col-sm-9">

					<!--發文時間=======================================-->
					<div class="col-sm-12 mb20">

						<span id="postTime" class="font-content" data-toggle="tooltip"
							data-placement="top" title="${caseVO.postTime}"></span>
					</div>
					<!--價格時間規模=======================================-->
					<div class="col-sm-4 vh_center_left">

						<table>
							<tr>
								<td><img src="<%=request.getContextPath()%>/img/case/coin_b_16.png"></td>
								<td class="td2">案件價格</td>
							</tr>
							<tr>
								<td></td>
								<td class="td2 font-content">$${nf.format(caseVO.csPayment)}</td>
							</tr>
						</table>


					</div>
					<div class="col-sm-4 vh_center_left">
						<div class="row">
							<table>
								<tr>
									<td><img src="<%=request.getContextPath()%>/img/case/calendar_b_16.png"></td>
									<td class="td2">案件預估時間</td>
								</tr>
								<tr>
									<td></td>
									<td class="td2 font-content">
										開始: ${caseVO.startDate.substring(0, 10)}<br>
										結束: ${caseVO.endDate.substring(0, 10)}
									</td>
								</tr>
							</table>

						</div>
					</div>
					<div class="col-sm-4 vh_center_left">
						<div class="row">
							<table>
								<tr>
									<td><img src="<%=request.getContextPath()%>/img/case/level_no_b_16.png"></td>
									<td class="td2">案件規模</td>
								</tr>
								<tr>
									<td></td>
									<td class="td2 font-content">${caseVO.csLevel}</td>
								</tr>
							</table>

						</div>
					</div>

					<!--發文內容=======================================-->

					<div class="col-sm-12 margin02">
						<div class="row  cs-content">
							<h4>
								<b>案件描述</b>
							</h4>
							<pre>${caseVO.csDesc}</pre>
							<br>


							<ul class="list-inline">
								<li><b>案件需要技能:</b></li>
								<c:forEach var="ctVO" items="${ctList}">
									<li><a href="#" class="skill" data-toggle="tooltip"
									data-placement="top" title="${stSrv.getMbno(ctVO.skillNo).skillDesc}">${stSrv.getMbno(ctVO.skillNo).skillName}</a></li>
								</c:forEach>
							</ul>

						</div>
					</div>
					
					<c:if test="${(caseVO.csState eq '接洽中') || (mbNo eq caseVO.freelancerNo) || (mbNo eq caseVO.hirerNo) }">
					<div class="col-sm-12">
						<div class="row  cs-content">
							<c:choose>
						
								<c:when test="${caseVO.csState eq '接洽中'}">
									<b>案件現況</b><br>
									申請人數:&nbsp&nbsp
									<span class="font-content">
										少於${appSvc.getOneCaseWhoApply(csVO.csNo).size()}人
									</span>
									<br>
								</c:when>
								
								<c:otherwise>
									<div class="col-xs-12 col-sm-12 ">
									<div class="row">
									<h4><b>接案者資訊</b></h4>
									</div>
									</div>
									
										<!-- member info -->
										<div class="col-xs-12 col-sm-9 ">
											<div class="row">
												<!-- name -->
												<span class="h2">
													<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${caseVO.freelancerNo}&action=getMbInfo" style="background-color:white !important;" class="purple">
													${mbSvc.getOneMember(caseVO.freelancerNo).mbLstName} ${mbSvc.getOneMember(caseVO.freelancerNo).mbFstName}
													</a>
												</span>
												<div class="mb20" style="margin-top:10px;">
													<div class="glyphicon glyphicon-map-marker">${mbSvc.getOneMember(caseVO.freelancerNo).mbLoc}</div>
												</div>
												<div class="mb20">
													<b>會員EMail</b>&nbsp&nbsp&nbsp&nbsp${mbSvc.getOneMember(caseVO.freelancerNo).eMail}
												</div>
											</div>
										</div>
										<!-- member portrait -->
										<div class="col-xs-12 col-sm-3 central margin-top ">
											<div class="inner" style='background-image: url(<%=request.getContextPath()%>/Image/Image.do?MBNO=${caseVO.freelancerNo}&index=0)'></div>
										</div>
									
								</c:otherwise>
							
							</c:choose>
						</div>
					</div>
					</c:if>
				</div>






				<!--=====================================RightContent===================================================================== -->
				<div class="col-xs-12 col-sm-3">
					
					<c:if test="${mbNo==null}">
						<div><h2><a href="<%=request.getContextPath()%>/login/vistor_login.jsp"  class="loginPlz">登入會員</a></h2></div>
					</c:if>
					<!-- 有登入且角色為接案者且並非停權狀態 -->
					<c:if test="${mbNo!=null && mbNo != caseVO.hirerNo && mbSvc.getOneMemberNoImg(mbNo).suspensionStatus eq '否'}">
						<% if(caseVO.getCsState().equals("接洽中")){ %>
								<!-- 還沒申請過該案件的人 -->
								<c:if test="${!appSvc.getOneCaseWhoApply(caseVO.csNo).contains(mbNo)}">
									<form action="<%=request.getContextPath()%>/Applicant/ApplicantServlet" method="post">
										<input type="hidden" name="freelancerNo" value="${mbNo}">
										<input type="hidden" name="csNo" value="${caseVO.csNo}">
										<input type="hidden" name="hirerNo" value="${caseVO.hirerNo}">
										<input type="hidden" name="action" value="insert">
										<button type="submit" class="btn btn-default btn-square">申請案件&nbsp&nbsp&nbsp&nbsp<div class="glyphicon glyphicon-plus"></div></button>
									</form>
								</c:if>
								
								
						<% } %>
						
								<!-- 還沒收藏過該案件的人 -->
								<jsp:useBean id="fcSvc" class="com.favorite_case.model.FavoriteCaseService"/>
								<input type="hidden" name="csNo" value="${caseVO.csNo}" id="fcCsNo">
								<input type="hidden" name="mbNo" value="${mbNo}" id="fcMbNo">
									<div id="addFavoriteArea" ${(!fcSvc.getOneCaseFavoritMb(caseVO.csNo).contains(mbNo)) ? '':'style="display:none;"' }>
										<button type="button" class="btn btn-default btn-square addFavorite">收藏案件&nbsp&nbsp&nbsp&nbsp<div class="glyphicon glyphicon-heart"></div></button>
									</div>
								
								
									<div id="removeFavoriteArea" ${(fcSvc.getOneCaseFavoritMb(caseVO.csNo).contains(mbNo)) ? '':'style="display:none;"' }>
										<button type="button" class="btn btn-default btn-square removeFavorite">取消收藏&nbsp&nbsp&nbsp&nbsp<div class="glyphicon glyphicon-heart-empty"></div></button>
									</div>
						
						<!-- 聯絡發案人 -->
						<a href='#modal-id' data-toggle="modal"><button type="button" class="btn btn-default btn-square">聯絡發案人&nbsp&nbsp<div class="glyphicon glyphicon-envelope"></div></button></a>
						<c:if test="${sendSuccess != null}">
							<div style="color:#f00;">信件發送成功</div>
							<% request.getSession().removeAttribute("sendSuccess"); %>
						</c:if>
					</c:if>
					<% 
						String canModifyState = "接洽中";
						request.setAttribute("canModifyState", canModifyState);
					%>
					<!-- 有登入且角色為發案者且並非停權狀態 -->
					<c:if test="${mbNo!=null && mbNo == caseVO.hirerNo && caseVO.csState.equals(canModifyState)}">
						<form action="<%=request.getContextPath() %>/case/case.do" method="post">
							<input type="hidden" name="csNo" value="${caseVO.csNo}">
							<input type="hidden" name="action" value="getOneUpdate">
							<input type="hidden" name="url" value="<%=request.getQueryString()%>">
							<button type="submit" class="btn btn-default btn-square">修改案件資訊</button>
						</form>
					</c:if>
					<c:if test="${updateSuccess != null}">
						<div style="color:#f00;">${updateSuccess}</div>
					</c:if>
				
					<c:if test="${!(caseVO.csState eq '接洽中') && ((mbNo eq caseVO.freelancerNo) || (mbNo eq caseVO.hirerNo))}">
						<form action="<%=request.getContextPath()%>/liveChat/LiveChat" method="post"  target="_blank">
							<input type="hidden" name="hirerNo" value="${caseVO.hirerNo}">
							<input type="hidden" name="freelancerNo" value="${caseVO.freelancerNo}">
							<input type="hidden" name="mbNo" value="${mbNo}">
							<button class="btn btn-default btn-square" type="submit">線上聊天</button>
						</form>
					</c:if>
					<hr class="hr-color">
					<p>
					<h3>發案者資訊</h3>
					</p>
					<p>
						<span class="font-content">
							<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${caseVO.hirerNo}&action=getMbInfo" class="purple">
							${mbSrv.getOneMember(caseVO.hirerNo).mbLstName}${mbSrv.getOneMember(caseVO.hirerNo).mbFstName}
							</a>
						</span>

					</p>
					<p>
						發案評價
						<div id="star_con" class="star-vote">
				            <span id="add_star" class="add-star"></span>
				            <span id="del_star" class="del-star"></span>
				        </div>
					</p>
					
					<jsp:useBean id="csSvc" class="com.cs.model.CaseService"></jsp:useBean>
					<p>
						發案成功次數<br>
						<span class="hirercount font-content">
						${csSvc.findByHirerNo(caseVO.hirerNo,"已完成").size()}
						</span>
					</p>
					<p>
						<span class="font-content">Member Since ${mbSrv.getOneMember(caseVO.hirerNo).sinceDate}</span>

					</p>

				</div>
			</div>
		</div>


</div>


<!-- ========================================新增郵件內容==========================================-->

<!-- 	<div class="fixed-action-btn"> -->
<!-- 		<a href='#modal-id' data-toggle="modal" class="btn-floating btn-large waves-effect waves-light purple"> -->
<!-- 			<i class="material-icons">mail</i> -->
<!-- 		</a> -->
<!-- 	</div> -->
	<div class="modal fade" id="modal-id" style="padding-right: 0px;">
		<div class="modal-header mesgBox">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: white; margin: 5px;">&times;</button>
			<h3 class="modal-title" style="width: 120%; font-size: 24px; padding-left: 10px; background-color: #5e2b97; color: white;">新郵件</h3>
		</div>
		<div class="modal-body mesgBox"></div>
	
		<!-- form傳送的地方： action -->
		<font color='red'>
			<div id="errorMsg" style="displsy: hidden;"></div>
		</font>
		<form id="addMesgForm" method="post" action="<%=request.getContextPath()%>/message/message.do">
			<label for="recipient">
				<span style="font-size: 16px; padding: 10px; padding-right: 0px;">收件者</span>
			</label>
			<!-- 取值的地方： getParameter("recipient"):輸入 使用者名稱 -->
			<input type="hidden" name="recipient" id="recipient" class="no-border newMesg" value="${mbSrv.getOneMember(caseVO.hirerNo).mbUserName}">${mbSrv.getOneMember(caseVO.hirerNo).mbUserName}<br>
			<label for="sender">
					<span style="font-size: 16px; padding: 10px; padding-right: 0px;">寄件者</span>
			</label>
			<!-- 取值的地方： getParameter("sender") -->
			<input type="hidden" name="sender" id="sender" class="no-border newMesg" value="${mbSrv.getOneMember(mbNo).mbUserName}">${mbSrv.getOneMember(mbNo).mbUserName}<br>
			<hr style="margin: 5px;">
			<label for="topic">
				<span style="font-size: 16px;"></span>
			</label>
			<!-- 取值的地方： getParameter("topic") -->
			<input type="text" name="topic" id="topic" placeholder=" 主旨" class="no-border newMesg"><br>
			<hr style="margin: 5px;">
			<!-- 取值的地方： getParameter("mesgContent") -->
			<label for="mesgContent"></label>
			<textarea name="mesgContent" id="mesgContent" class="no-border" style="height: 12.5em;"></textarea>
			<input type="hidden" name="action" value="addMesg"> 
			<input type="hidden" name="servletPath" value="<%="/case/case.do?" + request.getQueryString() %>">
			<div class="modal-footer mesgBox">
				<!-- magic button   ============================================================= -->	
						
							<button type="button" onclick="inputInfo3()">從TO乃</button>
				<!-- magic button   ============================================================= -->	
				<button type="submit" class="btn btn-default" style="background-color: #5e2b97; color: white;" >傳送</button>
			</div>
		</form>
	</div>



	
<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
<!-- footer-end   ============================================================= -->
	

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.js"></script>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>
	<script src="<%=request.getContextPath()%>/js/case_info.js"></script>
	<script src="<%=request.getContextPath()%>/js/stars.js"></script>
	<jsp:useBean id="csSrv" class="com.cs.model.CaseService"/>
	<script type="text/javascript">
	function init(){
		 showStar(${csSrv.getMbHirerScoreAvg(caseVO.hirerNo)}); 
		 postTimeInterval(${caseVO.getPostTime().getTime()});
		 getcsState("${caseVO.csState}");
	}
	window.onload = init;
	function getcsState(csState){
		if("接洽中"==csState){
			$("#caseMilestone").attr("disabled","");
			$("#caseTimeline").attr("disabled","");
		}
	}
	
	$(".addFavorite").click(function(){
		var xhr = new XMLHttpRequest();
		
		//設定好回呼函數   
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					$('#addFavoriteArea').attr("style","display:none;");
					$('#removeFavoriteArea').attr("style","");
				} else {
					alert(xhr.status);
				}//xhr.status == 200
			}//xhr.readyState == 4
		};//onreadystatechange 

		//建立好Get連接
		var url =  "<%=request.getContextPath()%>/favoriteCase/favoriteCase.do?action=addFavorite&csNo=" + $("#fcCsNo").val() + "&mbNo="+ $("#fcMbNo").val();
		xhr.open("Get", url, true);
		//送出請求 
		xhr.send(null);
		console.log(url);
	});
	
	$(".removeFavorite").click(function(){
		var xhr = new XMLHttpRequest();
		
		//設定好回呼函數   
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					$('#addFavoriteArea').attr("style","");
					$('#removeFavoriteArea').attr("style","display:none;");
				} else {
					alert(xhr.status);
				}//xhr.status == 200
			}//xhr.readyState == 4
		};//onreadystatechange 

		//建立好Get連接
		var url =  "<%=request.getContextPath()%>/favoriteCase/favoriteCase.do?action=removeFavorite&csNo=" + $("#fcCsNo").val() + "&mbNo="+ $("#fcMbNo").val();
		xhr.open("Get", url, true);
		//送出請求 
		xhr.send(null);
		console.log(url);
	});
	</script>
	
<!-- magic button   ============================================================= -->	
	<script>	
	
	function inputInfo3(){

	      document.getElementById("topic").value="奶哥好";
	      document.getElementById("mesgContent").value="請問奶哥我你可以嗎??";

	    }
	</script>
<!-- magic button   ============================================================= -->		
</body>
</html>