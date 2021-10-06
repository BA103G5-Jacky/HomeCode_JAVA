<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reported_case.model.*"%>
<%@ page import="com.cs.model.*"%>
<%
	response.setHeader("Cache-Control","no-store");
	Reported_caseVO reported_caseVO = (Reported_caseVO) request.getAttribute("reported_caseVO");
	
// 	String csNo=(String)session.getAttribute("csNo");
// 	if ( csNo == null) {
// 		session.setAttribute("csNo", "CS0000005");
// 		CaseService csSvc = new CaseService();
// 		CaseVO caseVO= csSvc.getOneCase(csNo);
// 		request.setAttribute("caseVO", caseVO);
// 	}
	
	
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
			<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Reported_case/css/reportedCase.css">
		
	</head>
	<body>




<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
<!-- ======================header-end ================================== -->
	
	
	

<!-- ============================================================================feedback========================================================================-->
			
	<div class="container margin-top-bottom cen">

		<div class=" mrg-top-15  col-xs-12  ">
		
			<div class=" title-bgcolor row margin-right-15">
				<h2>檢舉案件</h2>
			</div>
	
			<div class=" cs-content row">
				<div class="col-xs-12 row ">
					<div class="mrg-top-15 "><h3>${caseVO.csName}</h3></div>
					<hr class="hr-color">
					<c:if test="${not empty errorMsgs}">
						<font color='red'>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li>${message}</li>
								</c:forEach>
							</ul>
						</font>
					</c:if>
				</div>
	
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rpcase/rpcase.do" name="form1">
					
					<!-- <input type="hidden" name="rpResult" value="未審核"> -->		
					<div class="mrg-top-15 cs-content-nb row ">
						<div class="row">
							<div class="col-xs-12 " >
								<div class="col-xs-12 col-sm-6 ">
									<div class="font-content"><input type="radio" name="rpReason" value="案件為廣告" <%= (reported_caseVO != null && reported_caseVO.getRpReason().equals("案件為廣告")) ? "checked" : ""%>/>案件為廣告<br></div>
									<div class="font-content"><input type="radio" name="rpReason" value="工作內容與實際不一致" <%= (reported_caseVO != null && reported_caseVO.getRpReason().equals("工作內容與實際不一致")) ? "checked" : ""%> />工作內容與實際不一致<br></div>
									<div class="font-content"><input type="radio" name="rpReason" value="濫發訊息" <%= (reported_caseVO != null && reported_caseVO.getRpReason().equals("濫發訊息")) ? "checked" : ""%> />濫發訊息<br></div>
									<div class="font-content"><input type="radio" name="rpReason" value="無故解約" <%= (reported_caseVO != null && reported_caseVO.getRpReason().equals("無故解約")) ? "checked" : ""%> />無故解約<br></div>
									<div class="font-content"><input type="radio" name="rpReason" value="要求免費工作" <%= (reported_caseVO != null && reported_caseVO.getRpReason().equals("要求免費工作")) ? "checked" : ""%> />要求免費工作<br></div>
									<div class="font-content"><input type="radio" name="rpReason" value="私下交易" <%= (reported_caseVO != null && reported_caseVO.getRpReason().equals("私下交易")) ? "checked" : ""%> />私下交易<br></div>
								</div>
								<div class="col-xs-12 col-sm-6 ">
								</div>
	
							</div>
						</div>
					</div>
					<div class="mrg-top-15">
						<h4>檢舉描述</h4>
					</div>
					<textarea class="form-control rpcs" rows="5" name="rpDesc" id="comment" placeholder="請填寫檢舉內容" /><%= (reported_caseVO==null)? "" : reported_caseVO.getRpDesc()%></textarea>
					<hr class="hr-color">
					<div class="form-gruop mrg-top-15">
						<div class="img-va central">
							<input type="hidden" name="rpedMb" value="${mbNo}">
							<c:choose>
								<c:when test="${reported_caseVO==null}">
									<input type="hidden" name="rpedCase" value="${caseVO.csNo}">
								</c:when>
								<c:otherwise>
									<input type="hidden" name="rpedCase" value="${reported_caseVO.rpedCase}">
								</c:otherwise>
							</c:choose>
							<input type="hidden" name="action" value="insert">
							<input type="hidden" name="url" value="${url}">
							<input type="submit" value="送出檢舉" class="btn btn-default  btn-tab-4">					
						</div>
					</div>
				</FORM>
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