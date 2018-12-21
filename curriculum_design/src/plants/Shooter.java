package plants;

import javax.swing.JFrame;
import javax.swing.JLabel;

import bullet.Bullet;
import game.ShooterJFrame;

public class Shooter extends Plant {
	private JFrame frame;
	private int x_index;
	private int y_index;
	public static int shootTime = 200;// ����3

	public Shooter() {
		// �չ��췽��
	}

	public Shooter(int x, int y, JFrame frame, int x_index, int y_index) {
		super();
		super.x = x;
		super.y = y;
		super.cooldown_time = 2;// ����ʱ��
		super.cost = 100;// �۸�
		super.hp = 100;// ����ֵ
		this.frame = frame;
		this.x_index = x_index;
		this.y_index = y_index;

		new Thread(new PeaShooterThread()).start();// �����µ��̲߳������㶹�����߳�
	}

	public void fireBullet() {
//		boolean flag = false;
//		for(Zombie zombie : Zombie.zombies[getRow(y)])//������ʬ��y����
//		{
//			if(zombie.getHit_Piont() > 0) //�����ʬ�����
//				flag = true;
//		}
//		if(flag)
//		{
//			Bullet bullet = new Bullet(x, y, 0,frame);//�����ӵ���
//			Bullet.bullets[getRow(y)].add(bullet); //�����ӵ�
//		}
	}

	public void fire(int status) {
		if (System.currentTimeMillis() - ((ShooterJFrame) this.frame).getShootTime() < shootTime) {
			return;
		}
		((ShooterJFrame) this.frame).SetShootTime(System.currentTimeMillis());
		Bullet bullet;
		if (status == 1)
			bullet = new Bullet(x, y, 1, frame, true);// ������ɫ�ӵ�
		else if (status == 2)
			bullet = new Bullet(x, y, 2, frame, true);// ���ú�ɫ�ӵ�
		else
			bullet = new Bullet(x, y, 0, frame, true);// ������ɫ�ӵ�
		Bullet.bullets[getRow(y)].add(bullet); // �����ӵ�
	}

	public static int getRow(int y) // ����yλ��
	{
		y += 10;
		if (y > 110 && y < 210)
			return 1;
		else if (y >= 210 && y < 300)
			return 2;
		else if (y >= 300 && y < 390)
			return 3;
		else if (y >= 390 && y < 480)
			return 4;
		else
			return 0;
	}

	public void change() {

	}

	public void refresh(int status) {
//		this.move("right", -1);
//		JLabel plantLabel = ShooterJFrame.plantLabel;
//		if (status == 0)
//			plantLabel = ((ShooterJFrame) frame).makeLabel(ShooterJFrame.movd_single_bullet_plant, x_index * 80 + 240,
//					y_index * 90 + 85, 100, 100);
//		if (status == 1)
//			plantLabel = ((ShooterJFrame) frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_2, x_index * 80 + 240,
//					y_index * 90 + 85, 100, 100);
//		if (status == 2)
//			plantLabel = ((ShooterJFrame) frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_3, x_index * 80 + 240,
//					y_index * 90 + 85, 100, 100);
//		frame.add(plantLabel);
		
		Plant[][] plants = ShooterJFrame.plants;
		JLabel[][] labels = ShooterJFrame.labels;
		JLabel plantLabel = ShooterJFrame.plantLabel;
		
		ShooterJFrame.shooter = new Shooter(x_index * 80 + 240, y_index * 90 + 85, this.frame, x_index, y_index);
		plants[y_index][x_index] = ShooterJFrame.shooter;
		
		if (status == 0) {
			plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant,
					x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			
			this.frame.remove(labels[y_index][x_index]);
			labels[y_index][x_index] = plantLabel;
			this.frame.add(plantLabel);
			
			this.setHp(0);
			return;
		} 
		
		if (status == 1) {
			plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_2,
					x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			
			this.frame.remove(labels[y_index][x_index]);
			labels[y_index][x_index] = plantLabel;
			this.frame.add(plantLabel);
			
			this.setHp(0);
			return;
		} 
		
		if (status == 2) {
			plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_3,
					x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			
			this.frame.remove(labels[y_index][x_index]);
			labels[y_index][x_index] = plantLabel;
			this.frame.add(plantLabel);
			
			this.setHp(0);
			return;
		} 
		
	}

	public void move(String order, int status) {

		Plant[][] plants = ShooterJFrame.plants;
		JLabel[][] labels = ShooterJFrame.labels;
		JLabel plantLabel = ShooterJFrame.plantLabel;

		if (order.equals("right") && this.x_index < 8) {
			this.x_index += 1;

			ShooterJFrame.shooter = new Shooter(x_index * 80 + 240, y_index * 90 + 85, this.frame, x_index, y_index);
			plants[y_index][x_index] = ShooterJFrame.shooter;
			if (status == 1) {
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_2,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
				labels[y_index][x_index] = plantLabel;
				this.frame.add(plantLabel);
				this.setHp(0);
			} else if (status == 2) {
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_3,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
				labels[y_index][x_index] = plantLabel;
				this.frame.add(plantLabel);
				this.setHp(0);
			} else if (status == 0) {
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
				labels[y_index][x_index] = plantLabel;
				this.frame.add(plantLabel);
				this.setHp(0);
			} else if (status == -1) {
				// plantLabel.setVisible(false);
				// this.frame.remove(plantLabel);
			}

			// labels[y_index][x_index] = plantLabel;
			// this.frame.add(plantLabel);
			// this.setHp(0);
			return;
		}

		if (order.equals("left") && this.x_index > 0) {
			this.x_index -= 1;

			ShooterJFrame.shooter = new Shooter(x_index * 80 + 240, y_index * 90 + 85, this.frame, x_index, y_index);
			plants[y_index][x_index] = ShooterJFrame.shooter;
			if (status == 1)
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_2,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			else if (status == 2)
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_3,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			else
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			labels[y_index][x_index] = plantLabel;
			this.frame.add(plantLabel);

			this.setHp(0);
			return;
		}

		if (order.equals("up") && this.y_index > 0) {
			this.y_index -= 1;

			ShooterJFrame.shooter = new Shooter(x_index * 80 + 240, y_index * 90 + 85, this.frame, x_index, y_index);
			plants[y_index][x_index] = ShooterJFrame.shooter;
			if (status == 1)
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_2,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			else if (status == 2)
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_3,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			else
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			labels[y_index][x_index] = plantLabel;
			this.frame.add(plantLabel);

			this.setHp(0);
			return;
		}

		if (order.equals("down") && this.y_index < 4) {
			this.y_index += 1;

			ShooterJFrame.shooter = new Shooter(x_index * 80 + 240, y_index * 90 + 85, this.frame, x_index, y_index);
			plants[y_index][x_index] = ShooterJFrame.shooter;
			if (status == 1)
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_2,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			else if (status == 2)
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant_3,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			else
				plantLabel = ((ShooterJFrame) this.frame).makeLabel(ShooterJFrame.movd_single_bullet_plant,
						x_index * 80 + 240, y_index * 90 + 85, 100, 100);
			labels[y_index][x_index] = plantLabel;
			this.frame.add(plantLabel);

			this.setHp(0);
			return;
		}
	}

	class PeaShooterThread implements Runnable {

		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			try {
				while (true) {
					if (hp > 0)// ����㶹���ֻ����
					{
						Thread.sleep(3000);// �߳���ͣ
						fireBullet();// ���·����ӵ�
					} else {
						// *��ȥplants
						break;
					}
				}
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

		}

	};
}
