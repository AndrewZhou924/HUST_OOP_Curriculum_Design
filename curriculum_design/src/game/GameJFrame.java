package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Container;
import java.awt.Point;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bullet.Bullet;
import car.Car;
import controller.FlowSunController;
import controller.ZombieController;
import plants.PeaShooter;
import plants.Plant;

import plants.Potato;
import plants.SunFlower;
import sun.Sun;
import zombies.Zombie;
import plants.Plant;

public class GameJFrame extends JFrame implements Runnable {
	//图片
	public final static ImageIcon background = new ImageIcon(
			GameJFrame.class.getResource("/images/background1.jpg"));//地板
	public final static ImageIcon SeedBank = new ImageIcon(
			GameJFrame.class.getResource("/images/SeedBank.png"));
	public final static ImageIcon sun_flower = new ImageIcon(
			GameJFrame.class.getResource("/images/sunflower_on.jpg"));
	public final static ImageIcon single_bullet_plant = new ImageIcon(
			GameJFrame.class.getResource("/images/card_norbeen_on.jpg"));
	protected final static ImageIcon movd_single_bullet_plant = new ImageIcon(
			GameJFrame.class.getResource("/images/gif/Peashooter.gif"));
	protected final static ImageIcon move_sun_flower = new ImageIcon(
			GameJFrame.class.getResource("/images/gif/SunFlower1.gif"));
	protected final static ImageIcon shovel = new ImageIcon(
			GameJFrame.class.getResource("/images/shovel.png"));//背景的铲子
	public final static ImageIcon shove = new ImageIcon(
			GameJFrame.class.getResource("/images/shove.png"));//拖拽出来的铲子
	public final static ImageIcon card_norbeen_off = new ImageIcon(
			GameJFrame.class.getResource("/images/card_norbeen_off.jpg"));
	public final static ImageIcon sunflower_off= new ImageIcon(
			GameJFrame.class.getResource("/images/sunflower_off.jpg"));
	public final static ImageIcon card_icebeen_on = new ImageIcon(
			GameJFrame.class.getResource("/images/card_icebeen_on.jpg"));
	public final static ImageIcon card_icebeen_off = new ImageIcon(
			GameJFrame.class.getResource("/images/card_icebeen_off.jpg"));
	protected final static ImageIcon moveIcebean = new ImageIcon(
			GameJFrame.class.getResource("/images/gif/SnowPea.gif"));
	protected final static ImageIcon potato_on = new ImageIcon(
			GameJFrame.class.getResource("/images/potato_on.jpg"));
	public final static ImageIcon potato_off = new ImageIcon(
			GameJFrame.class.getResource("/images/potato_off.jpg"));
	protected final static ImageIcon movePotato = new ImageIcon(
			GameJFrame.class.getResource("/images/gif/WallNut.gif"));
	private final static ImageIcon chili_on = new ImageIcon(
			GameJFrame.class.getResource("/images/pepper_card(1).png"));
	public final static ImageIcon chili_off = new ImageIcon(
			GameJFrame.class.getResource("/images/pepper_card2(1).png"));
	public final static ImageIcon chili = new ImageIcon(
			GameJFrame.class.getResource("/images/gif/bbb.png"));
	
	public final static ImageIcon black = new ImageIcon(
			GameJFrame.class.getResource("/images/gif/black.png"));

	
	public final static ImageIcon buckethead = new ImageIcon(
			Zombie.class.getResource("/images/gif/buckethead.gif"));
	public final static ImageIcon conehead = new ImageIcon(
			Zombie.class.getResource("/images/gif/conehead.gif"));
	public final static ImageIcon zombiez = new ImageIcon(
			Zombie.class.getResource("/images/gif/zombiez.gif"));
	public final static ImageIcon zombiezz = new ImageIcon(
			Zombie.class.getResource("/images/gif/zombiezz.gif"));


	public static Plant[][] plants = new Plant[5][10]; // 5行10列的方格
	public static JLabel[][] labels = new JLabel[5][10]; // 植物对应的标签

