 function showStar(n){
	 var con_wid=document.getElementById("star_con").offsetWidth;
	 var del_star=document.getElementById("del_star");
	 console.log(con_wid);
	    
	 //星星移動的px
	 var del_move=(n*con_wid)/5;
	 del_star.style.backgroundPosition=-del_move+"px 0px";
	 del_star.style.left=del_move+"px";
}
 function showStarhAvg(n){
	 var con_wid3=document.getElementsByClassName("star-vote-havg")[0].offsetWidth;
	 var del_star3=document.getElementsByClassName("del-star-havg")[0];
	 console.log(con_wid3);
	    
	 //星星移動的px
	 var del_move3=(n*con_wid3)/5;
	 del_star3.style.backgroundPosition=-del_move3+"px 0px";
	 del_star3.style.left=del_move3+"px";
}
 function showStarfAvg(n){
	 var con_wid4=document.getElementsByClassName("star-vote-favg")[0].offsetWidth;
	 var del_star4=document.getElementsByClassName("del-star-favg")[0];
	 console.log(con_wid4);
	    
	 //星星移動的px
	 var del_move4=(n*con_wid4)/5;
	 del_star4.style.backgroundPosition=-del_move4+"px 0px";
	 del_star4.style.left=del_move4+"px";
}
 
 function showStarsList(score, index){
	 var con_wid=document.getElementsByClassName("star-vote")[index].offsetWidth;
	 var del_star=document.getElementsByClassName("del-star")[index];
	 console.log(con_wid);
	    
	 //星星移動的px
	 var del_move=(score*con_wid)/5;
	 del_star.style.backgroundPosition=-del_move+"px 0px";
	 del_star.style.left=del_move+"px";
 }
 
 function showStarsListforF(score, index){
	 var con_wid=document.getElementsByClassName("star-vote-f")[index].offsetWidth;
	 var del_star=document.getElementsByClassName("del-star-f")[index];
	 console.log(con_wid);
	    
	 //星星移動的px
	 var del_move=(score*con_wid)/5;
	 del_star.style.backgroundPosition=-del_move+"px 0px";
	 del_star.style.left=del_move+"px";
 }
 
 function showStarsListforH(score, index){
	 var con_wid2=120;
	 var del_star2=document.getElementsByClassName("del-star-h")[index];
	    
	 //星星移動的px
	 var del_move2=(score*con_wid2)/5;
	 del_star2.style.backgroundPosition=-del_move2+"px 0px";
	 del_star2.style.left=del_move2+"px";
 }