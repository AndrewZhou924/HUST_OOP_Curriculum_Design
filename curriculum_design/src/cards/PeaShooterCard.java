//package cards;
//
//import java.awt.Point;
//import java.awt.event.MouseEvent;
//
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//import game.NutJFrame;
//import makelabel.MakeLabel;
//import plants.PeaShooter;
//
//public class PeaShooterCard extends CardforNut {
//	private static ImageIcon peashooterCardPic = new ImageIcon (PeaShooterCard.class.getResource("/images/card_norbeen_on.jpg"));
//	private static ImageIcon peashooterMovePic = new ImageIcon (PeaShooterCard.class.getResource("/images/A_pic/A_Peashooter.png"));
//	
//	public PeaShooterCard(int x, int y,JFrame frame) {
//		super(x,y,frame);
//		// TODO Auto-generated constructor stub
//		//this.x = 360;
//		//this.y = -2;
//		this.width = 100;
//		this.height = 100;
//		this.originPoint = new Point(this.x-width/2,this.y-height/2);
//		this.frozenTime = 7000;
//		this.costEnergy = 50;
//		this.cardLabel = MakeLabel.makeLabel(peashooterCardPic, this.x, this.y,width, height);
//		this.frame.getLayeredPane().add(this.cardLabel, new Integer(9));//�ڵ����ȼ�Ϊ6
//		//this.realLabel = makeLabel(peashooterCardGif,x,)???//�������ֲ���а�gif����Ҳ��Ҫ���ƶ���ʱ����־�̬pngֲ��ʾ��ͼ
//		this.moveLabel = MakeLabel.makeLabel(peashooterMovePic, this.originPoint, width, height);
//		this.frame.getLayeredPane().add(this.moveLabel, new Integer(1));//�ڵ����ȼ�Ϊ1
//		this.moveLabel.setVisible(false);//Ĭ�ϰ�͸��ʾ��ͼΪfalse
//		this.cardLabel.addMouseListener(this);//??��֪���ɲ�����
//		this.cardLabel.addMouseMotionListener(this);
//	}
//	
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		super.mouseReleased(e);
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		// TODO Auto-generated method stub
//		super.mouseMoved(e);
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//		super.mousePressed(e);
//	}
//
//
//	@Override
//	public void createPlant() {
//		// TODO Auto-generated method stub
//		System.out.println(""+line_y+""+line_x);
//		NutJFrame.plants[line_y][line_x] = new PeaShooter(line_x*grassWidth+leftOffset,line_y*grassHeight+upOffset,this.frame);
//	}
//
//	
//	
//	
//}
