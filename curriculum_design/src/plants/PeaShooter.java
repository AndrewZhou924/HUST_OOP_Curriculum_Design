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
		super.cooldown_time = 2;//����ʱ��
		super.cost= 100 ;//�۸�
		super.hp = 100;//����ֵ
		this.frame = frame;
		new Thread(new PeaShooterThread()).start();//�����µ��̲߳������㶹�����߳�
	}
	public void fireBullet()
	{
		boolean flag = false;
		for(Zombie zombie : Zombie.zombies[getRow(y)])//������ʬ
		{
			if(zombie.getHit_Piont() > 0) //�����ʬ�����
				flag = true;
		}
		if(flag)
		{
			firer=new JLabel(Peashooterfire);
			firer.setBounds(x, y, 71, 71); 
			game.GameJFrame.movePeaLabel.setVisible(false);
			//firer.setVisible(true);
			Bullet bullet = new Bullet(x, y, 0,frame);//���Լ���λ�ô������ӵ�
			Bullet.bullets[getRow(y)].add(bullet); //��¼���ӵ�
		}
	}
	public static int getRow(int y) //����yλ��//û�����Ƿ�����ʲô����
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
			// TODO �Զ����ɵķ������
			try {
				while(true)
				{
					if(hp > 0)//����㶹���ֻ����
					{
						Thread.sleep(3000);//�߳���ͣ
				 		fireBullet();//���·����ӵ�
					}
					else
					{
						//*��ȥplants
						break;
					}
				}
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
		}
		
	}
}
