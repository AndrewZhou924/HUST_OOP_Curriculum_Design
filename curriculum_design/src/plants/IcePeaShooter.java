package plants;

import javax.swing.JFrame;

import bullet.Bullet;
import game.GameJFrame;
import zombies.Zombie;

public class IcePeaShooter extends Plant{
	private JFrame frame;
	public IcePeaShooter(int x,int y,JFrame frame) 
	{
		super();
		super.hp = 100; //生命值
		this.frame = frame; //
		new Thread(new PeaShooterThread()).start(); //开启新的线程并运行豌豆射手线程
	}
	public void fireBullet()
	{
		boolean flag = false;
		for(Zombie zombie : Zombie.zombies[PeaShooter.getRow(y)])//PeaShooter.getRow(y)是PeaShooter类的getRow函数里的y位置
		{
			if(zombie.getHit_Piont() > 0) //如果僵尸还存活
				flag = true; 
		}
		if(flag) 
		{	
			
			Bullet bullet = new Bullet(x, y, 1,frame); //调用子弹类
			Bullet.bullets[PeaShooter.getRow(y)].add(bullet); //发射子弹
		}
	}
	class PeaShooterThread implements Runnable{

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			try {
				while(hp > 0) //如果豌豆射手还存活
				{
					Thread.sleep(3000); //线程暂停
					fireBullet(); //重新发射子弹
				}
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		
	}
}
