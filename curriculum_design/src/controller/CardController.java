package controller;

import javax.swing.JFrame;

import cards.WallNutCard;
import zombies.Zombie;

public class CardController {
	private JFrame frame;
	private static int speed = 3;
	private boolean first;

	public CardController(JFrame frame) {// 构造方法 ，运行"ControllerThread"线程，不定期生成卡片
		this.frame = frame;
		new Thread(new ControllerThread()).start();
		this.first = true;
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
					Thread.sleep((int) (500+Math.random() * 3000));
					//Thread.sleep(20);
					new WallNutCard(600,-2, 2,frame);

				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}
