<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member_skill.model.*"%>
<%@ page import="com.skill_type.model.*"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>人才搜尋</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/search/css/caseSearch.css">
<link rel="icon" type="<%=request.getContextPath()%>/images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png"
			size="16x16">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/search/css/peopleSearch.css">




				<!-- 這邊先getall 回傳一筆list 不然會crash掉 -->
<%	


MemberService memberSvc= new MemberService();
List<MemberVO> listPeople_ByCompositeQuery = null;
if(session.getAttribute("listCases_ByCompositeQuery") == null || request.getQueryString() == null){
	listPeople_ByCompositeQuery = memberSvc.getAll();
	session.setAttribute("listPeople_ByCompositeQuery", listPeople_ByCompositeQuery);
} else{
	listPeople_ByCompositeQuery = (List<MemberVO>)session.getAttribute("listPeople_ByCompositeQuery");
	pageContext.setAttribute("listPeople_ByCompositeQuery", listPeople_ByCompositeQuery);
}


%>

<!-- 一定要先set東西給分頁不然會nullpointer -->

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
	
	<jsp:useBean id="Skill_typeSvc" scope="page" class="com.skill_type.model.Skill_typeService" />
	<jsp:useBean id="Member_skillSvc" scope="page" class="com.member_skill.model.Member_skillService" />
	<jsp:useBean id="mbSvc" class="com.member.model.MemberService"/>

	<!-- 把前端內容放在這邊喔 ============================================================================================== -->
	
		<div class="container  search-case">
			<div class="row">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
				
						
							<div class="col-xs-12 col-sm-8 col-sm-offset-2  content-all">
								<div class="row">
									<!-- 標題 -->
									<h1 id="title" >人才案件</h1>
									
									<table style="margin:auto; text-align:left;">
										<!-- 關鍵字 -->
										<tr>
											<td><span style="margin:15px;">關鍵字</span></td>
											<td><input type="text" name="mbName" class="input"></td>
										</tr>
										<!-- 地區 -->
										<tr>
											<td><span style="margin:15px;">地區</span></td>
											<td>
												<select name="mbLoc" class="input location">
													<option value="">不限地區</option>
													<option value="基隆市">基隆市</option>
													<option value="台北市">台北市</option>
													<option value="新北市">新北市</option>
													<option value="桃園市">桃園市</option>
													<option value="新竹市">新竹市</option>
													<option value="新竹縣">新竹縣</option>
													<option value="苗栗縣">苗栗縣</option>
													<option value="台中市">台中市</option>
													<option value="彰化縣">彰化縣</option>
													<option value="南投縣">南投縣</option>
													<option value="嘉義市">嘉義市</option>
													<option value="嘉義縣">嘉義縣</option>
													<option value="台南市">台南市</option>
													<option value="高雄市">高雄市</option>
													<option value="屏東市">屏東市</option>
													<option value="台東縣">台東縣</option>
													<option value="花蓮縣">花蓮縣</option>
													<option value="宜蘭縣">宜蘭縣</option>
													<option value="澎湖縣">澎湖縣</option>
													<option value="金門縣">金門縣</option>
													<option value="連江縣">連江縣</option>
													<option value="海外地區">海外地區</option>
												</select>
	
											</td>
										</tr>
										<!-- 金額 -->
										<tr>
											<td><span style="margin:15px;">案件成功次數大於</span></td>
											<td>
												<input type="number" name="csSuccessTimes" class="input">次
											</td>
										</tr>
										
										<!-- 技能搜尋 -->
										<tr>
											<td><span style="margin:15px;">具備技能</span></td>
											<td>
												<c:forEach var="Skill_typeVO" items="${Skill_typeSvc.all}" varStatus="i">
													<input type="checkbox" name="skillNo" value="${Skill_typeVO.skillNo}">${Skill_typeVO.skillName}
													<c:if test="${i.count%4 == 0}"><br></c:if>
												</c:forEach>
											</td>
										</tr>
									</table>
									
									
									
									<div class="central">
										<input type="hidden" name="action" value="SEARCH_PEOPLE">
										<button id="submitbtn" type="submit">
												<strong>搜尋</strong>
										</button>
									</div>
									
									
									
								</div>
							</div>
						</form>


					


				
		





	<!--================================= 分頁 ============================================-->
	<%@ include file="/pages/page1(people).file"%>
