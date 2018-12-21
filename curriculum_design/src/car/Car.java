package car;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bullet.Bullet;
import zombies.Zombie;

public class Car {
	int x, y, h;
	int flySpeed = 0;// С�����ٶ�
	int width = 70, height = 57;// С���Ĵ�С
	int flag = 0;// ��Ч���Ŵ�������ֹ��������
	JLabel carLabel;
	private final static ImageIcon car = new ImageIcon(Bullet.class.getResource("/images/gif/LawnCleaner.png"));

	public Car(int x, int y, int h, JFrame jframe) {
		this.x = x;
		this.y = y;
		this.h = h;
		carLabel = new JLabel(car);
		carLabel.setBounds(x, y, width, height);
		jframe.add(carLabel);
		new Thread(new moveCar()).start();// ��С���ƶ�

	}

	class moveCar implements Runnable {

		@Override
		public void run() {
			while (x <= 1600) {
				for (int i = 0; i < Zombie.zombies[h].size(); i++) {
					if (x+Zombie.zombies[h].get(i).getX() <= 0) {
						Zombie.zombies[h].get(i).setHit_Piont(0);// ����С��Ϊ�Ѿ�������
						flySpeed = 20;// �����ʬ�ߵ�С������С��׼���ƶ�

						if (flag == 0)
							new Thread(new playSoundThread()).start();
						flag = 1;
					}
				}

				try {
					Thread.sleep(100);
					x += flySpeed;// ͨ����ʱ�޸�x������ʵ����С���ƶ�
					carLabel.setLocation(x, y);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class playSoundThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			AudioClip aau;
			try {
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

	// getter��|setter����
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
}
