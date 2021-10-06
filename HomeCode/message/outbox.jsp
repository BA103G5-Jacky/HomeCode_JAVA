

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cs.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.message.model.*"%>
<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>


<%	


	if(request.getAttribute("receivingMailList") == null){
		MemberService mbSvc = new MemberService();
		MessageService msgSvc = new MessageService();
		List<MessageVO> sendingMailList = new ArrayList<MessageVO>();
		List<MessageVO> receivingMailList = new ArrayList<MessageVO>();
		MemberVO memberVO = mbSvc.getOneMember((String) session.getAttribute("mbNo"));
		sendingMailList = msgSvc.findByMailMbNo((String) session.getAttribute("mbNo"));
		receivingMailList = msgSvc.findByMailRecvMbNo((String) session.getAttribute("mbNo"));
		pageContext.setAttribute("sendingMailList", sendingMailList);
		pageContext.setAttribute("receivingMailList", receivingMailList);
		pageContext.setAttribute("memberVO", memberVO);
		//log
		System.out.println("[message.jsp]-[sendingMailList]:"+ sendingMailList.size());
		System.out.println("[message.jsp]-[getMbUserName]:" + memberVO.getMbUserName());
	}

	//給顯示未讀訊息
	int sysMesgNotify = 0;
	int inBoxMesgNotify = 0;
%>



<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		
		<!-- Floating button css - power by materializecss -->
		
		<!--Import Google Icon Font-->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!--Import materialize.css-->
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/materialize.css" media="screen,projection" />
		
		<!--Let browser know website is optimized for mobile-->
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- Floating button css ..... end -->
		
		
		<!-- # linking piont tab-icon-->
		<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/animatecss/3.5.2/animate.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style-albe-timeline.css" />
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
		            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
		            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		        <![endif]-->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-timeline.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/message/css/message.css">
		<title>訊息中心</title>
		<style>
		.fixed-action-btn{
			margin-bottom: 40px;
		}
		.purple{
			background-color:rgba(255,255,255,0) !important;
		}
		</style>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pre.css">
	
	</head>
<body>




	<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
	<!-- ======================header-end ================================== -->
	
<div style="padding-bottom:40px;">		
	<!-- ========================================Tab 3 欄 + 訊息內容==========================================-->
<jsp:useBean id="mbSvcforMailMb" scope="page" class="com.member.model.MemberService" />	
	<c:forEach var="receiveMailVO" items="${receivingMailList}">
		
		<c:if
			test="${mbSvcforMailMb.getOneMember(receiveMailVO.mailMbNo).getMbLstName() == '系統'}">
			<c:if test="${receiveMailVO.readStatus eq '未讀'}">
				<%
								sysMesgNotify++;
								//log
								System.out.println("[message.jsp]-[sysMesgNotify]:" + sysMesgNotify);
							%>
			</c:if>
		</c:if>
		<c:if
				test="${mbSvcforMailMb.getOneMember(receiveMailVO.mailMbNo).getMbLstName() != '系統'}">
				<c:if test="${receiveMailVO.readStatus eq '未讀'}">
					<%
					inBoxMesgNotify++;
								//log
								System.out.println("[message.jsp]-[sysMesgNotify]:" + inBoxMesgNotify);
							%>
				</c:if>
			</c:if>
	</c:forEach>

	<!-- ========================================Process==========================================-->
	<div class="container mrg-top-15 mb20">
		<div class="row">
			<a href="#">
				<div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4 active">
					寄件匣
					<span class="badge" style="background-color: #5e2b97;"></span>
				</div>				
			</a>
			<a href="<%=request.getContextPath()%>/message/message.do?mbNo=${mbNo}&action=inbox">
				<div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">
					收件匣
					<c:if test="<%=inBoxMesgNotify!=0 %>">
							<span class="badge inBoxMesgNotify" style="background-color: #5e2b97;"><%=inBoxMesgNotify %></span>
					</c:if>
				</div>
			</a>
			<a href="<%=request.getContextPath()%>/message/message.do?mbNo=${mbNo}&action=sysbox">
				<div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">
					HomeCode訊息
					<c:if test="<%=sysMesgNotify!=0 %>">
						<span id="sysMesgNotify" class="badge sysMesgNotify" style="background-color: #5e2b97;"><%=sysMesgNotify %></span>
					</c:if>
				</div>
			</a>
		</div>
		<!-- Row End -->
	</div>
	<!-- container mrg-top-15 width-75per End -->



	<!-- ========================================訊息內容==========================================-->

	<div class="container mb20">
		<div class=" mrg-top-15  col-xs-12  ">

			<div class="mrg-top-15 cs-content row">
				<%
					int i = 0;
					MessageVO sendMailVO = new MessageVO();
				%>
				<c:forEach var="sendMailVO" items="${sendingMailList}">
