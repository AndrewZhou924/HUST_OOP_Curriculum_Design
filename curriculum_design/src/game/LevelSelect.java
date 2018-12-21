package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.starGame.MouseHandler;
import login.Charts;
import zombies.Zombie;

public class LevelSelect extends JFrame {
	private final static ImageIcon menu = new ImageIcon(Zombie.class.getResource("/images/gif/Surface.png")); // 选择界面
	private final static ImageIcon hand = new ImageIcon(Zombie.class.getResource("/images/gif/menuhand.gif")); //手
	private final static ImageIcon game = new ImageIcon(Zombie.class.getResource("/images/gif/game.png")); 
	private final static ImageIcon gameoff = new ImageIcon(Zombie.class.getResource("/images/gif/gameoff.png")); 
	private final static ImageIcon nugget = new ImageIcon(Zombie.class.getResource("/images/gif/nugget.png")); 
	private final static ImageIcon nuggetoff = new ImageIcon(Zombie.class.getResource("/images/gif/nuggetoff.png")); 
	private final static ImageIcon gun=new ImageIcon(Zombie.class.getResource("/images/gif/gun.png")); 
	private final static ImageIcon gunoff = new ImageIcon(Zombie.class.getResource("/images/gif/gunoff.png")); 
	private final static ImageIcon board = new ImageIcon(Zombie.class.getResource("/images/gif/board.png")); 
	private final static ImageIcon exit = new ImageIcon(Zombie.class.getResource("/images/gif/exit.png")); 
	private final static ImageIcon user = new ImageIcon(Zombie.class.getResource("/images/gif/SelectorScreen_WoodSign1_32.png"));
	private final static ImageIcon music = new ImageIcon(Zombie.class.getResource("/images/gif/music.png"));
	private final static ImageIcon musicon = new ImageIcon(Zombie.class.getResource("/images/gif/musicon.png"));
	private final static ImageIcon musicoff = new ImageIcon(Zombie.class.getResource("/images/gif/musicoff.png"));
	private final static ImageIcon changeuser = new ImageIcon(Zombie.class.getResource("/images/gif/SelectorScreen_WoodSign2_32.png"));
	private final static ImageIcon changeuseroff = new ImageIcon(Zombie.class.getResource("/images/gif/SelectorScreen_WoodSign2_32off.png"));
	
	JLabel handLabel,gameLabel,gameoffLabel,nuggetLabel,nuggetoffLabel,gunLabel,gunoffLabel,
			boardLabel,exitLabel,musicLabel,musiconLabel,musicoffLabel,userLabel,changeuserLabel,changeuseroffLabel;
	JFrame f = this;
	public static boolean cMusic=true;
	static JLabel sunCountLabel;
	
	public LevelSelect() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭程序，也会关闭dos中的运行.
		setVisible(true);
		this.setResizable(false);//如果此窗体是可调整大小的，则为 true；否则为 false。 
		setLayout(null);//布局
		setBounds(520, 200, menu.getIconWidth(), menu.getIconHeight() + 30);//开始界面的位置和长宽设置

		JLabel imgLabel = new JLabel(menu);
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		/*JLayeredPane 允许组件在需要时互相重叠
		  add前一个参数是组件，后一个参数是Integer对象，Integer指定容器中每个组件的深度，其中编号较高的组件位于其他组件之上。 */
		imgLabel.setBounds(0, 0, menu.getIconWidth(), menu.getIconHeight());
		Container cp = getContentPane();
		((JPanel) cp).setOpaque(false);//true就会显示背景颜色，false背景就是透明
		/*
		 即如果设置为true的话，原样显示组件中的每个像素，也就是正常显示；这里主要讲解设置为false时。
	当设置为false时，组件并未不会显示其中的某些像素，允许控件下面的像素显现出来。
		 */

		handLabel = new JLabel(hand);
		handLabel.setBounds(241, 250, 201, 324);
		handLabel.setVisible(true);
		add(handLabel);	
		
		userLabel = new JLabel(user);
		userLabel.setBounds(75, 2, 293, 150);
		userLabel.setVisible(true);
		add(userLabel);	

