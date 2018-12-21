package sun;

import java.applet.Applet;
import java.applet.AudioClip;
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

import game.GameJFrame;

public class Sun implements MouseMotionListener, MouseListener {
	public int type;
	protected int flag = 0;
	protected JLabel z1, z2, z3, z4, z5, z6, z7, z8, z9, z0;
	protected int x, y;
	protected int flowSpeed;// 下落速度
	protected long sunBeginTime;
	protected boolean isClicked = false;// 是否被点击的flag，默认false
	protected static int WIDTH = 65;
	protected static int HEIGHT = 65;
	public static ArrayList<Sun> suns = new ArrayList<>();
	protected final static ImageIcon sun1 = new ImageIcon(Sun.class.getResource("/images/gif/sun/1.png"));
	protected final static ImageIcon sun2 = new ImageIcon(Sun.class.getResource("/images/gif/sun/3.png"));
	protected final static ImageIcon sun3 = new ImageIcon(Sun.class.getResource("/images/gif/sun/5.png"));
	protected final static ImageIcon sun4 = new ImageIcon(Sun.class.getResource("/images/gif/sun/7.png"));
	protected final static ImageIcon sun5 = new ImageIcon(Sun.class.getResource("/images/gif/sun/9.png"));
	protected final static ImageIcon sun6 = new ImageIcon(Sun.class.getResource("/images/gif/sun/11.png"));
	protected final static ImageIcon sun7 = new ImageIcon(Sun.class.getResource("/images/gif/sun/13.png"));
	protected final static ImageIcon sun8 = new ImageIcon(Sun.class.getResource("/images/gif/sun/15.png"));
	protected final static ImageIcon sun9 = new ImageIcon(Sun.class.getResource("/images/gif/sun/17.png"));
	protected final static ImageIcon sun0 = new ImageIcon(Sun.class.getResource("/images/gif/sun/0.png"));
	protected JFrame frame;

	public Sun() {
		// 空方法，用于继承
	}
	
	public Sun(int x, int y, JFrame frame) {
		this.x = x + 75;
		this.y = y + 20;
		this.frame = frame;
		sunBeginTime = System.currentTimeMillis();
		z1 = new JLabel(sun1);
		z1.setBounds(this.x, this.y, WIDTH, HEIGHT);
		z1.setVisible(true);
		frame.getLayeredPane().add(z1, new Integer(8));

		z2 = new JLabel(sun2);
		z2.setBounds(this.x, this.y, WIDTH, HEIGHT);
		z2.setVisible(false);
		frame.getLayeredPane().add(z2, new Integer(8));

		z3 = new JLabel(sun3);
		z3.setBounds(this.x, this.y, WIDTH, HEIGHT);
		z3.setVisible(false);
		frame.getLayeredPane().add(z3, new Integer(8));

		z4 = new JLabel(sun4);
		z4.setBounds(this.x, this.y, WIDTH, HEIGHT);
		z4.setVisible(false);
		frame.getLayeredPane().add(z4, new Integer(8));

		z5 = new JLabel(sun5);
		z5.setBounds(this.x, this.y, WIDTH, HEIGHT);
		z5.setVisible(false);
		frame.getLayeredPane().add(z5, new Integer(8));

		z6 = new JLabel(sun6);
		z6.setBounds(this.x, this.y, WIDTH, HEIGHT);
		z6.setVisible(false);
		frame.getLayeredPane().add(z6, new Integer(8));

		z7 = new JLabel(sun7);
		z7.setBounds(this.x, this.y, WIDTH, HEIGHT);
		z7.setVisible(false);
		frame.getLayeredPane().add(z7, new Integer(8));

		z8 = new JLabel(sun8);
		z8.setBounds(this.x, this.y, WIDTH, HEIGHT);
		z8.setVisible(false);
		frame.getLayeredPane().add(z8, new Integer(8));

		z9 = new JLabel(sun9);
		z9.setBounds(this.x, this.y, WIDTH, HEIGHT);
		z9.setVisible(false);
		frame.getLayeredPane().add(z9, new Integer(8));

		z0 = new JLabel(sun0);
		z0.setBounds(this.x + 75, this.y + 27, WIDTH, HEIGHT);
		z0.setVisible(true);
		frame.getLayeredPane().add(z0, new Integer(8));

		z0.setVisible(true);
		z0.setLocation(x + 75, y + 27);

		newThread();

		z0.addMouseListener(this);// 添加一个鼠标监听
		z0.addMouseMotionListener(this);
	}

	public void newThread() {
		new Thread(new SunClickedThread()).start();// 检测点击
	}

	class SunClickedThread implements Runnable {// 检测点击事件的线程

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while (true) {

				if (System.currentTimeMillis() - sunBeginTime >= 5000) {
					removeSun();
					break;
				}
				while (System.currentTimeMillis() - sunBeginTime < 5000) {
					try {
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
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

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

			}
		}
	}

	public void removeSun() {

		z1.setEnabled(false);
		z2.setEnabled(false);
		z3.setEnabled(false);
		z4.setEnabled(false);
		z5.setEnabled(false);
		z6.setEnabled(false);
		z7.setEnabled(false);
		z8.setEnabled(false);
		z9.setEnabled(false);
		z0.setEnabled(false);

		z1.setVisible(false);// 设置本太阳不可见
		z2.setVisible(false);
		z3.setVisible(false);
		z4.setVisible(false);
		z5.setVisible(false);
		z6.setVisible(false);
		z7.setVisible(false);
		z8.setVisible(false);
		z9.setVisible(false);
		z0.setEnabled(false);

		z1.setIcon(null);
		z2.setIcon(null);
		z3.setIcon(null);
		z4.setIcon(null);
		z5.setIcon(null);
		z6.setIcon(null);
		z7.setIcon(null);
		z8.setIcon(null);
		z9.setIcon(null);
		z0.setIcon(null);

		frame.getLayeredPane().remove(z1);// 移除太阳
		frame.getLayeredPane().remove(z2);
		frame.getLayeredPane().remove(z3);
		frame.getLayeredPane().remove(z4);
		frame.getLayeredPane().remove(z5);
		frame.getLayeredPane().remove(z6);
		frame.getLayeredPane().remove(z7);
		frame.getLayeredPane().remove(z8);
		frame.getLayeredPane().remove(z9);
		frame.getLayeredPane().remove(z0);

		// frame.repaint();

		// sunLabel.setVisible(false);// 设置本太阳不可见

		suns.remove(this);
	}

	// getter和setter方法
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("鼠标点击");
		removeSun();
		GameJFrame.count += 50;
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		System.out.println("鼠标按下");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println("鼠标松开");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		System.out.println("鼠标已经进入窗体");
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		System.out.println("鼠标已经移出窗体");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//		String str = "鼠标所在的坐标:(" + e.getX() + "," + e.getY() + ")";
//		System.out.println(str);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		System.out.println("鼠标移动了");
	}
}