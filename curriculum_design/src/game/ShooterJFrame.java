package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Container;
import java.awt.Font;
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
import controller.ZombieController;
import plants.Plant;
import plants.Shooter;
import sun.Sun;
import zombies.Zombie;

public class ShooterJFrame extends javax.swing.JFrame implements Runnable {

	public final static ImageIcon background = new ImageIcon(GameJFrame.class.getResource("/images/background1.jpg"));
	public final static ImageIcon SunBank = new ImageIcon(GameJFrame.class.getResource("/images/SunBankChange.png"));
	public final static ImageIcon sun_flower = new ImageIcon(
			GameJFrame.class.getResource("/images/sunflower_on.jpg"));
	public final static ImageIcon single_bullet_plant = new ImageIcon(
			GameJFrame.class.getResource("/images/card_norbeen_on.jpg"));
	public static ImageIcon movd_single_bullet_plant = new ImageIcon(
			GameJFrame.class.getResource("/images/single_bullet_plant.png"));// �㶹����
	//movd_single_bullet_plant=new ImageIcon(GameJFrame.class.getResource("/images/single_bullet_plant.png"));// �㶹����

	public final static ImageIcon movd_single_bullet_plant_2 = new ImageIcon(
			GameJFrame.class.getResource("/images/plant_icebean_7.png"));// ��������
	public final static ImageIcon movd_single_bullet_plant_3 = new ImageIcon(
			GameJFrame.class.getResource("/images/single_bullet_plant_fire.png"));// ��������
	public final static ImageIcon move_sun_flower = new ImageIcon(
			GameJFrame.class.getResource("/images/sun_flower1.png"));
	public final static ImageIcon shovel = new ImageIcon(GameJFrame.class.getResource("/images/shovel.png"));
	public final static ImageIcon shove = new ImageIcon(GameJFrame.class.getResource("/images/shove.png"));
	public final static ImageIcon card_norbeen_off = new ImageIcon(
			GameJFrame.class.getResource("/images/card_norbeen_off.jpg"));
	public final static ImageIcon sunflower_off = new ImageIcon(
			GameJFrame.class.getResource("/images/sunflower_off.jpg"));
	public final static ImageIcon card_icebeen_on = new ImageIcon(
			GameJFrame.class.getResource("/images/card_icebeen_on.jpg"));
	public final static ImageIcon card_icebeen_off = new ImageIcon(
			GameJFrame.class.getResource("/images/card_icebeen_off.jpg"));
	public static ImageIcon moveIcebean = new ImageIcon(GameJFrame.class.getResource("/images/plant_icebean_7.png"));// ��������
	public final static ImageIcon potato_on = new ImageIcon(GameJFrame.class.getResource("/images/potato_on.jpg"));
	public final static ImageIcon potato_off = new ImageIcon(GameJFrame.class.getResource("/images/potato_off.jpg"));
	public final static ImageIcon movePotato = new ImageIcon(
			GameJFrame.class.getResource("/images/plant_xiaojianguoqiang_01.png"));
	public final static ImageIcon chili_on = new ImageIcon(GameJFrame.class.getResource("/images/pepper_card(1).png"));
	public final static ImageIcon chili_off = new ImageIcon(
			GameJFrame.class.getResource("/images/pepper_card2(1).png"));
	public final static ImageIcon chili = new ImageIcon(GameJFrame.class.getResource("/images/tchill.png"));

	public static Plant[][] plants = new Plant[5][10]; // 5��10�еķ���
	public static JLabel[][] labels = new JLabel[5][10]; // ֲ���Ӧ�ı�ǩ
	
