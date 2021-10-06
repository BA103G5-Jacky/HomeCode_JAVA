<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reported_member.model.*"%>
<%@ page import="com.member.model.*"%>
 
<% 
	Reported_memberVO reported_memberVO = (Reported_memberVO) request.getAttribute("reported_memberVO");
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>檢舉會員</title>
</head>
<body>
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
				<h2>檢舉會員</h2>
			</div>

			<div class=" cs-content row">
				<div class="col-xs-12 row ">
					<div class="mrg-top-15 "><h3>${memberVO.mbLstName}${memberVO.mbFstName}</h3></div>
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

				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rpmember/rpmember.do" name="form1">
				
				
					<div class="mrg-top-15 cs-content-nb row ">
						<div class="row ">
			
							<div class="col-xs-12 " >
		
								<div class="col-xs-12 col-sm-6 ">
									<div class="font-content"><input type="radio" name="reportReason" value="虛偽經歷" <%= (reported_memberVO != null && reported_memberVO.getReportReason().equals("虛偽經歷")) ? "checked" : ""%>/>虛偽經歷<br></div>
									<div class="font-content"><input type="radio" name="reportReason" value="重複他人資料" <%= (reported_memberVO != null && reported_memberVO.getReportReason().equals("重複他人資料")) ? "checked" : ""%>/>重複他人資料<br></div>
									<div class="font-content"><input type="radio" name="reportReason" value="濫發訊息" <%= (reported_memberVO != null && reported_memberVO.getReportReason().equals("濫發訊息")) ? "checked" : ""%>/>濫發訊息<br></div>
									<div class="font-content"><input type="radio" name="reportReason" value="個人作品違反商標權" <%= (reported_memberVO != null && reported_memberVO.getReportReason().equals("個人作品違反商標權")) ? "checked" : ""%>/>個人作品違反商標權<br></div>
									<div class="font-content"><input type="radio" name="reportReason" value="違反隱私權" <%= (reported_memberVO != null && reported_memberVO.getReportReason().equals("違反隱私權")) ? "checked" : ""%>/>違反隱私權<br></div>
									<div class="font-content"><input type="radio" name="reportReason" value="照片非本人" <%= (reported_memberVO != null && reported_memberVO.getReportReason().equals("照片非本人")) ? "checked" : ""%>/>照片非本人<br></div>
								</div>
								<div class="col-xs-12 col-sm-6 ">
								
								</div>
							</div>
						</div>
					</div>
					
					<div class="mrg-top-15">
						<h4>檢舉描述</h4>
					</div>
					<textarea class="form-control rpcs" rows="5" name="reportDesc" id="comment" placeholder="請填寫檢舉內容"><%= (reported_memberVO == null) ? "" : reported_memberVO.getReportDesc()%></textarea>
					<hr class="hr-color">
					<div class="form-gruop mrg-top-15">
						<div class="img-va central">
							<input type="hidden" name="reportMbno" value="${mbNo}">
							<c:choose>
								<c:when test="${reported_memberVO==null}">
									<input type="hidden" name="reportedMbno" value="${memberVO.mbNo}">
								</c:when>
								<c:otherwise>
									<input type="hidden" name="reportedMbno" value="${reported_memberVO.reportedMbno}">
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
</body>
</html>