	private long PeaBeginTime = 0;
	private long flowerBeginTime = 0;
	private long icePeaBeginTime = 0;
	private long potatoBeginTime = 0;
	private long chiliBeginTime = 0;
	static JLabel barLabel;
	static JLabel sunFlowerLabel;
	static JLabel peaLabel;
	static JLabel icePeaLabel;
	static JLabel potatoLabel;
	static JLabel chiliLabel;
	static JLabel sunCountLabel;
	public static JLabel movePeaLabel;
	static JLabel moveFlowerLabel;
	static JLabel moveShovelLabel;
	static JLabel moveIcePeaLabel;
	static JLabel movePotatoLabel;
	static JLabel moveChiliLabel;
	static JLabel plantLabel;
	static JLabel shovelLabel;
	static JLabel uIcePeaLabel;
	static JLabel uPeaLabel;
	static JLabel uFlowerLabel;
	static JLabel uPotatoLabel;
	static JLabel uChiliLabel;

	JLabel bucket,cone,z,zz,bucket1,bucket2,cone1,cone2,z1,z2,zz1,zz2;
	static JLabel b1,b2,b3,b4,b5;
	
	Point movePeaPoint; // Point类 (getX,getY,getPoint)
	Point moveFlowePoint;
	Point moveShovelPoint;
	Point moveIcePeaPoint;
	Point movePotatoPoint;
	Point moveChiliPoint;

	protected int  coolTime=1;

	public static int species = 100; // species用于存放当前选中的植物
	public static int count = 500;
	
	boolean zombieWin = false;

	static int k;
	private int move_Speed;     //移动速度
	JLabel imgLabel = new JLabel(background);
	private boolean move=true;
	
	// jFrame为游戏画面
	public static JFrame jFrame;
	private quitButton quit_button = new quitButton("quit");
	private MusicButton music_button;
	private AudioClip aau = null;