<%-- 					<jsp:useBean id="mbSvcforMailMb" scope="page" --%>
<%-- 						class="com.member.model.MemberService" /> --%>
							
							<!-- 刪除信件 傳送點 -->
							<div class="col-xs-12 col-sm-1 ">
								<div class="font-content">
									<form action="message.do" method="post">
										<input type="hidden" name="mesgNo"
											value="${sendMailVO.mesgNo }"> 
										<input type="hidden" name="action" value="deletMail"> 
										<input type="hidden" name="servletPath"
											value="<%=request.getServletPath()%>">
										<input type="hidden" name="fromSysBox"
											value="fromSysBox">
										<button type="submit" class="close">
										<img style="height: 24px; width: 24px" class="img-responsive"
												src="<%=request.getContextPath()%>/img/garbage.png">
										</button>
									</form>
								</div>
							</div>
							
							
							<a href="message.do?action=changeReadStatus&readStatus=read&mesgNo=${sendMailVO.mesgNo }&servletPath=<%=request.getServletPath()%>">
							<a href='#mesg-content<%=i%>' data-toggle="modal" class="" >
							<div class="col-xs-12 col-sm-3 "  >
								
								<div class="font-content">
									<h3 class="${ sendMailVO.mesgNo }"  style="margin-top:5px;">收件人：${mbSvcforMailMb.getOneMember(sendMailVO.mailRecvMbNo).getMbLstName() }
										${mbSvcforMailMb.getOneMember(sendMailVO.mailRecvMbNo).getMbFstName() }</h3>
								</div>
							</div>

							<div class="col-xs-12 col-sm-6 ">
								<div>
									<div>
										<h3 class="${ sendMailVO.mesgNo }" style="color: #7d7d7d; margin-top:5px;">${sendMailVO.mesgTitle}</h3>
									</div>
								</div>
							</div>

							<div class="col-xs-12 col-sm-2 ">
								<div class="font-content" style="font-size:24px;">
									${sendMailVO.getMailTime()}
								</div>
							</div>
							</a>
					</a>
					<hr class="hr-color">
					<!-- 信件內容跳窗區域：Start -->
					<div class="modal fade" id="mesg-content<%=i%>">
						<div class="modal-header padding-l10px">
						
							<script type="text/javascript">
							function test<%=i%>(){
								var mesgNo = $('#mesg-content<%=i%> div>button').val();
// 								checkReadStatus(mesgNo);								
							}
						</script>	
											
							<a type="button" class="close" data-dismiss="modal"
								aria-hidden="true" 
								onclick="test<%=i %>()" value="${sendMailVO.mesgNo}">&times;</a>
							<!-- 跳窗標題 -->
							<h4 class="modal-title">
								<b>${sendMailVO.mesgTitle}</b>
							</h4>
							<!-- 寄件人姓名 -->
							<p>${memberVO.getMbLstName() }${memberVO.getMbFstName() }</p>
							<!-- 收件人姓名 -->

							<p>寄給：
								<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${sendMailVO.mailRecvMbNo}&action=getMbInfo" class="purple purple-bg">
									${mbSvcforMailMb.getOneMember(sendMailVO.mailRecvMbNo).getMbLstName() }
									${mbSvcforMailMb.getOneMember(sendMailVO.mailRecvMbNo).getMbFstName() }
								</a>
								</p>
						</div>
						
						

						<div class="modal-body padding-l10px">
							<pre>${sendMailVO.getMesgContent()}</pre>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="test<%=i %>()" value="${sendMailVO.mesgNo }" >關閉</button>
						</div>
						<%
							i++;
						%>
					</div>
					<!-- 信件內容跳窗區域：End -->
				</c:forEach>

			</div>
		</div>
	</div>



	<!-- ========================================新增郵件內容==========================================-->

	<div class="fixed-action-btn">
		<a href='#modal-id' data-toggle="modal"
			class="btn-floating btn-large waves-effect waves-light purple"><i
			class="material-icons">mail</i></a>
	</div>
	<div class="modal fade" id="modal-id" style="padding-right: 0px;">

		<div class="modal-header mesgBox">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true" style="color: white; margin: 5px;">&times;</button>
			<h3 class="modal-title"
				style="width: 120%; font-size: 24px; padding-left: 10px; background-color: #5e2b97; color: white;">新郵件</h3>
		</div>
		<div class="modal-body mesgBox"></div>

		<!-- form傳送的地方： action -->
		<font color='red'>

			<div id="errorMsg" style="displsy: hidden;"></div>
		</font>
		<form id="addMesgForm" method="post" action="message.do">
			<label for="recipient"><span
				style="font-size: 16px; padding: 10px; padding-right: 0px;">收件者</span>
			</label>
			<!-- 取值的地方： getParameter("recipient"):輸入 使用者名稱 -->
			<input type="text" name="recipient" id="recipient" placeholder=""
				class="no-border newMesg"><br> <label for="sender">
				<span style="font-size: 16px; padding: 10px; padding-right: 0px;">寄件者</span>
			</label>
			<!-- 取值的地方： getParameter("sender") -->
			<input type="hidden" name="sender" id="sender" placeholder=""
				class="no-border newMesg" value="${memberVO.mbUserName}">${memberVO.mbUserName}<br>

			<hr style="margin: 5px;">

			<label for="topic"><span style="font-size: 16px;"></span></label>
			<!-- 取值的地方： getParameter("topic") -->
			<input type="text" name="topic" id="topic" placeholder=" 主旨"
				class="no-border newMesg"><br>

			<hr style="margin: 5px;">
			<!-- 取值的地方： getParameter("mesgContent") -->
			<label for="mesgContent"></label>
			<textarea name="mesgContent" id="mesgContent" class="no-border"
				style="height: 12.5em;"></textarea>
			<input type="hidden" name="action" value="addMesg"> <input
				type="hidden" name="servletPath"
				value="<%=request.getServletPath()%>">

			<div class="modal-footer mesgBox">
				<button type="button" class="btn btn-default"
					style="background-color: #5e2b97; color: white;"
					onclick="checkRecipentIsNull()">傳送</button>
			</div>
		</form>
	</div>



