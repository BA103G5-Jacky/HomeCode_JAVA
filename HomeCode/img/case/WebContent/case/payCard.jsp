<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reported_case.model.*"%>
<%@ page import="com.cs.model.*"%>

<%@ page import="com.skill_type.model.*"%>
<%@ page import= "java.util.*"%>
<%@ page import="com.case_type.model.*"%>

 <%CaseVO caseVO = (CaseVO) request.getAttribute("caseVO");
 	Case_typeVO case_typeVO=(Case_typeVO) request.getAttribute("case_typeVO");
%>
 	
 





<html lang="">
	<head>
		<title>付款畫面</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/case/css/updateCase.css">
					
</head>
<body>




	<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
	<!-- ======================header-end ================================== -->
	
	
	<div class="container Portfolio">
			<h1>案件張貼</h1>
	</div> 	
		



<div class="hiw"> 	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/case/case.do" name="form1">

		<div class="container Portfolio mrg-top-15 mrg-bottom-15">
			<div class="row cs-content">
					<div class="col-xs-12 col-xs-8 col-sm-offset-2 ">
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
											<label class="size">案件名稱</label>
										</div>
										<div class="col-sm-9">
											<input type="text" name="csName" id="" class="form-control update" value="<%= (caseVO==null)? "" : caseVO.getCsName()%>"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row  img-va central">
										<div class="col-sm-3 control-label">
											<label class="size">案件描述</label>
										</div>
										<div class="col-sm-9">
											<textarea class="form-control update" name="csDesc" rows="5" id="comment" placeholder="請詳細專案需求內容(日期.價格.工作內容.需要技能等)" /><%= (caseVO==null)? "" : caseVO.getCsDesc()%></textarea>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row  img-va central">
										<div class="col-sm-3 control-label">
											<label class="size">案件地區</label>
										</div>
										<div class="col-sm-9">
											<!-- <input type="text" name="csLoc" id="" class="form-control"> -->
											<select name="csLoc" style="style=style=font-family: 'Calibri'; font-size: 12pt; color: #000000; width:100%">
												<% if(caseVO == null){
														caseVO = new CaseVO();
														caseVO.setCsLoc("台北市");
														caseVO.setCsLevel("小");
													}
												%>
	  											<option value="基隆市" <%=(caseVO.getCsLoc().equals("基隆市"))? "selected" : "" %> >基隆市</option>
												<option value="台北市" <%=(caseVO.getCsLoc().equals("台北市"))? "selected" : "" %> >台北市</option>
												<option value="新北市" <%=(caseVO.getCsLoc().equals("新北市"))? "selected" : "" %> >新北市</option>
												<option value="桃園市" <%=(caseVO.getCsLoc().equals("桃園市"))? "selected" : "" %> >桃園市</option>
												<option value="新竹市" <%=(caseVO.getCsLoc().equals("新竹市"))? "selected" : "" %> >新竹市</option>
												<option value="新竹縣" <%=(caseVO.getCsLoc().equals("新竹縣"))? "selected" : "" %> >新竹縣</option>
												<option value="苗栗縣" <%=(caseVO.getCsLoc().equals("苗栗縣"))? "selected" : "" %> >苗栗縣</option>
												<option value="台中市" <%=(caseVO.getCsLoc().equals("台中市"))? "selected" : "" %> >台中市</option>
												<option value="彰化縣" <%=(caseVO.getCsLoc().equals("彰化縣"))? "selected" : "" %> >彰化縣</option>
												<option value="南投縣" <%=(caseVO.getCsLoc().equals("南投縣"))? "selected" : "" %> >南投縣</option>
												<option value="嘉義市" <%=(caseVO.getCsLoc().equals("嘉義市"))? "selected" : "" %> >嘉義市</option>
												<option value="嘉義縣" <%=(caseVO.getCsLoc().equals("嘉義縣"))? "selected" : "" %> >嘉義縣</option>
												<option value="台南市" <%=(caseVO.getCsLoc().equals("台南市"))? "selected" : "" %> >台南市</option>
												<option value="高雄市" <%=(caseVO.getCsLoc().equals("高雄市"))? "selected" : "" %> >高雄市</option>
												<option value="屏東市" <%=(caseVO.getCsLoc().equals("屏東市"))? "selected" : "" %> >屏東市</option>
												<option value="台東縣" <%=(caseVO.getCsLoc().equals("台東縣"))? "selected" : "" %> >台東縣</option>
												<option value="花蓮縣" <%=(caseVO.getCsLoc().equals("花蓮縣"))? "selected" : "" %> >花蓮縣</option>
												<option value="宜蘭縣" <%=(caseVO.getCsLoc().equals("宜蘭縣"))? "selected" : "" %> >宜蘭縣</option>
												<option value="澎湖縣" <%=(caseVO.getCsLoc().equals("澎湖縣"))? "selected" : "" %> >澎湖縣</option>
												<option value="金門縣" <%=(caseVO.getCsLoc().equals("金門縣"))? "selected" : "" %> >金門縣</option>
												<option value="連江縣" <%=(caseVO.getCsLoc().equals("連江縣"))? "selected" : "" %> >連江縣</option>
												<option value="海外地區" <%=(caseVO.getCsLoc().equals("海外地區"))? "selected" : "" %> >海外地區</option>
											</select>
										</div>
									</div>
								</div>
								
								<jsp:useBean id="stSvc" class="com.skill_type.model.Skill_typeService"/>
								<div class="form-group">
									<div class="row  img-va">
										<div class="col-sm-3 control-label">
											<label class="size">所需技能</label>
												</div>
											<div class="col-xs-12 col-sm-9 ">
												<c:forEach var="stVO" items="${stSvc.all}">
												<label>
													<div class="font-content btn btn-default btn-tab-4">
														<input type="checkbox" name="skillNo" value="${stVO.skillNo}" ${(ctSkNoList.contains(stVO.skillNo)) ? "checked":"" } >&nbsp${stVO.skillName}
													</div>
												</label>
											</c:forEach>
									</div>
								</div>
							</div>
								
								<div class="form-group">
									<div class="row  img-va central">
										<div class="col-sm-3 control-label">
											<label class="size">案件金額</label>
										</div>
										<div class="col-sm-9">
											<input type="number" name="csPayment" id="" class="form-control  update" value="<%= (caseVO.getCsPayment()==null)? "" : caseVO.getCsPayment()%>" />
										</div>
									</div>
								</div>
								
								
								
								<div class="form-group">
									<div class="row  img-va central">
										<div class="col-sm-3 control-label">
											<label class="size">案件規模</label>
										</div>
										<div class="col-sm-9">
											 <div class="btn-group-justified ">
		    									<label><div class="btn btn-lpurple "><input type="radio" name="csLevel" value="小" <%=(caseVO.getCsLevel().equals("小"))? "checked" : "" %>/>小型<img class="img-responsive" src="<%=request.getContextPath()%>/img/g1.png"></div></label>
		    									<label><div class="btn btn-lpurple "><input type="radio" name="csLevel" value="中" <%=(caseVO.getCsLevel().equals("中"))? "checked" : "" %>/>中型<img class="img-responsive" src="<%=request.getContextPath()%>/img/g2.png"></div></label>
		    									<label><div class="btn btn-lpurple "><input type="radio" name="csLevel" value="大" <%=(caseVO.getCsLevel().equals("大"))? "checked" : "" %>/>大型<img class="img-responsive" src="<%=request.getContextPath()%>/img/g3.png"></div></label>
											</div>
										</div>
									</div>
								</div>
						