	private long PeaBeginTime = 0;
	private long flowerBeginTime = 0;
	private long icePeaBeginTime = 0;
	private long potatoBeginTime = 0;
	private long chiliBeginTime = 0;
	private long ShootTime = 0;
	static JLabel barLabel;
	static JLabel sunFlowerLabel;
	static JLabel peaLabel;
	static JLabel icePeaLabel;
	static JLabel potatoLabel;
	static JLabel chiliLabel;
	static JLabel sunCountLabel;
	//ʱ��
	static JLabel timeLabel;
	static JLabel movePeaLabel;
	static JLabel moveFlowerLabel;
	static JLabel moveShovelLabel;
	static JLabel moveIcePeaLabel;
	static JLabel movePotatoLabel;
	static JLabel moveChiliLabel;
	public static JLabel plantLabel;
	static JLabel shovelLabel;
	static JLabel uIcePeaLabel;
	static JLabel uPeaLabel;
	static JLabel uFlowerLabel;
	static JLabel uPotatoLabel;
	static JLabel uChiliLabel;
	Point movePeaPoint; // Point�� (getX,getY,getPoint)
	Point moveFlowePoint;
	Point moveShovelPoint;
	Point moveIcePeaPoint;
	Point movePotatoPoint;
	Point moveChiliPoint;
	int species, count = 1000; // species���ڴ�ŵ�ǰѡ�е�ֲ��
	boolean zombieWin = false;
	private ReentrantLock mainLock;

	// jFrameΪ��Ϸ����
	public static JFrame jFrame;


    public static Shooter shooter;
  
    //����
    public static int mark=0;
    //ʱ��10����
    public static long time=600;
    public static String timeS;
    
	private quitButton quit_button = new quitButton("quit");
	private MusicButton music_button;
	private AudioClip aau = null;
    



	public ShooterJFrame() {
		// ��ʼ����Ϸ����

		for (int i = 0; i < 5; i++) {
			Bullet.bullets[i] = new ArrayList<>();
		}

		for (int i = 0; i < 5; i++) {
			Zombie.zombies[i] = new ArrayList();
		}

		jFrame = this;
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		setBounds(500,200, 856, 600);
//		setLocation(-200,200);
		setBounds(250, 70, background.getIconWidth(), background.getIconHeight() + 40);

		setResizable(false);

		JLabel imgLabel = new JLabel(background);
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		Container cp = getContentPane();
		((JPanel) cp).setOpaque(false);

		this.setVisible(true);
		this.setFocusable(true);
	

		

		// ��ͼƬ�����ϱ�ǩ

//		uFlowerLabel = makeLabel(sunflower_off, 300, 0, 100, 100);
//		uFlowerLabel.setVisible(false);
//		add(uFlowerLabel);
//
//		sunFlowerLabel = makeLabel(sun_flower, 300, 0, 100, 100);
//		add(sunFlowerLabel);
//
//		uPeaLabel = makeLabel(card_norbeen_off, 360, -2, 100, 100);
//		add(uPeaLabel);
//		uPeaLabel.setVisible(false);
//
//		peaLabel = makeLabel(single_bullet_plant, 360, -2, 100, 100);
//		add(peaLabel);
//
//		uIcePeaLabel = makeLabel(card_icebeen_off, 420, 0, 100, 100);
//		add(uIcePeaLabel);
//		uIcePeaLabel.setVisible(false);
//
//		icePeaLabel = makeLabel(card_icebeen_on, 420, 0, 100, 100);
//		add(icePeaLabel);
//
//		uPotatoLabel = makeLabel(potato_off, 500, 15, 70, 70);
//		add(uPotatoLabel);
//		uPotatoLabel.setVisible(false);
//
//		potatoLabel = makeLabel(potato_on, 500, 15, 70, 70);
//		add(potatoLabel);
//
//		uChiliLabel = makeLabel(chili_off, 560, 0, 100, 100);
//		add(uChiliLabel);
//		uChiliLabel.setVisible(false);
//
//		chiliLabel = makeLabel(chili_on, 560, 0, 100, 100);
//		add(chiliLabel);

		sunCountLabel = new JLabel("",JLabel.CENTER);//��ԭ����sun�ĳ�mark
		sunCountLabel.setBounds(220, -35, 250, 150);
		
		timeLabel=new JLabel();
		timeLabel.setBounds(225, -30, 250, 150);
		add(sunCountLabel);
		add(timeLabel);
		
		Thread timeThread=new Thread()
		{
			public void run() {
				while (time > 0) {
					             time--;
					 	           try {
					                 Thread.sleep(1000);
					                 
					                 int mm = (int)time / 60 % 60;
					                 int ss = (int)time % 60;
					                 timeS= mm + ":" + ss;
				
					 	           }catch (Exception e) {
									// TODO: handle exception
					 	        	  e.printStackTrace();
								}
				
				}		 	           
			}
		};
		timeThread.start();

//		shovelLabel = makeLabel(shovel, 700, 0, 100, 100);
//		add(shovelLabel);
//
		barLabel = makeLabel(SunBank, 220, 0, 200, 100);
		add(barLabel);
		

		


		// GameController���ڲ�׽�û�������¼�
		ShooterController gamecontroller = new ShooterController(this);
		this.addMouseMotionListener(gamecontroller);
		this.addMouseListener(gamecontroller);

		// �½�һ���̣߳���run()
		Thread gameThread = new Thread(this);
		gameThread.start();


        // ���ֳ�ʼ��
        ShooterJFrame.shooter = new Shooter(400,265,this,2,2);
        plants[2][2] = ShooterJFrame.shooter;//y0-4 x0-8
		plantLabel = this.makeLabel(ShooterJFrame.movd_single_bullet_plant, 400,265, 100, 100);
		labels[2][2] = plantLabel;  

		this.add(plantLabel);

		new ZombieController(5000,this);
		
		this.quit_button.setBounds(750, 10, 89, 22);
		this.quit_button.setJFrame(this);
		this.getContentPane().add(quit_button);
		
	}

