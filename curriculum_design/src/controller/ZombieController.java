package controller;

import javax.swing.JFrame;

import zombies.Zombie;

public class ZombieController {
	private JFrame frame;
	private int speed;
	private boolean first;

	public ZombieController(int speed,JFrame frame)// ���췽�� ������"ControllerThread"�̣߳����������ɽ�ʬ
	{
		this.frame = frame;
		this.speed = speed;
		this.first = true;
		new Thread(new ControllerThread()).start();
	}

	class ControllerThread implements Runnable {

		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			try {
				while (true) {
					if(first)
					{
						Thread.sleep(6000);
					}
					first = false;
					Thread.sleep((int) (Math.random() * speed));// ����ʱ���ɽ�ʬ
					new Zombie((int)(Math.random()*3),1, frame);//type,movespeed

				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}
