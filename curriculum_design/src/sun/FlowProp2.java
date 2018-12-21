package sun;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.ShooterController;
import game.ShooterJFrame;

public class FlowProp2 extends Sun implements MouseMotionListener, MouseListener {
	private int flowSpeed = 1;// �����ٶ�
	private final static ImageIcon sun = new ImageIcon(Sun.class.getResource("/images/gif/prop2.gif"));
	private int randomNumber;
	private long sunBeginTime = 0;

	public FlowProp2(int x, int y, JFrame jFrame) {
		super();// ����û����
		super.type = 1;
		this.x = x;
		this.y = y;
		this.frame = jFrame;
		Random random = new Random();
		randomNumber = random.nextInt(570);// ���������

		z0 = new JLabel(sun);
		z0.setBounds(this.x, this.y, WIDTH, HEIGHT);
		z0.setVisible(true);
		frame.add(z0);

		z0.setVisible(true);
		z0.setLocation(x, y);

		sunBeginTime = System.currentTimeMillis();
		new Thread(new MoveSun()).start();// �����

		z0.addMouseListener(this);// ���һ��������
		z0.addMouseMotionListener(this);
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	private void move() {
		if (y < randomNumber) {
			z0.setLocation(x, y);
			y += flowSpeed;// ��ֱ����
		}
	}

	public void removeSun() {

		z0.setEnabled(false);
		z0.setEnabled(false);
		z0.setIcon(null);
		frame.remove(z0);
		// frame.repaint();
		suns.remove(this);
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

					if (System.currentTimeMillis() - sunBeginTime >= 10000) {
						removeSun();
						break;
					}
					Thread.sleep(25);
					move();
				}
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		removeSun();
		ShooterController.propBeginTime = System.currentTimeMillis();
		ShooterJFrame.shooter.refresh(2);
		ShooterController.status = 2;
	}

}