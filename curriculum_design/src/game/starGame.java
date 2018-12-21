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

// starGame ��ʼ�˵�����
public class starGame extends JFrame {
	private final static ImageIcon menu = new ImageIcon(Zombie.class.getResource("/images/menu.png")); // ��ʼ����
	private final static ImageIcon start_over = new ImageIcon(Zombie.class.getResource("/images/gif/1.png")); // ��ʼ��Ϸ������ͼ��
	JLabel starLabel;
	JFrame f = this;

	public starGame() {
		// ��ʼ�����ʼ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رճ���Ҳ��ر�dos�е�����.
		setVisible(true);
		this.setResizable(false);//����˴����ǿɵ�����С�ģ���Ϊ true������Ϊ false�� 
		setLayout(null);//����
		setBounds(520, 200, menu.getIconWidth(), menu.getIconHeight() + 30);//��ʼ�����λ�úͳ�������

		JLabel imgLabel = new JLabel(menu);
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		/*JLayeredPane �����������Ҫʱ�����ص�
		  addǰһ���������������һ��������Integer����Integerָ��������ÿ���������ȣ����б�Žϸߵ����λ���������֮�ϡ� */
		imgLabel.setBounds(0, 0, menu.getIconWidth(), menu.getIconHeight());
		Container cp = getContentPane();
		((JPanel) cp).setOpaque(false);//true�ͻ���ʾ������ɫ��false��������͸��
		/*
		 ���������Ϊtrue�Ļ���ԭ����ʾ����е�ÿ�����أ�Ҳ����������ʾ��������Ҫ��������Ϊfalseʱ��
������Ϊfalseʱ�������δ������ʾ���е�ĳЩ���أ�����ؼ�������������ֳ�����
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

			// ������¼�����Ӧ����
			int x = starLabel.getX();
			int y = starLabel.getY();
			int w = 323;
			int h = 87;

			if (e.getX() >= x && e.getX() <= x + w && e.getY() >= y && e.getY() <= y + h) {
				new LevelSelect(); // ������Ϸ����
				f.dispose(); // �رտ�ʼ�˵���GUIҳ��
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

			// �������� [��ʼ��Ϸ] ͼ���һ����Χ�ڣ���ı�����ʾ
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
