package cloudnote.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
public class VerifyCode {
	private String[] font_family={"宋体","华文楷体","黑体","微软雅黑","楷体_GB2312"};
	private String codes="23456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Random r=new Random();
	//private Color color=null;
	private Color backgroungColor=new Color(200,200,200);
	private String text=null;
	private int width=0;
	private int height=0;
	public VerifyCode(int width,int height){
		this.width=width;
		this.height=height;
	}
	private String getFamily(){
		return font_family[r.nextInt(font_family.length)];
	}
	private Color getcolor(){
		int red=r.nextInt(150);
		int green=r.nextInt(150);
		int blue=r.nextInt(150);
		Color color=new Color(red, green, blue);	
		return color;
	}
	private char[] createCode(){
		char[] code=new char[4];
		for(int i=0;i<4;i++){
			code[i]=codes.charAt(r.nextInt(codes.length()));
		}
		text=String.valueOf(code);
		return code;
		
	}
	public String getText(){
		return text;
	}
	public BufferedImage getCodeImage(){
		BufferedImage bf=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2=(Graphics2D) bf.getGraphics();
		g2.setColor(backgroungColor);
		g2.fillRect(0, 0, width, height);
		char[] code=createCode();
		for(int i=0;i<4;i++){
			g2.setColor(getcolor());
			g2.setFont(new Font(getFamily(),r.nextInt(3)+1, (height/3)*2));
			g2.drawString(String.valueOf(code[i]), (width/6)*(i+1), (height/3)*2);
			if(i>0&&i<4){
				g2.draw(new Line2D.Double(r.nextInt(width/3)+i*width/3, r.nextInt(height), r.nextInt(width/3)+i*width/3, r.nextInt(height)));
			}
		}
		return bf;
	}
	public void write(BufferedImage bf,OutputStream out) {
			try {
				ImageIO.write(bf, "JPEG",out);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	}
}
