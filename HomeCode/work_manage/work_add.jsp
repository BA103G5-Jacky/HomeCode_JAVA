<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>個人作品新增</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.js"></script>
		<!-- # linking piont local style css-->	
		<!-- check point1 -->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
		
		<!-- # linking piont tab-icon-->
		<!-- check point1 -->
		<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
			
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/work_manage/css/work.css">
	</head>
	<body>

	<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
	<!-- ======================header-end ================================== -->




<!-- ========================================作品資料修改區==========================================-->
			
	<div class="container mb20" style="padding-bottom:40px;">
		<div class="mrg-top-15 cs-content row">
			<div class=" row ">
				<div class="col-sm-10 col-sm-offset-1">
					<h2 style="margin-bottom: 20px; margin-top: 0px;"><label style="padding: 10px; color:#5e2b97;">新增作品集</label></h2>
				
					<hr style="margin: 5px;">
				</div>

				
				
				<div class="col-sm-10 col-sm-offset-1">
					<form id="addWorkForm" method="post" action="work_manage.do" enctype="multipart/form-data" name="insert_wk_form">
					
					<!-- 作品標題(I)====== ====== ====== ====== ====== ====== ====== ====== ====== ====== -->
						<label for="wkName">
							<span style="font-size: 24px; padding: 10px; padding-right: 0px;">
								作品標題
							</span>
							<c:if test="${not empty errorMsgs}">
								<span style="color:#f00; font-size:16px; padding: 10px;">${errorMsgs.get(0)}</span>
							</c:if>
								
							
						</label><br>
						
						<input type="text" name="wkName" id="wkName" placeholder=""
						class="no-border wkName" style="width:100%; height: 3em;"><br> 
					<!--  ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ -->
					
					<!-- 作品簡介(II)====== ====== ====== ====== ====== ====== ====== ====== ====== ===== -->	
						<label for="wkDecr">
							<span style="font-size: 24px; padding: 10px; padding-right: 0px;">作品簡介</span>
						</label><br>
					
						<textarea name="wkDecr" id="wkDecr" class="no-border"
						style="height: 12.5em; width:100%;">${(workVO.wkdecr == null) ? "": workVO.wkdecr}</textarea><br>				
					<!--  ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ -->
					
					<!-- 作品圖片上傳(III)====== ====== ====== ====== ====== ====== ====== ====== ======  -->
						
						
						<div class="">
							<label for="wkUpload">
							<span style="font-size: 24px; padding: 10px; padding-right: 0px;">作品縮圖</span>
						</label><br>
							
							<!-- preview zone -->
							<input type="file" class="upl" id="wkUpload" name="wkPic" style="width:100%;"> 
							<span>
								<img class="preview" style="max-width: 400px; max-height: 400px;">
							    <div class="size"></div>
							 </span>
							 <!--  ↑ ↑ ↑ ↑ ↑ --> 
					
							<!-- hidden zone -->
							<input type="hidden" name="action" value="addWork">
							<input type="hidden" name="mbNo" value="${mbNo}">
					
						</div>
					<!--  ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ --> 
						<br>
						<div class="">
							<button type="submit" class="btn btn-default" style="background-color: #5e2b97; color: white;" >傳送</button>
							<!-- magic button -->
							<button type="button" class="btn btn-default" style="background-color: #eee; color: white;" onclick="inputInfo1();">從1</button>
							<button type="button" class="btn btn-default" style="background-color: #eee; color: white;" onclick="inputInfo2();">從2</button>
						</div>
					</form>
				</div>

			</div>
		</div>


	</div>


	







<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
<!-- footer-end   ============================================================= -->
		
					<script>

						$(".upl").change(function (){

							function format_float(num, pos)
							{
								var size = Math.pow(10, pos);
								return Math.round(num * size) / size;
							}

							function preview(input) {

								if (input.files && input.files[0]) {
									var reader = new FileReader();

									reader.onload = function (e) {
										$('.preview').attr('src', e.target.result);
										var KB = format_float(e.total / 1024, 2);
										$('.size').text("檔案大小：" + KB + " KB");
									}

									reader.readAsDataURL(input.files[0]);
								}
							}

							$("body").on("change", ".upl", function (){
								preview(this);
							})

						})		

						$(document).ready(function(){
							$(".btn1").click(function(){
								$("p").slideToggle();
							});


						});

					</script>

<!-- floating button js start -->
		<!--Import jQuery before materialize.js-->
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="https://cdn.jsdelivr.net/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script>
function inputInfo1(){
	$('#wkName').val('萬穗貿易股份有限公司響應式網站設計');
	$('#wkDecr').text('萬穗貿易股份有限公司響應式網站設計完成，RWD方式的配置可以支援大部份的行動裝置，網頁皆有後台管理功能，只要登錄管理員帳號密碼後直接在要修改的位置旁按下編輯即可針對要修改的區塊設計新內容。');
    
    }
    
function inputInfo2(){
	$('#wkName').val('美華漆行響應式購物網站設計');
	$('#wkDecr').text('美華油漆購物網站除了採用響應式網頁設計外，在購物流程中增加了議價功能');
    

  }
</script>
	</body>
</html>