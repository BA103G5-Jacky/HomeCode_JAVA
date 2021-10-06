<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="mbSvc" class="com.member.model.MemberService"/>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>視訊通話</title>
</head>
<body>
	<script>
		var member = '${(mbNo eq freelancerNo) ? mbSvc.getOneMember(hirerNo).mbUserName : mbSvc.getOneMember(freelancerNo).mbUserName}';
		function onVidyoClientLoaded(status){
			console.log("VidyoClient load state - " + status.state);
			if (status.state == "READY"){
				$('h1').html('正在與'+member+'視訊連線中...');
				VC.CreateVidyoConnector({
					viewId:"render",
					viewStyle:"VIDYO_CONNECTORVIEWSTYLE_Default",
					remoteParticipants:16,
					logFileFilter:"error",
					logFileName:"",
					userData:""
				}).then(function(vc){
					console.log("Create success");
					joinCall();
					$('h1').html('已與'+member+'視訊通話中');
				}).catch(function(error){
					
				});
			  }
		}

		function joinCall(){
			vidyoConnector.Connect({
				host:"prod.vidyo.io",
				//token:"cHJvdmlzaW9uAFNob25ANDUxYWNkLnZpZHlvLmlvADYzNzExMDI4Nzc4AAA0YTFlMTNiYzc3Yjc2NjhhZDMzNDU2ZmJlN2QzNTM5MWU1ODY5N2RkMDBmZWFjOGE3NjI4MDg2OWRjOTc3NmUyYzU2NDBjYzJkY2FlMjBkZWE3YWVjYmM0YmI1ZTYyMjE=",
				token:"cHJvdmlzaW9uAEphY2t5QDRkOTc4Ni52aWR5by5pbwA2MzY3NTcyNDQzNwAAZDI3YzkyNGU0MmEzMGY5MTExMmVlMGMyYmVlYjFlMWNkOWViZDNjZjgwNGY0OWEzMWZjYmM5ZTA5NjUxMDczNDhmM2Y1OTRhM2UxNDE2ZmY3ZDMyODZiYzU4MjljNzI4",
				displayName:"${mbUsrName}",
				resourceId:"${hirerNo}${freelancerNo}",
				onSuccess:function(){
					console.log("Connected!! Yay!");
				},
				onFailure:function(reason){
					console.error("connection failed");
				},
				onDisconnected:function(reason){
					console.log("disconnected - " + reason);
				}
			})
			
		}

	</script>
	
<!-- 	<button onclick="joinCall()">Connect</button> -->
	
	
	<script src="//static.vidyo.io/latest/javascript/VidyoClient/VidyoClient.js?onload=onVidyoClientLoaded"></script>
	<h1></h1>
	<div style="outline:0px solid red; height:90%;" id="render"></div>

	
	
	
	<script src="https://code.jquery.com/jquery.js"></script>
</body>

</html>
