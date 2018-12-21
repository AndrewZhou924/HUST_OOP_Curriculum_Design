package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Container;
import java.awt.Label;
import java.awt.Point;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.corba.se.impl.orbutil.closure.Future;

import bullet.Bullet;

//import cards.PeaShooterCard;
import cards.WallNutCard;
import controller.CardController;
import controller.ZombieController;
import makelabel.MakeLabel;
import plants.NewPlant;
import plants.PeaShooter;
import sun.Sun;
import zombies.Zombie;

public class NutJFrame extends javax.swing.JFrame implements Runnable {

	public final static ImageIcon background = new ImageIcon(GameJFrame.class.getResource("/images/background1.jpg"));
	public final static ImageIcon SeedBank = new ImageIcon(NutJFrame.class.getResource("/images/SeedBank.png"));
	public static NewPlant[][] plants = new NewPlant[5][10]; // 5行10列的方格

	
	int count = 1000; // species用于存放当前选中的植物
	boolean zombieWin = false;

	// jFrame为游戏画面
	public static JFrame jFrame;
	private static JLabel seedBank;
	
	private quitButton quit_button = new quitButton("quit");
	private MusicButton music_button;
	private AudioClip aau = null;

	public NutJFrame() {
		// 初始化游戏界面

		for (int i = 0; i < 5; i++) {
			Bullet.bullets[i] = new ArrayList<>();
		}

		for (int i = 0; i < 5; i++) {

			Zombie.zombies[i] = new ArrayList<>();
		}

		jFrame = this;
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 70, background.getIconWidth(), background.getIconHeight() + 40);
		setResizable(false);

		JLabel imgLabel = new JLabel(background);
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		Container cp = getContentPane();
		((JPanel) cp).setOpaque(false);

		this.setVisible(true);
		

		seedBank = MakeLabel.makeLabel(SeedBank, 220, -5, 500,100);
		getLayeredPane().add(seedBank, new Integer(9));
		
		this.quit_button.setBounds(750, 10, 89, 22);
		this.quit_button.setJFrame(this);
		this.getContentPane().add(quit_button);
		
		//WallNutCard p = new WallNutCard(360,-2,this);
		//PeaShooterCard p = new PeaShooterCard(360,-2,this);
		// 新建一个线程，跑run()
		Thread gameThread = new Thread(this);
		gameThread.start();


		new ZombieController(4000,this);
		new CardController(this);
	}

	@Override
	public void run() { // 初始化时执行

		// 循环播放音乐
		AudioClip aau = null;
		try {
			// File f = new File("E:\\JAVA\\eclipse
			// x64\\MyGame\\src\\sounds\\backmusic3.wav");
			File f = new File("curriculum_design\\src\\sounds\\UraniwaNi.wav");
			URI uri = f.toURI();
			URL url = uri.toURL();
			aau = Applet.newAudioClip(url);
			aau.loop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		music_button = new MusicButton(aau);
		music_button.setBounds(750, 35, 89, 22);
		this.getContentPane().add(music_button);

		// 只要游戏不结束，就不会退出该循环
		while (true) {

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 9; j++) {
					if ( plants[i][j] != null && plants[i][j].getHp() <= 0) {
						plants[i][j] = null;
					}
				}
			}

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < Zombie.zombies[i].size(); j++) {
					if (Zombie.zombies[i] != null) {
						if (Zombie.zombies[i].get(j).isGameOver() == true)
							zombieWin = true;
					}
				}
			}

			if (zombieWin == true) {
				this.dispose();
				aau.stop();
				new overGame();
				break;
			}

			try {
				Thread.sleep(20);
				repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