<%-- begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
	<!--================================= 分頁 ============================================-->

									
								
									
	<!-- 顯示列表 -->
	<div class="container">
		<div class="row">
			<c:if test="${listPeople_ByCompositeQuery.size() != 0}">
			<c:forEach var="memberVO" items="${listPeople_ByCompositeQuery}" varStatus="i" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<c:if test="${!(memberVO.mbNo eq 'MB0000001') }">
				<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="row">
					<hr>
					<div class="col-sm-3">
						
						<div class="inner" style='background-image: url(<%=request.getContextPath()%>/Image/Image.do?MBNO=${memberVO.mbNo}&index=0)'></div>
						
					</div>
					<div class="col-sm-6">
						<!-- 姓名 -->
						<div>
							<h3><b>
								<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${memberVO.mbNo}&action=getMbInfo" class="green"> 
								${memberVO.mbLstName}${memberVO.mbFstName}
								</a>
							</b></h3>
							
						</div>
						
						<!-- 會員地區 -->
						<div class="mrg-top-15">
							<img src="<%=request.getContextPath()%>/img/loc.png" class="photo-size-forCseListIcon">${memberVO.mbLoc}
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							<b>成為會員時間</b>&nbsp&nbsp&nbsp&nbsp${memberVO.sinceDate}
						</div>
						
						<!--接案總數/成功接案數 -->
						<div>
							<table class="csTimes">
								<tr>
									<td><b>接案次數</b></td><td>${memberVO.csTimes }次</td>
									<td><b>成功接案次數</b></td><td>${memberVO.csSuccessTimes }次</td>
								</tr>
							</table>
						</div>
						
						<div >
							<b>公司名稱</b>&nbsp&nbsp&nbsp&nbsp${memberVO.comName}
						</div>
						
						<div class="mrg-top-15">
							<b>會員EMail</b>&nbsp&nbsp&nbsp&nbsp${memberVO.eMail}
						</div>
						
					</div>
					
					<!-- 聯絡接案人按鈕 -->
					<div class="col-sm-3 btn-list">
						<c:choose>
					
							<c:when test="${mbNo != null }">
							<!-- 會員為自己則不顯示按鈕 -->
								<div class="flex-end" style="margin-top:40px;">
									<c:if test="${mbNo != memberVO.mbNo }">
										<a href="#modal-id${memberVO.mbNo}" data-toggle="modal">
										<button class="hirebtn" type="submit" style="color:white;">聯絡會員</button>
										</a>
									</c:if>
								</div>
							</c:when>
							<c:otherwise>
								<div class="align-content">
									<a href="/login/visitor_login.jsp" class="green" style="font-size:26px;">聯絡會員前<br>請先登入</a>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			</c:if>
			
			<!-- ========================================新增郵件內容==========================================-->
		
			<div class="modal fade" id="modal-id${memberVO.mbNo}" style="padding-right: 0px;">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header mesgBox">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: white; margin: 5px;">&times;</button>
					<h3 class="modal-title" style="width: 100%; font-size: 24px; padding-left: 10px; background-color: #8bc34a; color: white;">新郵件</h3>
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
					<input type="hidden" name="sender" id="sender" class="no-border newMesg" value="${mbSvc.getOneMember(mbNo).mbUserName}">
					${mbSvc.getOneMember(mbNo).mbLstName}${mbSvc.getOneMember(mbNo).mbFstName}<br>
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
					<input type="hidden" name="servletPath" value="/member/member.do?action=getMbInfo&mbNo=${memberVO.mbNo}">
					<div class="modal-footer mesgBox">
						<button type="submit" class="btn btn-default" style="background-color: #8bc34a; color: white;" >傳送</button>
					</div>
				</form>
			</div>
			</div>
			</div>
			<!-- mail end ====================================================================== -->
			
			
		</c:forEach>
		</c:if>	
		
		<c:if test="${listPeople_ByCompositeQuery.size() == 0}">
			
			<div class="col-sm-8 col-sm-offset-2 central" style="padding:30px; font-size:20px;">
				<div style="margin-bottom:20px;"><img src="<%=request.getContextPath()%>/img/people_search.png"></div>
				搜尋的條件無任何會員
			</div>
			
		</c:if>
		
<div class="col-xs-12 col-sm-10 col-sm-offset-1 content-all central">
			<div class="central">
	<!--================================= 分頁 ============================================-->
			<%@ include file="/pages/page2(people).file"%>
	<!--================================= 分頁 ============================================-->
</div>
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
			<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
			<script src="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.js"></script>
			
</body>




</html>