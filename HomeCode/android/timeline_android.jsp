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
	




<div class="hiw" style="min-height:100%;">	
		
		<div class="container mrg-top-15 width-75per">
			<div class="row">
				<!-- Timeline????????? -->
				<c:if test="true">
				<div class="container mrg-bottom-15 width-90per">
					<div class="row">
						<div id="myTimeline">
							<!-- ??????jsp???useBean?????????service, ????????????jsp??????????????????????????????????????????????????????print?????? -->
							<jsp:useBean id="timelineSvc1" scope="page" class="com.timeline.model.TimelineService" />
							<!-- ?????????????????????????????????????????? -->
							<%  
								List<TimelineVO> list = (List<TimelineVO>) request.getAttribute("timelineList");
								int i = 0;
								String setYearTag;
								String showFullDate = null;
								String showYear;
								String showMonth;
								String showDate;
								ArrayList<String> yearRecord = new ArrayList<String>();//???????????????????????????
								String hasShown = null; // ????????????????????????
								Set<String> yearSet = new HashSet<String>();
								for(TimelineVO tlVO : list){
									String year = tlVO.getRecordDate().toString().substring(0, 4);
									yearSet.add(year);
								};
								TreeSet treeSet = new TreeSet(yearSet);//??????????????????
								treeSet.comparator();
							%>
							<!-- ??????????????????????????????????????????????????? -->
							 <ul id="timeline-menu">
							 	<c:forEach var="year" items="<%=treeSet%>">
							 		<li><a href="#y${year}">${year}</a></li>
							 	</c:forEach>
							</ul> 

							<section id="timeline"> <!-- ?????????????????? --> 
								<c:forEach var="timelineVO" items="${timelineList}" varStatus="j">
								<!-- ??????c ????????????EL??????????????????timelineVO????????????????????? ????????????jsp????????? -->
								<%		
										
										//???????????????TransUtil???????????????????????????????????????c???????????????list??????TimelineVO???????????????jsp???????????????										
										showYear = TransUtil.sqlDateToStr(list.get(i).getRecordDate()).substring(0, 4);
										showMonth = TransUtil.sqlDateToStr(list.get(i).getRecordDate()).substring(5, 7);
										showDate = TransUtil.sqlDateToStr(list.get(i).getRecordDate()).substring(8, 10);
										//String showFullDate=null;	
										//??????????????????0??????????????????????????????
										if (showMonth.length() < 2) {
											showFullDate = showYear + "-" + "0" + showMonth + "-" + showDate;
										} else if (showDate.length() < 2) {
											showFullDate = showYear + "-" + showMonth + "-" + "0" + showDate;
										} else {
											showFullDate = showYear + "-" + showMonth + "-" + showDate;
										}
										//log
										//System.out.println("[timeline_jsp]-[showFullDate]:"+showFullDate);

										//System.out.println("??????????????????????????????" + showYear);

										// ??????????????????????????????????????????????????????????????????????????????????????????timeline note
										String checkTag = null; //check tag??????????????????????????????yes:?????????????????????????????????????????????null
										for (int y = 0; y < yearRecord.size(); y++) {
											if (yearRecord.get(y).equals(showYear)) {
												checkTag = "Yes";//????????????yearRecord???????????????yes
											}
										}

										if (checkTag == null) {//??????????????????hasShown??????No
											yearRecord.add(showYear);
											hasShown = "No";
										}

										i++;
										//System.out.println("i????????????"+i);
								%>

								<!-- ?????????if??????????????????????????????????????????????????????????????? NO????????????????????????????????? -->
								<c:if test="<%=yearRecord.contains(showYear)%>">
									<c:if test="<%=hasShown.equals(\"No\")%>">

										<div id="y<%=showYear%>" class="group<%=showYear%>"><%=showYear%></div>
										<%
											hasShown = "Yes";//????????????????????????????????????yes
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
										<!-- ?????????????????????????????????????????????FileDownLoad???servlet -->
										<!-- ?????????????????? -->
										<a href="<%=request.getContextPath() %>/FileDownload${timelineVO.filePath }">
											<c:if test='${ timeline.fileName.contains(".jpg") || timeline.fileName.contains(".png") }'>
												<img style="height: 150px;" class="img-responsive" src="<%=request.getContextPath() %>${timelineVO.filePath}">
											</c:if>
										</a>
										<h2>
											${timelineVO.noteTitle }${request.getContentType()}
										</h2>



										<a href='#modal-id-edit<%=i%>' data-toggle="modal" target="_blank" class="mylink">??????</a>
										<!-- ??????????????? -->
										<p>
											<span class="more">${timelineVO.noteContent}</span> <br>

										</p>
										<!-- ???????????????????????? -->
										<c:if test="${ timelineVO.filePath != null}">
											<a href="<%=request.getContextPath() %>${timelineVO.filePath}">????????????:${timelineVO.fileName }</a>
										</c:if>
										<p>???????????????${timelineVO.uploadTime }</p>
									</div>
									
							</c:otherwise>		
						</c:choose>			
								</div>
								</article>
								<!-- ??????timeline zone -->
								<div class="modal fade" id="modal-id-edit<%=i%>" >
	
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title">??????Timeline??????</h4>
									</div>
									<div class="modal-body">

										<form action="<%=request.getContextPath() %>/timeline/timeline.do" method="post" Name="tl-insert-form"
											enctype="multipart/form-data">
											<div class="">
												<label for="timeline-title"><h4>Timeline??????:</h4></label>
												<input type="text" class="" id="timeline-title" name="timeline-title" value=${timelineVO.noteTitle }>

											</div>
											<div class="">
												<label for="timeline-date"><h4>Timeline??????:</h4></label>
												<input type="date" class="" id="timeline-date" name="timeline-date" value="<%=showFullDate%>">
											</div>
											<div class="">
												<label for="timeline-content"><h4>Timeline??????:</h4></label>

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
												<button type="button" class="btn btn-default" data-dismiss="modal">??????</button>
												<input type="hidden" id="timeline-upload" name="action" value="update_TimelineNote">
												<button type="submit" class="btn btn-primary" style="background-color: #5e2b97; color: white;">??????</button>
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
									<h2>????????????Timeline?????????</h2>
									<p>Timeline????????????????????????????????????????????????????????????????????????????????????????????????????????????Timeline???????????????????????????????????????????????????????????????????????????????????????????????????</p>
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
					
							
					<c:if test="${caseVO.csState eq '?????????'}">
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
								<h4 class="modal-title">??????Timeline??????</h4>
							</div>
							<div class="modal-body">

								<form action="<%=request.getContextPath() %>/timeline/timeline.do" method="post" Name="tl-insert-form"
									enctype="multipart/form-data">
									<div class="">
										<label for="timeline-title"><h4>Timeline??????:</h4></label> <input
											type="text" class="" id="timeline-title"
											name="timeline-title">

									</div>
									<div class="">
										<label for="timeline-date"><h4>Timeline??????:</h4></label> <input
											type="date" class="" id="timeline-date" name="timeline-date">
									</div>
									<div class="">
										<label for="timeline-content"><h4>Timeline??????:</h4></label>
										<textarea style="height: 20em;" type="text" class=""
											id="timeline-content" name="timeline-content"></textarea>

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
											data-dismiss="modal">??????</button>
										<input type="hidden" id="timeline-upload" name="action"
											value="upload_TimelineNote">
										<button type="submit" class="btn btn-primary" style="background-color: #5e2b97; color: white;">??????</button>
									</div>

								</form>
							</div>
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
		
</body>
</html>