package controller;

import java.util.Random;

import javax.swing.JFrame;

import sun.FlowSun;
import sun.Sun;

public class FlowSunController {

	private JFrame frame;

	public FlowSunController(JFrame frame)// 构造方法 ，运行"ControllerThread"线程，每10秒生成一个太阳
	{
		this.frame = frame;
		new Thread(new ControllerThread()).start();
	}

	private void randomCreateSun()// 随机位置生成太阳方法
	{
		Random random = new Random();
		FlowSun sun = new FlowSun(50 + random.nextInt(720), 150, frame);
		Sun.suns.add(sun);
	}

	class ControllerThread implements Runnable {// 每10秒生成一个太阳

		@Override
		public void run() {
			// TODO 自动生成的方法存根
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
