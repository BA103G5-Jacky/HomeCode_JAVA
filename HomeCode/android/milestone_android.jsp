<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.milestone.model.*"%>
<%@ page import="com.cs.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.DecimalFormat" %>

<%
	//清除cash萬用方法
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);

%>

<%
	double completeCount = 0;
%>
<%
	double listTotal = 0;

	DecimalFormat df = new DecimalFormat("###.#");
%>
<html lang="pt-br">
	<head>
		<title>案件進度</title>
		
		<meta charset="UTF-8" />		
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">		
		<!-- Floating button css - power by materializecss -->
		
		<!--Import Google Icon Font-->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!--Import materialize.css-->
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/materialize.css" media="screen,projection" />
		
		<!-- Floating button css ..... end -->	
		<!-- # linking piont tab-icon-->
		<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/animatecss/3.5.2/animate.min.css" />
		
		
		<!-- # linking piont progress-bar -->
		<link rel='stylesheet prefetch' href='<%=request.getContextPath()%>/css/progress-bar-kiaod.css'>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/progress-bar-style.css">
		
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
		            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
		            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		    <![endif]-->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-timeline.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
	
	
	
	</head>
<body>

	<jsp:useBean id="csSvc" class="com.cs.model.CaseService"/>
<c:if test="${csVO.csState eq '進行中' }">
	<!-- add milestone floating button -->
	<div class="fixed-action-btn">
		<a href='<%=request.getContextPath()%>/milestone/addMilestoneAfter.jsp?csNo=${csVO.csNo}' data-toggle="modal"
			class="btn-floating btn-large waves-effect waves-light purple">
			<i class="material-icons">add</i>
		</a>
	</div>
</c:if>

