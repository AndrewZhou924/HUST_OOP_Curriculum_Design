package zombies;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.GameJFrame;
import game.eatMusic;


public class Zombie implements Comparable<Zombie>{ //extends JPanel{
	
	private int x, y;           //��ʬλ��
	private int attack_Piont;   //��ATK��������
	private int move_Speed;     //�ƶ��ٶ�
	private int hp;      //��HP������ֵ
	private boolean isSlow;     //�Ƿ񱻼���
	private boolean isMove;     //�Ƿ��ƶ�
	private boolean isBurned = false;//��¼�Ƿ���������
	private boolean ishead=true;//��¼ͷ�Ƿ����
	//public boolean isHit = false;//������ģʽ
	private int k=0;

	private final static ImageIcon rua1 = new ImageIcon(Zombie.class.getResource("/images/gif/Zombie3.gif"));
	private final static ImageIcon rua2 = new ImageIcon(Zombie.class.getResource("/images/gif/ZombieLostHead.gif"));
	private final static ImageIcon rua3 = new ImageIcon(Zombie.class.getResource("/images/gif/ZombieHead.gif"));
	private final static ImageIcon rua4 = new ImageIcon(
			Zombie.class.getResource("/images/gif/ZombieLostHeadAttack.gif"));
	private final static ImageIcon rua5 = new ImageIcon(Zombie.class.getResource("/images/gif/ZombieAttack.gif"));
	private final static ImageIcon rua6 = new ImageIcon(Zombie.class.getResource("/images/gif/ZombieDie.gif"));
	private final static ImageIcon rua7 = new ImageIcon(Zombie.class.getResource("/images/gif/ConeheadZombie.gif"));
	private final static ImageIcon rua8 = new ImageIcon(
			Zombie.class.getResource("/images/gif/ConeheadZombieAttack.gif"));
	private final static ImageIcon rua9 = new ImageIcon(Zombie.class.getResource("/images/gif/BucketheadZombie.gif"));
	private final static ImageIcon rua10 = new ImageIcon(
			Zombie.class.getResource("/images/gif/BucketheadZombieAttack.gif"));
	private final static ImageIcon rua11 = new ImageIcon(Zombie.class.getResource(""));
	private final static ImageIcon burned = new ImageIcon(Zombie.class.getResource("/images/gif/BoomDie.gif"));
	private JLabel burnedLabel;
	private JLabel z1, z2, z3, z4, z5, z6, z7, z8;// һ����ʬͼƬ

	private int type;

	private JFrame zFrame;
	public static ArrayList<Zombie>[] zombies = new ArrayList[5];// �������·���ϵĽ�ʬ��̬����
	private boolean gameOver, eat = false;// ��¼�Ƿ������Ϸ�����߽�ʬ�Ƿ�ʼ��ֲ��
	// ���췽��

	public Zombie(int type,int movespeed, JFrame frame) {// type����ʬ����
		zFrame = frame;
		x = 850;
		y = (int) (Math.random() * 5);// ��ʱxy����ʬ������
		attack_Piont = 10;
		move_Speed = 5;
		hp = 600;

		isSlow = false;
		isMove = true;
		gameOver = false;
		this.type = type;

		z1 = new JLabel(rua1);// ��ͷ
		z1.setBounds(this.x, 40 + y * 90, 106, 144);
		z1.setVisible(false);
		zFrame.getLayeredPane().add(z1, new Integer(3+y));

		z2 = new JLabel(rua2);// û��ͷ
		z2.setBounds(this.x, 40 + y * 90, 106, 144);
		z2.setVisible(false);
		zFrame.getLayeredPane().add(z2, new Integer(3+y));

		z3 = new JLabel(rua3);// ͷ
		z3.setBounds(this.x, 40 + y * 90, 150, 186);
		z3.setVisible(false);
		zFrame.getLayeredPane().add(z3, new Integer(3+y));

		z4 = new JLabel(rua4);// û��ͷ��
		z4.setBounds(this.x, 40 + y * 90, 99, 144);
		z4.setVisible(false);
		zFrame.getLayeredPane().add(z4, new Integer(3+y));

		z5 = new JLabel(rua5);// ��ͷ��
		z5.setBounds(this.x, 40 + y * 90, 99, 144);
		z5.setVisible(false);
		zFrame.getLayeredPane().add(z5, new Integer(3+y));

		z6 = new JLabel(rua6);// ��ͷ��
		z6.setBounds(this.x, 40 + y * 90, 166, 144);
		z6.setVisible(false);
		zFrame.getLayeredPane().add(z6, new Integer(3+y));

		if (this.type == 0) {
			z7 = new JLabel(rua11);
			z8 = new JLabel(rua11);
			z7.setVisible(false);
			z8.setVisible(false);
			zFrame.getLayeredPane().add(z7, new Integer(3+y));
			zFrame.getLayeredPane().add(z8, new Integer(3+y));
		}

		if (this.type == 1) {
			hp += 1000;
			z7 = new JLabel(rua7);// ��ͷ��
			z7.setBounds(this.x, 40 + y * 90, 96, 144);
			z7.setVisible(false);

			//zFrame.add(z7);
			zFrame.getLayeredPane().add(z7, new Integer(3+y));
			
			z8 = new JLabel(rua8);//��ͷ��
			z8.setBounds(this.x, 40 + y * 90, 96, 144);
			z8.setVisible(false);
			//zFrame.add(z8);
			zFrame.getLayeredPane().add(z8, new Integer(3+y));
		}

		if (this.type == 2) {
			hp += 1500;
			z7 = new JLabel(rua9);// ��ͷ��
			z7.setBounds(this.x, 40 + y * 90, 96, 144);
			z7.setVisible(false);

			//zFrame.add(z7);
			zFrame.getLayeredPane().add(z7, new Integer(3+y));
			
			z8 = new JLabel(rua10);//��ͷ��
			z8.setBounds(this.x, 40 + y * 90, 96, 144);
			z8.setVisible(false);
			//zFrame.add(z8);
			zFrame.getLayeredPane().add(z8, new Integer(3+y));
		}

		zombies[y].add(this);
		new Thread(new moveZombie()).start();

	}

