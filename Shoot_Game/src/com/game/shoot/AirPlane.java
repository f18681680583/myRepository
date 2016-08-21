package com.game.shoot;

import java.util.Random;

public class AirPlane extends FlyingObject implements Enemy{

	private int speed=2;//ËÙ

	public AirPlane(){
		this.image=ShootGame.airPlane;
		this.width=image.getWidth();
		this.height=image.getHeight();
		this.x=(new Random()).nextInt(ShootGame.WIDTH-this.width);
	    this.y=-height;
	}
	@Override
	public int getStroe() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	protected boolean outOfBounds() {
		// TODO Auto-generated method stub
		return this.y>ShootGame.HEIGHT;
	}

	@Override
	protected void step() {
		// TODO Auto-generated method stub
		this.y=y+speed;
	}

	
}
