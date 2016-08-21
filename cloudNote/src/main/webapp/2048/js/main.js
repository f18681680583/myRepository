var bord=new Array();
var gameOver;
var score=0;
window.onload=function() {
   for(var i=0;i<4;i++){
	  bord[i]=new Array();
   }
   newgame();
}
function upBorad(){
	for(var i=0;i<4;i++){
		for(var j=0;j<4;j++){
			$("#grid-container").append('<div class="grid-cell" id="grid-cell-'+i+'-'+j+'"></div>');
		}
	}
}
function newgame(){
	 for(var i=0;i<4;i++){
		  bord[i]=new Array();
	   }
  gameOver=false;
  upBorad();
  for(var i=0;i<4;i++){
	   for(var j=0;j<4;j++){
		  bord[i][j]=0;
		  var gridCell = $("#grid-cell-" + i + "-" + j);
          //通过getPosTop()方法设置每个格子距顶端的距离
    	  gridCell.css("top", getPosTop(i, j));
    	  //通过getPosLeft()方法设置每个格子距左端的距离
          gridCell.css("left", getPosLeft(i, j));	  
	   }
   }
   upBorderCell();
   //在棋盘上随机生成两个数字 
   generateOneNumber();
   generateOneNumber();
}
function upBorderCell(){
	$(".number-cell").remove();
	for(var i=0;i<4;i++){
		for(var j=0;j<4;j++){
			$("#grid-container").append('<div class="number-cell" id="number-cell-'+i+'-'+j+'"></div>');
			var cell=$("#number-cell-"+i+"-"+j);
			//如果棋盘值为0，将对应的数字格设置宽高为0；
			if(bord[i][j]==0){
				cell.css("width","0px");
				cell.css("height","0px");
				cell.css("top",getPosTop(i,j)+50);
				cell.css("left",getPosLeft(i,j)+50);
				cell.text("");
			}//如果棋盘值不为0，将数字格高宽设为100px
			else{
				cell.text(bord[i][j]);
				cell.css("width","100px");
				cell.css("height","100px");
				cell.css("background-color",getNumberBackGroundColor(bord[i][j]));
                cell.css("color",getNumberColor(bord[i][j]));  
				cell.css("top",getPosTop(i,j));
				cell.css("left",getPosLeft(i,j));
			}
		}
	}
}
function getPosTop(i, j) {
    return 20 + i * 120;
}
function getPosLeft(i, j) {
	return 20 + j * 120;
}
//随机生成一个数并填在方格中
function generateOneNumber(){
	var num=Math.random()>0.5?2:4;
	var x=parseInt(Math.random()*4);
	var y=parseInt(Math.random()*4);
	while(bord[x][y]!=0){
		x=parseInt(Math.random()*4);
		y=parseInt(Math.random()*4);
	}
	bord[x][y]=num;
	numberAnimation(x,y,num);
}
//根据数字大小选择不同的字体颜色，
function getNumberColor(number) {
    if (number <= 4) {
        return "#776e65";
    }
    return "white";
}
//根据字体大小选择不能的背景颜色
function getNumberBackGroundColor(number){    
	switch (number) {
		case 2:return "#eee4da";break;
		case 4:return "#ede0c8";break;
		case 8:return "#f2b179";break;
		case 16:return "#f59563";break;
		case 32:return "#f67c5f";break;
		case 64:return "#f65e3b";break;
		case 128:return "#edcf72";break;
		case 256:return "#edcc61";break;
		case 512:return "#9c0";break;
		case 1024:return "#33b5e5";break;
		case 2048:return "#09c";break;
		case 4096:return "#a6c";break;
		case 8192:return "#93c";break;
	}
}
//游戏结束逻辑
function isGameOver(){
	if(!(isCanMoveLeft()||isCanMoveRight()||isCanMoveDown()||isCanMoveUp())){
		$(".g2048").append("<div class='gameOver'>"+"<p>本次得分</p>"+"<span>"+score+"</span>"+
			"<a href='javascript:restartgame();' id='restartgamebutton'>Restart</a></div>");
		gameOver=true;
	}

}
function upScore(){
	$("#score").text(score);
}
function restartgame(){
   $(".gameOver").remove();
   newgame();
}