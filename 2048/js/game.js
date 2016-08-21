//添加键盘监听
$(document).keydown(function (e){
	  switch(e.keyCode){
       case 37:
    	   //向左
	   if(leftMove()){
		   if(!gameOver){
			 generateOneNumber();
			 isGameOver();
		   }
	   }
		   break;
	   case 38:
		   //向上
	   if(upMove()){
			 if(!gameOver){
			 generateOneNumber();
			 isGameOver();
		   }
	   }
		   break;
	   case 39:
		   //向右
		 if(rightMove()){			 
			if(!gameOver){
			 generateOneNumber();
			 isGameOver();
		   }
		 }
		   break;
	   case 40:
		   //向下
		  if(downMove()){
			if(!gameOver){
			 generateOneNumber();
			 isGameOver();
		   }
		  }
		   break; 
  }
});
//判断能否向下移动
function isCanMoveDown(){
	for(var i=2;i>0;i--){
		for(var j=0;j<4;j++){
			if(bord[i+1][j]==0 || bord[i+1][j]==bord[i][j] || bord[i][j]==0){
				return true;
			}
		}
	}
	return false;
}
function isDownZero(i,j,k){
	for(var l=k-1;l>i;l--){
		if(bord[l][j]!=0){
			return false;
		}
	}
	return true;
}
//向下移动动作
function downMove(){
	if(!isCanMoveDown){
		return false;
	}else{
		for(var i=2;i>=0;i--){
			for(var j=0;j<4;j++){
				if(bord[i][j]!=0){
					for (var k=3;k>i;k--)
					{
						if(bord[k][j]==0 && isDownZero(i,j,k)){
							moveAnimation(i,j,k,j);
							bord[k][j]=bord[i][j];
							bord[i][j]=0;
						}else if(bord[k][j]==bord[i][j]&& isDownZero(i,j,k)){
							moveAnimation(i,j,k,j);
							bord[k][j]+=bord[i][j];
							score+=bord[k][j];
							upScore();
							bord[i][j]=0;
						}
					}
				}
			}
		}
		setTimeout("upBorderCell();",200);
		return true;
	}
}
//判断能否向右移动
function isCanMoveRight(){
	for(var i=0;i<4;i++){
		for(var j=3;j>0;j--){
			if(bord[i][j-1]==0 || bord[i][j-1]==bord[i][j]||bord[i][j]==0){
				return true;
			}
		}
	}
	return false;
}
//右移动作
function rightMove(){
   if(!isCanMoveRight()){
		return false;
   }else{
	   for(var i=0;i<4;i++){
			for( var j=2;j>=0;j--){
				if(bord[i][j]!=0){
					for(var k=3;k>j;k--){
						if(bord[i][k]==0 && isRightZero(i,j,k)){
							moveAnimation(i,j,i,k);
							bord[i][k]=bord[i][j];
							bord[i][j]=0;
						}else if(bord[i][k]==bord[i][j]&& isRightZero(i,j,k)){
							moveAnimation(i,j,i,k);
							bord[i][k]+=bord[i][j];
							score+=bord[i][k];
							upScore();
							bord[i][j]=0;
						}
					}
				}
			}
	   }
	   setTimeout("upBorderCell();",200);
	   return true;
   }
}
function isRightZero(i,j,k){
	for(var l=k-1;l>j;l--){
	   if(bord[i][l]!=0){
			return false;
	   }
	}
	return true;
}
//判断能否向上移动
function isCanMoveUp(){
	for(var j=0;j<4;j++){
		for(var i=1;i<4;i++){
			if(bord[i-1][j]==0||bord[i-1][j]==bord[i][j]||bord[i][j]==0){
				return true;
			}
		}
	}
	return false;
}
//向上移动的动作
function upMove(){
   if(!isCanMoveUp()){
		return false;
   }else{
	   for(var j=0;j<4;j++){
			for(var i=1;i<4;i++){
				if(bord[i][j]!=0){
					for(var k=0;k<i;k++){
						//isZero(i,k,j)判断从k到i之间的格子数字是否为0
						if(bord[k][j]==0&&isUpZero(i,k,j)){
						   moveAnimation(i,j,k,j);
						   bord[k][j]=bord[i][j];
						   bord[i][j]=0;
						}else if (bord[k][j]==bord[i][j] && isUpZero(i,k,j)){	
						   moveAnimation(i,j,k,j);
						   bord[k][j]+=bord[i][j];
						   score+=bord[k][j];
						   upScore();
						   bord[i][j]=0;
						}
					}
				}
			}
		}
		setTimeout("upBorderCell();",200);
		return true;
   }
}
function isUpZero(i,k,j){
	for(var l=k+1;l<i;l++){
		if(bord[l][j]!=0){
			return false;
		}
	}
	return true;
}
//左移动作
function leftMove(){
	if(!isCanMoveLeft()){
		return false;
	}else{
		for(var i=0;i<4;i++){
			for(var j=1;j<4;j++){
				if(bord[i][j]!=0){
					for(var k=0;k<j;k++){
						//isZero(i,k,j)判断从k到j之间的格子数字是否为0
						if(bord[i][k]==0&&isLeftZero(i,k,j)){
						   moveAnimation(i,j,i,k);
						   bord[i][k]=bord[i][j];
						   bord[i][j]=0;
						}else if (bord[i][k]==bord[i][j] && isLeftZero(i,k,j)){	
						   moveAnimation(i,j,i,k);
						   bord[i][k]+=bord[i][j];
						   score+=bord[i][k];
						   upScore();
						   bord[i][j]=0;
						}
					}
				}
			}
		}
		setTimeout("upBorderCell();",200);
		return true;
	}
}
//isZero(i,k,j)判断从k到j之间的格子数字是否为0
function isLeftZero(i,k,j){
	for ( var l=k+1;l<j;l++ ){
		if(bord[i][l]!=0){
			return false;
		}
	}
	return true;
}
//判断是否可以向左移动
function isCanMoveLeft(){
	for(var i=0;i<4;i++){
		for(var j=1;j<4;j++){
			if(bord[i][j-1]==0 || bord[i][j-1] == bord[i][j]||bord[i][j]==0){
				return true;
			}
		}
	}
	return false;
}