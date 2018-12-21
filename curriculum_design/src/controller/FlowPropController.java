package controller;

import java.util.concurrent.ThreadLocalRandom;//产生线程安全的随机数

import javax.swing.JFrame;

import sun.FlowProp;
import sun.FlowProp2;
import sun.FlowProp3;
import sun.Sun;

public class FlowPropController {

	private JFrame frame;

	public FlowPropController(JFrame frame)// 构造方法 ，运行"ControllerThread"线程，每10秒生成一个太阳
	{
		this.frame = frame;
		new Thread(new ControllerThread()).start();
		new Thread(new ControllerThread2()).start();
		new Thread(new ControllerThread3()).start();
	}

//	private void randomCreateSun()// 随机位置生成太阳方法
//	{
//		Random random = new Random();
//		FlowProp prop = new FlowProp(250 + random.nextInt(970), 150, frame);
//		Sun.suns.add(prop);
//	}

	private void randomCreateSun()// 随机位置生成太阳方法
	{
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		FlowProp prop = new FlowProp(250 + tlr.nextInt(970), 150, frame);
		Sun.suns.add(prop);
	}

	private void randomCreateSun2() {
		ThreadLocalRandom tlr2 = ThreadLocalRandom.current();
		FlowProp2 prop2 = new FlowProp2(250 + tlr2.nextInt(970), 150, frame);
		Sun.suns.add(prop2);
	}

	private void randomCreateSun3() {
		ThreadLocalRandom tlr3 = ThreadLocalRandom.current();
		FlowProp3 prop3 = new FlowProp3(250 + tlr3.nextInt(970), 150, frame);
		Sun.suns.add(prop3);
	}

	class ControllerThread implements Runnable {

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			try {
				while (true) {
					Thread.sleep(5000);
					randomCreateSun();
				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	class ControllerThread2 implements Runnable {

		public void run() {
			// TODO 自动生成的方法存根
			try {
				while (true) {
					Thread.sleep(4000);
					randomCreateSun2();
				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	class ControllerThread3 implements Runnable {

		public void run() {
			// TODO 自动生成的方法存根
			try {
				while (true) {
					Thread.sleep(5000);
					randomCreateSun3();
				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}