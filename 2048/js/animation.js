function numberAnimation(x,y,num){
	var numberCell = $("#number-cell-" + x + "-" + y);
	//设置当前的数字格的背景色和前景色及数字值
	numberCell.css("background-color",getNumberBackGroundColor(num));
	numberCell.css("color",getNumberColor(num));
	numberCell.text(num);
	//设置当前的数字格的显示动画
	numberCell.animate({
		width: "100px",
		height: "100px",
		top: getPosTop(x,y),
		left: getPosLeft(x,y)
	},50);
}
//移动动画
function moveAnimation(i,j,x,y){
	$("#number-cell-"+i+"-"+j).animate({
		top: getPosTop(x,y),
		left: getPosLeft(x,y)
	},200);
}