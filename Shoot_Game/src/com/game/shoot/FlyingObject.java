package com.game.shoot;

import java.awt.image.BufferedImage;

public abstract class FlyingObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	protected  int  getX(){
		return this.x;
	}
	protected  int getY(){
		return this.y;
	}
	protected  void setX(int x){
		this.x=x;
	}
	protected  void setY(int y){
		this.y=y;
	}
	protected void setWidth(int width){
		this.width=width;
	}
	protected  void setHeight(int height){
		this.height=height;
	}
	protected int  getWidth(){
		return this.width;
	}
	protected int getHeight(){
		return this.height;
	}
	protected void setImage(BufferedImage image){
		this.image=image;
	}
	protected BufferedImage getImage(){
		return this.image;
	}
	/**
	 * 判断是否出界
	 * @return
	 */
	protected abstract boolean outOfBounds();
	protected abstract void step();//飞行物移动一步
	//飞行物是否被子弹击中
	protected  boolean shootBy(Bullet[] bt){
		for(Bullet b : bt){
		if(this.y+this.height>b.y&&
				this.y<b.y&&
				this.x<b.getX()&&
				this.x+this.width>b.getX()){
			return true;
		    }
	    }
		return false;
	}
}
