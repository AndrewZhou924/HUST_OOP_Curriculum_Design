
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
	private int x, y; // �ӵ�λ��
	private int attack_Piont; // ��ATK��������
	private int fly_Speed; // �����ٶ�;
	private boolean slow_Down; // �Ƿ���м�������

	private JLabel m;
	private static int WIDTH = 36;
	private static int HEIGHT = 36;
	private final static ImageIcon bb = new ImageIcon(Bullet.class.getResource("/images/bb_20.png"));// ��ɫ�ӵ�

	private final static ImageIcon bb_fire = new ImageIcon(Bullet.class.getResource("/images/gif/bb_fire.gif"));// �����ӵ�
	private final static ImageIcon bb_blue = new ImageIcon(Bullet.class.getResource("/images/gif/PB-10.png"));// ��ɫ�ӵ�

	private final static ImageIcon bb_hit = new ImageIcon(Bullet.class.getResource("/images/bullet_hit.png"));// �ӵ��ҵ���ʬ����ʱ������Ч����Ȼ������Ϸ�ﲻ���ԣ�
	private JFrame frame;
	private Bullet bullet;
	public static ArrayList<Bullet>[] bullets = new ArrayList[5];// ����λ�ã����ϵ���5�У�ÿһ�д���һ��ArrayList�����ж�
	private Zombie zombie;
	boolean hit = false;// �����ж��Ƿ�򵽽�ʬ��������Ϊtrue����ֹͣ�ӵ�����Ч��λ�ƣ��������Ϊ�ж��Ƿ��ƶ���

	public Bullet(int x, int y, int type, JFrame frame) {
		bullet = this;
		this.frame = frame;
		this.x = x + 60;
		this.y = y + 10;
		this.attack_Piont = 50;// ��������
		this.fly_Speed = 4;
		if (type == 0)// 0Ϊ��ͨ�ӵ���1Ϊ��ɫ�����ӵ�
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
		this.attack_Piont = 50;// ��������
		this.fly_Speed = 4;
		if (type == 0)// 0Ϊ��ͨ�ӵ���1Ϊ��ɫ�����ӵ�,2Ϊ����
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
		x += fly_Speed;// ���ݷ����ٶ������ӵ�ˮƽλ��

	}

	public void hitZom() {
		if (zombie == null)
			return;
		if (zombie.getHit_Piont() > 0)// ��ʬ����
		{
			// bullets[PeaShooter.getRow(y)].remove(bullet);// �Ѹ��ӵ��Ӹ��ж�Ӧ���ӵ�������ȥ��//model

			m.setIcon(bb_hit);// ���ӵ������ͼƬ���а�//Ȼ���˴��Ѿ���m��frame��ȥ����..//��Ӧframe.remove,���޸ģ��ӵ�����Ч������
			m.setBounds(x, y, 40, 40);
			//frame.repaint();
			zombie.setHit_Piont(zombie.getHit_Piont() - attack_Piont);
			ShooterJFrame.mark++;                                                        //��ʬ�������ӻ�������
			zombie.setSlow(this.isSlow_Down());
			bullets[PeaShooter.getRow(y)].remove(bullet);
			try {
				Thread.sleep(200);// �ӵ�����Ч��ʱ��
			} catch (InterruptedException e) {
			}
			// hit = true;// �жϴ򵽽�ʬ�ˣ������ӵ���Ӧ�ƶ�//model

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
		if(zombie.getX()+5<this.x && zombie.getX()+80>this.x) //��ʬ����ķ�Χ
		{
			// z.setHit_Piont(z.getHit_Piont() - attack_Piont);
			// ��ǰ�ӵ������Ľ�ʬ
			return true;
		}
		return false;// û����������false
	}

	public static boolean outOfBounds(int x)// �Ƿ񳬳�����߽磬û�г�������ture����������false���������������������ᣩ
	{
		if (x > 1500)
			return false;
		else
			return true;
	}

	class moveBullet implements Runnable {

		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			while (outOfBounds(x) && !isMeet())// �ӵ�û�г�����Χ��û�д򵽽�ʬ��Ҫ�ƶ�
			{
				try {
					Thread.sleep(20);// ����ÿ֡���ٶ�
					move();
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}

			}
			hitZom();
			// bullets[PeaShooter.getRow(y)].remove(bullet);//�Ѿ���move()��ȥ����

			//frame.remove(m);// �ӵ�����Ч��������remove
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
