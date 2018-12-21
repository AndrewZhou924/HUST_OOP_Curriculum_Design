package plants;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bullet.Bullet;
import game.GameJFrame;
import zombies.Zombie;

public class PeaShooter extends Plant{
	private JLabel firer;
	public final static ImageIcon Peashooterfire = new ImageIcon(GameJFrame.class.getResource("/images/gif/Peashooterfire.gif"));
	private JFrame frame;
	public PeaShooter(int x,int y,JFrame frame)
	{
		super();
		super.x = x;
		super.y = y;
		super.cooldown_time = 2;//冰冻时间
		super.cost= 100 ;//价格
		super.hp = 100;//生命值
		this.frame = frame;
		new Thread(new PeaShooterThread()).start();//开启新的线程并运行豌豆射手线程
	}
	public void fireBullet()
	{
		boolean flag = false;
		for(Zombie zombie : Zombie.zombies[getRow(y)])//遍历僵尸
		{
			if(zombie.getHit_Piont() > 0) //如果僵尸还存活
				flag = true;
		}
		if(flag)
		{
			firer=new JLabel(Peashooterfire);
			firer.setBounds(x, y, 71, 71); 
			game.GameJFrame.movePeaLabel.setVisible(false);
			//firer.setVisible(true);
			Bullet bullet = new Bullet(x, y, 0,frame);//在自己的位置处发射子弹
			Bullet.bullets[getRow(y)].add(bullet); //记录该子弹
		}
	}
	public static int getRow(int y) //设置y位置//没看懂是返回有什么作用
	{
		y += 10;
		if(y> 110 && y < 210)
			return 1;
		else if(y >= 210 && y < 300)
			return 2;
		else if(y >= 300 && y < 390)
			return 3;
		else if(y >= 390 && y < 480)
			return 4;
		else
			return 0;
	}
	class PeaShooterThread implements Runnable{

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			try {
				while(true)
				{
					if(hp > 0)//如果豌豆射手还存活
					{
						Thread.sleep(3000);//线程暂停
				 		fireBullet();//重新发射子弹
					}
					else
					{
						//*除去plants
						break;
					}
				}
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		
	}
}
