<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cs.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.DecimalFormat" %>

<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>案件搜尋</title>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
	<link rel="icon" type="<%=request.getContextPath()%>/images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png"
			size="16x16">
	<!-- linking point : local style.ccs -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/search/css/caseSearch.css">

<%
		System.out.println(request.getQueryString());
		
		CaseService csSvc = new CaseService();
		List<CaseVO> listCases_ByCompositeQuery = null;
		if(session.getAttribute("listCases_ByCompositeQuery") == null || request.getQueryString() == null){
			listCases_ByCompositeQuery = csSvc.getAll();
			session.setAttribute("listCases_ByCompositeQuery", listCases_ByCompositeQuery);
		} else{
			listCases_ByCompositeQuery = (List<CaseVO>)session.getAttribute("listCases_ByCompositeQuery");
			
		}
		
		
		DecimalFormat df = new DecimalFormat("$###,###,###"); 
		pageContext.setAttribute("df", df);

%>

<style>
	.sk{
		width:120px; padding:3px;
		font-size:16px;
	}
	.sktr{
		height:auto;
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

	<jsp:useBean id="appSvc" class="com.applicant.model.ApplicantService"/>
	<jsp:useBean id="fcSvc" class="com.favorite_case.model.FavoriteCaseService"/>
	<jsp:useBean id="ctSvc" class="com.case_type.model.Case_typeService"/>
	<jsp:useBean id="Skill_typeSvc" scope="page" class="com.skill_type.model.Skill_typeService" />
	<!-- 尋找案件的整個表單 -->

				<div class="container search-case">
					<div class="row ">
						
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/case/case.do">
				
						
							<div class="col-xs-12 col-sm-8 col-sm-offset-2  content-all">
								<div class="row">
									<!-- 標題 -->
									<h1 id="title" >搜尋案件</h1>
									
									<table style="margin:auto; text-align:left;">
										<!-- 關鍵字 -->
										<tr>
											<td><span style="margin:15px;">關鍵字</span></td>
											<td><input type="text" name="csname" class="input"></td>
										</tr>
										<!-- 地區 -->
										<tr>
											<td><span style="margin:15px;">地區</span></td>
											<td>
												<select name="csloc" class="input location">
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
											<td><span style="margin:15px;">金額</span></td>
											<td>
												<input type="number" class=" money" name="lowpayment" min="0">
												 &nbsp; ～ &nbsp; 
												<input type="number" class=" money money2" name="highpayment" min="0">
											</td>
										</tr>
										
										<!-- 技能搜尋 -->
										<tr>
											<td><span style="margin:15px;">需具備技能</span></td>
											<td>
												<table>
													<c:forEach var="Skill_typeVO" items="${Skill_typeSvc.all}" varStatus="i">
														<label>
															<div class="font-content ">
													
														<c:choose >	
														<c:when test="${i.count mod 3 eq 1  }">
															
															<tr class="sktr"><td class="sk">
																<input type="checkbox" name="skillNo" value="${Skill_typeVO.skillNo}" >&nbsp${Skill_typeVO.skillName}
															</td>
														</c:when>
															<c:otherwise>
															<td class="sk">
													  			<input type="checkbox" name="skillNo" value="${Skill_typeVO.skillNo}" >&nbsp${Skill_typeVO.skillName}
													  		</td>
													  		</c:otherwise>
														</c:choose>
														
													  		
														<c:if test="${i.count mod 3 eq 0  }">
														</tr>
														</c:if>
														
															</div>
														</label>
														
<%-- 														<input type="checkbox" name="skillNo" value="${Skill_typeVO.skillNo}">${Skill_typeVO.skillName} --%>
<%-- 														<c:if test="${i.count%4 == 0}"><br></c:if> --%>
													</c:forEach>
												</table>
											</td>
										</tr>
									</table>
									
									
									
									<div class="central">
										<input type="hidden" name="action" value="listCases_ByCompositeQuery">
										<button id="submitbtn" type="submit">
												<strong>搜尋</strong>
										</button>
									</div>
									
									
									
								</div>
							</div>
						</form>
						
					<div class="col-xs-12 col-sm-8 col-sm-offset-2  content-all">
						<!-- 價格排序鈕 -->
						<div class="central mrg-top-15">
							<ul class="list-inline">
								<!-- 這是價格由高到低的按鈕 -->
								<li>
									<form METHOD="post" ACTION="<%=request.getContextPath()%>/case/case.do">
										<button id="submitbtn" type="submit" class="td" name="action" value="highToLow">
											價格由高到低</button>
									</form>
								</li>
								<!-- 這是價格由低到高的按鈕 -->
								<li>
									<form METHOD="post" ACTION="<%=request.getContextPath()%>/case/case.do">
										<button id="submitbtn" type="submit" class="td" name="action" value="lowToHigh">
											價格由低到高</button>
									</form>
								</li>
							</ul>
						</div>
			
					</div>	


		
<!--================================= 分頁 ============================================-->
<%@ include file="/pages/page1(case).file" %>
<%-- begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
<!--================================= 分頁 ============================================-->
		
		<jsp:useBean id="memberSvc" class="com.member.model.MemberService" /> 
		<c:if test="${listCases_ByCompositeQuery.size() != 0}">
		<!-- 列表的個人專欄 -->
		<c:forEach var="caseVO" items="${listCases_ByCompositeQuery}" varStatus="i" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="row">
					<hr>
					<div class="col-sm-4">
						<div id="name">
							<b style="font-size:24px;"><a href="<%=request.getContextPath()%>/case/case.do?csNo=${caseVO.csNo}&action=caseInfo" class="green">${caseVO.csName}</a></b>
						</div>
						<!--hirer人 -->
						<div id="job">
							<br>
							<b>發案者</b> - 
							<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${caseVO.hirerNo}&action=getMbInfo" class="green">	
								${memberSvc.getOneMember(caseVO.hirerNo).mbLstName}${memberSvc.getOneMember(caseVO.hirerNo).mbFstName}
							</a>
						</div>
						
					</div>
					<div class="col-sm-5">
						<!-- 案件地區 -->
						<div class="mrg-top-15">
							<img src="<%=request.getContextPath()%>/img/loc.png" class="photo-size-forCseListIcon">${caseVO.csLoc}
						</div>
						<!--價錢 -->
						<div class="mrg-top-15">
							<strong>${df.format(caseVO.csPayment)}</strong>
						</div>
						
						<!--日期 -->
						<div class="mrg-top-15">
							開始時間&nbsp&nbsp<span>${caseVO.startDate.substring(0, 10)}</span>&nbsp&nbsp
							結束時間&nbsp&nbsp<span>${caseVO.endDate.substring(0, 10)}</span>
						</div>
						
					</div>
					
					<!-- 邀請按鈕 -->
					<div class="col-sm-3 btn-list">
						<c:if test="${mbNo != null }">
						<!-- 發案人為自己則不顯示按鈕 -->
							<div class="flex-end">
								<!-- 沒申請過的會員才可申請 -->
								<c:if test="${!appSvc.getOneCaseWhoApply(caseVO.csNo).contains(mbNo) && mbNo != caseVO.hirerNo}">
									<form action="<%=request.getContextPath()%>/Applicant/ApplicantServlet" method="post">
										<input type="hidden" name="freelancerNo" value="${mbNo}">
										<input type="hidden" name="csNo" value="${caseVO.csNo}">
										<input type="hidden" name="hirerNo" value="${caseVO.hirerNo}">
										<input type="hidden" name="action" value="insert">
										<button class="hirebtn" type="submit">申請案件</button>
									</form>
								</c:if>
							</div>
							
							<div class="flex-end" style='${(mbNo != caseVO.hirerNo)? "":"display:none;"}'>
								<input type="hidden" value="${caseVO.csNo}" class="fcCsNo">
								<input type="hidden" value="${mbNo}" class="fcMbNo">
								<div class="addFavoriteArea" id="addFavoriteArea" ${(!fcSvc.getOneCaseFavoritMb(caseVO.csNo).contains(mbNo)) ? '':'style="display:none;"' }>
									<button class="savebtn addFavorite" onclick="addFavorite(${i.index%5});">
										<img src="<%=request.getContextPath()%>/img/like.png"> 收藏案件
									</button>
								</div>
								<div class="removeFavoriteArea" id="removeFavoriteArea" ${(fcSvc.getOneCaseFavoritMb(caseVO.csNo).contains(mbNo)) ? '':'style="display:none;"' }>
									<button class="savebtn removeFavorite" onclick="removeFavorite(${i.index%5});">
										<img src="<%=request.getContextPath()%>/img/unlike.png"> 取消收藏
									</button>
								</div>
							</div>
						</c:if>
						<div class="flex-end">
							<c:if test="${mbNo != caseVO.hirerNo}">
								<form action="<%=request.getContextPath()%>/rpcase/rpcase.do" method="post">
									<input type="hidden" name="action" value="rpCase">
									<input type="hidden" name="csNo" value="${caseVO.csNo}">
									<input type="hidden" name="url" value="csNo=${caseVO.csNo}&action=caseInfo">
									<button class="hirebtn" type="submit">檢舉案件</button>
								</form>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		</c:if>
		
		<c:if test="${listCases_ByCompositeQuery.size() == 0}">
		
			<div class="col-sm-8 col-sm-offset-2 central" style="padding:30px; font-size:20px;">
				<div style="margin-bottom:20px;"><img src="<%=request.getContextPath()%>/img/case_search.png"></div>
				搜尋的條件無任何案件
			</div>
			
		</c:if>
		
		<div class="col-xs-12 col-sm-10 col-sm-offset-1 content-all central">
			<div class="central">
				<!--================================= 分頁 ============================================-->
				<%@ include file="/pages/page2(case).file" %>
				<!--================================= 分頁 ============================================-->
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

<script>
function addFavorite(index){
	var xhr = new XMLHttpRequest();
	//設定好回呼函數   
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				$('.addFavoriteArea')[index].setAttribute("style","display:none;");
				$('.removeFavoriteArea')[index].removeAttribute("style");
			} else {
				alert(xhr.status);
			}//xhr.status == 200
		}//xhr.readyState == 4
	};//onreadystatechange 

	//建立好Get連接
	var url =  "<%=request.getContextPath()%>/favoriteCase/favoriteCase.do?action=addFavorite&csNo=" + $(".fcCsNo")[index].value + "&mbNo="+ $(".fcMbNo")[index].value;
	xhr.open("Get", url, true);
	//送出請求 
	xhr.send(null);
	console.log(url);
}

function removeFavorite(index){
	var xhr = new XMLHttpRequest();
	
	//設定好回呼函數   
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				$('.addFavoriteArea')[index].removeAttribute("style");
				$('.removeFavoriteArea')[index].setAttribute("style","display:none;");
			} else {
				alert(xhr.status);
			}//xhr.status == 200
		}//xhr.readyState == 4
	};//onreadystatechange 

	//建立好Get連接
	var url =  "<%=request.getContextPath()%>/favoriteCase/favoriteCase.do?action=removeFavorite&csNo=" + $(".fcCsNo")[index].value + "&mbNo="+ $(".fcMbNo")[index].value;
	xhr.open("Get", url, true);
	//送出請求 
	xhr.send(null);
	console.log(url);
}
</script>

</body>
</html>