	// �����ǩ
	public JLabel makeLabel(ImageIcon im, int x, int y, int w, int h) {
		JLabel temp = new JLabel(im);
		temp.setBounds(x, y, w, h);
		return temp;
	}

	// �ж��û�������ĸ�ֲ��
	int isOk(int x, int y) {
//		if (x >= sunFlowerLabel.getX() && x <= sunFlowerLabel.getX() + 80 && y >= sunFlowerLabel.getY()
//				&& y <= sunFlowerLabel.getY() + 100 && System.currentTimeMillis() - flowerBeginTime > 5000
//				&& count >= 50)
//			return 1;
//		if (x >= peaLabel.getX() && x <= peaLabel.getX() + 80 && y >= peaLabel.getY() && y <= peaLabel.getY() + 100
//				&& System.currentTimeMillis() - PeaBeginTime > 7000 && count >= 100) {
//			return 2;
//		}
//		if (x >= shovelLabel.getX() && x <= shovelLabel.getX() + 100 && y >= shovelLabel.getY()
//				&& y <= shovelLabel.getY() + 100)
//			return 3;
		if (Sun.suns != null) {
			for (int i = 0; i < Sun.suns.size(); i++) {
				if (Sun.suns.get(i) != null) {
					if (Math.abs(Sun.suns.get(i).getX() - x) <= 35 && Math.abs(Sun.suns.get(i).getY() - y) <= 35)
						return 4;
				}

			}
		}
//		if (x >= icePeaLabel.getX() && x <= icePeaLabel.getX() + 100 && y >= icePeaLabel.getY()
//				&& y <= icePeaLabel.getY() + 100 && System.currentTimeMillis() - icePeaBeginTime >= 10000
//				&& count >= 170) {
//			return 5;
//		}
//		if (x >= potatoLabel.getX() && x <= potatoLabel.getX() + 70 && y >= potatoLabel.getY()
//				&& y <= potatoLabel.getY() + 70 && System.currentTimeMillis() - potatoBeginTime >= 5000
//				&& count >= 50) {
//			return 6;
//		}
//		if (x >= chiliLabel.getX() && x <= chiliLabel.getX() + 80 && y >= chiliLabel.getY()
//				&& y <= chiliLabel.getY() + 80 && System.currentTimeMillis() - chiliBeginTime >= 10000
//				&& count >= 150) {
//			return 7;
//		}
		return 0;
	}

