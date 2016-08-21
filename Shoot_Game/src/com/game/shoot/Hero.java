package com.game.shoot;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject{

	public int life;
	public int doubleFire;
	private BufferedImage[] images;
    private int index;
    public Hero(){
    	this.image=ShootGame.hero0;
		this.width=image.getWidth();
		this.height=image.getHeight();
		this.x=150;
	    this.y=400;
	    life=3;
	    doubleFire=0;
	    images=new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
	    index=0;
    }
	@Override
	protected boolean outOfBounds() {
		// TODO Auto-generated method stub
		return (this.x<-this.width/2)||
				(this.x+this.width/2)>ShootGame.WIDTH
				;
	}
	@Override
	protected void step() {
		// TODO Auto-generated method stub
		this.image=images[index++/10%images.length];
		
	}
	public Bullet[] shoot(){
		
		if(doubleFire>0){
			Bullet[] bs=new Bullet[2];
			bs[0]=new Bullet(this.x+this.width/2-2*ShootGame.bullet.getWidth(),
					this.y-ShootGame.bullet.getHeight());
			bs[1]=new Bullet(this.x+this.width/2+2*ShootGame.bullet.getWidth(),
					this.y-ShootGame.bullet.getHeight());
			doubleFire-=2;
			return bs;
		}else 
		{
			Bullet[] bs=new Bullet[1];	
			bs[0]=new Bullet(this.x+this.width/2,
					this.y-ShootGame.bullet.getHeight());
			return bs;
		}
    }
}
