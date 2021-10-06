<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.timeline.model.*"%>
<%@ page import="com.cs.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.tool.TransUtil"%>
<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>
<% String csNo =((CaseVO) request.getAttribute("caseVO")).getCsNo(); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Timeline</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Floating button css - power by materializecss -->

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/materialize.css"
	media="screen,projection" />

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- Floating button css ..... end -->


<!-- # linking piont tab-icon-->
<link rel="icon" type="images/gif" href="<%=request.getContextPath()%>/img/hc_icon_w.png"
	sizes="16x16">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/animatecss/3.5.2/animate.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style-albe-timeline.css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style-timeline.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/timeline/css/timeline.css">
	
	<title>HomeCode!</title>

<style>
.fixed-action-btn{
	margin-bottom: 40px;
}
</style>

</head>
<body>
	

	<!-- ======================header-start================================= -->
	<jsp:include page="/loginHeader.jsp" />
	<!-- ======================header-end ================================== -->


	<div class="container mrg-top-15 mb20">
		<div class="row">
			<a href="<%=request.getContextPath()%>/case/case.do?csNo=${caseVO.csNo}&action=caseInfo">
				<button type="button" class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4" id="caseInfo">案件詳情</button>
			</a>
			<c:if test="${mbNo.equals(caseVO.hirerNo) || mbNo.equals(caseVO.freelancerNo)}">
				<a href="<%=request.getContextPath()%>/milestone/milestone.do?action=getCase_milestone&csNo=${caseVO.csNo}">
					<button type="button" class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4" id="caseMilestone">案件進度</button>
				</a>
				<a href="<%=request.getContextPath()%>/timeline/timeline.do?csNo=${caseVO.csNo}&action=getCaseTimeline">
					<button type="button" class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4 active" id="caseTimeline">Timeline</button>
				</a>
			</c:if>
		</div>
		<!-- Row End -->
	</div>
	<!-- container mrg-top-15 width-75per End -->


