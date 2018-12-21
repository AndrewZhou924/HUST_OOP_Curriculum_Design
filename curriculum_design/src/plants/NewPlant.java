package plants;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class NewPlant{
	protected int x, y;           //植物在屏幕的位置坐标
	protected int hp;      //即HP，生命值
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

