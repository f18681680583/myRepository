package com.game.shoot;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class ShootGame extends JPanel{

	public static final int WIDTH=400;//宽
	public static final int HEIGHT=654;//高
	public static BufferedImage backGround;//背景
	public static BufferedImage start;
	public static BufferedImage pause;
	public static BufferedImage gameOver;
	public static BufferedImage airPlane;
	public static BufferedImage bee;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage bullet;
	private Hero hero=new Hero();;
	private FlyingObject[] flyings=null;
	private Bullet[] bullets=null;
	private int flyEnterIndex=0;
	private int scores=0;
	State state=State.FRIST;
	ShootGame(){
		flyings=new FlyingObject[2];
	    flyings[0]=new AirPlane();
	    flyings[1]=new Bee();
	    bullets=new Bullet[1];
	    bullets[0]=new Bullet(hero.x+hero.width/2,hero.y-bullet.getHeight());
		
	}
	static {
		try{
			backGround=ImageIO.read(new File("img//background.png"));
			start=ImageIO.read(new File("img//start.png"));
			pause=ImageIO.read(new File("img//pause.png"));
			gameOver=ImageIO.read(new File ("img//gameover.png"));
			airPlane=ImageIO.read(new File("img//airplane.png"));
			bee=ImageIO.read(new File("img//bee.png"));
			hero0=ImageIO.read(new File("img//hero0.png"));
			hero1=ImageIO.read(new File("img//hero1.png"));
			bullet=ImageIO.read(new File("img//bullet.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void action(){
		MouseListener ml=new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(state==State.FRIST)
				{state=State.BEGIN;}
				if(state==State.OVER){
					hero=new Hero();
					scores=0;
					flyings=new FlyingObject[0];
					bullets=new Bullet[0];
					state=State.FRIST;
					
				}
			}
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(state==State.BEGIN||state==State.PAUSE)
					{state=State.START;}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if(state==State.START)
				{state=State.PAUSE;}
			}
			
		};
		MouseMotionListener mml=new MouseMotionAdapter(){

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				if(state==State.BEGIN||state==State.START){
				hero.setX(e.getX()-hero.image.getWidth()/2);
				hero.setY(e.getY()-hero.image.getHeight()/2);
				}
			}
			
		};
		this.addMouseListener(ml);
		this.addMouseMotionListener(mml);
		Timer timer=new Timer();
		int intevel=10;
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(hero.life<=0){state=State.OVER;}
				switch(state){
				case FRIST:
					repaint();break;
				case BEGIN:
				case START:
					enterAction();
					moveAction();
					shootAction();
					outOfBoundAction();
					shootByAction();
					heroShootByFly();
					repaint();break;
				case PAUSE:
					repaint();break;
				case OVER:
					repaint();break;
				}
			}	
		},intevel,intevel);	
	}
	public void heroShootByFly(){
		for(int i=0;i<flyings.length;i++){
			FlyingObject fly=flyings[i];
			if(((hero.getX()>fly.getX()&&
			   hero.getX()<fly.getX()+fly.getWidth())||
					(hero.getX()+hero.getWidth()>fly.getX()&&
					hero.getX()+hero.getWidth()<fly.getX()+fly.width))&&
			   hero.getY()<fly.getY()+fly.getHeight()&&
			   (hero.getY()+hero.getHeight())>fly.getY()){
				hero.life-=1;
				hero.doubleFire=0;
				flyings[i]=flyings[flyings.length-1];
				flyings[flyings.length-1]=fly;
				flyings=Arrays.copyOf(flyings, flyings.length-1);
		}
	}
}
	public void paintScore(Graphics g){
		Font font=new Font(Font.SANS_SERIF,Font.BOLD,15);
		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString("SCORE:"+this.scores,10,20);
		g.drawString("LIFE:"+hero.life,10,40);
	}
	private int shootIndex=0;
	public void shootAction(){
		shootIndex++;
		if(shootIndex>30){
			shootIndex=0;
			Bullet[] bul=hero.shoot();
			bullets=Arrays.copyOf(bullets, bullets.length+bul.length);
			System.arraycopy(bul, 0, bullets, bullets.length-bul.length, bul.length);
		}
	}
	public void moveAction(){
		hero.step();
		for(FlyingObject fy : flyings){
			fy.step();
		}
		for(Bullet bt:bullets){
			bt.step();
		}
	}
	public void shootByAction(){
		int index=-1;
		for(int i=0;i<flyings.length ;i++){
			if(flyings[i].shootBy(bullets)){
				index=i;
				break;
			}
		}
		if(index!=-1){
			FlyingObject one =flyings[index];
			FlyingObject temp =flyings[index];
			flyings[index]=flyings[flyings.length-1];
			flyings[flyings.length-1]=temp;
			flyings=Arrays.copyOf(flyings,flyings.length-1);
			if(one instanceof Bee){
				Bee one2=(Bee)one;
				int x=one2.getType();
				switch(x){
				case 0:
					hero.life+=1;break;
				case 1:
					hero.doubleFire+=20;break;
				}
			}else {
				AirPlane one1=(AirPlane)one;
				scores=scores+one1.getStroe();
			}
		}
	}
	public void outOfBoundAction(){
		int index=0;
		FlyingObject[] fly=new FlyingObject[flyings.length];
		for(int i = 0;i<flyings.length;i++){
			if(!flyings[i].outOfBounds()){
				fly[index++]=flyings[i];
			}
		}
		flyings=Arrays.copyOfRange(fly,0,index);
		index=0;
		Bullet[] bul=new Bullet[bullets.length];
		for(int i=0;i<bullets.length;i++){
			if(!bullets[i].outOfBounds()){
			    bul[index++]=bullets[i];
			}
		}
		bullets=Arrays.copyOf(bul, index);
	}
	private void enterAction() {
		// TODO Auto-generated method stub
		flyEnterIndex++;
		if(flyEnterIndex>40){
			FlyingObject obj=nextOne();
			flyings=Arrays.copyOf(flyings, flyings.length+1);
			flyings[flyings.length-1]=obj;
			flyEnterIndex=0;
		}
		
	}
    private FlyingObject nextOne() {
		// TODO Auto-generated method stub
    	Random rand=new Random();
		int type=rand.nextInt(20);
		if(type==0){
			return new Bee();
		}else{
			return new AirPlane();
		}
	}

	public void paint(Graphics g){
		switch(state){
		case FRIST:
			g.drawImage(backGround, 0, 0,null);
			paintScore(g);
			paintHero(g);
			g.drawImage(start, 0, 0,null);
			break;
		case BEGIN:
			g.drawImage(backGround, 0, 0,null);	
			paintHero(g);
			paintFlyingObjects(g);
			paintBullets(g);
			paintScore(g);
			break;
		case START:
			g.drawImage(backGround, 0, 0,null);	
			paintHero(g);
			paintFlyingObjects(g);
			paintBullets(g);
			paintScore(g);
			break;
		case PAUSE:
			g.drawImage(backGround, 0, 0,null);	
			paintHero(g);
			paintFlyingObjects(g);
			paintBullets(g);
			paintScore(g);
			g.drawImage(pause, 0, 0, null);
			break;
		case OVER:
			g.drawImage(backGround, 0, 0,null);	
			paintHero(g);
			paintFlyingObjects(g);
			paintBullets(g);
			paintScore(g);
			g.drawImage(gameOver, 0, 0, null);
			break;
		}
		
	}
	public void paintHero(Graphics g){
		g.drawImage(hero.image, hero.x, hero.y, null);
	}
	public void paintFlyingObjects(Graphics g){
		for(FlyingObject flyobj : flyings){
			g.drawImage(flyobj.image, flyobj.x, flyobj.y,null);
		}
	}
	public void paintBullets(Graphics g){
		for(Bullet bl : bullets){
			g.drawImage(bl.image, bl.x,bl.y,null);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame("fly");
		ShootGame game=new ShootGame();
		frame.add(game);
		//frame.setAlwaysOnTop(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Component c=new com();
		c.setName("退出");
		
		WindowListener  wl=new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				//super.windowClosing(e);
				int exi=JOptionPane.showConfirmDialog(c, "是否退出游戏？");
				if (exi==JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
			
		};
		frame.addWindowListener(wl);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
		game.action();
	}
	private FlyingObject[] getFly() {
		// TODO Auto-generated method stub
		Random rand =new Random();
		int x=rand.nextInt(10);
		FlyingObject fly;
		if(x==0){
		   fly= new Bee();
		}else{
			fly= new AirPlane();
		}
		FlyingObject[] flys=new FlyingObject[flyings.length+1];
		for(int i=0;i<flyings.length;i++){
			flys[i]=flyings[i];
		}
		flys[flyings.length]=fly;
		return flys;
	}	
}
