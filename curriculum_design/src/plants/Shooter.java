package plants;

import javax.swing.JFrame;
import javax.swing.JLabel;

import bullet.Bullet;
import game.ShooterJFrame;

public class Shooter extends Plant {
	private JFrame frame;
	private int x_index;
	private int y_index;
	public static int shootTime = 200;// 道具3

	public Shooter() {
		// 空构造方法
	}

	public Shooter(int x, int y, JFrame frame, int x_index, int y_index) {
		super();
		super.x = x;
		super.y = y;
		super.cooldown_time = 2;// 冰冻时间
		super.cost = 100;// 价格
		super.hp = 100;// 生命值
		this.frame = frame;
		this.x_index = x_index;
		this.y_index = y_index;

		new Thread(new PeaShooterThread()).start();// 开启新的线程并运行豌豆射手线程
	}

	public void fireBullet() {
//		boolean flag = false;
//		for(Zombie zombie : Zombie.zombies[getRow(y)])//遍历僵尸的y坐标
//		{
//			if(zombie.getHit_Piont() > 0) //如果僵尸还存活
//				flag = true;
//		}
//		if(flag)
//		{
//			Bullet bullet = new Bullet(x, y, 0,frame);//调用子弹类
//			Bullet.bullets[getRow(y)].add(bullet); //发射子弹
//		}
	}

	public void fire(int status) {
		if (System.currentTimeMillis() - ((ShooterJFrame) this.frame).getShootTime() < shootTime) {
			return;
		}
		((ShooterJFrame) this.frame).SetShootTime(System.currentTimeMillis());
		Bullet bullet;
		if (status == 1)
			bullet = new Bullet(x, y, 1, frame, true);// 调用蓝色子弹
		else if (status == 2)
			bullet = new Bullet(x, y, 2, frame, true);// 调用红色子弹
		else
			bullet = new Bullet(x, y, 0, frame, true);// 调用绿色子弹
		Bullet.bullets[getRow(y)].add(bullet); // 发射子弹
	}

	public static int getRow(int y) // 设置y位置
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
			// TODO 自动生成的方法存根
			try {
				while (true) {
					if (hp > 0)// 如果豌豆射手还存活
					{
						Thread.sleep(3000);// 线程暂停
						fireBullet();// 重新发射子弹
					} else {
						// *除去plants
						break;
					}
				}
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		}

	};
}