		String username = UserDA.userName;
		sunCountLabel = new JLabel(username);
		sunCountLabel.setFont(new java.awt.Font("Dialog", 0, 25));
		sunCountLabel.setForeground(Color.white);
		getLayeredPane().add(sunCountLabel, new Integer(3));
		sunCountLabel.setBounds(165,80, 400, 50);
		sunCountLabel.setVisible(true);

		changeuserLabel = new JLabel(changeuser);
		changeuserLabel.setBounds(75, 141, 291, 66);
		changeuserLabel.setVisible(false);
		add(changeuserLabel);	
		
		changeuseroffLabel = new JLabel(changeuseroff);
		changeuseroffLabel.setBounds(75, 140, 291, 66);
		changeuseroffLabel.setVisible(true);
		add(changeuseroffLabel);	
		
		gameLabel = new JLabel(game);
		gameLabel.setBounds(480, 115, 312, 133);
		gameLabel.setVisible(false);
		add(gameLabel);	
		
		gameoffLabel = new JLabel(gameoff);
		gameoffLabel.setBounds(480, 115, 312, 133);
		gameoffLabel.setVisible(true);
		add(gameoffLabel);	
		
		nuggetLabel = new JLabel(nugget);
		nuggetLabel.setBounds(470, 210, 312, 133);
		nuggetLabel.setVisible(false);
		add(nuggetLabel);	
		
		nuggetoffLabel = new JLabel(nuggetoff);
		nuggetoffLabel.setBounds(470, 210, 312, 133);
		nuggetoffLabel.setVisible(true);
		add(nuggetoffLabel);	
		
		gunLabel = new JLabel(gun);
		gunLabel.setBounds(460, 300, 312, 133);
		gunLabel.setVisible(false);
		add(gunLabel);	
		
		gunoffLabel = new JLabel(gunoff);
		gunoffLabel.setBounds(460, 300, 312, 133);
		gunoffLabel.setVisible(true);
		add(gunoffLabel);	
		
		boardLabel = new JLabel(board);
		boardLabel.setBounds(645, 495, 74, 31);
		boardLabel.setVisible(false);
		add(boardLabel);	
		
		exitLabel = new JLabel(exit);
		exitLabel.setBounds(797, 512, 74, 31);
		exitLabel.setVisible(false);
		add(exitLabel);	
		
		musicLabel = new JLabel(music);
		musicLabel.setBounds(720, 521, 74, 31);
		musicLabel.setVisible(false);
		add(musicLabel);	
		
		musiconLabel = new JLabel(musicon);
		musiconLabel.setBounds(720, 521, 74, 31);
		musiconLabel.setVisible(true);
		add(musiconLabel);	
	
		musicoffLabel = new JLabel(musicoff);
		musicoffLabel.setBounds(720, 521, 74, 31);
		musicoffLabel.setVisible(false);
		add(musicoffLabel);	