	@Override
	public void run() { // ��ʼ��ʱִ��

		// ѭ����������
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

//		movePeaPoint = new Point(360, -100);
//		movePeaLabel = makeLabel(movd_single_bullet_plant, 360, -100, 100, 100);
//		movePeaLabel.setVisible(false);
//		add(movePeaLabel);
//
//		moveFlowePoint = new Point(300, -100);
//		moveFlowerLabel = makeLabel(move_sun_flower, 300, -100, 100, 100);
//		moveFlowerLabel.setVisible(false);
//		add(moveFlowerLabel);
//
//		moveShovelPoint = new Point(700, -100);
//		moveShovelLabel = makeLabel(shove, 700, -100, 100, 100);
//		moveShovelLabel.setVisible(false);
//		add(moveShovelLabel);
//
//		moveIcePeaPoint = new Point(420, -100);
//		moveIcePeaLabel = makeLabel(moveIcebean, 420, -100, 100, 100);
//		moveIcePeaLabel.setVisible(false);
//		add(moveIcePeaLabel);
//
//		movePotatoPoint = new Point(480, -100);
//		movePotatoLabel = makeLabel(movePotato, 480, -100, 100, 100);
//		movePotatoLabel.setVisible(false);
//		add(movePotatoLabel);
//
//		moveChiliPoint = new Point(560, -100);
//		moveChiliLabel = makeLabel(chili, 560, -100, 100, 100);
//		moveChiliLabel.setVisible(false);
//		add(moveChiliLabel);

		String sunName;

		// ֻҪ��Ϸ���������Ͳ����˳���ѭ��
		while (true) {
//			movePeaLabel.setBounds((int) movePeaPoint.x - 40, (int) movePeaPoint.y - 40, 100, 100);
//			moveFlowerLabel.setBounds((int) moveFlowePoint.getX() - 40, (int) moveFlowePoint.getY() - 40, 100, 100);
//			moveShovelLabel.setBounds((int) moveShovelPoint.getX() - 40, (int) moveShovelPoint.getY() - 40, 80, 80);
//			moveIcePeaLabel.setBounds((int) moveIcePeaPoint.x - 40, (int) moveIcePeaPoint.y - 40, 100, 100);
//			movePotatoLabel.setBounds((int) movePotatoPoint.x - 40, (int) movePotatoPoint.y - 40, 100, 100);
//			moveChiliLabel.setBounds((int) moveChiliPoint.x - 40, (int) moveChiliPoint.y - 40, 100, 100);
		
			
			sunCountLabel.setFont(new Font("BALD",Font.BOLD,90));
			sunCountLabel.setText(mark+"");
			
			timeLabel.setFont(new Font("BALD",Font.BOLD,20));
			timeLabel.setText(timeS);

			// �ж�ֲ���Ƿ���ȴ��ϣ���������ʾ��ɫͼ��
//			if (System.currentTimeMillis() - PeaBeginTime <= 7000) {
//				uPeaLabel.setVisible(true);
//			} else {
//				uPeaLabel.setVisible(false);
//			}
//
//			if (System.currentTimeMillis() - flowerBeginTime <= 5000) {
//				uFlowerLabel.setVisible(true);
//			} else {
//				uFlowerLabel.setVisible(false);
//			}
//
//			if (System.currentTimeMillis() - icePeaBeginTime <= 10000) {
//				uIcePeaLabel.setVisible(true);
//			} else {
//				uIcePeaLabel.setVisible(false);
//			}
//
//			if (System.currentTimeMillis() - potatoBeginTime <= 5000) {
//				uPotatoLabel.setVisible(true);
//			} else {
//
//				uPotatoLabel.setVisible(false);
//			}
//
//			if (System.currentTimeMillis() - chiliBeginTime <= 10000) {
//				uChiliLabel.setVisible(true);
//			} else {
//				uChiliLabel.setVisible(false);
//			}


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
						if (Zombie.zombies[i].get(j).isGameOver() == true)
							zombieWin = true;
					}
				}
			}

			if (zombieWin == true) {
				this.dispose();
				aau.stop();
				
				//add score
				UserDA.addScore(mark);
				
				new overGame();
				break;
			}

			repaint();
		}
	}
	
	//�̳߳�
	
	public interface ExecutorService extends Executor {
		 
	    void shutdown();
	    boolean isShutdown();
	    boolean isTerminated();
	    boolean awaitTermination(long timeout, TimeUnit unit)
	        throws InterruptedException;//�ж��쳣
	    
	}
	
		public static void Threadpool() {   
			  for (int i = 0; i < 10; i++) {  
			   final int index = i;  
			   try {  
			    Thread.sleep(index * 1000);  
			   } catch (InterruptedException e) {  
			    e.printStackTrace();  
			   }  
			   Executor cachedThreadPool = null;
			cachedThreadPool.execute(new Runnable() {  
			    public void run() {  
			     System.out.println(index);  
			    }  
			   });  
			  } 
		}
		private void runTask(Runnable task) {
		    final ReentrantLock runLock = null;
		    runLock.lock();
		    try {
		        int STOP = 0;
				int runState = 0;
				if (runState < STOP &&
		            Thread.interrupted() &&
		            runState >= STOP);

		        //�ȴ�֮ǰ��������߳���ȫ�����ٽ��лص�           
					int completedTasks = 0;
		        try {
		            task.run();
		            
					++completedTasks;
		        } catch (RuntimeException ex) {
		           
		            throw ex;
		        }
		    } finally {
		        runLock.unlock();
		    }
		    try {
		        int runState = 0;
				int state = runState;
		        int SHUTDOWN = 0;
				boolean allowCoreThreadTimeOut;
				int poolSize;//�����߳������һЩ����
				Object keepAliveTime;
				
		        }finally {
					
				}
		    try {
		        int poolSize = 0;
				Object firstTask = null;
				int maximumPoolSize = 0;
				Object RUNNING = null;
				Object runState = null;
				Object t;
				if (poolSize < maximumPoolSize && runState == RUNNING)
		            t = firstTask;
		    } finally {
		       
		    }
		}
		
		public boolean addIfUnderCorePoolSize(Runnable firstTask) {//���ӳ�����
		    String t = null;
		    final ReentrantLock mainLock = this.mainLock;
		    mainLock.lock();
		    try {
		        int corePoolSize = 0;
				int poolSize = 0;
				Object RUNNING = null;
				Object runState = null;
				if (poolSize < corePoolSize && runState == RUNNING)
		            t = firstTask.toString();        //�����߳�ȥִ�е�һ����������   
		        } finally {
		        mainLock.unlock();
		    }
		    if (t == null)
		        return false;
		    else return true;
		   
		}
		
		public boolean prestartCoreThread() {
		    return addIfUnderCorePoolSize(null); //ע�⴫��ȥ�Ĳ�����null
		}
		 
		public int prestartAllCoreThreads() {
		    int n = 0;
		    while (addIfUnderCorePoolSize(null))//ע�⴫��ȥ�Ĳ�����null
		        ++n;
		    return n;
		}
		

	public int getSpecies() {
		return this.species;
	}

	
	public int getMark() {
		return this.mark;
	}
	
	synchronized public void SetMark(int mark) {
		this.mark = mark;

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

	synchronized public void SetShootTime(long shootTime) {
		this.ShootTime = shootTime;
	}

	public long getShootTime() {
		return this.ShootTime;
	}
	
	

	
}


		   
		       
		    
