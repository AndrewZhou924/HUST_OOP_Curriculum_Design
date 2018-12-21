package plants;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import zombies.Zombie;

public class Pepper extends Plant {
	private JLabel boomLabel,jalapenoLabel;
	protected final static ImageIcon pepper = new ImageIcon(Pepper.class.getResource("/images/gif/JalapenoAttack.gif"));//������Ƭ
	protected final static ImageIcon jalapeno = new ImageIcon(Pepper.class.getResource("/images/gif/Jalapeno.gif"));//������������Ƭ
	private JFrame frame;
	public Pepper(int x,int y,JFrame frame)
	{
		this.x = x;
		this.y = y;
		this.hp = 100; //����ֵ
		this.cooldown_time = 2; //����ʱ��
		this.cost = 125; //�۸�
		this.frame = frame;
		boomLabel=new JLabel(pepper); 
		jalapenoLabel = new JLabel(jalapeno);
		boomLabel.setBounds(x -400, y, 1233, 87); //���û����
		boomLabel.setVisible(false);
		frame.add(boomLabel);
		jalapenoLabel.setBounds(x-600, y, 1233, 87); //���û����
		jalapenoLabel.setVisible(false);
		frame.add(jalapenoLabel);
		new Thread(new Boomthreaad()).start();//�����µ��̲߳����������߳�
	}
	
	
	class Boomthreaad implements Runnable{

		@Override
		public void run() {
			try {
				while(hp > 0) //���������û��ʧ
				{
					boolean flag = false;
					for(Zombie zombie : Zombie.zombies[PeaShooter.getRow(y)]) //�������к��㶹���ֵ�yλ����ͬ�Ľ�ʬ
					{
						if(zombie.getHit_Piont() > 0)//�����ʬ�����
							flag  = true;
					}
					if(flag)
					{
						hp = 0; //����ֵΪ��
						jalapenoLabel.setVisible(true);
						Thread.sleep(500);
						jalapenoLabel.setVisible(false);
						frame.remove(jalapenoLabel);
						boomLabel.setVisible(true);
						for(Zombie zombie : Zombie.zombies[PeaShooter.getRow(y)])//�������к��㶹���ֵ�yλ����ͬ�Ľ�ʬ
						{
							zombie.setBurned(true); //����ʬ
							zombie.setHit_Piont(0); //��ʬ������ֵΪ��
						}
						
					}
				}
				Thread.sleep(1000); //��ͣ�߳�
				boomLabel.setVisible(false);
				frame.remove(boomLabel);//��ȥ����Ч��
				frame.repaint(); //���»���
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
	}
}
