package com.game.shoot;

import java.util.Random;

public class Bee extends FlyingObject implements Award{

	private int speedY=2;
	private int speedX=2;
	private int awardType;
	public Bee(){
		this.image=ShootGame.bee;
		this.width=image.getWidth();
		this.height=image.getHeight();
		Random rand=new Random();
		this.x=rand.nextInt(ShootGame.WIDTH-this.width);
	    this.y=-height;
	    awardType=rand.nextInt(2);
	}
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return this.awardType;
	}
	@Override
	protected boolean outOfBounds() {
		// TODO Auto-generated method stub
		
		return this.y>ShootGame.HEIGHT;
	}
	@Override
	protected void step() {
		// TODO Auto-generated method stub
		if((this.x+this.width)>=ShootGame.WIDTH||(this.x<=0)){
			this.speedX=-this.speedX;
		}
		this.y+=this.speedY;
		this.x+=this.speedX;
	}

}
