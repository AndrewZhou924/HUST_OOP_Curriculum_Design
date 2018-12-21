package sun;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FlowSun extends Sun implements MouseMotionListener, MouseListener {
	private int randomNumber;
	private long flowsunBeginTime;
	public FlowSun(int x, int y, JFrame jFrame) {
		super(x,y,jFrame);
		this.flowSpeed = 1;
		Random random = new Random();
		randomNumber = random.nextInt(570);// ���������
		flowsunBeginTime = System.currentTimeMillis();
		
//		z0.addMouseListener(this);// ���һ��������
//		z0.addMouseMotionListener(this);
	}

	@Override
	public void newThread() {
		// TODO Auto-generated method stub
		new Thread(new MoveSun()).start();// �����
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}


	class MoveSun implements Runnable {// ������¼����߳�

		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			try {
				while (y < 570) {// ���̫��û������Ϸ��������
					System.out.print("");
					if (isClicked) {
						removeSun();
						AudioClip aau;
						try {
							File f = new File("curriculum_design\\src\\sounds\\points.wav");
							URI uri = f.toURI();
							URL url = uri.toURL();
							aau = Applet.newAudioClip(url);
							aau.play();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					}

					if (System.currentTimeMillis() - flowsunBeginTime >= 10000) {
						removeSun();
						break;
					}
					
					while(y < randomNumber){
						z9.setVisible(false);
						z1.setVisible(true);
						z1.setLocation(x, y);
						y += flowSpeed;// ��ֱ����
						Thread.sleep(50);
						
						z1.setVisible(false);
						z2.setVisible(true);
						z2.setLocation(x, y);
						y += flowSpeed;
						Thread.sleep(50);

						z2.setVisible(false);
						z3.setLocation(x, y);
						z3.setVisible(true);
						y += flowSpeed;
						Thread.sleep(50);

						z3.setVisible(false);
						z4.setLocation(x, y);
						z4.setVisible(true);
						y += flowSpeed;
						Thread.sleep(50);

						z4.setVisible(false);
						z5.setLocation(x, y);
						z5.setVisible(true);
						y += flowSpeed;
						Thread.sleep(50);

						z5.setVisible(false);
						z6.setLocation(x, y);
						z6.setVisible(true);
						y += flowSpeed;
						Thread.sleep(50);

						z6.setVisible(false);
						z7.setLocation(x, y);
						z7.setVisible(true);
						y += flowSpeed;
						Thread.sleep(50);

						z7.setVisible(false);
						z8.setLocation(x, y);
						z8.setVisible(true);
						y += flowSpeed;
						Thread.sleep(50);

						z8.setVisible(false);
						z9.setLocation(x, y);
						z9.setVisible(true);
						y += flowSpeed;
						Thread.sleep(50);

					}
					while(System.currentTimeMillis() - flowsunBeginTime < 10000) {
						z9.setVisible(false);
						z1.setVisible(true);
						z1.setLocation(x, y);
						Thread.sleep(50);
						
						z1.setVisible(false);
						z2.setVisible(true);
						z2.setLocation(x, y);
						Thread.sleep(50);

						z2.setVisible(false);
						z3.setLocation(x, y);
						z3.setVisible(true);
						Thread.sleep(50);

						z3.setVisible(false);
						z4.setLocation(x, y);
						z4.setVisible(true);
						Thread.sleep(50);

						z4.setVisible(false);
						z5.setLocation(x, y);
						z5.setVisible(true);
						Thread.sleep(50);

						z5.setVisible(false);
						z6.setLocation(x, y);
						z6.setVisible(true);
						Thread.sleep(50);

						z6.setVisible(false);
						z7.setLocation(x, y);
						z7.setVisible(true);
						Thread.sleep(50);

						z7.setVisible(false);
						z8.setLocation(x, y);
						z8.setVisible(true);
						Thread.sleep(50);

						z8.setVisible(false);
						z9.setLocation(x, y);
						z9.setVisible(true);
						Thread.sleep(50);
					}
					
				}
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}