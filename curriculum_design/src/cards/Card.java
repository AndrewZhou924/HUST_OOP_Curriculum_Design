package cards;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Container;
import java.awt.Cursor;
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

public abstract class Card implements MouseMotionListener, MouseListener{
	protected int width,height;//��ƬͼƬ���
	protected int x,y;//��Ļ����
	protected int line_x,line_y;//��Ӧ�Ĳݵ�����������x��0~8��y��0~4
	protected static int leftOffset = 254;
	protected static int upOffset = 96;
	protected static int grassWidth = 81;
	protected static int grassHeight = 95;
	protected long frozenTime;
	protected long beginTime;
	protected int costEnergy;
	protected Point movePoint;
	protected Point originPoint;
	protected GameJFrame frame;
	protected boolean isPress;
	protected boolean isGrid;//�ж�������һ��������
	protected JLabel cardLabel;
	protected JLabel moveLabel;//��͸��ʾ��png
	
	public Card(GameJFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		this.beginTime = System.currentTimeMillis();		
		this.isPress = false;//��ʼ��Ϊfalse
		this.isGrid = false;
		
		this.cardLabel.addMouseListener(this);//??��֪���ɲ�����
		this.cardLabel.addMouseMotionListener(this);
		//new Thread(new CardClickedThread()).start();
	}
	
	public boolean validRange(int x, int y)
	{
		if(x >=this.x&&x<=this.x+width&&y>=this.y&&y<=this.y+height&&notFrozen()&&!notAfford())
		{
			return true;
		}
		return false;
		
	}
	
	public boolean notFrozen()
	{
		if(System.currentTimeMillis() - beginTime>this.frozenTime)
		{
			return true;
		}
		return false;
	}
	
	public boolean notAfford()
	{
		if(this.frame.getCount()>this.costEnergy)
		{
			return false;
		}
		return true;
	}
	
	public abstract void createPlant();
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override//����������д ��ΪҪ����static��label//ò���ֲ���
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(validRange(e.getX(),e.getY()))
		{
			this.isPress = true;
			moveLabel.setBounds(e.getX(),e.getY(),width,height);
			moveLabel.setVisible(true);
		}
	}

	@Override//ҲҪ����������д 
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
		this.line_x = (e.getX() - leftOffset) / grassWidth;
		this.line_y = (e.getY() - upOffset) / grassHeight;
		
		if(this.isPress && this.line_x >= 0 && line_x < 9 && line_y >= 0 && line_y < 5 && GameJFrame.plants[line_y][line_x]==null)
		{
			this.frame.SetCount(this.frame.getCount()-this.costEnergy);
			this.beginTime=System.currentTimeMillis();
			this.createPlant();
		}
		
		this.isGrid = false;//�������Ϊfalse
		this.isPress = false;
		this.moveLabel.setVisible(false);
		this.movePoint = this.originPoint;//����Ϊԭʼ��
		//this.movePoint = this.originPoint;
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		this.movePoint = e.getPoint();
		this.moveLabel.setBounds((int)movePoint.getX()-width/2, (int)movePoint.getY()-height/2, width, height);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(validRange(e.getX(),e.getY()))//����Ƶ���Ƭ�Ϸ�ʱ��Ϊ��
		{
			this.frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else 
		{
			this.frame.setCursor(Cursor.getDefaultCursor());
		}
	}

}