		MouseHandler handler = new MouseHandler();
		this.addMouseMotionListener(handler);
		this.addMouseListener(handler);
		
	}
	class MouseHandler implements MouseMotionListener, MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			// 鼠标点击事件的响应函数
			int w = 313;
			int h = 133;

			if (e.getX() >= gameLabel.getX() && e.getX() <= gameLabel.getX() + w && 
					e.getY() >= gameLabel.getY() && e.getY() <= gameLabel.getY() + h) {
				new GameJFrame(); // 进入游戏界面
				f.dispose(); // 关闭开始菜单的GUI页面
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if (e.getX() >= nuggetLabel.getX() && e.getX() <= nuggetLabel.getX() + w && 
						e.getY() >= nuggetLabel.getY() && e.getY() <= nuggetLabel.getY() + h) {
				new NutJFrame();
//				new GameJFrame(); // 进入游戏界面
				f.dispose(); // 关闭开始菜单的GUI页面
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if (e.getX() >= gunLabel.getX() && e.getX() <= gunLabel.getX() + w && 
					e.getY() >= gunLabel.getY() && e.getY() <= gunLabel.getY() + h) {
				new ShooterJFrame();
				f.dispose(); // 关闭开始菜单的GUI页面
				setCursor(new Cursor(Cursor.HAND_CURSOR));
//				JFrame f = new ShooterJFrame(); // 进入枪战模式游戏界面
				
				
				
			}
			else if (e.getX() >= boardLabel.getX() && e.getX() <= boardLabel.getX() + 74 && 
					e.getY() >= boardLabel.getY() && e.getY() <= boardLabel.getY() + 40) {
//				boardLabel.setVisible(true);
				new Charts().init();
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if (e.getX() >= exitLabel.getX() && e.getX() <= exitLabel.getX() + 74 && 
					e.getY() >= exitLabel.getY() && e.getY() <= exitLabel.getY() + 40) {
				System.exit(0);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if (e.getX() >= musicLabel.getX() && e.getX() <= musicLabel.getX() + 74 && 
					e.getY() >= musicLabel.getY() && e.getY() <= musicLabel.getY() + 40) {
				if(cMusic) {
					cMusic=false;
					musicoffLabel.setVisible(true);
					musiconLabel.setVisible(false);
					musicLabel.setVisible(false);
				}
				else {
					cMusic=true;
					musicoffLabel.setVisible(false);
					musiconLabel.setVisible(true);
					musicLabel.setVisible(false);
				}
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else {
				setCursor(Cursor.getDefaultCursor());
			}

		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			int w = 313;
			int h = 133;

			// 如果鼠标在 [开始游戏] 图标的一定范围内，则改变其显示
			if (e.getX() >= gameLabel.getX() && e.getX() <= gameLabel.getX() + w && 
					e.getY() >= gameLabel.getY() && e.getY() <= gameLabel.getY() + h) {
				gameLabel.setVisible(true);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if (e.getX() >= nuggetLabel.getX() && e.getX() <= nuggetLabel.getX() + w && 
						e.getY() >= nuggetLabel.getY() && e.getY() <= nuggetLabel.getY() + h) {
				nuggetLabel.setVisible(true);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if (e.getX() >= gunLabel.getX() && e.getX() <= gunLabel.getX() + w && 
					e.getY() >= gunLabel.getY() && e.getY() <= gunLabel.getY() + h) {
				gunLabel.setVisible(true);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if (e.getX() >= boardLabel.getX() && e.getX() <= boardLabel.getX() + 74 && 
					e.getY() >= boardLabel.getY() && e.getY() <= boardLabel.getY() + 40) {
				boardLabel.setVisible(true);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if (e.getX() >= exitLabel.getX() && e.getX() <= exitLabel.getX() + 74 && 
					e.getY() >= exitLabel.getY() && e.getY() <= exitLabel.getY() + 40) {
				exitLabel.setVisible(true);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if (e.getX() >= musicLabel.getX() && e.getX() <= musicLabel.getX() + 74 && 
					e.getY() >= musicLabel.getY() && e.getY() <= musicLabel.getY() + 40) {
				musicLabel.setVisible(true);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if (e.getX() >= changeuserLabel.getX() && e.getX() <= changeuserLabel.getX() + 291 && 
					e.getY() >= changeuserLabel.getY() && e.getY() <= changeuserLabel.getY() + 66) {
				changeuserLabel.setVisible(true);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			 else {
				gameLabel.setVisible(false);
				nuggetLabel.setVisible(false);
				gunLabel.setVisible(false);
				boardLabel.setVisible(false);
				exitLabel.setVisible(false);
				changeuserLabel.setVisible(false);
				musicLabel.setVisible(false);
				setCursor(Cursor.getDefaultCursor());
			}

		}	

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		
	}
	

	public JLabel getHandLabel() {
		return handLabel;
	}

	public void setHandLabel(JLabel handLabel) {
		this.handLabel = handLabel;
	}


	public JFrame getF() {
		return f;
	}

	public void setF(JFrame f) {
		this.f = f;
	}

	public static ImageIcon getMenu() {
		return menu;
	}

	public static ImageIcon getHand() {
		return hand;
	}




}
