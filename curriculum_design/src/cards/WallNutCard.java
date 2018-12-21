package cards;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.NutJFrame;
import makelabel.MakeLabel;
import plants.Nut;
import plants.PeaShooter;

public class WallNutCard extends CardforNut {
	private static ImageIcon wallnutCardPic = new ImageIcon (WallNutCard.class.getResource("/images/potato_on.jpg"));
	private static ImageIcon wallnutMovePic = new ImageIcon (WallNutCard.class.getResource("/images/A_pic/A_Wallnut.png"));
	
	public WallNutCard(int x, int y,int speed,JFrame frame) {
		super(x,y,speed,frame);
		// TODO Auto-generated constructor stub
		//this.x = 360;
		//this.y = -2;
		this.width = 100;
		this.height = 100;
		this.originPoint = new Point(this.x-width/2,this.y-height/2);
		this.frozenTime = 7000;
		this.costEnergy = 50;
		this.cardLabel = MakeLabel.makeLabel(wallnutCardPic, this.x, this.y,width, height);
		this.frame.getLayeredPane().add(this.cardLabel, new Integer(10));//�ڵ����ȼ�Ϊ6
		//this.realLabel = makeLabel(peashooterCardGif,x,)???//�������ֲ���а�gif����Ҳ��Ҫ���ƶ���ʱ����־�̬pngֲ��ʾ��ͼ
		this.moveLabel = MakeLabel.makeLabel(wallnutMovePic, this.originPoint, width, height);
		this.frame.getLayeredPane().add(this.moveLabel, new Integer(1));//�ڵ����ȼ�Ϊ1
		this.moveLabel.setVisible(false);//Ĭ�ϰ�͸��ʾ��ͼΪfalse
		this.cardLabel.addMouseListener(this);//??��֪���ɲ�����
		this.cardLabel.addMouseMotionListener(this);
	}
	

	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseMoved(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mousePressed(e);
	}


	@Override
	public void createPlant() {
		// TODO Auto-generated method stub
		//System.out.println(""+line_y+""+line_x);
		NutJFrame.plants[line_y][line_x] = new Nut(line_x*grassWidth+leftOffset,line_y*grassHeight+upOffset,this.frame);
	}

	
	
	
}
