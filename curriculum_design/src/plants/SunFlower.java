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
		super.hp = 70; //����ֵ
		this.frame = frame; 
		new Thread(new SunFlowerThread()).start(); //�����߳�
	}
	private void createSun() //����̫������
	{
		Sun sun = new Sun(x, y, frame);  //����sun���������ù��캯��
		Sun.suns.add(sun); //��sun�Ķ�̬����suns��������sunԪ��
	}
	class SunFlowerThread implements Runnable{

		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			try {
				while(hp > 0) //���̫���������
				{
					Thread.sleep(9000); //�߳���ͣ
					if(hp > 0) 
						createSun(); //����̫������
				}
			} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				
			}
		
			
	}
}
