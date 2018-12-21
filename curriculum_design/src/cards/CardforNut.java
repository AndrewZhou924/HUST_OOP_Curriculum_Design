package cards;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bullet.Bullet;
import car.Car;
import controller.FlowSunController;
import controller.ZombieController;
import game.GameJFrame;
import plants.PeaShooter;
import plants.Plant;

import plants.Potato;
import plants.SunFlower;
import sun.Sun;
import zombies.Zombie;

public class CardforNut implements MouseMotionListener, MouseListener{
	protected int width,height;//卡片图片宽高
	protected int x,y;//屏幕坐标
	protected int line_x,line_y;//对应的草地行数列数，x：0~8，y：0~4
	protected static int leftOffset = 254;
	protected static int upOffset = 80;
	protected static int grassWidth = 81;
	protected static int grassHeight = 95;
	protected long frozenTime;
	protected long beginTime;
	protected int costEnergy;
	protected Point originPoint;
	protected JFrame frame;
	protected boolean isPress;
	protected boolean isGrid;//判断是落在一个格子上
	protected JLabel cardLabel;
	protected JLabel moveLabel;//半透明示意png
	protected int speed;
	
	
	protected static ArrayList cards = new ArrayList<CardforNut>(); 
	
	public CardforNut(int x, int y,int speed,JFrame frame) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.frame = frame;
		this.beginTime = System.currentTimeMillis();		
		this.isPress = false;//初始化为false
		this.isGrid = false;
		this.speed = speed;
		cards.add(this);
		
		new Thread(new moveCard()).start();
		//new Thread(new CardClickedThread()).start();
	}
	
	class moveCard implements Runnable
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(x>=300)
			{
				try {
					Thread.sleep(20);
					changeLocation(x-speed,y);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			cardLabel.setVisible(false);
			cards.remove(this);
		}
		
	}
	
	public void changeLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.cardLabel.setLocation(this.x,this.y);
	}
	
	public void createPlant()
	{
		
	};
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override//在子类里重写 因为要设置static的label//貌似又不用
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.isPress = true;
		moveLabel.setBounds(e.getX()+this.x-width/2,e.getY()+this.y-height/2,width,height);
		moveLabel.setVisible(true);
	}

	@Override//也要在子类里重写 
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		/*int x = (e.getX() - 254) / 81;
		int y = (e.getY() - 96) / 95;
		
		if(this.type>0&&x >= 0 && x < 9 && y >= 0 && y < 5 && e.getX() >= 254 && e.getX() <= 982
				&& e.getY() >= 96 && e.getY() <= 572 && GameJFrame.plants[y][x]==null)
		{
			this.frame.SetCount(this.frame.getCount()-this.costEnergy);
			this.beginTime=System.currentTimeMillis();
			GameJFrame.plants[y][x] = new ?
		
		}
		
		
		*/
		this.line_x = (e.getX()+this.x-this.width/2 - leftOffset) / grassWidth;
		this.line_y = (e.getY()+this.y-this.height/2 - upOffset) / grassHeight;
		
		if(this.isPress && this.line_x >= 0 && line_x < 9 && line_y >= 0 && line_y < 5 && GameJFrame.plants[line_y][line_x]==null)
		{
			//this.frame.SetCount(this.frame.getCount()-this.costEnergy);
			//this.beginTime=System.currentTimeMillis();
			//System.out.println(""+this.line_x+" "+this.line_y);
			this.createPlant();
		}
		
		this.isGrid = false;//点击重置为false
		this.isPress = false;
		this.moveLabel.setVisible(false);
		this.cardLabel.setVisible(false);
		cards.remove(this);
		//this.movePoint = this.originPoint;
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		this.moveLabel.setBounds((int)e.getX()+this.x-this.width/2, (int)e.getY()+this.y-height/2, width, height);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		this.frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.frame.setCursor(Cursor.getDefaultCursor());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
