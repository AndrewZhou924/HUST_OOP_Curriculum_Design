package plants;

import javax.swing.JFrame;

import sun.Sun;

public class SunFlower extends Plant {
	private JFrame frame;
	public SunFlower(int x,int y,JFrame frame)
	{
		super();
		super.x = x;
		super.y = y;
		super.hp = 70; //生命值
		this.frame = frame; 
		new Thread(new SunFlowerThread()).start(); //开启线程
	}
	private void createSun() //创建太阳能量
	{
		Sun sun = new Sun(x, y, frame);  //调用sun函数并设置构造函数
		Sun.suns.add(sun); //在sun的动态数组suns中添加这个sun元素
	}
	class SunFlowerThread implements Runnable{

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			try {
				while(hp > 0) //如果太阳花还存活
				{
					Thread.sleep(9000); //线程暂停
					if(hp > 0) 
						createSun(); //创造太阳能量
				}
			} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		
			
	}
}