<div class="hiw" style="padding-bottom:40px; min-height:80%;">	
		<div class="container mb20">
			<div class="col-xs-12 col-sm-12">
				<h2>${caseVO.csName}</h2>
			</div>
			
		</div>
		<div class="container mrg-top-15 width-75per">
			<div class="row">
				<!-- Timeline顯示區 -->
				<c:if test="true">
				<div class="container mrg-bottom-15 width-90per">
					<div class="row">
						<div id="myTimeline">
							<!-- 利用jsp：useBean去產生service, 再用傳統jsp方式去取得字串，並且把想要的值處理後print出來 -->
							<jsp:useBean id="timelineSvc1" scope="page" class="com.timeline.model.TimelineService" />
							<!-- 變數宣告：對顯示年月日作處理 -->
							<%  
								List<TimelineVO> list = (List<TimelineVO>) request.getAttribute("timelineList");
								int i = 0;
								String setYearTag;
								String showFullDate = null;
								String showYear;
								String showMonth;
								String showDate;
								ArrayList<String> yearRecord = new ArrayList<String>();//存放以顯示過的年份
								String hasShown = null; // 年份有沒有出現過
								Set<String> yearSet = new HashSet<String>();
								for(TimelineVO tlVO : list){
									String year = tlVO.getRecordDate().toString().substring(0, 4);
									yearSet.add(year);
								};
								TreeSet treeSet = new TreeSet(yearSet);//取得所有年分
								treeSet.comparator();
							%>
							<!-- 修改成動態產生：所有事件年份超連結 -->
							 <ul id="timeline-menu">
							 	<c:forEach var="year" items="<%=treeSet%>">
							 		<li><a href="#y${year}">${year}</a></li>
							 	</c:forEach>
							</ul> 

							<section id="timeline"> <!-- 回圈抓取資料 --> 
								<c:forEach var="timelineVO" items="${timelineList}" varStatus="j">
								<!-- 因為c 標籤利用EL取值不能抓到timelineVO的年份再做處理 所以就用jsp自己寫 -->
								<%		
										
										//從工具類別TransUtil去轉換字元，又因為我直接用c標籤拿不到list內的TimelineVO，所以改用jsp自己寫一個										
										showYear = TransUtil.sqlDateToStr(list.get(i).getRecordDate()).substring(0, 4);
										showMonth = TransUtil.sqlDateToStr(list.get(i).getRecordDate()).substring(5, 7);
										showDate = TransUtil.sqlDateToStr(list.get(i).getRecordDate()).substring(8, 10);
										//String showFullDate=null;	
										//給月或日補上0，如果只有個位數的話
										if (showMonth.length() < 2) {
											showFullDate = showYear + "-" + "0" + showMonth + "-" + showDate;
										} else if (showDate.length() < 2) {
											showFullDate = showYear + "-" + showMonth + "-" + "0" + showDate;
										} else {
											showFullDate = showYear + "-" + showMonth + "-" + showDate;
										}
										//log
										//System.out.println("[timeline_jsp]-[showFullDate]:"+showFullDate);

										//System.out.println("有沒有秀出正確年份：" + showYear);

										// 檢查年份是否已經出現過，讓畫面只會有一個年份顯示所有年份內的timeline note
										String checkTag = null; //check tag：年份有顯示過就標注yes:區域變數，所以每次進來都會變成null
										for (int y = 0; y < yearRecord.size(); y++) {
											if (yearRecord.get(y).equals(showYear)) {
												checkTag = "Yes";//年份有在yearRecord裡面才標注yes
											}
										}

										if (checkTag == null) {//如果沒有就把hasShown標注No
											yearRecord.add(showYear);
											hasShown = "No";
										}

										i++;
										//System.out.println("i是多少："+i);
								%>

								<!-- 用兩個if去判斷年份有沒有相同，如果有相同就不再顯示 NO的雙引號要記得跳脫字元 -->
								<c:if test="<%=yearRecord.contains(showYear)%>">
									<c:if test="<%=hasShown.equals(\"No\")%>">

										<div id="y<%=showYear%>" class="group<%=showYear%>"><%=showYear%></div>
										<%
											hasShown = "Yes";//顯示完成之後就改回原本的yes
										%>

									</c:if>
								</c:if>


								<article class=" inverted animated fadeInUp">
								<div class="panel">
									<div class="badge">
										<%=TransUtil.noMonToStrMon(showMonth)%>
										<br><%=showDate%>
									</div>
									
						<c:choose>
							<c:when test="${timelineVO.filePath eq 'milestone'}">
								<div class="panel-heading" style="color:#fff;">
									${timelineVO.noteTitle }
								</div>
							</c:when>
							
							<c:otherwise>
									<div class="panel-body">
										<!-- <img src="img/qrcode.png" width="150px" class="img-responsive"> -->
										<form action="<%=request.getContextPath() %>/timeline/timeline.do" method="post">
											<input type="hidden" name="csNo" value="${timelineVO.csNo}">
											<input type="hidden" name="noteNo" value="${timelineVO.noteNo}">
											<input type="hidden" name="action" value="delete_TimelineNote">
											<button type="submit" class="close">
												<img style="height:24px; width: 24px;" class="img-responsive" src="<%=request.getContextPath()%>/img/garbage.png">
											</button>
										</form>
										<!-- 檔按下載路徑要看到下載的那一支FileDownLoad的servlet -->
										<!-- 圖片下載連結 -->
										<a href="<%=request.getContextPath() %>/FileDownload${timelineVO.filePath }">
											<c:if test="${timelineVO.filePath != null}">
												<img style="height: 150px;" class="img-responsive" src="<%=request.getContextPath() %>${timelineVO.filePath}">
											</c:if>
										</a>
										<h2>
											${timelineVO.noteTitle }${request.getContentType()}
										</h2>



										<a href='#modal-id-edit<%=i%>' data-toggle="modal" target="_blank" class="mylink">修改</a>
										<!-- 內文顯示區 -->
										<p>
											<span class="more">${timelineVO.noteContent}</span> <br>

										</p>
										<!-- 檔案路徑下載連結 -->
										<c:if test="${timelineVO.filePath != null}">
											<a href="<%=request.getContextPath() %>/FileDownload${timelineVO.filePath}">檔案下載:${timelineVO.fileName }</a>
