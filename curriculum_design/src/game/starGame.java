package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import zombies.Zombie;

// starGame 开始菜单界面
public class starGame extends JFrame {
	private final static ImageIcon menu = new ImageIcon(Zombie.class.getResource("/images/menu.png")); // 开始界面
	private final static ImageIcon start_over = new ImageIcon(Zombie.class.getResource("/images/gif/1.png")); // 开始游戏高亮化图标
	JLabel starLabel;
	JFrame f = this;

	public starGame() {
		// 开始界面初始化
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭程序，也会关闭dos中的运行.
		setVisible(true);
		this.setResizable(false);//如果此窗体是可调整大小的，则为 true；否则为 false。 
		setLayout(null);//布局
		setBounds(520, 200, menu.getIconWidth(), menu.getIconHeight() + 30);//开始界面的位置和长宽设置

		JLabel imgLabel = new JLabel(menu);
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		/*JLayeredPane 允许组件在需要时互相重叠
		  add前一个参数是组件，后一个参数是Integer对象，Integer指定容器中每个组件的深度，其中编号较高的组件位于其他组件之上。 */
		imgLabel.setBounds(0, 0, menu.getIconWidth(), menu.getIconHeight());
		Container cp = getContentPane();
		((JPanel) cp).setOpaque(false);//true就会显示背景颜色，false背景就是透明
		/*
		 即如果设置为true的话，原样显示组件中的每个像素，也就是正常显示；这里主要讲解设置为false时。
当设置为false时，组件并未不会显示其中的某些像素，允许控件下面的像素显现出来。
		 */

		starLabel = new JLabel(start_over);
		starLabel.setBounds(241, 500, 323, 87);
		starLabel.setVisible(false);
		add(starLabel);

		MouseHandler handler = new MouseHandler();
		this.addMouseMotionListener(handler);
		this.addMouseListener(handler);
		AudioClip aau;

		try {
			File f = new File("curriculum_design\\src\\sounds\\Faster.wav");
			URI uri = f.toURI();
			URL url = uri.toURL();
			aau = Applet.newAudioClip(url);
			aau.loop();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class MouseHandler implements MouseMotionListener, MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			// 鼠标点击事件的响应函数
			int x = starLabel.getX();
			int y = starLabel.getY();
			int w = 323;
			int h = 87;

			if (e.getX() >= x && e.getX() <= x + w && e.getY() >= y && e.getY() <= y + h) {
				new LevelSelect(); // 进入游戏界面
				f.dispose(); // 关闭开始菜单的GUI页面
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else {

				setCursor(Cursor.getDefaultCursor());
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
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

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			int x = starLabel.getX();
			int y = starLabel.getY();
			int w = 323;
			int h = 87;

			// 如果鼠标在 [开始游戏] 图标的一定范围内，则改变其显示
			if (e.getX() >= x && e.getX() <= x + w && e.getY() >= y && e.getY() <= y + h) {
				starLabel.setVisible(true);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else {
				starLabel.setVisible(false);
				setCursor(Cursor.getDefaultCursor());
			}

		}

	}

}
