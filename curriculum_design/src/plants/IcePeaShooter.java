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
		super.hp = 100; //����ֵ
		this.frame = frame; //
		new Thread(new PeaShooterThread()).start(); //�����µ��̲߳������㶹�����߳�
	}
	public void fireBullet()
	{
		boolean flag = false;
		for(Zombie zombie : Zombie.zombies[PeaShooter.getRow(y)])//PeaShooter.getRow(y)��PeaShooter���getRow�������yλ��
		{
			if(zombie.getHit_Piont() > 0) //�����ʬ�����
				flag = true; 
		}
		if(flag) 
		{	
			
			Bullet bullet = new Bullet(x, y, 1,frame); //�����ӵ���
			Bullet.bullets[PeaShooter.getRow(y)].add(bullet); //�����ӵ�
		}
	}
	class PeaShooterThread implements Runnable{

		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			try {
				while(hp > 0) //����㶹���ֻ����
				{
					Thread.sleep(3000); //�߳���ͣ
					fireBullet(); //���·����ӵ�
				}
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
		}
		
	}
}