<%-- 											<a href="<%=request.getContextPath() %>${timelineVO.filePath}">檔案下載:${timelineVO.fileName }</a> --%>
										</c:if>
										<p>更新時間：${timelineVO.uploadTime }</p>
									</div>
									
							</c:otherwise>		
						</c:choose>			
								</div>
								</article>
								<!-- 修改timeline zone -->
								<div class="modal fade" id="modal-id-edit<%=i%>" >
	
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title">修改Timeline內容</h4>
									</div>
									<div class="modal-body">

										<form action="<%=request.getContextPath() %>/timeline/timeline.do" method="post" Name="tl-insert-form"
											enctype="multipart/form-data">
											<div class="">
												<label for="timeline-title"><h4>Timeline標題:</h4></label>
												<input type="text" class="" id="timeline-title" name="timeline-title" value=${timelineVO.noteTitle }>

											</div>
											<div class="">
												<label for="timeline-date"><h4>Timeline日期:</h4></label>
												<input type="date" class="" id="timeline-date" name="timeline-date" value="<%=showFullDate%>">
											</div>
											<div class="">
												<label for="timeline-content"><h4>Timeline內容:</h4></label>

												<textarea style="height: 20em;" type="text" class="" 
												id="timeline-content" name="timeline-content">${timelineVO.noteContent}</textarea>

											</div>
											<div class="">
												<label for="timeline-upload"></label>
												<input type="file" id="timeline-upload" name="timeline-updatefile">
												<input type="hidden" id="timeline-upload" name="uploadFile_CsNo" value=<%=csNo%>>
												<input type="hidden" id="timeline-upload" name="uploadFile_Name" value="${timelineVO.fileName }">
												<input type="hidden" id="timeline-upload" name="uploadFile_Path" value="${timelineVO.filePath}">
												<input type="hidden" id="timeline-upload" name="uploadFile_NoteNo" value="${timelineVO.noteNo }">
											</div>

											<div class="modal-footer">
												<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
												<input type="hidden" id="timeline-upload" name="action" value="update_TimelineNote">
												<button type="submit" class="btn btn-primary" style="background-color: #5e2b97; color: white;">儲存</button>
											</div>

										</form>
									</div>
								</div>

							</c:forEach> <!-- 							<div id="y2017" class="group2017">2017</div>
							<article id="a2013-1" class="inverted animated fadeInUp">
							<div class="panel">
								<div class="badge">
									20 <br>Jan
								</div>
								<div class="panel-body">
									<img src="img/girl.png" width="80px" class="img-responsive">
									<h2>歡迎使用Timeline記事本</h2>
									<p>Timeline記事本將會協助您專案開發的所有記事功能，舉凡已更新的進度日期會自動更新在Timeline中，您也可以自行加入與合作人的重要記事，幫助您專案的歷史記錄管理。</p>
								</div>
							</div>
							</article> <article class="animated fadeInUp">
							<div class="panel">
								<div class="badge">&nbsp;</div>
							</div>
							</article>
							<div class="clearfix" style="float: none;"></div> --> </section>
							<!-- #timeline End -->
						</div>
						<!-- Timeline End -->
						
					<c:if test="${caseVO.csState eq '進行中' }">
						<!-- add floating button here -->
						<div class="fixed-action-btn">
							<a href='#modal-id' data-toggle="modal"
								class="btn-floating btn-large waves-effect waves-light purple"><i
								class="material-icons">add</i></a>
						</div>
					</c:if>


						<div class="modal fade" id="modal-id">

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">新增Timeline內容</h4>
							</div>
							<div class="modal-body">

								<form action="<%=request.getContextPath() %>/timeline/timeline.do" method="post" Name="tl-insert-form"
									enctype="multipart/form-data">
									<div class="">
										<label for="timeline-title"><h4>Timeline標題:</h4></label> <input
											type="text" class="" id="add-timeline-title"
											name="timeline-title">

									</div>
									<div class="">
										<label for="timeline-date"><h4>Timeline日期:</h4></label> <input
											type="date" class="" id="add-timeline-date" name="timeline-date">
									</div>
									<div class="">
										<label for="timeline-content"><h4>Timeline內容:</h4></label>
										<textarea style="height: 20em;" type="text" class=""
											id="add-timeline-content" name="timeline-content"></textarea>

									</div>
									<div class="">
										<label for="timeline-upload"></label> <input type="file"
											id="timeline-upload" name="timeline-uploadfile">
											<input
											type="hidden" id="timeline-upload" name="uploadFile_CsNo"
											value=<%=csNo%>> <input type="hidden"
											id="timeline-upload" name="uploadFile_Name"
											value="fileNameTest"> <input type="hidden"
											id="timeline-upload" name="uploadFile_Path"
											value="fileNamePath">
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">關閉</button>
										<input type="hidden" id="timeline-upload" name="action"
											value="upload_TimelineNote">
										<button type="submit" class="btn btn-primary" style="background-color: #5e2b97; color: white;">儲存</button>
									</div>

								</form>
							</div>
							<!-- magic button   ============================================================= -->									
								<div class="mrg-top-15 form-group">	
									<button  onclick="inputInfo1()">一</button>
									<button  onclick="inputInfo2()">二</button>
									<button  onclick="inputInfo3()">三</button>
									<button  onclick="inputInfo4()">消</button>
								</div>
							<!-- magic button   ============================================================= -->
						</div>

					</div>
					<!-- Row End -->
				</div>
				<!-- container mrg-bottom-15 width-90per End -->
				</c:if>
				<!-- </div> -->
				<!-- Row End -->
			</div>
			<!-- container mrg-top-15 width-75per End -->
		</div>
		<!-- hiw End -->
	</div>
		<!-- footer-start ============================================================= -->
		<jsp:include page="/footer.jsp" />
		<!-- footer-end   ============================================================= -->

		<!-- script session -->

		<!-- floating button js start -->
		<!--Import jQuery before materialize.js-->
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/materialize.js"></script>
		<!-- floating button js end -->

		<!-- collapse article js start -->
		<script src="<%=request.getContextPath()%>/js/readmore-toggle.js"></script>
		<!-- collapse article js end -->

		<script src="https://cdn.jsdelivr.net/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<!-- magic button   ============================================================= -->		
		<script>
			function inputInfo1(){
	
			      document.getElementById("add-timeline-title").value="前端功能finish";
			      document.getElementById("add-timeline-date").value="2017-10-30";
			      document.getElementById("add-timeline-content").value="phase 1 done!!!!";
	
			    }
			
			function inputInfo2(){
	
			      document.getElementById("add-timeline-title").value="後端功能finish";
			      document.getElementById("add-timeline-date").value="2017-11-15";
			      document.getElementById("add-timeline-content").value="phase 2 done!!!!";
	
			    }
			function inputInfo3(){ 
	
			      document.getElementById("add-timeline-title").value="衝衝衝2.0完成";
			      document.getElementById("add-timeline-date").value="2017-11-30";
			      document.getElementById("add-timeline-content").value="phase 3 done!!!!";
	
			    }
			function inputInfo4(){ 
				
			      document.getElementById("add-timeline-title").value="更改專案功能";
			      document.getElementById("add-timeline-date").value="2017-11-16";
			      document.getElementById("add-timeline-content").value="取消手機端服務，加強前端功能，升級升級再升級";
	
			    }	
		
		
		</script>		
		<!-- magic button   ============================================================= -->	
</body>
</html>