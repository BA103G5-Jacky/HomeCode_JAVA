function addFavorite(index){
	var xhr = new XMLHttpRequest();
	//設定好回呼函數   
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				$('.addFavoriteArea')[index].setAttribute("style","display:none;");
				$('.removeFavoriteArea')[index].removeAttribute("style");
			} else {
				alert(xhr.status);
			}//xhr.status == 200
		}//xhr.readyState == 4
	};//onreadystatechange 

	//建立好Get連接
	var url =  "<%=request.getContextPath()%>/favoriteCase/favoriteCase.do?action=addFavorite&csNo=" + $(".fcCsNo")[index].value + "&mbNo="+ $(".fcMbNo")[index].value;
	xhr.open("Get", url, true);
	//送出請求 
	xhr.send(null);
	console.log(url);
}

function removeFavorite(index){
	var xhr = new XMLHttpRequest();
	
	//設定好回呼函數   
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				$('.addFavoriteArea')[index].removeAttribute("style");
				$('.removeFavoriteArea')[index].setAttribute("style","display:none;");
			} else {
				alert(xhr.status);
			}//xhr.status == 200
		}//xhr.readyState == 4
	};//onreadystatechange 

	//建立好Get連接
	var url =  "<%=request.getContextPath()%>/favoriteCase/favoriteCase.do?action=removeFavorite&csNo=" + $(".fcCsNo")[index].value + "&mbNo="+ $(".fcMbNo")[index].value;
	xhr.open("Get", url, true);
	//送出請求 
	xhr.send(null);
	console.log(url);
}