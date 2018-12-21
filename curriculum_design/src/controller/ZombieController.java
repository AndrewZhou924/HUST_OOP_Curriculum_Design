package controller;

import javax.swing.JFrame;

import zombies.Zombie;

public class ZombieController {
	private JFrame frame;
	private int speed;
	private boolean first;

	public ZombieController(int speed,JFrame frame)// 构造方法 ，运行"ControllerThread"线程，不定期生成僵尸
	{
		this.frame = frame;
		this.speed = speed;
		this.first = true;
		new Thread(new ControllerThread()).start();
	}

	class ControllerThread implements Runnable {

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			try {
				while (true) {
					if(first)
					{
						Thread.sleep(6000);
					}
					first = false;
					Thread.sleep((int) (Math.random() * speed));// 不定时生成僵尸
					new Zombie((int)(Math.random()*3),1, frame);//type,movespeed

				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}
