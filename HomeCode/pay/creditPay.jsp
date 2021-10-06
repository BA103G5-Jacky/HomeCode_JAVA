<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信用卡付款</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
		<!-- # linking piont bootstrap-lightbox-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-lightbox/bootstrap-lightbox.css">
		<!--[if lt IE 9]>
					<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
					<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
		<!-- # linking piont tab-icon-->
		<link rel="icon" type="<%=request.getContextPath()%>/images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png" sizes="16x16">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/pay/css/card.css">
		<style>
        #cardForm {
            margin: 30px;
        }
        .cardInput {
            width: 200px;
            margin: 10px auto;
            display: block;
        }
		.Portfolio {
		    margin-top: 15px;
		}
    	</style>
	</head>
	<body>
	
	
<!-- ======================================================================================= -->
		
		<jsp:include page="/loginHeader.jsp" />
<!-- ========================================process==========================================-->
		
		<div class="container Portfolio">
			<h1>案件成立流程</h1>
			新增合約進度里程 >> <span style="font-size:20px; color:red;">信用卡付款</span>
			<!-- magic button   ============================================================= -->	
					<button type="button" onclick="inputInfo1()">乃乃</button>
			<!-- magic button   ============================================================= -->	
		</div> 	


		<div class="hiw">
			
			<div class="container Portfolio mrg-top-15 mrg-bottom-15">
				<div class="mrg-bottom-15">
			        <div class="card-wrapper mrg-top-15"></div>

			        <div class="form-container active">
			            <form action="<%=request.getContextPath()%>/case/case.do" method="post" id="cardForm">
			                <input placeholder="Card number" type="text" name="number" class="cardInput">
			                <input placeholder="Full name" type="text" name="name" class="cardInput">
			                <input placeholder="MM/YY" type="text" name="expiry" class="cardInput">
			                <input placeholder="CVC" type="text" name="cvc" class="cardInput">
			                <input type="hidden" name="csNo" value="${csNo}">
			                <input type="hidden" name="action" value="creditPay">
			                <button type="submit" class="cardInput btn-primary btn">確認付款</button>
			            </form>
			        </div>
			    </div>
			     	

			</div>
			
		</div><!-- /container -->
		
		
		
		
<!-- footer區 =========================================================== -->
		<jsp:include page="/footer.jsp" />
<!-- footer區結束 =========================================================== -->
		<script src="<%=request.getContextPath()%>/pay/js/jquery.min.js"></script>
	    <script src="<%=request.getContextPath()%>/pay/js/card.js"></script>
	    <script>
	        $('.active #cardForm').card({
	            container: $('.card-wrapper')
	        })
	    </script>
	    
	   	<!-- magic button   ============================================================= -->	
		<script>		
		function inputInfo1(){
		
			 document.getElementsByName("number")[0].value="4555 5555 6666 7777";
		     document.getElementsByName("name")[0].value="徐乃麟";
		     document.getElementsByName("expiry")[0].value="10/19";
		     document.getElementsByName("cvc")[0].value="987";
		
		    }
		
		</script> 
		<!-- magic button   ============================================================= -->	
		
</body>
</html>