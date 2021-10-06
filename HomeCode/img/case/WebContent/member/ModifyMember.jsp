<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cs.model.*"%>
<%@ page import="com.member.model.*"%>

<% 
  	MemberVO memberVO=(MemberVO)request.getAttribute("memberVO");
 	
%>	 


<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/demos/style.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style-albe-timeline.css" />
		<title>會員資料修改</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
  			<link rel="stylesheet" href="<%=request.getContextPath()%>/Content/bootstrap-datetimepicker.css" />
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
			<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
<!-- # linking piont tab-icon-->
			<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
			<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/member/css/modifyMember.css">	
</head>
<body>


	<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
	<!-- ======================header-end ================================== -->
	
	


	
	<div class="container Portfolio">
		<h1>會員資料修改</h1>
	</div> 	
		

<div class="hiw" > 
	<FORM ACTION="<%=request.getContextPath()%>/member/member.do" method=post enctype="multipart/form-data">
		
			<div class="panel-heading">
        		<h2 class="panel-title glyphicon glyphicon-pencil">${memberVO.mbLstName}${memberVO.mbFstName} 基本資料修改</h2>
        	</div>
    
		<div class="container Portfolio mrg-top-15 ">	
			<div class="row cs-content">
				<div class="col-xs-12 col-xs-6 col-sm-offset-3 ">
					<c:if test="${not empty errorMsgs}">
						<font color='red'>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
						</font>
					</c:if>		
					<div class="form-horizontal">
						<div class="form-group">
							<div class="row  img-va central">
								<div class="col-sm-3 control-label">
									<label class="size">會員姓氏</label>
								</div>
								<div class="col-sm-9">
									<input type="text" name="mbLstName" id=""   class="form-control" value="<%= (memberVO.getMbLstName()==null)? "" :memberVO.getMbLstName()%>"/>
								</div>
							</div>
						</div>
						
						<div class="form-group margin01">
							<div class="row  img-va central">
								<div class="col-sm-3 control-label">
									<label class="size">會員名字</label>
								</div>
								<div class="col-sm-9">
									<input type="text" name="mbFstName" id=""  class="form-control"  value="<%= (memberVO.getMbFstName()==null)? "" : memberVO.getMbFstName()%>"/>
								</div>
							</div>
						</div>
						
							<div class="form-group margin01">
							<div class="row  img-va central">
								<div class="col-sm-3 control-label">
									<label class="size">使用者名稱</label>
								</div>
								<div class="col-sm-9">
									<input type="text" name="mbUsrName" id="" class="form-control" value="<%= (memberVO.getMbUserName()==null)? "" : memberVO.getMbUserName()%>" />
								</div>
							</div>
						</div>
						
						
						<div class="form-group margin01">
							<div class="row  img-va central">
								<div class="col-sm-3 control-label">
									<label class="size">公司名稱</label>
										</div>
								<div class="col-sm-9">
									<input type="text" name="comName" id="" class="form-control" value="<%= (memberVO.getComName()==null)? "" :memberVO.getComName()%>"/>
								</div>
							</div>
						</div>
								
						
								
						<div class="form-group margin01">
							<div class="row  img-va central">
								<div class="col-sm-3 control-label">
									<label class="size">會員介紹</label>
								</div>
								<div class="col-sm-9">
									<textarea class="form-control" name="mbIntroduce" rows="5" id="mbIntroduce" placeholder="輸入會員介紹" >${memberVO.mbIntroduce}</textarea>
								</div>
							</div>
						</div>								
								
						<div class="form-group margin01">
							<div class="row  img-va central">
								<div class="col-sm-3 control-label">
									<label class="size">會員居住地</label>
								</div>
								<div class="col-sm-9">
									<select name="mbLoc" style="style=style=font-family: 'Calibri'; font-size: 12pt; color: #000000; width:100%">
	  										<option value="基隆市" <%=(memberVO.getMbLoc().equals("基隆市"))? "selected" : "" %> >基隆市</option>
											<option value="台北市" <%=(memberVO.getMbLoc().equals("台北市"))? "selected" : "" %> >台北市</option>
											<option value="新北市" <%=(memberVO.getMbLoc().equals("新北市"))? "selected" : "" %> >新北市</option>
											<option value="桃園市" <%=(memberVO.getMbLoc().equals("桃園市"))? "selected" : "" %> >桃園市</option>
											<option value="新竹市" <%=(memberVO.getMbLoc().equals("新竹市"))? "selected" : "" %> >新竹市</option>
											<option value="新竹縣" <%=(memberVO.getMbLoc().equals("新竹縣"))? "selected" : "" %> >新竹縣</option>
											<option value="苗栗縣" <%=(memberVO.getMbLoc().equals("苗栗縣"))? "selected" : "" %> >苗栗縣</option>
											<option value="台中市" <%=(memberVO.getMbLoc().equals("台中市"))? "selected" : "" %> >台中市</option>
											<option value="彰化縣" <%=(memberVO.getMbLoc().equals("彰化縣"))? "selected" : "" %> >彰化縣</option>
											<option value="南投縣" <%=(memberVO.getMbLoc().equals("南投縣"))? "selected" : "" %> >南投縣</option>
											<option value="嘉義市" <%=(memberVO.getMbLoc().equals("嘉義市"))? "selected" : "" %> >嘉義市</option>
											<option value="嘉義縣" <%=(memberVO.getMbLoc().equals("嘉義縣"))? "selected" : "" %> >嘉義縣</option>
											<option value="台南市" <%=(memberVO.getMbLoc().equals("台南市"))? "selected" : "" %> >台南市</option>
											<option value="高雄市" <%=(memberVO.getMbLoc().equals("高雄市"))? "selected" : "" %> >高雄市</option>
											<option value="屏東市" <%=(memberVO.getMbLoc().equals("屏東市"))? "selected" : "" %> >屏東市</option>
											<option value="台東縣" <%=(memberVO.getMbLoc().equals("台東縣"))? "selected" : "" %> >台東縣</option>
											<option value="花蓮縣" <%=(memberVO.getMbLoc().equals("花蓮縣"))? "selected" : "" %> >花蓮縣</option>
											<option value="宜蘭縣" <%=(memberVO.getMbLoc().equals("宜蘭縣"))? "selected" : "" %> >宜蘭縣</option>
											<option value="澎湖縣" <%=(memberVO.getMbLoc().equals("澎湖縣"))? "selected" : "" %> >澎湖縣</option>
											<option value="金門縣" <%=(memberVO.getMbLoc().equals("金門縣"))? "selected" : "" %> >金門縣</option>
											<option value="連江縣" <%=(memberVO.getMbLoc().equals("連江縣"))? "selected" : "" %> >連江縣</option>
											<option value="海外地區" <%=(memberVO.getMbLoc().equals("海外地區"))? "selected" : "" %> >海外地區</option>
										</select>
								</div>							</div>
						</div>
								
						<div class="form-group margin01">
							<div class="row  img-va central">
								<div class="col-sm-3 control-label">
									<label class="size">會員詳細地址</label>
								</div>
								<div class="col-sm-9">
									<input type="text" name="mbAddress" id="" class="form-control" value="${memberVO.mbAddress}" />
								</div>
							</div>
						</div>
					
						
						<div class="form-group margin01">
							<div class="row  img-va central">
								<div class="col-sm-3 control-label">
									<label class="size">會員電話號碼</label>
								</div>
								<div class="col-sm-9">
									<input type="number" name="phone" id="" class="form-control" value="${memberVO.phone}" />
								</div>
							</div>
						</div>
						
						<jsp:useBean id="stSvc" class="com.skill_type.model.Skill_typeService"/>
						<div class="form-group">
							<div class="row  img-va">
								<div class="col-sm-3 control-label">
									<label class="size">您會的技能</label>
								</div>
								<div class="col-sm-9 col-xs-12 ">
									<c:forEach var="stVO" items="${stSvc.all}">
										<label>
											<div class="font-content btn btn-default btn-tab-4">
												<input type="checkbox" name="skillNo" value="${stVO.skillNo}" ${(mbSkillNoList.contains(stVO.skillNo)) ? "checked":"" } >&nbsp${stVO.skillName}
											</div>
										</label>
									</c:forEach>
								</div>
							</div>
						</div>	
				
						
						<div class="form-group margin01">
							<div class="row  img-va central">
								<div class="col-sm-3 control-label">
									<label class="size">會員大頭貼</label>
										</div>
											<div class="col-sm-4">
												<img src="<%=request.getContextPath()%>/Image/Image.do?MBNO=${memberVO.mbNo}&index=0" class="img-thumbnail photo-size">
											</div>	
										<div class="col-sm-5">
								 	<input type="file" name="poritait" accept='image/*'  onchange='openFile(event)'  value="<%= (memberVO==null)? "" : memberVO.getPortrait()%>" />
       								
							</div>
						</div>
					</div>	

						<div class="form-gruop mrg-top-20 ">
							<div class="img-va central">
								<input type="hidden" name="action" value="Update">
								<input type="hidden" name="mbNo" value="${memberVO.mbNo}">
								<input type="submit" value="確認修改" class="btn btn-default  btn-tab-4">
							</div>
						</div>	
					</div>
				</div>
			</div>			
		</div>
 	</FORM>
 </div> 
		
<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
<!-- footer-end   ============================================================= -->

		<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.js"></script> 
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.3.2/js/mdb.min.js"></script>	
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		
	</body>
</html>
