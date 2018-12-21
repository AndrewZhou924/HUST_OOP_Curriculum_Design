package plants;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import zombies.Zombie;

public class Pepper extends Plant {
	private JLabel boomLabel,jalapenoLabel;
	protected final static ImageIcon pepper = new ImageIcon(Pepper.class.getResource("/images/gif/JalapenoAttack.gif"));//喷火的照片
	protected final static ImageIcon jalapeno = new ImageIcon(Pepper.class.getResource("/images/gif/Jalapeno.gif"));//辣椒快喷火的照片
	private JFrame frame;
	public Pepper(int x,int y,JFrame frame)
	{
		this.x = x;
		this.y = y;
		this.hp = 100; //生命值
		this.cooldown_time = 2; //冰冻时间
		this.cost = 125; //价格
		this.frame = frame;
		boomLabel=new JLabel(pepper); 
		jalapenoLabel = new JLabel(jalapeno);
		boomLabel.setBounds(x -400, y, 1233, 87); //设置火组件
		boomLabel.setVisible(false);
		frame.add(boomLabel);
		jalapenoLabel.setBounds(x-600, y, 1233, 87); //设置火组件
		jalapenoLabel.setVisible(false);
		frame.add(jalapenoLabel);
		new Thread(new Boomthreaad()).start();//开启新的线程并运行辣椒线程
	}
	
	
	class Boomthreaad implements Runnable{

		@Override
		public void run() {
			try {
				while(hp > 0) //如果辣椒还没消失
				{
					boolean flag = false;
					for(Zombie zombie : Zombie.zombies[PeaShooter.getRow(y)]) //遍历所有和豌豆射手的y位置相同的僵尸
					{
						if(zombie.getHit_Piont() > 0)//如果僵尸还存活
							flag  = true;
					}
					if(flag)
					{
						hp = 0; //生命值为零
						jalapenoLabel.setVisible(true);
						Thread.sleep(500);
						jalapenoLabel.setVisible(false);
						frame.remove(jalapenoLabel);
						boomLabel.setVisible(true);
						for(Zombie zombie : Zombie.zombies[PeaShooter.getRow(y)])//遍历所有和豌豆射手的y位置相同的僵尸
						{
							zombie.setBurned(true); //毁灭僵尸
							zombie.setHit_Piont(0); //僵尸的生命值为零
						}
						
					}
				}
				Thread.sleep(1000); //暂停线程
				boomLabel.setVisible(false);
				frame.remove(boomLabel);//移去火焰效果
				frame.repaint(); //重新绘制
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
}
