
//	private final static ImageIcon bb = new ImageIcon(Bullet.class.getResource("/images/bb_20.png"));
//	private final static ImageIcon bb_blue = new ImageIcon(Bullet.class.getResource("/images/bb_20_BLUE.png"));
//	private final static ImageIcon bb_hit = new ImageIcon(Bullet.class.getResource("/images/bullet_hit.png"));
package bullet;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.ShooterJFrame;
import plants.PeaShooter;
import zombies.Zombie;

public class Bullet {// extends JLabel {
	private int x, y; // 子弹位置
	private int attack_Piont; // 即ATK，攻击力
	private int fly_Speed; // 飞行速度;
	private boolean slow_Down; // 是否带有减速属性

	private JLabel m;
	private static int WIDTH = 36;
	private static int HEIGHT = 36;
	private final static ImageIcon bb = new ImageIcon(Bullet.class.getResource("/images/bb_20.png"));// 绿色子弹

	private final static ImageIcon bb_fire = new ImageIcon(Bullet.class.getResource("/images/gif/bb_fire.gif"));// 火球子弹
	private final static ImageIcon bb_blue = new ImageIcon(Bullet.class.getResource("/images/gif/PB-10.png"));// 蓝色子弹

	private final static ImageIcon bb_hit = new ImageIcon(Bullet.class.getResource("/images/bullet_hit.png"));// 子弹砸到僵尸身上时的破碎效果（然而在游戏里不明显）
	private JFrame frame;
	private Bullet bullet;
	public static ArrayList<Bullet>[] bullets = new ArrayList[5];// 根据位置（从上到下5行）每一行创建一个ArrayList用于判断
	private Zombie zombie;
	boolean hit = false;// 用于判断是否打到僵尸，打到了设为true用于停止子弹破碎效果位移（可以理解为判断是否移动）

	public Bullet(int x, int y, int type, JFrame frame) {
		bullet = this;
		this.frame = frame;
		this.x = x + 60;
		this.y = y + 10;
		this.attack_Piont = 50;// 攻击点数
		this.fly_Speed = 4;
		if (type == 0)// 0为普通子弹，1为蓝色减速子弹
		{
			this.slow_Down = false;
			m = new JLabel(bb);
		} else if (type == 1) {
			this.slow_Down = true;
			m = new JLabel(bb_blue);
		} else if (type == 2) {
			this.slow_Down = false;
			m = new JLabel(bb_fire);
		}
		
		AudioClip aau = null;
		try {
			File f = new File("curriculum_design\\src\\sounds\\shoop.wav");
			URI uri = f.toURI();
			URL url = uri.toURL();

			aau = Applet.newAudioClip(url);
			aau.play();
		} catch (Exception e) {
			e.printStackTrace();
		}

		m.setBounds(this.x+10, this.y, WIDTH, HEIGHT);
		frame.add(m);
		new Thread(new moveBullet()).start();
	}

	public Bullet(int x, int y, int type, JFrame frame, boolean Shoot) {
		bullet = this;
		this.frame = frame;
		this.x = x + 60;
		this.y = y + 10;
		this.attack_Piont = 50;// 攻击点数
		this.fly_Speed = 4;
		if (type == 0)// 0为普通子弹，1为蓝色减速子弹,2为火球
		{
			this.slow_Down = false;
			m = new JLabel(bb);
		} else if (type == 1) {
			this.slow_Down = true;
			m = new JLabel(bb_blue);
		} else if (type == 2) {
			this.slow_Down = false;
			this.attack_Piont = 100;
			m = new JLabel(bb_fire);
		}

		m.setBounds(this.x, this.y, WIDTH, HEIGHT);
		//setPosition(m, )
		//frame.add(m);
		frame.getLayeredPane().add(m, new Integer(3));
		new Thread(new moveBullet()).start();
	}

	public void move() {
		m.setLocation(x, y);
		x += fly_Speed;// 根据飞行速度增加子弹水平位移

	}

	public void hitZom() {
		if (zombie == null)
			return;
		if (zombie.getHit_Piont() > 0)// 僵尸活着
		{
			// bullets[PeaShooter.getRow(y)].remove(bullet);// 把该子弹从该行对应的子弹数组中去除//model

			m.setIcon(bb_hit);// 把子弹破碎的图片进行绑定//然而此处已经把m从frame里去除了..//不应frame.remove,已修改，子弹破碎效果出现
			m.setBounds(x, y, 40, 40);
			//frame.repaint();
			zombie.setHit_Piont(zombie.getHit_Piont() - attack_Piont);
			ShooterJFrame.mark++;                                                        //僵尸被打到增加积分数量
			zombie.setSlow(this.isSlow_Down());
			bullets[PeaShooter.getRow(y)].remove(bullet);
			try {
				Thread.sleep(200);// 子弹破碎效果时间
			} catch (InterruptedException e) {
			}
			// hit = true;// 判断打到僵尸了，破碎子弹不应移动//model

		}

	}

	public boolean isMeet() {
		boolean flag1 = false;
		Zombie z1 = null;
		for (Zombie temp_z : Zombie.zombies[PeaShooter.getRow(y)]) {
			if (!flag1 && temp_z.getHit_Piont() > 0) {
				z1 = temp_z;
				flag1 = true;
				continue;
			}
		}
		if(z1==null) return false;
		this.zombie = z1;
		if(zombie.getX()+5<this.x && zombie.getX()+80>this.x) //僵尸身体的范围
		{
			// z.setHit_Piont(z.getHit_Piont() - attack_Piont);
			// 当前子弹遇到的僵尸
			return true;
		}
		return false;// 没有遇到返回false
	}

	public static boolean outOfBounds(int x)// 是否超出画面边界，没有超出返回ture，超出返回false（这个函数名容易引起误会）
	{
		if (x > 1500)
			return false;
		else
			return true;
	}

	class moveBullet implements Runnable {

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while (outOfBounds(x) && !isMeet())// 子弹没有超出范围且没有打到僵尸，要移动
			{
				try {
					Thread.sleep(20);// 控制每帧的速度
					move();
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}

			}
			hitZom();
			// bullets[PeaShooter.getRow(y)].remove(bullet);//已经在move()中去除了

			//frame.remove(m);// 子弹破碎效果完了再remove
			frame.getLayeredPane().remove(m);
			//frame.repaint();
		}

	}

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

	public int getAttack_Piont() {
		return attack_Piont;
	}

	public void setAttack_Piont(int attack_Piont) {
		this.attack_Piont = attack_Piont;
	}

	public int getFly_Speed() {
		return fly_Speed;
	}

	public void setFly_Speed(int fly_Speed) {
		this.fly_Speed = fly_Speed;
	}

	public boolean isSlow_Down() {
		return slow_Down;
	}

	public void setSlow_Down(boolean slow_Down) {
		this.slow_Down = slow_Down;
	}

}
