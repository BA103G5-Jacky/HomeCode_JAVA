<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.DecimalFormat" %>
<!-- 燈箱排行榜=================================================================== -->
	<jsp:useBean id="rankSvc" class="com.rank.model.RankService"/>
	<jsp:useBean id="mbSvc" class="com.member.model.MemberService"/>
	<jsp:useBean id="csSvc" class="com.cs.model.CaseService"/>
	<jsp:useBean id="stSvc" class="com.skill_type.model.Skill_typeService"/>
	<jsp:useBean id="msSvc" class="com.member_skill.model.Member_skillService"/>
	<%
	DecimalFormat df = new DecimalFormat("###.0"); 
	pageContext.setAttribute("df", df);
	%>
	<div id="myCarousel" class="carousel slide rank" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target=".rank" data-slide-to="0" class="active"></li>
			<c:if test="${rankSvc.all.size()>5}">
				<li data-target="myCarousel" data-slide-to="1"></li>
			</c:if>
		</ol>
		<!-- Wrapper for slides -->
		<div class="carousel-inner pb40">
			<div class="item active">
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-12 central rankTitle">
							<h2>HomeCode! 排行榜</h2>
						</div>
					</div>
				</div>
				
				<div class="container search-area">
					<div class="row">
						<c:forEach var="rankVO" items="${rankSvc.all}" varStatus="i" begin="0" end="4">
							
							<div class='col-xs-12 ${(i.index == 0) ? "col-sm-offset-1" : ""} col-sm-2 central rank-card'>
								<c:choose>
									<c:when test="${i.index == 0 }">
										<img src="<%=request.getContextPath()%>/img/king.gif"
										class="photo-size margin01 rankImg mb20">
									</c:when>
									<c:when test="${i.index == 1 }">
										<img src="<%=request.getContextPath()%>/img/diamond.gif"
											class="photo-size margin01 rankImg mb20">
									</c:when>
									<c:when test="${i.index == 2 }">
										<img src="<%=request.getContextPath()%>/img/diamond3.gif"
											class=" photo-size margin01 rankImg mb20">
									</c:when>
									
									<c:otherwise>
										<img src="<%=request.getContextPath()%>/img/null.JPG"
											class=" photo-size margin01 rankImg mb20">
									</c:otherwise>
								</c:choose>
								
								<div class="inner" style='background-image: url(<%=request.getContextPath()%>/Image/Image.do?MBNO=${rankVO.getMbno()}&index=0)'></div>
								<br>
								<div class="central">
									<h3>
										<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${rankVO.getMbno()}&action=getMbInfo" class="purple"> 
										${mbSvc.getOneMember(rankVO.getMbno()).mbLstName}${mbSvc.getOneMember(rankVO.getMbno()).mbFstName}
										</a>
									</h3>
								</div>
								<div class="left">
									<div class="mrg-top-15" >
										<b>接案成功率</b>
										<span>${ (mbSvc.getOneMember(rankVO.mbno).csTimes== 0) ? "0": df.format(mbSvc.getOneMember(rankVO.mbno).csSuccessTimes*100/mbSvc.getOneMember(rankVO.mbno).csTimes)}%</span>
									</div>
									
									<div>
									  <div class="progress">
									    <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:${ (mbSvc.getOneMember(rankVO.mbno).csTimes== 0) ? "0": df.format(mbSvc.getOneMember(rankVO.mbno).csSuccessTimes*100/mbSvc.getOneMember(rankVO.mbno).csTimes)}%; background-color:#5e2b97;">
									      
									    </div>
									  </div>
									</div>
									
									<div>
										<b>平均接案評價</b>
									</div>
									<div>
										<div id="star_con" class="star-vote star-vote-f" >
											<span id="add_star" class="add-star"></span>
											<span id="del_star" class="del-star del-star-f"></span>
										</div>
									</div>
									<div  class="mrg-top-15">
										<ul class="list-inline">
											<c:forEach var="skillNo" items="${msSvc.searchmbno(rankVO.mbno)}" varStatus="k" begin="0" end="3">
												<li><a href="#" class="skill" data-toggle="tooltip"
										data-placement="top" title="${stSvc.getMbno(skillNo).skillDesc}">${stSvc.getMbno(skillNo).skillName}</a></li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>
		
			
			<c:if test="${rankSvc.all.size()>5}">
			<div class="item">
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-12 central rankTitle">
							<h2>HomeCode! 排行榜</h2>
						</div>
					</div>
				</div>
				<div class="container search-area">
					<div class="row">
						<c:forEach var="rankVO" items="${rankSvc.all}" varStatus="i" begin="5" end="9">

							<div class='col-xs-12 ${(i.index == 5) ? "col-sm-offset-1" : ""} col-sm-2 central rank-card'>
								<img src="<%=request.getContextPath()%>/img/null.JPG"
											class=" photo-size margin01 rankImg mb20">
								<div class="inner" style='background-image: url(<%=request.getContextPath()%>/Image/Image.do?MBNO=${rankVO.getMbno()}&index=0)'></div>
								<br>
								<div class="central">
									<h3>
										<a href="<%=request.getContextPath()%>/member/member.do?mbNo=${rankVO.getMbno()}&action=getMbInfo" class="purple"> 
										${mbSvc.getOneMember(rankVO.getMbno()).mbLstName}${mbSvc.getOneMember(rankVO.getMbno()).mbFstName}
										</a>
									</h3>
								</div>
								<div class="left">
									<div class="mrg-top-15" >
										<b>接案成功率</b>
										<span>${ (mbSvc.getOneMember(rankVO.mbno).csTimes== 0) ? "0": df.format(mbSvc.getOneMember(rankVO.mbno).csSuccessTimes*100/mbSvc.getOneMember(rankVO.mbno).csTimes)}%</span>
									</div>
									
									<div>
									  <div class="progress">
									    <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:${ (mbSvc.getOneMember(rankVO.mbno).csTimes== 0) ? "0": df.format(mbSvc.getOneMember(rankVO.mbno).csSuccessTimes*100/mbSvc.getOneMember(rankVO.mbno).csTimes)}%; background-color:#5e2b97;">
									      
									    </div>
									  </div>
									</div>
									
									<div>
										<b>平均接案評價</b>
									</div>
									<div>
										<div id="star_con" class="star-vote star-vote-f" >
											<span id="add_star" class="add-star"></span>
											<span id="del_star" class="del-star del-star-f"></span>
										</div>
									</div>
									<div>
										<ul class="list-inline">
											<c:forEach var="skillNo" items="${msSvc.searchmbno(rankVO.mbno)}" varStatus="k" begin="0" end="8">
												<li><a href="#" class="skill" data-toggle="tooltip"
										data-placement="top" title="${stSvc.getMbno(skillNo).skillDesc}">${stSvc.getMbno(skillNo).skillName}</a></li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			

			<!-- Left and right controls -->
			<a class="left carousel-control" href=".rank" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href=".rank" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
			</c:if>
		</div>
	</div>



	<!-- 燈箱排行榜結束=================================================================== -->
	
	<script>
		window.onload = function(){
			
			for(var i = 0; i<${rankSvc.all.size()}; i++){
				<c:forEach var="rkvo" items="${rankSvc.all}" varStatus="j">
					showStarsListforF(${csSvc.getMbFreelancerScoreAvg(rkvo.mbno)}, ${j.index});
				</c:forEach>
			}
			
		}
	</script>