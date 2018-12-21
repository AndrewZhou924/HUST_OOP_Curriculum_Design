package controller;

import java.util.Random;

import javax.swing.JFrame;

import sun.FlowSun;
import sun.Sun;

public class FlowSunController {

	private JFrame frame;

	public FlowSunController(JFrame frame)// ���췽�� ������"ControllerThread"�̣߳�ÿ10������һ��̫��
	{
		this.frame = frame;
		new Thread(new ControllerThread()).start();
	}

	private void randomCreateSun()// ���λ������̫������
	{
		Random random = new Random();
		FlowSun sun = new FlowSun(50 + random.nextInt(720), 150, frame);
		Sun.suns.add(sun);
	}

	class ControllerThread implements Runnable {// ÿ10������һ��̫��

		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			try {
				while (true) {
					Thread.sleep(10000);
					randomCreateSun();
				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}