<div class="hiw" style="min-height:100%;">	
	
		<div class="container mrg-bottom-15 mrg-top-15 progress-area">
			<div class="row">

				<!-- 進度百分比顯示開始 -->
				<c:forEach var="milestoneVO" items="${milestoneList}">
					<c:if test="${'已完成'eq milestoneVO.milestoneState}">
						<%
							completeCount++;
						%>
					</c:if>
					<%
						listTotal++;
					%>
				</c:forEach>

				<div class=" percentage central">
					<div id="process"
						style="font-size: 60px; display: flex; align-items: flex-end; justify-content: center; margin-top: 20px;">
						<c:choose>
							<c:when test="<%=(listTotal==0)%>">0%</c:when>
							<c:otherwise><%=df.format( completeCount / listTotal * 100)%>%</c:otherwise>						
						</c:choose>
					</div>
				</div>

				<!-- 進度百分比顯示結素 -->



				<!-- 進度條顯示開始 -->
				<div>
					<ol class="ProgressBar">

						<c:forEach var="milestoneVO" items="${milestoneList}">
							<c:if test="${'已完成'eq milestoneVO.milestoneState}">
								<li class="ProgressBar-step is-complete"><svg
										class="ProgressBar-icon">
									<use xlink:href="#checkmark-bold" /></svg> <span
									class="ProgressBar-stepLabel">${milestoneVO.getMilestoneName()}</span>
								</li>
							</c:if>

							<c:if
								test="${'未完成'eq milestoneVO.milestoneState or '已提交'eq milestoneVO.milestoneState  }">
								<li class="ProgressBar-step"><svg class="ProgressBar-icon">
									<use xlink:href="#checkmark-bold" /></svg> <span
									class="ProgressBar-stepLabel">${milestoneVO.getMilestoneName()}</span>
								</li>
							</c:if>

						</c:forEach>

					</ol>
				</div>

				<svg xmlns="http://www.w3.org/2000/svg" style="height: 0px;">
                <symbol id="checkmark-bold" viewBox="0 0 24 24">
                <path
						d="M20.285 2l-11.285 11.567-5.286-5.011-3.714 3.716 9 8.728 15-15.285z" />
                </symbol>
                </svg>
				<!-- 進度條顯示結束 -->


				<!-- 里程碑顯示開始 -->
				<div class="" style="width: 85%; margin-left: auto; margin-right: auto;">
						
						<c:forEach var="milestoneVO_r" items="${milestoneListReverse}" varStatus="i">
							<div class="panel panel-default">
								<div class="panel-body row">
								<div class="col-sm-8  img-va">
									<div class="col-xs-12 col-sm-2 central">
										<c:if test="${ ('未完成' eq milestoneVO_r.milestoneState) && (mbNo eq csSvc.getOneCase(milestoneVO_r.csNo).hirerNo) }">
											<img src="<%=request.getContextPath()%>/img/garbage.png" class="close removeMilestone" 
												style="height:30px; width:30px;" onclick="removeMilestone('${milestoneVO_r.milestoneNO}');">
											<form id="deleteMilestone${milestoneVO_r.milestoneNO}" action="<%=request.getContextPath()%>/milestone/milestone.do" method="post">
												<input type="hidden" name="milestoneNO" value="${milestoneVO_r.milestoneNO}">
												<input type="hidden" name="csNo" value="${milestoneVO_r.csNo}">
												<input type="hidden" name="action" value="deleteMilestone">
											</form>
										</c:if>
									</div>
									<div class="col-xs-12 col-sm-10">
										<h1>${milestoneVO_r.milestoneName}</h1>
										<span>開始時間:${milestoneVO_r.mileStartTime}</span> <span>結束時間:${milestoneVO_r.mileEndTime}</span>
									</div>
								</div>
									<!-- if mbNo == hirerNo   start -->
									<c:if test="${mbNo  eq csVO.hirerNo }">
										<c:if test="${'已提交'eq milestoneVO_r.milestoneState}">
											<div class="col-xs-12 col-sm-offset-2 col-sm-2" id="accept">
												<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/milestone/milestone.do">
													<input type="hidden" name="action" value="agree-milestone">
													<input type="submit" value="同意"
														class="btn btn-default btn-square" style="width: 100%;"><br>
													<input type="hidden" name="milestoneState" value="已完成">
													<input type="hidden" name="milestoneNo"
														value="${milestoneVO_r.getMilestoneNO()}"> <input
														type="hidden" name="csNo" value="${csVO.csNo }">
	
												</FORM>
												<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/milestone/milestone.do">
													<input type="hidden" name="action"
														value="disagree-milestone"> <input type="submit"
														value="不同意" class="btn btn-default btn-square"
														style="width: 100%;"><br> <input type="hidden"
														name="milestoneState" value="未完成"> <input
														type="hidden" name="milestoneNo"
														value="${milestoneVO_r.getMilestoneNO()}"> <input
														type="hidden" name="csNo" value="${csVO.csNo }">
	
												</FORM>
											</div>
										</c:if>
	
										<c:if
											test="${'已完成'eq milestoneVO_r.milestoneState or '未完成'eq milestoneVO_r.milestoneState }">
											<div class="col-xs-12 col-sm-offset-2 col-sm-2">
	
												<h3>${milestoneVO_r.milestoneState}</h3>
											</div>
										</c:if>
									</c:if>
									<!-- if mbNo == hirerNo   End -->
									
									<!-- if mbNo == freelancerNo   Start -->
									<c:if test="${mbNo eq csVO.freelancerNo }">
										<c:if test="${'已完成' eq milestoneListReverse[i.index+1].milestoneState && '未完成' == milestoneVO_r.milestoneState}">
										
											<div class="col-xs-12 col-sm-offset-2 col-sm-2" id="accept">
												<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/milestone/milestone.do">
													<input type="hidden" name="action" value="agree-milestone">
													<input type="hidden" name="milestoneState" value="已提交">
													<input type="hidden" name="milestoneNo" value="${milestoneVO_r.getMilestoneNO()}">
													<input type="hidden" name="csNo" value="${csVO.csNo }">
													<input type="submit" value="提交進度" class="btn btn-default btn-square" style="width: 100%;"><br>
												</FORM>
											</div>
										</c:if>
	
										<c:if test="${'已完成'eq milestoneVO_r.milestoneState or '已提交'eq milestoneVO_r.milestoneState}"> 
											<div class="col-xs-12 col-sm-offset-2 col-sm-2">
												<h3>${milestoneVO_r.milestoneState}</h3>
											</div> 
										</c:if>
									</c:if>
									<!-- if mbNo == freelancerNo   End -->
								</div>
							</div>
						</c:forEach>


				</div>
			</div>
			<!-- 里程碑顯示結束 -->

		</div>
		<!-- row End -->
	</div>
	<!-- container End -->



	<!-- hiw End -->



	<script src="https://cdn.jsdelivr.net/jquery/1.11.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="<%=request.getContextPath()%>/js/dot-progress-bar.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
	<script>
		function removeMilestone(milestoneNo){
			var id = '#deleteMilestone' + milestoneNo;
			$(id).submit();
		}
	</script>

</body>
</html>