</div>





<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
<!-- footer-end   ============================================================= -->


<script>
		function $id(id) {
			return document.getElementById(id);
		}
		
		function checkReadStatus(mesgNo) {
			console.log(mesgNo);
			var h3 = "."+mesgNo;
			var xhr = new XMLHttpRequest();
			var noMb;
			//設定好回呼函數   
			 xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						console.log(xhr.status);
						console.log(xhr.responseText);
						$(h3).attr("style","color: #7d7d7d; margin-top:5px;");
					}//xhr.status == 200
				}//xhr.readyState == 4
			};//onreadystatechange 

			//建立好Get連接
			var url = "message.do?action=changeReadStatus&readStatus=已讀&mesgNo="+mesgNo+"&servletPath=<%=request.getServletPath()%>";
			xhr.open("Get", url, true);
			//送出請求 
			xhr.send(null);
			console.log(url);
 
		}

		function checkRecipentIsNull() {

			var xhr = new XMLHttpRequest();
			var noMb;
			//設定好回呼函數   
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						console.log(xhr.status);
						//console.log(xhr.responseText);
						noMb = xhr.responseText;
						console.log(noMb);
						if ($id("recipient").value.trim() == "") {
							$("#errorMsg").text("請輸入收件人帳號 或 email!")
						} else if (noMb == 1) {
							console.log(noMb);
							$("#errorMsg").text("查無此帳號")
						} else {
							$("#addMesgForm").submit();
						}
						//$("#errorMsg").text(JSON.parse(xhr.responseText).errorMsgs);
						//showEmployee(xhr.responseText);
					} else {
						alert(xhr.status);
					}//xhr.status == 200
				}//xhr.readyState == 4
			};//onreadystatechange 

			//建立好Get連接
			var url = "message.do?action=checkMbExist&recipient="
					+ $("#recipient").val();
			xhr.open("Get", url, true);
			//送出請求 
			xhr.send(null);
			console.log(url);

		}
	</script>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>
</body>
<%-- 	<%@ include file="page2.file" %> --%>
</html>