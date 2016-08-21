package com.game.shoot;

public class Bullet extends FlyingObject{

	private int speed=3;
	public Bullet(int x,int y){
		this.image=ShootGame.bullet;
		this.width=image.getWidth();
		this.height=image.getHeight();
		this.x=x;
	    this.y=y;
	}
	@Override
	protected boolean outOfBounds() {
		// TODO Auto-generated method stub
		return this.y<-this.height;
	}
	@Override
	protected void step() {
		// TODO Auto-generated method stub
		this.y-=this.speed;
	}
}
