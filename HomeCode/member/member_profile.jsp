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
<!-- # linking piont bootstrap-lightbox-->	
			<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
<!-- # linking piont tab-icon-->
			<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
<!-- # linking piont local style css-->	
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/stars.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/member/css/memberProfile.css">	
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pre.css">
		<style>
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



<div style="padding-bottom:40px;">	

<!-- ========================================個人資料==========================================-->
			
<div class="container mb20">
	<div class=" mrg-top-15  col-xs-12 col-sm-9 ">
		
		<!-- member info -->
		<div class="row cs-content ">
		
			
			
			
			<!-- member portrait -->
			<div class="col-xs-12 col-sm-3 central margin-top ">
				<div class="inner" style='background-image: url(<%=request.getContextPath()%>/Image/Image.do?MBNO=${memberVO.mbNo}&index=0)'></div>
			</div>
			
			<!-- member info -->
			<div class="col-xs-12 col-sm-9 ">
				<!-- name -->
				<span class="h1" style="color:#5e2b97;">${memberVO.mbLstName} ${memberVO.mbFstName}</span>
				<!-- update member profile btn -->
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
					<input type="hidden" name="mbNo" value="${memberVO.mbNo}"> 
					<input type="hidden" name="action" value="getOne_For_Update">
					<button type="submit" class="close">
						<img  src="<%=request.getContextPath()%>/img/pencil.png">
					</button>			
				</FORM>
				<!-- loc -->
				<div class="mb20">
					<div class="glyphicon glyphicon-map-marker">${memberVO.mbLoc}</div>
				</div>
				<div class="mb20">
					<b>公司名稱</b>&nbsp&nbsp&nbsp&nbsp${memberVO.comName}
				</div>
				<div class="mb20">
					<b>會員EMail</b>&nbsp&nbsp&nbsp&nbsp${memberVO.eMail}
				</div>
				<!-- member skill list -->
				<div>
					<ul class="list-inline">
						<jsp:useBean id="stSrv" class="com.skill_type.model.Skill_typeService"/>
						<c:forEach var="mbSkNo" items="${mbSkillNoList}">
							<li>
								<a href="#" class="skill" data-toggle="tooltip" data-placement="top" title="${stSrv.getOneSkill_type(mbSkNo).skillDesc}">${stSrv.getOneSkill_type(mbSkNo).skillName}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				
				<hr class="hr-color">
				<!-- introduce -->
				<div class="h2">簡介</div>	
				<div>
					<pre>${memberVO.mbIntroduce}</pre>
				</div>
			</div>
		</div>

		<div class="mrg-top-15 cs-content row">
		
			<jsp:useBean id="mbSvc" class="com.member.model.MemberService"/>
			<div role="tabpanel">
			    <!-- 標籤面板：標籤區 -->
			    <ul class="nav nav-tabs" role="tablist">
			        <li role="presentation" class="active">
			            <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab" class="loginPlz">接案紀錄</a>
			        </li>
			        <li role="presentation">
			            <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" class="loginPlz">發案紀錄</a>
			        </li>
			    </ul>
			
			    <!-- 標籤面板：內容區 -->
			    <div class="tab-content">
			    	<!-- 接案紀錄 -->
			        <div role="tabpanel" class="tab-pane active" id="tab1">
						<c:forEach var="csFreeVO" items="${csFreeList}" varStatus="i">
							
							<div class="row img-va" style="${i.index>4 ? 'display:none;':''}">
								<div class="col-xs-12 col-sm-8" >
									<div><h3><a href="<%=request.getContextPath()%>/case/case.do?csNo=${csFreeVO.csNo}&action=caseInfo" class="purple">${csFreeVO.csName}</a></h3></div>
									<div class="font-content">
										<p>發案人 - 
											<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${csFreeVO.hirerNo}&action=getMbInfo" class="purple">
											${mbSvc.getOneMember(csFreeVO.hirerNo).mbLstName}${mbSvc.getOneMember(csFreeVO.hirerNo).mbFstName}
											</a>
										</p>
									</div>
									<div>
										<table class="font-content">	
											<tr>
												<td>開始時間 </td><td>${csFreeVO.startDate}</td>
												<td>正式結束時間</td><td>${csFreeVO.realEndDate}</td>
												<td>案件規模</td><td>${csFreeVO.csLevel}</td>
											</tr>
										</table>
									</div>
								</div>
			
								<div class="col-xs-12 col-sm-4" >
									<div class="col-xs-12 col-sm-6 col-sm-offset-6" >
										<div class = "rating">
											接案評價
											<div id="star_con" class="star-vote-f star-vote" >
											   	<span id="add_star" class="add-star"></span>
											    <span id="del_star" class="del-star-f del-star"></span>
											</div>
										</div>
										<c:if test="${csFreeVO.freelancerScore==0}">
											 <script>
												 var raiting=document.getElementsByClassName("rating")[${i.index}];
												 raiting.style = "display:none";
											 </script>
											 <div>發案者無回饋</div>
										</c:if>
									</div>
								</div>
							</div>
							
							<hr class="hr-color" style="${i.index>4 ? 'display:none;':''}">
							
						</c:forEach>
						<div><a href="#" class="purple">顯示更多</a></div>
			        </div>
			        <!-- 發案紀錄 -->
			        <div role="tabpanel" class="tab-pane" id="tab2">
			        	
			        	<c:forEach var="csHirerVO" items="${csHirerList}" varStatus="i">
							<div class="row img-va">
								<div class="col-xs-12 col-sm-8" >
									<div><h3><a href="<%=request.getContextPath()%>/case/case.do?csNo=${csHirerVO.csNo}&action=caseInfo" class="purple">${csHirerVO.csName}</a></h3></div>
									<div class="font-content">
										<p>接案人 - 
											<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${csHirerVO.freelancerNo}&action=getMbInfo" class="purple">
												${mbSvc.getOneMember(csHirerVO.freelancerNo).mbLstName}${mbSvc.getOneMember(csHirerVO.freelancerNo).mbFstName}
											</a>
										</p>
									</div>
									<div>
										<table class="font-content">	
											<tr>
												<td>開始時間 </td><td>${csHirerVO.startDate}</td>
												<td>正式結束時間</td><td>${csHirerVO.realEndDate}</td>
												<td>案件規模</td><td>${csHirerVO.csLevel}</td>
											</tr>
										</table>
									</div>
								</div>
			
								<div class="col-xs-12 col-sm-4" >
									<div class="col-xs-12 col-sm-6 col-sm-offset-6" >
										<div class = "rating">
											發案評價
											<div id="star_con" class="star-vote star-vote-h" >
											   	<span id="add_star" class="add-star"></span>
											    <span id="del_star" class="del-star del-star-h"></span>
											</div>
										</div>
										<c:if test="${csHirerVO.hirerScore==0}">
											 <script>
												 var raiting=document.getElementsByClassName("rating")[${i.index}];
												 raiting.style = "display:none";
											 </script>
											 <div>接案者無回饋</div>
										</c:if>
									</div>
								</div>
							</div>
							<hr class="hr-color">
						</c:forEach>
			        </div>
			        
			    </div>
			</div>
		</div>

