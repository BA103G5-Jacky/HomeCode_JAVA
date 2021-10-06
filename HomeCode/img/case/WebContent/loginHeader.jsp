<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<!-- # linking piont 首頁-->
				<a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><img
					src="<%=request.getContextPath() %>/img/homecode2.png" height="35" class="img-va"></a>

			</div>

			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">

				<!-- 左選單 -->
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="collapse-toggle"
						data-toggle="dropdown">接手案件 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/favoriteCase/favoriteCase.do?mbNo=${mbNo}&action=favoriteCaseList">收藏中案件</a></li>
							<li><a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=applicant&actor=freelancer">申請中案件</a></li>
							<li><a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=onGoCase&actor=freelancer">進行中案件</a></li>
							<li><a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=doneCase&actor=freelancer">已完成案件</a></li>
						</ul></li>
				</ul>

				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="collapse-toggle"
						data-toggle="dropdown">發包案件 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/case/case_post.jsp">張貼案件</a></li>
							<li><a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=applicant&actor=hirer">等待中案件</a></li>
							<li><a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=onGoCase&actor=hirer">進行中案件</a></li>
							<li><a href="<%=request.getContextPath()%>/case/case.do?mbNo=${mbNo}&action=doneCase&actor=hirer">已完成案件</a></li>

						</ul></li>
				</ul>

				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="collapse-toggle"
						data-toggle="dropdown">訊息中心 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/message/message.do?mbNo=${mbNo}&action=sysbox">HomeCode! 訊息</a></li>
							<li><a href="<%=request.getContextPath()%>/message/message.do?mbNo=${mbNo}&action=inbox">會員訊息</a></li>
						</ul></li>
				</ul>

				<form class="navbar-form navbar-left ng-pristine margin01">
					<div class="input-group">
						<div class="dropdown input-group-btn collapse-toggle">
							<label class="btn dropdown-toggle" data-toggle="dropdown">
								<span class="glyphicon glyphicon-search "></span>
								<span class="glyphicon glyphicon-chevron-down "></span>
							</label>
							<ul class="dropdown-menu">
								<li><a href="<%=request.getContextPath()%>/search/Case_Search.jsp">尋找案件</a></li>
								<li><a href="<%=request.getContextPath()%>/search/People_Search.jsp">尋找人才</a></li>
							</ul>
						</div>
						<input type="text" class="form-control" placeholder=" 找設計人才/工作案件">
					</div>
				</form>



				<!-- 右選單 -->
				<ul class="nav navbar-nav navbar-right">

					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#"
							class="collapse-toggle glyphicon glyphicon-question-sign navicon"
							data-toggle="dropdown"></a>
							<ul class="dropdown-menu">
								<li><a href="#">如何接案</a></li>
								<li><a href="#">如何發案</a></li>
								<li><a href="#custMsg" data-toggle="modal" onclick="resetModal();">客服中心</a></li>
								<li><a href="#">合約範本下載</a></li>
							</ul></li>
					</ul>


					<li><a href="#" class="glyphicon glyphicon-bell navicon">
					</a></li>
					<li class="dropdown"><a href="#"
						class="glyphicon glyphicon-user collapse-toggle"
						data-toggle="dropdown"> 
						
						
						
						<span class="font-18">${username}</span>
							
							
							
							<span class="glyphicon glyphicon-chevron-down"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/member/member.do?action=getMbInfo&mbNo=${mbNo}">${username}</a></li>
							<li><a href="<%=request.getContextPath()%>/member/resetPassword.jsp">重設密碼</a></li>
							<li><a href="<%=request.getContextPath()%>/login/member.do?action=logout">登出</a></li>
						</ul></li>

				</ul>

			</div>
			<!-- 手機隱藏選單區結束 -->
		</div>
	</nav>
	
<!-- nav bar end  -->	
	


<!-- modal cus_message start  -->		
<div class="modal fade" id="custMsg" style="padding-right: 0px;">
	<div class="modal-dialog" >
		<div class="modal-content" id="modal-content">
			<div class="modal-header mesgBox">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" style="color: white; margin: 5px;">&times;</button>
				<h3 class="modal-title"
					style="font-size: 24px; padding-left: 10px; background-color: #5e2b97; color: white;">客服訊息</h3>
			</div>
			

		<!-- form傳送的地方： action -->
			
			<div id="addCusMesgForm">
			
				<!-- 收件者客服 -->
				<label for="recipient">
					<span style="font-size: 16px; padding: 10px; padding-right: 0px;">收件者</span>
				</label>
				客服中心<br>
				
				
				<!-- 寄件人訊息 取值的地方： getParameter("sender") -->
				<label for="sender">
					<span style="font-size: 16px; padding: 10px; padding-right: 0px;">寄件者</span>
				</label>
				${username}
				<hr style="margin: 5px;">
				
				<!-- 主旨 取值的地方： getParameter("topic") -->
				<label for="topic">
					<span style="font-size: 16px;"></span>
				</label>
				<input type="text" name="topic" id="cusMesgTopic" placeholder=" 主旨" class="no-border newMesg"><br>
				<hr style="margin: 5px;">
				
				<!-- 信件內容 取值的地方： getParameter("mesgContent") -->
				<label for="mesgContent"></label>
				<textarea name="mesgContent" id="cusMesgContent" placeholder=" 請填入信件內容" class="no-border" style="height: 32em; width:100%;"></textarea>
				
				<input type="hidden" name="servletPath" id="cusMesgServletPath" value="<%=request.getServletPath()%>">
				<div class="modal-footer mesgBox">
					<button type="button" class="btn btn-default" style="background-color: #5e2b97; color: white;"
						onclick="sendCusMesg('${mbNo}')">傳送</button>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- cus_mesg script ajax -->
<!-- bug 沒轉跳頁面的話再打開會維持發送成功 -->
<script>
	var addCusMesgForm = document.getElementById("addCusMesgForm").cloneNode(true);
	function resetModal(){
		console.log("1111");
		document.getElementById("modal-content").removeChild(document.getElementById("addCusMesgForm"));
		document.getElementById("modal-content").appendChild(addCusMesgForm);
	}
	
	function sendCusMesg(mbNo){
		var topic = $("#cusMesgTopic").val();
		var content = $("#cusMesgContent").val();
		$("#cusMesgTopic").val("");
		$("#cusMesgContent").val("");
		addCusMesgForm = document.getElementById("addCusMesgForm").cloneNode(true);
		
		var xhr = new XMLHttpRequest();
		 xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					console.log(xhr.responseText == 1);
					if(xhr.responseText == 1){
						$("#addCusMesgForm").empty();
						$("#addCusMesgForm").append("<div style='align:center; font-size:26px; color:#f00; padding:15px;'>客服訊息發送成功</div>");
					}
					
				}//xhr.status == 200
			}//xhr.readyState == 4
		};//onreadystatechange 

		//建立好Get連接http://localhost:8081/Local-BA103G5_Jacky/message/sysbox.jsp#mesg-content0
		var url = "<%=request.getContextPath()%>/cus_serv/cusMail.do?action=addMesg&sender="+mbNo+"&topic="+topic+"&mesgContent="+content;
		
		xhr.open("Get", url, true);
		//送出請求 
		xhr.send(null);
		console.log(url);
		
	}
</script>