	public GameJFrame() {
		// 初始化游戏界面
		move_Speed = 50;
		
		for (int i = 0; i < 5; i++) {
			Bullet.bullets[i] = new ArrayList<>();
		}

		for (int i = 0; i < 5; i++) {
			Zombie.zombies[i] = new ArrayList();
		}

		jFrame = this;
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(500,200, 856, 600);
		//setBounds(500,200, background.getIconWidth(), background.getIconHeight());

		setResizable(false);//resizeable值为false时，表示生成的窗体大小是由程序员决定的，用户不可以自由改变该窗体的大小。 
		
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		Container cp = getContentPane();
		((JPanel) cp).setOpaque(false);

		this.setVisible(true);
		

		// 给图片都贴上标签
		uFlowerLabel = makeLabel(black, 121, 15, 55, 70);
		uFlowerLabel.setVisible(true);
		add(uFlowerLabel);

		sunFlowerLabel = makeLabel(sun_flower, 100, 0, 100, 100);
		add(sunFlowerLabel);

		uPeaLabel = makeLabel(black, 185, 15, 50, 70);
		add(uPeaLabel);
		uPeaLabel.setVisible(true);

		peaLabel = makeLabel(single_bullet_plant, 160, -2, 100, 100);
		add(peaLabel);

		uIcePeaLabel = makeLabel(black, 242, 15, 55, 70);
		add(uIcePeaLabel);
		uIcePeaLabel.setVisible(true);

		icePeaLabel = makeLabel(card_icebeen_on, 220, 0, 100, 100);
		add(icePeaLabel);

		uPotatoLabel = makeLabel(black, 310, 15, 50, 70);
		add(uPotatoLabel);
		uPotatoLabel.setVisible(true);

		potatoLabel = makeLabel(potato_on, 300, 15, 70, 70);
		add(potatoLabel);

		uChiliLabel = makeLabel(black, 385, 15, 50, 70);
		add(uChiliLabel);
		uChiliLabel.setVisible(true);

		chiliLabel = makeLabel(chili_on, 360, 0, 100, 100);
		add(chiliLabel);

		sunCountLabel = new JLabel("0");
		sunCountLabel.setBounds(70, 55, 50, 50);
		add(sunCountLabel);
		sunCountLabel.setVisible(false);

		shovelLabel = makeLabel(shovel, 500, 0, 100, 100);
		add(shovelLabel);

		barLabel = makeLabel(SeedBank, 20, 0, 500, 100);
		add(barLabel);

		bucket = new JLabel(buckethead);
		bucket.setBounds(675, 25, 166, 144);
		bucket.setVisible(false);
		add(bucket);	
		
		bucket1 = new JLabel(buckethead);
		bucket1.setBounds(550, 300, 166, 144);
		bucket1.setVisible(false);
		add(bucket1);	
		
		bucket2 = new JLabel(buckethead);
		bucket2.setBounds(631, 150, 166, 144);
		bucket2.setVisible(false);
		add(bucket2);	
		
		cone = new JLabel(conehead);
		cone.setBounds(500,100, 166, 144);
		cone.setVisible(false);
		add(cone);	
		
		cone1 = new JLabel(conehead);
		cone1.setBounds(574,225, 166, 144);
		cone1.setVisible(false);
		add(cone1);	
		
		cone2 = new JLabel(conehead);
		cone2.setBounds(600,400, 166, 144);
		cone2.setVisible(false);
		add(cone2);	
		
		z = new JLabel(zombiez);
		z.setBounds(700, 150, 166, 144);
		z.setVisible(false);
		add(z);	
		
		z1 = new JLabel(zombiez);
		z1.setBounds(550, 20, 166, 144);
		z1.setVisible(false);
		add(z1);	
		
		z2 = new JLabel(zombiez);
		z2.setBounds(700, 150, 166, 144);
		z2.setVisible(false);
		add(z2);	
		
		zz = new JLabel(zombiezz);
		zz.setBounds(650, 300, 166, 144);
		zz.setVisible(false);
		add(zz);	
		
		zz1 = new JLabel(zombiezz);
		zz1.setBounds(650, 300, 166, 144);
		zz1.setVisible(false);
		add(zz1);	
		
		zz2 = new JLabel(zombiezz);
		zz2.setBounds(650, 300, 166, 144);
		zz2.setVisible(false);
		add(zz2);	
		
		// GameController用于捕捉用户的鼠标事件
		GameController gamecontroller = new GameController(this);
		this.addMouseMotionListener(gamecontroller);
		this.addMouseListener(gamecontroller);
		
		this.quit_button.setBounds(750, 10, 89, 22);
		this.quit_button.setJFrame(this);
		this.getContentPane().add(quit_button);

		// 新建一个线程，跑run()
		Thread gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	// 制造标签
	public JLabel makeLabel(ImageIcon im, int x, int y, int w, int h) {
		JLabel temp = new JLabel(im);
		temp.setBounds(x, y, w, h);
		return temp;
	}

	// 判断用户点击了哪个植物
	int isOk(int x, int y) {
		if (x >= sunFlowerLabel.getX() && x <= sunFlowerLabel.getX() + 70 && y >= sunFlowerLabel.getY()
				&& y <= sunFlowerLabel.getY() + 100 && uFlowerLabel.getHeight()<=0
				&& count >= 50) {
			return 1;
		}
			
		if (x >= peaLabel.getX() && x <= peaLabel.getX() + 70 && y >= peaLabel.getY() && y <= peaLabel.getY() + 100
				&& uPeaLabel.getHeight()<=0 && count >= 100) {
			return 2;
		}
		if (x >= shovelLabel.getX() && x <= shovelLabel.getX() + 100 && y >= shovelLabel.getY()
				&& y <= shovelLabel.getY() + 100)
			return 3;
		if (Sun.suns != null) {
			for (int i = 0; i < Sun.suns.size(); i++) {
				if (Sun.suns.get(i) != null) {
					if (Math.abs(Sun.suns.get(i).getX() - x) <= 35 && Math.abs(Sun.suns.get(i).getY() - y) <= 35)
						return 4;
				}

			}
		}
		if (x >= icePeaLabel.getX() && x <= icePeaLabel.getX() + 70 && y >= icePeaLabel.getY()
				&& y <= icePeaLabel.getY() + 100 && uIcePeaLabel.getHeight()<=0
				&& count >= 170) {
			return 5;
		}
		if (x >= potatoLabel.getX() && x <= potatoLabel.getX() + 70 && y >= potatoLabel.getY()
				&& y <= potatoLabel.getY() + 70 && uPotatoLabel.getHeight()<=0
				&& count >= 50) {
			return 6;
		}
		if (x >= chiliLabel.getX() && x <= chiliLabel.getX() + 70 && y >= chiliLabel.getY()
				&& y <= chiliLabel.getY() + 80 && uChiliLabel.getHeight()<=0
				&& count >= 150) {
			return 7;
		}
		return 0;
	}

	private void playUprootSound() {
		AudioClip aau;
		try {
			File f = new File("curriculum_design\\src\\sounds\\coffee.wav");
			URI uri = f.toURI();
			URL url = uri.toURL();
			aau = Applet.newAudioClip(url);
			aau.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		if(move) {
			while(k>-500) {
				try {
					Thread.sleep(100);
					k -= move_Speed;
					imgLabel.setLocation(k, 0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				bucket.setVisible(true);
				cone.setVisible(true);
				z.setVisible(true);
				zz.setVisible(true);
				
				bucket1.setVisible(true);
				cone1.setVisible(true);
				z1.setVisible(true);
				zz1.setVisible(true);
				
				bucket2.setVisible(true);
				cone2.setVisible(true);
				z2.setVisible(true);
				zz2.setVisible(true);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(k<-200) {
				try {
					bucket.setLocation(bucket.getX()+move_Speed, bucket.getY());
					cone.setLocation(cone.getX()+move_Speed, cone.getY());
					z.setLocation(z.getX()+move_Speed, z.getY());
					zz.setLocation(zz.getX()+move_Speed, zz.getY());
					
					bucket1.setLocation(bucket1.getX()+move_Speed, bucket1.getY());
					cone1.setLocation(cone1.getX()+move_Speed, cone1.getY());
					z1.setLocation(z1.getX()+move_Speed, z1.getY());
					zz1.setLocation(zz1.getX()+move_Speed, zz1.getY());
					
					bucket2.setLocation(bucket2.getX()+move_Speed, bucket2.getY());
					cone2.setLocation(cone2.getX()+move_Speed, cone2.getY());
					z2.setLocation(z2.getX()+move_Speed, z2.getY());
					zz2.setLocation(zz2.getX()+move_Speed, zz2.getY());
					
					k += move_Speed;
					imgLabel.setLocation(k, 0);
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			move=false;
			jFrame.remove(bucket);
			jFrame.remove(cone);
			jFrame.remove(z);
			jFrame.remove(zz);
			jFrame.remove(bucket1);
			jFrame.remove(cone1);
			jFrame.remove(z1);
			jFrame.remove(zz1);
			jFrame.remove(bucket2);
			jFrame.remove(cone2);
			jFrame.remove(z2);
			jFrame.remove(zz2);
		}
	
		
		new FlowSunController(this);
		new ZombieController(8000,this);
		new Car(-10, 80, 0, jFrame);
		new Car(-10, 170, 1, jFrame);
		new Car(-10, 270, 2, jFrame);
		new Car(-10, 370, 3, jFrame);
		new Car(-10, 470, 4, jFrame);
		
		sunCountLabel.setVisible(true);
		
		movePeaPoint = new Point(360, -100);
		movePeaLabel = makeLabel(movd_single_bullet_plant, 360, -100, 71, 71);
		movePeaLabel.setVisible(false);
		add(movePeaLabel);

		moveFlowePoint = new Point(300, -100);
		moveFlowerLabel = makeLabel(move_sun_flower, 300, -100, 73, 74);
		moveFlowerLabel.setVisible(false);
		add(moveFlowerLabel);

		moveShovelPoint = new Point(700, -100);
		moveShovelLabel = makeLabel(shove, 700, -100, 100, 100);
		moveShovelLabel.setVisible(false);
		add(moveShovelLabel);
		
		moveIcePeaPoint = new Point(420,-100);
		moveIcePeaLabel = makeLabel(moveIcebean,420,-100,71,71);
		moveIcePeaLabel.setVisible(false);
		add(moveIcePeaLabel);
		
		movePotatoPoint = new Point(480,-100);
		movePotatoLabel = makeLabel(movePotato,480,-100,65,73);

		movePotatoLabel.setVisible(false);
		add(movePotatoLabel);

		moveChiliPoint = new Point(560, -100);
		moveChiliLabel = makeLabel(chili, 560, -100, 68, 89);
		moveChiliLabel.setVisible(false);
		add(moveChiliLabel);

		String sunName;
		
		
		
		// 只要游戏不结束，就不会退出该循环
		while (true) {
			movePeaLabel.setBounds((int) movePeaPoint.x - 40, (int) movePeaPoint.y - 40, 100, 100);
			//moveFlowerLabel.setBounds((int) moveFlowePoint.getX() - 40, (int) moveFlowePoint.getY() - 40, 100, 100);
			moveShovelLabel.setBounds((int) moveShovelPoint.getX() - 40, (int) moveShovelPoint.getY() - 40, 80, 80);
			moveIcePeaLabel.setBounds((int) moveIcePeaPoint.x - 40, (int) moveIcePeaPoint.y - 40, 100, 100);
			movePotatoLabel.setBounds((int) movePotatoPoint.x - 40, (int) movePotatoPoint.y - 40, 100, 100);
			moveChiliLabel.setBounds((int) moveChiliPoint.x - 40, (int) moveChiliPoint.y - 40, 100, 100);
			sunName = "" + count;
			sunCountLabel.setText(sunName);

			// 判断植物是否冷却完毕，若否，则显示灰色图标
			if(uPeaLabel.getHeight()>0) {
				uPeaLabel.setVisible(true);
				if ((System.currentTimeMillis() - PeaBeginTime)%5==0) 
					uPeaLabel.setSize(uPeaLabel.getWidth(), (int) (uPeaLabel.getHeight()-coolTime));
			} else {
				uPeaLabel.setVisible(false);
			}
			
			if(uFlowerLabel.getHeight()>0) {
				uFlowerLabel.setVisible(true);
				if ((System.currentTimeMillis() - flowerBeginTime)%10==0) 
					uFlowerLabel.setSize(uFlowerLabel.getWidth(), (int) (uFlowerLabel.getHeight()-coolTime));
			} else {
				uFlowerLabel.setVisible(false);
			}

			if(uIcePeaLabel.getHeight()>0) {
				uIcePeaLabel.setVisible(true);
				if ((System.currentTimeMillis() - icePeaBeginTime)%30==0) 
					uIcePeaLabel.setSize(uIcePeaLabel.getWidth(), (int) (uIcePeaLabel.getHeight()-coolTime));
			} else {
				uIcePeaLabel.setVisible(false);
			}

			if(uPotatoLabel.getHeight()>0) {
				uPotatoLabel.setVisible(true);
				if ((System.currentTimeMillis() - potatoBeginTime)%40==0) 
					uPotatoLabel.setSize(uPotatoLabel.getWidth(), (int) (uPotatoLabel.getHeight()-coolTime));
			} else {
				uPotatoLabel.setVisible(false);
			}

			if (uChiliLabel.getHeight()>0) {
				uChiliLabel.setVisible(true);
				if((System.currentTimeMillis() - chiliBeginTime)%50==0)
					uChiliLabel.setSize(uChiliLabel.getWidth(), (int) (uChiliLabel.getHeight()-coolTime));
			} else {
				uChiliLabel.setVisible(false);
			}

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 9; j++) {
					if (labels[i][j] != null && plants[i][j] != null && plants[i][j].getHp() <= 0) {
						remove(labels[i][j]);
						plants[i][j] = null;
					}
				}
			}

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < Zombie.zombies[i].size(); j++) {
					if (Zombie.zombies[i] != null) {						
						if(Zombie.zombies[i].get(j)!=null) {
							if(Zombie.zombies[i].get(j).isGameOver() == true) zombieWin = true;
							
						}
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
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public int getSpecies() {
		return this.species;
	}
	
	public int getCount() {
		return this.count;
	}
	
	synchronized public void SetCount(int count) {
		this.count = count;
	}
	
	synchronized public void SetFlowerBeginTime(long flowerBeginTime) {
		this.flowerBeginTime = flowerBeginTime;
	}

	synchronized public void SetPeaBeginTime(long peaBeginTime) {
		this.PeaBeginTime = peaBeginTime;
	}
	
	synchronized public void SetIcePeaBeginTime(long icePeaBeginTime) {
		this.icePeaBeginTime = icePeaBeginTime;
	}
	
	synchronized public void SetPotatoBeginTime(long potatoBeginTime) {
		this.potatoBeginTime = potatoBeginTime;
	}
	
	synchronized public void SetChiliBeginTime(long chiliBeginTime) {
		this.chiliBeginTime = chiliBeginTime;
	}
	
}