<!--=====================================work collection====================-->


		<div class="mrg-top-15 cs-content row">
								
			 
			<!-- member WorkList -->
			<div class="row">
				<div>
					<h3 class="img-lm"><label>作品集</label>
					<!-- add new work -->
						<a href="<%=request.getContextPath()%>/work_manage/work_add.jsp" class="close" >									
							<span class="glyphicon glyphicon-plus img-circle img-va" style="color:#fff; background-color:#5e2b97; height:20px; width:20px; font-size:10px;"></span>
						</a>
						    	
					</h3>
					
					
				</div>
				<c:forEach var="wkVO" items="${workList}" >
					<div class="col-xs-12 col-sm-6  margin-top central ${wkVO.wkno}" >
						
						<div>
							
							<a href='#modal-id${wkVO.wkno}' data-toggle="modal">
<%-- 								<img src="<%=request.getContextPath()%>/Image/Image.do?wkNo=${wkVO.wkno}&index=1" class="img-thumbnail" alt="Cinque Terre" style="height:250px;" > --%>
								<div style="height:250px; background-size:cover;  
									background-image: url(<%=request.getContextPath()%>/Image/Image.do?wkNo=${wkVO.wkno}&index=1);
									border: 1px solid #ddd; border-radius: 4px;">
    							</div>
							
							</a>
							
							<div class="updateWork">
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/work_manage/work_manage.do">
									<input type="hidden" name="wkNo" value="${wkVO.wkno}">
									<input type="hidden" name="mnNo" value="${mbNo}"> 
									<input type="hidden" name="action" value="getOneUpdate">
									<button type="submit" class="close">
										<img  src="<%=request.getContextPath()%>/img/pencil.png" class="img-circle" style="background-color:#fff;">
									</button>			
								</FORM>
							</div>
							<div class="deleteWork">
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/work_manage/work_manage.do"> 
									<input type="hidden" name="wkNo" value="${wkVO.wkno}">
									<input type="hidden" name="mbNo" value="${mbNo}"> 
				     				<input type="hidden" name="action"	value="deleteWork">
				     				<button type="button" class="close" id="deleteWork" onclick='deleteWorks("${wkVO.wkno}");'>									
										<img  src="<%=request.getContextPath()%>/img/cancel.png" class="img-circle" style="background-color:#fff;">
									</button>
				     			</FORM>
							</div>
						</div>
						<p class="text-center">${wkVO.wkname}</p>
					</div>
					
					<!-- ======modal work start======== -->
					<div class="modal fade" id="modal-id${wkVO.wkno}">
						<div class="modal-dialog">
							<div class="modal-content rate">
								<div class="modal-header modal-header-title">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
									<h3 class="modal-title">${wkVO.wkname}</h3>
								</div>
								<div >
									<c:choose>
										<c:when test="${wkVO.wkdecr == null}">
											<div style="min-height:200px; padding: 15px; color:#f00; font-size:26px;">
												無作品描述
											</div>
										</c:when>
										<c:otherwise>
											<div style="min-height:200px; padding: 15px;">
												<h4>${wkVO.wkdecr}</h4>
											</div>
										</c:otherwise>
									</c:choose>
									
								</div>
								<div class="modal-header">
									<h4>作品圖片</h4>		
								</div>
								<div style="padding: 15px;">
									<div class="modal-Work-pic" style='background-image: url(<%=request.getContextPath()%>/Image/Image.do?wkNo=${wkVO.wkno}&index=1);'>
									
									</div>
									<img src="<%=request.getContextPath()%>/Image/Image.do?wkNo=${wkVO.wkno}&index=1" class="img-responsive">
								</div>
							</div>
						</div>
					</div>
					<!-- ======modal work end======== -->
					
				</c:forEach>
			</div>
		</div>
	</div>
	
	
	<!--=====================================我要檢舉+發送訊息==================================-->	
	<div class="col-xs-12 col-sm-3 mrg-top-15" >
		<div style="margin-top:10px;">
		
			<!-- 有登入會員 && 會員編號不等於自己 && 自己沒有被停權 -->
			<c:if test="${mbNo != null && mbNo != memberVO.mbNo && mbSvc.getOneMemberNoImg(mbNo).suspensionStatus eq '否'}">
			
				<!-- 檢舉會員 -->			
				<span>
					<form action="<%=request.getContextPath()%>/rpmember/rpmember.do" method="post">
						<input type="hidden" name="action" value="rpMember">
						<input type="hidden" name="mbNo" value="${memberVO.mbNo}">
						<input type="hidden" name="url" value="<%=request.getQueryString()%>">
						<button type="submit" class="btn btn-default btn-square">
							檢舉會員&nbsp&nbsp<span class="glyphicon glyphicon-flag"></span> 
						</button>
					</form>
				</span>
	        	
	        	<!-- 聯絡會員 -->
				<a href='#modal-id' data-toggle="modal"><button type="button" class="btn btn-default btn-square">聯絡會員&nbsp&nbsp<div class="glyphicon glyphicon-envelope"></div></button></a>
				<c:if test="${sendSuccess != null}">
					<div style="color:#f00;">信件發送成功</div>
					<% request.getSession().removeAttribute("sendSuccess"); %>
				</c:if>
	        </c:if>
	        
	        <!-- 有登入 && 正在觀看自己的資料  -->
			<c:if test="${mbNo!=null && mbNo == memberVO.mbNo}">
				<button class="btn2  btn btn-default btn-square">修改會員資料</button>
			</c:if>
	        
	         <!-- 沒登入  -->
	  		<c:if test="${mbNo==null}">
				<div><h2><a href="<%=request.getContextPath()%>/login/vistor_login.jsp"  class="loginPlz">登入會員</a></h2></div>
			</c:if>
			
			
			<jsp:useBean id="csSvc" class="com.cs.model.CaseService"></jsp:useBean>
			<hr class="hr-color">
			<p><h3>會員資訊</h3></p>
			
			<p><span >接案平均評價</span></p>
			<div id="star_con" class="star-vote star-vote-favg">
				<span id="add_star" class="add-star"></span>
				<span id="del_star" class="del-star del-star-favg"></span>
			</div>
			<p>
				接案成功數<br>
				<span class="freesuccess font-content">${memberVO.csSuccessTimes}</span>
			</p>
			<p>
				接案總數<br>
				<span class="freecount font-content">${memberVO.csTimes}</span>
			</p>
			
			<p><span >發案平均評價</span></p>
			<div id="star_con" class="star-vote star-vote-havg">
				<span id="add_star" class="add-star"></span>
				<span id="del_star" class="del-star del-star-havg"></span>
			</div>
			
			<p>
				發案總數<br>
				<span class="hirercount font-content">${csSvc.getPostCaseTimes(memberVO.mbNo)}</span>
			</p>
				
			<p>
				<span class="font-content">Member Since ${memberVO.sinceDate}</span>
			</p>
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
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header mesgBox">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: white; margin: 5px;">&times;</button>
					<h3 class="modal-title" style="width: 100%; font-size: 24px; padding-left: 10px; background-color: #5e2b97; color: white;">新郵件</h3>
				</div>
		
				<!-- form傳送的地方： action -->
				<form id="addMesgForm" method="post" action="<%=request.getContextPath()%>/message/message.do">
					<label for="recipient">
						<span style="font-size: 16px; padding: 10px; padding-right: 0px;">收件者</span>
					</label>
					<!-- 取值的地方： getParameter("recipient"):輸入 使用者名稱 -->
					<input type="hidden" name="recipient" id="recipient" class="no-border newMesg" value="${mbSvc.getOneMember(memberVO.mbNo).mbUserName}">${memberVO.mbLstName} ${memberVO.mbFstName}<br>
					<label for="sender">
							<span style="font-size: 16px; padding: 10px; padding-right: 0px;">寄件者</span>
					</label>
					<!-- 取值的地方： getParameter("sender") -->
					<input type="hidden" name="sender" id="sender" class="no-border newMesg" value="${mbSvc.getOneMember(mbNo).mbUserName}">${mbSvc.getOneMember(mbNo).mbUserName}<br>
					<hr style="margin: 5px;">
					<label for="topic">
						<span style="font-size: 16px;"></span>
					</label>
					<!-- 取值的地方： getParameter("topic") -->
					<input type="text" name="topic" id="topic" placeholder=" 主旨" class="no-border newMesg" style="width:100%; padding-left:5px;"><br>
					<hr style="margin: 5px;">
					<!-- 取值的地方： getParameter("mesgContent") -->
					<label for="mesgContent"></label>
					<textarea name="mesgContent" id="mesgContent" placeholder=" 內容" class="no-border" style="height: 12.5em; width:100%; padding-left:5px;"></textarea>
					<input type="hidden" name="action" value="addMesg"> 
					<input type="hidden" name="servletPath" value="<%="/member/member.do?" + request.getQueryString() %>">
					<div class="modal-footer mesgBox">
								
						<!-- magic button   ============================================================= -->	
								<button type="button" onclick="inputInfo1()">從從</button>
								<button type="button" onclick="inputInfo2()">乃乃</button>
								<button type="button" onclick="inputInfo3()">Shari</button>
						<!-- magic button   ============================================================= -->		
						<button type="submit" class="btn btn-default" style="background-color: #5e2b97; color: white;" >傳送</button>
					</div>
				</form>
			</div>
		</div>
	</div>


