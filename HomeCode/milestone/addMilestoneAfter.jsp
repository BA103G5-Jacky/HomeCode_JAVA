<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			td{
				width:150px;
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
	
	
	
	<div class="hiw" style="min-height:100%">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/milestone/milestone.do" name="form1">
			
				
			
			
			<div class="container Portfolio mrg-top-15 mrg-bottom-15">
				<div class="row mrg-top-15">
					<button type="button" class="btn btn-default"
						style="background-color: #9ee4a7; color: white;" id="add">新增里程</button>
					<input type='hidden' name='mbNo' value="${mbNo}">
					<input type='hidden' name='csNo' value="<%=request.getParameter("csNo") %>">
					<input type='hidden' name="action" value="insertAfter">
					<button type="submit" class="btn btn-default"
						style="background-color: #5e2b97; color: white;" id="add">確認新增</button>
					<!-- magic button -->
					<button type="button" class="btn btn-default" style="background-color: #eee; color: white;" onclick="inputInfo4();">2.0</button>
				</div>
				
				
				<div class="row cs-content" >						
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
									
<!-- 									<div class="form-group"> -->
<!-- 										<div class="row central"> -->
<!-- 											<div class="col-sm-2 control-label"> -->
<!-- 												<label for="milestoneName">案件金額</label> -->
<!-- 											</div> -->
<!-- 											<div class="col-sm-10"> -->
<!-- 												<input type="number" name="csPayment" class="form-control update" value=""/> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
									
<!-- 									<hr> -->
									<div style="padding:10px; color:red;">填入以下欄位，按下新增里程可新增新的進度里程</div>
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
			var str2 = "</b><button type='button' class='removeMilestone close' onclick='removeMilestone(event);' ><img style='height: 24px; width: 24px' class='img-responsive remove' src='<%=request.getContextPath()%>/img/garbage.png'></button></h3><div><table><tr><td>開始時間 </td><td>";
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
		function removeMilestone(e){
			var milestone = e.target.parentNode.parentNode.parentNode;
			document.getElementById("milestoneList").removeChild(milestone);
		}
		</script>
		
		<script>
			function inputInfo4(){

		   	  document.getElementById("milestoneName").value="前端網站改版2.0";
		      document.getElementById("startDate").value="11/16/2018";
		      document.getElementById("endDate").value="11/30/2018";
		    }
		</script>		
	</body>
</html>
