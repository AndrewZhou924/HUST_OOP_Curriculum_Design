
package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Container;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import zombies.Zombie;

// ��Ϸ�������棨��ʬ��ʤ��
public class overGame extends JFrame implements Runnable{
	private final static ImageIcon zombie_win = new ImageIcon(Zombie.class.getResource("/images/zombie_win.png"));
	public final static ImageIcon background = new ImageIcon(GameJFrame.class.getResource("/images/background1.jpg"));
	public final static ImageIcon dave = new ImageIcon(GameJFrame.class.getResource("/images/gif/Dave3.gif"));
	public final static ImageIcon gameover = new ImageIcon(GameJFrame.class.getResource("/images/gif/gameover.png"));
	JLabel overLabel,daveLabel,gameoverLabel;
	JFrame f = this;
	
	public overGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رճ���Ҳ��ر�dos�е�����.
		setVisible(true);
		this.setResizable(false);//����˴����ǿɵ�����С�ģ���Ϊ true������Ϊ false�� 
		setLayout(null);//����
		setBounds(500,200, 856, 600);//��ʼ�����λ�úͳ�������


		
		JLabel overLabel = new JLabel(background);
		getLayeredPane().add(overLabel, new Integer(Integer.MIN_VALUE));
		/*JLayeredPane �����������Ҫʱ�����ص�
		  addǰһ���������������һ��������Integer����Integerָ��������ÿ���������ȣ����б�Žϸߵ����λ���������֮�ϡ� */
		overLabel.setBounds(-200, 0, background.getIconWidth(), background.getIconHeight());
		Container cp = getContentPane();
		((JPanel) cp).setOpaque(false);//true�ͻ���ʾ������ɫ��false��������͸��
		/*
		 ���������Ϊtrue�Ļ���ԭ����ʾ����е�ÿ�����أ�Ҳ����������ʾ��������Ҫ��������Ϊfalseʱ��
	������Ϊfalseʱ�������δ������ʾ���е�ĳЩ���أ�����ؼ�������������ֳ�����
		 */
		
		
		daveLabel = new JLabel(dave);
		daveLabel.setBounds(0, 100, 316, 519);
		daveLabel.setVisible(true);
		add(daveLabel);	
		
		gameoverLabel = new JLabel(gameover);
		gameoverLabel.setBounds(200, 100, 535, 408);
		gameoverLabel.setVisible(true);
		add(gameoverLabel);	
		
		//run();
		
		AudioClip aau = null;
		try {
			File f = new File("curriculum_design\\src\\sounds\\losemusic.wav");
			URI uri = f.toURI();
			URL url = uri.toURL();

			aau = Applet.newAudioClip(url);
			aau.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Thread gameThread = new Thread(this);
		gameThread.start();

	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			new LevelSelect();
			this.dispose();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