</div>

<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
<!-- footer-end   ============================================================= -->


		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/script.js"></script> 
		<script src="<%=request.getContextPath()%>/js/stars.js"></script>
<script>
$(document).ready(function(){
	   
	 $(".btn2").click(function(){
	        $(".close").toggle();
	    });
  
  
});

window.onload = function(){
	for(var i=0; i<${csFreeList.size()}; i++){
		<c:forEach var="freevo" items="${csFreeList}" varStatus="i">
			showStarsListforF(${freevo.freelancerScore}, ${i.index});
		</c:forEach>
	}
	
	for(var j=0; j<${csHirerList.size()}; j++){
		<c:forEach var="hirervo" items="${csHirerList}" varStatus="j">
			showStarsListforH(${hirervo.hirerScore}, ${j.index});
		</c:forEach>
	}
	
	showStarhAvg(${csSvc.getMbHirerScoreAvg(memberVO.mbNo)});
	showStarfAvg(${csSvc.getMbFreelancerScoreAvg(memberVO.mbNo)});
}


function deleteWorks(wkNo){
	var xhr = new XMLHttpRequest();
	var wk =  "."+wkNo;
	 xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				$(wk).remove();
			}//xhr.status == 200
		}//xhr.readyState == 4
	};//onreadystatechange 

	//建立好Get連接http://localhost:8081/Local-BA103G5_Jacky/message/sysbox.jsp#mesg-content0
	var url = "<%=request.getContextPath()%>/work_manage/work_manage.do?action=deleteWork&wkNo="+wkNo;
	xhr.open("Get", url, true);
	//送出請求 
	xhr.send(null);
	console.log(url);
}

</script>

<!-- magic button   ============================================================= -->	
	<script>	
	function inputInfo1(){

	      document.getElementById("topic").value="正妹你好";
	      document.getElementById("mesgContent").value="最近手邊沒什麼錢，可以包養一下嗎??拜託拜託拜託";

	    }
	
	function inputInfo2(){

	      document.getElementById("topic").value="請問你有興趣接案嗎?";
	      document.getElementById("mesgContent").value="我是滑氏負責人,我想建置一個網站請問，看你排名也滿前面的，請問你有興趣一起做嗎?????";

	    }
	function inputInfo3(){

	      document.getElementById("topic").value="吳神我好想你";
	      document.getElementById("mesgContent").value="我們可以約嗎，我想關心一下現在的進度，今天可以視訊一下嗎?";

	    }
	</script>
<!-- magic button   ============================================================= -->	
	</body>
	
</html>