	@Override
	public int compareTo(Zombie zombie) {
		// TODO Auto-generated method stub
		if (this.getX() > zombie.getX())
			return 1;
		else if (this.getX() < zombie.getX())
			return -1;
		return 0;
	}

	public boolean isBurned() {
		return isBurned;
	}

	public void setBurned(boolean isBurned) {
		this.isBurned = isBurned;
	}

	public class moveZombie implements Runnable {
		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			while (hp > 0)// ����ʬhp>0
			{
				try {
					if (x <= 780) {

						int temp = (x + 20 - 74) / 80;

						if (GameJFrame.plants[y][temp] != null) {// ����ֲ��ʱ
							isMove = false;// �����ƶ�
							eat = true;// ��ʼ��ֲ��
							GameJFrame.plants[y][temp].setHp(GameJFrame.plants[y][temp].getHp() - attack_Piont);// ֲ���Ѫ
						} else
							isMove = true;
					}
					if (isMove) {// ��ʬ�ƶ�ʱ��ͼƬ����
						if (isSlow)
							x -= (move_Speed / 2);// ����ʬ�����������ֵ��ӵ� ���ý�ʬ�ƶ���ʽ
						else
							x -= move_Speed;
						if (hp > 2000) { // ���hp����2000������ͨ��ʬ����������Ͱ����·�ϣ�
							z7.setVisible(true);
							z8.setVisible(false);
							z7.setLocation(x, 40 + y * 90);
							Thread.sleep(225);
						} else {
							z7.setVisible(false); // ��hpС��2000ʱ�����⽫
							zFrame.getLayeredPane().remove(z7);
							if (hp <= 100 && hp > 0) {
								if (ishead) {

									z3.setVisible(true);
									z3.setLocation(x + 4, 40 + y * 90);
									ishead = false;
								}
								if (!ishead) {
									++k;
								}
								if (k >= 20) {
									z3.setVisible(false);
									//zFrame.remove(z3);
									zFrame.getLayeredPane().remove(z3);
								}
								z1.setVisible(false);
								//zFrame.remove(z1);
								//zFrame.remove(z5);
								zFrame.getLayeredPane().remove(z1);
								zFrame.getLayeredPane().remove(z5);
								z2.setVisible(true);
								z2.setLocation(x, 40 + y * 90);
								Thread.sleep(225);
							} else {
								z1.setVisible(true);
								z1.setLocation(x, 40 + y * 90);
								Thread.sleep(225);
							}
						}

					} else {
						if (hp > 600) {
							z7.setVisible(false);
							z8.setVisible(true);
							z8.setLocation(x, 40 + y * 90);
							Thread.sleep(225);
						} else {
							z8.setVisible(false);
							zFrame.getLayeredPane().remove(z8);
							if (hp <= 100 && hp > 0) {
								z2.setVisible(false);
								z5.setVisible(false);
								zFrame.getLayeredPane().remove(z5);
								zFrame.getLayeredPane().remove(z1);
								z4.setVisible(true);
								z4.setLocation(x, 40 + y * 90);
								Thread.sleep(225);
								z4.setVisible(false);
							} else {
								z1.setVisible(false);
								z5.setVisible(true);
								z5.setLocation(x, 40 + y * 90);
								Thread.sleep(225);
								z5.setVisible(false);
							}
						}

						if (eat == true) {// ����������
							Thread musiThread = new Thread(new eatMusic());
							musiThread.start();
							eat = false;
						}
					}
					if (x <= -10) {// ��ʬ�߽����� ��Ϸ����
						gameOver = true;
						break;
					}

				} catch (InterruptedException e) {

					e.printStackTrace();
				}

			}
			if (isBurned)// ����ʬ������������ͼƬ����
			{
				playBurstSound();
				burnedLabel = new JLabel(burned);
				burnedLabel.setBounds(x + 10, 70 + y * 90, 66, 106);

				try {
					Thread.sleep(50);

//					System.out.println("before remove: "+zombies[y].size());

					for (Zombie z : zombies[y]) {
						if (z.getHit_Piont() <= 0) {
							zombies[y].remove(z);
							break;
						}
					}

//					Zombie.zombies[y].remove(this);//�ӽ�ʬ�б���ɾ�������ʬ

//					System.out.println("after remove: "+zombies[y].size());
					zFrame.getLayeredPane().remove(z1);
					zFrame.getLayeredPane().remove(z2);
					zFrame.getLayeredPane().remove(z3);
					zFrame.getLayeredPane().remove(z4);
					zFrame.getLayeredPane().remove(z5);
					zFrame.getLayeredPane().remove(z6);
					zFrame.getLayeredPane().remove(z7);
					zFrame.getLayeredPane().remove(z8);

					//zFrame.invalidate();
					Thread.sleep(50);
					zFrame.add(burnedLabel);// ���ϱ�������ͼƬ
					Thread.sleep(2000);
					burnedLabel.setVisible(false);
					zFrame.remove(burnedLabel);// ��ȥ��������ͼƬ
					// zFrame.invalidate();//�����Jframe������ʾ

				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			} else {// �ų����� ��ֲ�� ����ֻʣ�±�������״̬
				try {
					//Thread.sleep(20);
					zombies[y].remove(this);
					playDieSound();
					/*zFrame.remove(z1);
					zFrame.remove(z2);
					zFrame.remove(z3);
					zFrame.remove(z4);
					zFrame.remove(z5);
					zFrame.remove(z7);*/
					zFrame.getLayeredPane().remove(z1);
					zFrame.getLayeredPane().remove(z2);
					zFrame.getLayeredPane().remove(z3);
					zFrame.getLayeredPane().remove(z4);
					zFrame.getLayeredPane().remove(z5);
//					zFrame.getLayeredPane().remove(z6);
					zFrame.getLayeredPane().remove(z7);

					z6.setVisible(true);
					z6.setLocation(x - 50, 40 + y * 90);
					Thread.sleep(1500);

					//zFrame.remove(z6);
					zFrame.getLayeredPane().remove(z6);
					//zFrame.invalidate();
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}

			}
		}

	}

	private void playDieSound() {
		AudioClip aau;
		try {
			File f = new File("curriculum_design\\src\\sounds\\ignite.wav");
			URI uri = f.toURI();
			URL url = uri.toURL();
			aau = Applet.newAudioClip(url);
			aau.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void playBurstSound() {
		AudioClip aau;
		try {
			File f = new File("curriculum_design\\src\\sounds\\cherrybomb.wav");
			URI uri = f.toURI();
			URL url = uri.toURL();
			aau = Applet.newAudioClip(url);
			aau.play();
		} catch (Exception e) {
			e.printStackTrace();
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

	public int getMove_Speed() {
		return move_Speed;
	}

	public void setMove_Speed(int move_Speed) {
		this.move_Speed = move_Speed;
	}

	public int getHit_Piont() {
		return hp;
	}

	public void setHit_Piont(int hit_Piont) {
		this.hp = hit_Piont;
	}

	public boolean isSlow() {
		return isSlow;
	}

	public void setSlow(boolean isSlow) {
		this.isSlow = isSlow;
	}

	public boolean isMove() {
		return isMove;
	}

	public void setMove(boolean isMove) {
		this.isMove = isMove;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getType() {
		return this.type;
	}

	public void SetType(int type) {
		this.type = type;
	}
}
