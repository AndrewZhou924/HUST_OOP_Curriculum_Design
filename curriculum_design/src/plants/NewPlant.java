package plants;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class NewPlant{
	protected int x, y;           //ֲ������Ļ��λ������
	protected int hp;      //��HP������ֵ
	protected static int width = 71;
	protected static int height = 71;
	protected JLabel realLabel;
	protected JFrame frame;
	
	public NewPlant(int x, int y,JFrame frame)
	{
		this.x = x;
		this.y = y;
		this.frame = frame;
	}
	
	public int getHp() {
		return hp;  
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
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

