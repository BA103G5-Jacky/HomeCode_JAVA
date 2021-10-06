
 
$(function() {
    $('[data-toggle="tooltip"]').tooltip();
})
$(function() {
    $('[data-toggle="popover"]').popover();
})

function postTimeInterval(postTime){
	var a = setInterval(getPostTime(postTime), 1000*60);
}

function getPostTime(postTime){
	var postTime2 = postTime/(60*1000);
	var today = new Date().getTime()/(60*1000);
	//和PO文當天相差的分鐘數
	var minutes = today-postTime2;
	var hours = minutes/60;
	var days =  minutes/60/24;
	var months = parseInt(minutes/60/24/30);
	days = parseInt(days - (months*30));
	hours = parseInt(hours - (months*30*24 + days*24));
	minutes = parseInt(minutes - (months*30*24*60 + days*60*24 + hours*60));
	
	if(months != 0 && days != 0 && hours != 0 && minutes != 0){
		$("#postTime").text(months + "個月前的發文");
	} else if(months == 0 && days != 0 && hours != 0 && minutes != 0){
		$("#postTime").text(days + "天前的發文");
	} else if (months == 0 && days == 0 && hours != 0 && minutes != 0) {
		$("#postTime").text(hours + "小時前的發文");
	} else if (months == 0 && days == 0 && hours == 0 && minutes != 0){
		$("#postTime").text(minutes + "分鐘前的發文");
	} else {
		$("#postTime").text("剛剛的發文");
	}
}