<!--=====================================================================================DATE======================================================================================-->
								<div class="mrg-top-15 form-group">				
									<div class="img-va row ">
										<div class="col-sm-3 control-label">
											 <label for="startDate">案件開始日</label>
										</div>
										<div class="col-sm-9">
											<input id="startDate" name="startDate" type="text" class="form-control update"  value="<%= (caseVO.getStartDate()==null)? "" : caseVO.getStartDate()%>" />
										</div>
											
									</div>			
									<div class="img-va row mrg-top-15" >
										<div class="col-sm-3 control-label">
											<label for="endDate">案件結束日</label>
										  </div>
										<div class="col-sm-9">
											<input id="endDate" name="endDate" type="text" class="form-control update" value="<%= (caseVO.getEndDate()==null)? "" : caseVO.getEndDate()%>" />
										</div>
									</div>	
								</div>
	
								<div class="form-gruop mrg-top-15">
									<div class="img-va central">
										<input type="hidden" name="hirerNo" value="${mbNo}">
										<input type="hidden" name="action" value="insert">
										<input type="submit" value="送出新增" class="btn btn-default  btn-tab-4">
											<!-- <button type="button" class="btn btn-default  btn-tab-4">送出</button> -->	
									</div>
								</div>	
						</div>
					</div>
				</div>			
			</div>
			
	</form>
</div>
<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
<!-- footer-end   ============================================================= -->

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.js"></script>
		<script src="<%=request.getContextPath()%>/js/script.js"></script>

		<script>
		


		 $(function() {
							$("#datepicker").datepicker({closeText: 'X',altField: '#actualDate',altFormat: 'yyyy-mm-dd',appendText: '(yyyy-mm-dd)',showButtonPanel: true,showClearButton:true,minDate: new Date(2017-9-22)});
						
							$("#startDate").datepicker(
							{onSelect: function(dateText,inst) {$('#endDate').datepicker('option','minDate',new Date(dateText.replace('-',',')));}
							}
							
	
							);

							$("#endDate").datepicker(
							{onSelect: function(dateText, inst) {$('#startDate').datepicker('option', 'maxDate', new Date(dateText.replace('-',',')));}
							});
						}); 
					
		
		</script>
	</body>
</html>
</body>
</html>