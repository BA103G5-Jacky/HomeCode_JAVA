<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	
	
%>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>確認合約流程</title>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
		<!-- # linking piont bootstrap-lightbox-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
		<!--[if lt IE 9]>
					<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
					<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<!-- # linking piont tab-icon-->
		<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/milestone/css/addMilestone.css">
		<style>
			.lbifuse{
				font-size:x-large;
			}
		</style>
	</head>
<body>

<div class="">
	<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
	<!-- ======================header-end ================================== -->
	
	


	
	<div class="container Portfolio">
		<h1>新增合約進度里程</h1>

			
	</div> 	
	
	
	
	<div class="hiw">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/milestone/milestone.do" name="form1">
			
				
			
			
			<div class="container Portfolio mrg-top-15 mrg-bottom-15">
				<div class="row mrg-top-15">
					<button type="button" class="btn btn-default"
						style="background-color: #9ee4a7; color: white;" id="add">新增里程</button>
					<input type='hidden' name='mbNo' value="${mbNo}">
					<input type='hidden' name='csNo' value="${csNo}">
					<input type='hidden' name="action" value="insert">
					<button type="submit" class="btn btn-default"
						style="background-color: #5e2b97; color: white;" id="add">前往付款</button>
				</div>
				
				
				<div class="row cs-content" >
<!-- 						<div class="col-xs-12 col-xs-10 col-sm-offset-1 central mb20"> -->
<!-- 							<h2 style="color:#f00">請勾選是否使用本網站所提供之合約進度管理系統</h2> -->
<!-- 							<input type="radio" name="useMilestone" value="yes" id="yes" checked><label class="lbifuse" for="yes">是</label> -->
<!-- 							&nbsp&nbsp&nbsp&nbsp&nbsp -->
<!-- 							<input type="radio" name="useMilestone" value="no" id="no"><label class="lbifuse" for="no">否</label> -->
<!-- 						</div> -->
						
						<!-- one milestone start -->
						<div class="col-xs-12 col-xs-10 col-sm-offset-1 " id="cs-content">
							<c:if test="${not empty errorMsgs}">
								<font color='red'>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li>${message}</li>
									</c:forEach>
								</ul>
								</font>
							</c:if>	
								
								<div class="form-horizontal milestone" >
									
									<div class="form-group">
										<div class="row central">
											<div class="col-sm-2 control-label">
												<label for="milestoneName">案件金額</label>
											</div>
											<div class="col-sm-10">
												<input type="number" name="csPayment" class="form-control update" value=""/>
											</div>
										</div>
									</div>
									
									<hr>
									
									<div class="form-group">
										<div class="row central">
											<div class="col-sm-2 control-label">
												<label for="milestoneName">進度里程名稱</label>
											</div>
											<div class="col-sm-10">
												<input type="text" id="milestoneName" class="form-control update" value=""/>
											</div>
										</div>
									</div>
									
									
							
	<!--=====================================================================================DATE======================================================================================-->
								<div class="mrg-top-15 form-group">				
									<div class="row img-va col-xs-12 col-sm-6">
										<div class="col-sm-4 control-label">
											<label for="startDate">進度里程開始日</label>
										</div>
										<div class="col-sm-8">
											<input id="startDate" type="text" class="form-control update startDate" value="" />
										</div>
											
									</div>			
									<div class="img-va row col-xs-12 col-sm-6">
										<div class="col-sm-4 control-label">
											<label for="endDate">進度里程結束日</label>
										</div>
										<div class="col-sm-8">
											<input id="endDate" type="text" class="form-control update endDate" value="" />
										</div>
										
									</div>	
								</div>
								</div>
								<hr>
								
								<div id="milestoneList">
									
									
								</div>
								
								
						</div>
						<!-- one milestone end -->
						
						
						
					</div>
			</div>
		</form>
	</div>
	
	
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
			{onSelect: function(dateText,inst) {$('#endDate').datepicker('option','minDate',new Date(dateText.replace('/','-')));}
			}
			

			);

			$("#endDate").datepicker(
			{onSelect: function(dateText, inst) {$('#startDate').datepicker('option', 'maxDate', new Date(dateText.replace('/','-')));}
			});
		}); 
							
		$("#add").click(function(){
			var str = "<div class='col-sm-12'><h3><b>";
			var title = $("#milestoneName").val();
			var str2 = "</b><button type='button' class='close'><img style='height: 24px; width: 24px' class='img-responsive remove' src='<%=request.getContextPath()%>/img/garbage.png'></button></h3><div><table><tr><td>開始時間 </td><td>";
			var startdate = $("#startDate").val();
			var str3 = "</td><td>結束時間</td><td>";
			var enddate = $("#endDate").val();
			var str4 = "</td></tr></table></div></div><input type='hidden' name='milestoneName' value='";
			var str5 = "'><input type='hidden' name='startDate' value='";
			var str6 = "'><input type='hidden' name='endDate' value='";
			var str7 = "'>";
			
			var i = str + title + str2 + startdate + str3 + enddate + str4 + title + str5 + startdate + str6 + enddate + str7;
			$("#milestoneList").append(i);
			
		});			
		 	
		</script>
	</body>
</html>
