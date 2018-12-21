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
	private int flowSpeed = 1;// 下落速度
	private final static ImageIcon sun = new ImageIcon(Sun.class.getResource("/images/gif/prop2.gif"));
	private int randomNumber;
	private long sunBeginTime = 0;

	public FlowProp2(int x, int y, JFrame jFrame) {
		super();// 好像没有用
		super.type = 1;
		this.x = x;
		this.y = y;
		this.frame = jFrame;
		Random random = new Random();
		randomNumber = random.nextInt(570);// 产生随机数

		z0 = new JLabel(sun);
		z0.setBounds(this.x, this.y, WIDTH, HEIGHT);
		z0.setVisible(true);
		frame.add(z0);

		z0.setVisible(true);
		z0.setLocation(x, y);

		sunBeginTime = System.currentTimeMillis();
		new Thread(new MoveSun()).start();// 检测点击

		z0.addMouseListener(this);// 添加一个鼠标监听
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
			y += flowSpeed;// 垂直下落
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

	class MoveSun implements Runnable {// 检测点击事件的线程

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			try {
				while (y < 570) {// 如果太阳没有在游戏画面以下
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
				// TODO 自动生成的 catch 块
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