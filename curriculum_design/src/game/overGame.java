
package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Container;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import zombies.Zombie;

// 游戏结束界面（僵尸获胜）
public class overGame extends JFrame implements Runnable{
	private final static ImageIcon zombie_win = new ImageIcon(Zombie.class.getResource("/images/zombie_win.png"));
	public final static ImageIcon background = new ImageIcon(GameJFrame.class.getResource("/images/background1.jpg"));
	public final static ImageIcon dave = new ImageIcon(GameJFrame.class.getResource("/images/gif/Dave3.gif"));
	public final static ImageIcon gameover = new ImageIcon(GameJFrame.class.getResource("/images/gif/gameover.png"));
	JLabel overLabel,daveLabel,gameoverLabel;
	JFrame f = this;
	
	public overGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭程序，也会关闭dos中的运行.
		setVisible(true);
		this.setResizable(false);//如果此窗体是可调整大小的，则为 true；否则为 false。 
		setLayout(null);//布局
		setBounds(500,200, 856, 600);//开始界面的位置和长宽设置


		
		JLabel overLabel = new JLabel(background);
		getLayeredPane().add(overLabel, new Integer(Integer.MIN_VALUE));
		/*JLayeredPane 允许组件在需要时互相重叠
		  add前一个参数是组件，后一个参数是Integer对象，Integer指定容器中每个组件的深度，其中编号较高的组件位于其他组件之上。 */
		overLabel.setBounds(-200, 0, background.getIconWidth(), background.getIconHeight());
		Container cp = getContentPane();
		((JPanel) cp).setOpaque(false);//true就会显示背景颜色，false背景就是透明
		/*
		 即如果设置为true的话，原样显示组件中的每个像素，也就是正常显示；这里主要讲解设置为false时。
	当设置为false时，组件并未不会显示其中的某些像素，允许控件下面的像素显现出来。
		 */
		
		
		daveLabel = new JLabel(dave);
		daveLabel.setBounds(0, 100, 316, 519);
		daveLabel.setVisible(true);
		add(daveLabel);	
		
		gameoverLabel = new JLabel(gameover);
		gameoverLabel.setBounds(200, 100, 535, 408);
		gameoverLabel.setVisible(true);
		add(gameoverLabel);	
		
		//run();
		
		AudioClip aau = null;
		try {
			File f = new File("curriculum_design\\src\\sounds\\losemusic.wav");
			URI uri = f.toURI();
			URL url = uri.toURL();

			aau = Applet.newAudioClip(url);
			aau.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Thread gameThread = new Thread(this);
		gameThread.start();

	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			new LevelSelect();
			this.dispose();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
