package plants;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import makelabel.MakeLabel;
import zombies.Zombie;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;


public class Nut extends NewPlant{
	public static int width = 71;//realLabel�Ŀ��
	public static int height = 71;
	private static int speed = 20;
	private static int damage = 1200;
	private boolean isPlay;
	private boolean[] isHit;
	
	private final static ImageIcon wallnutRealGif= new ImageIcon (PeaShooter.class.getResource("/images/gif/WallNutRoll.gif"));//����޸�Ϊ�����汾
	
	public Nut(int x,int y,JFrame frame)
	{
		super(x, y, frame);
		this.isPlay = false;
		this.isHit = new boolean[50];
		for(int i=0;i<isHit.length;i++)
		{
			this.isHit[i]=false;
		}
		this.hp = 100;
		this.realLabel = MakeLabel.makeLabel(wallnutRealGif, this.x, this.y, width, height);
		this.frame.getLayeredPane().add(this.realLabel, new Integer(2));//�ڵ����ȼ�Ϊ2
		
		/*for (int i = 0; i < Zombie.zombies[PeaShooter.getRow(y)].size(); i++) //��������zombie��ishit�ٳ�ʼ��Ϊfalse,����ÿ����һ��nut�ͳ�ʼ��
		{
			Zombie.zombies[PeaShooter.getRow(y)].get(i).isHit =false;
		}*/
		new Thread(new moveNutThread()).start();//�����µ��̲߳������㶹�����߳�
	}
	
	class moveNutThread implements Runnable
	{
		@Override
		public void run() {
			while (x <= 1600) {				
				try {
					Thread.sleep(100);
					x += speed;// ͨ����ʱ�޸�x������ʵ����С���ƶ�
					realLabel.setLocation(x, y);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				for (int i = 0; i < Zombie.zombies[PeaShooter.getRow(y)].size(); i++) 
				{
					//System.out.println(""+Zombie.zombies[PeaShooter.getRow(y)].get(i).isHit);
					if (Zombie.zombies[PeaShooter.getRow(y)].get(i).getX() - x <= 0) 
					{
						if(!isHit[i])
						{
							int newHp = Zombie.zombies[PeaShooter.getRow(y)].get(i).getHit_Piont() - damage;
							Zombie.zombies[PeaShooter.getRow(y)].get(i).setHit_Piont(newHp);
						}

						isHit[i] = true;
						
						if (!isPlay) {
							new Thread(new playSoundThread()).start();
						}
						isPlay = true;
						
					}
					
				}
				
			}
		}
	}

	class playSoundThread implements Runnable {

		@Override
		public void run() {
			AudioClip aau;
			try {
                // TODO replace the .wav file
				File f = new File("curriculum_design\\src\\sounds\\lawnmower.wav");
				URI uri = f.toURI();
				URL url = uri.toURL();
				aau = Applet.newAudioClip(url);
				aau.play();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}		
}
