package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import controller.FlowPropController;

// 用于捕捉用户的鼠标事件
public class ShooterController extends KeyAdapter implements MouseMotionListener, MouseListener {
	public static long propBeginTime = 0;
	public static int status = 0;
	private ShooterJFrame frame;

	public ShooterController(ShooterJFrame frame) {
		if(frame==null) {
			System.out.println("null!");
		}
		
		System.out.println("not null!");
		this.frame = frame;
		this.frame.addKeyListener(new MyKeyListener());
		new FlowPropController(frame);
	}

	@Override
	public void mouseDragged(MouseEvent e) { // 从菜单栏上面拽拉植物
//    	int species = this.frame.getSpecies();
//		if(species == 1)
//			this.frame.moveFlowePoint = e.getPoint();
//		if(species == 2)
//			this.frame.movePeaPoint = e.getPoint();
//        if(species == 3)
//        	this.frame.moveShovelPoint = e.getPoint();
//        if(species == 5) {
//        	this.frame.moveIcePeaPoint = e.getPoint();
//        }
//        if(species == 6) {
//        	this.frame.movePotatoPoint = e.getPoint();
//        }
//		if(species == 7) {
//			this.frame.moveChiliPoint = e.getPoint();
//		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		if (this.frame.isOk(e.getX(), e.getY()) > 0) {
//			this.frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		} else {
//			this.frame.setCursor(Cursor.getDefaultCursor());
//		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { // 语义事件
//		// 判断是否有道具被点击
//		for (int i = 0; i < Sun.suns.size(); i++) {
//			if (Sun.suns.get(i).isClicked() == false && Math.abs(Sun.suns.get(i).getX() - e.getX() + 40) <= 50
//					&& Math.abs(Sun.suns.get(i).getY() - e.getY() + 70) <= 50) {
//				Sun.suns.get(i).setClicked(true);
//				this.frame.count += 50;
//				if (Sun.suns.get(i).type == 1) {// 寒冰
//					propBeginTime = System.currentTimeMillis();
//					// Shooter s1 = new Shooter();
//					ShooterJFrame.shooter.refresh(1);
//					status = 1;
//				}
//				if (Sun.suns.get(i).type == 2) {// 火球
//					propBeginTime = System.currentTimeMillis();
//					// Shooter s2 = new Shooter();
//					// s2.refresh(2);
//					ShooterJFrame.shooter.refresh(2);
//					status = 2;
//				}
//				if (Sun.suns.get(i).type == 3) {// 加速
//					new Thread(new SpeedUpThread()).start();// 开启新的线程并运行豌豆射手线程
//				}
//				Sun.suns.get(i).removeSun();
//			}
//		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// 判断用户点击了哪个植物
//			this.frame.species = this.frame.isOk(e.getX(), e.getY());
//			int species = this.frame.getSpecies();
//			if(species == 1) {
//				GameJFrame.moveFlowerLabel.setVisible(true);	    
//			}
//			
//			if(species == 2) {
//				GameJFrame.movePeaLabel.setVisible(true);
//			}
//			
//			if(species == 3) {
//				GameJFrame.moveShovelLabel.setVisible(true);
//			}
//			
//			if(species == 5) {
//				GameJFrame.moveIcePeaLabel.setVisible(true);
//			}
//			
//			if(species == 6) {
//				GameJFrame.movePotatoLabel.setVisible(true);
//			}
//			if(species == 7) {
//				GameJFrame.moveChiliLabel.setVisible(true);
//			}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// 鼠标松开事件

		// 判断当前鼠标在哪个格子上面
//			int x = (e.getX() - 254) / 81;
//			int y = (e.getY() - 96) / 95;
//
//				
//			int species = this.frame.getSpecies();
//			
//			Plant[][]  plants = ShooterJFrame.plants;
//			JLabel[][] labels = ShooterJFrame.labels;
//			JLabel plantLabel =  ShooterJFrame.plantLabel;
//			
//			if (species > 0 && x >= 0 && x < 9 && y >= 0 && y < 5 && e.getX() >= 254 && e.getX() <= 982
//					&& e.getY() >= 96 && e.getY() <= 572) {
//
//				if (species == 1 && plants[y][x] == null) {
//					this.frame.count -= 50;
//					this.frame.SetFlowerBeginTime(System.currentTimeMillis());
//					plants[y][x] = new SunFlower(x * 80 + 240, y * 90 + 85, this.frame);
//					plantLabel = this.frame.makeLabel(GameJFrame.move_sun_flower, x * 80 + 240, y * 90 + 85, 100, 100);
//					labels[y][x] = plantLabel;
//					this.frame.add(plantLabel);
//
//				}
//				if (species == 2 && plants[y][x] == null) {
//					this.frame.count -= 100;
//					this.frame.SetPeaBeginTime(System.currentTimeMillis());
//					plants[y][x] = new PeaShooter(x * 80 + 240, y * 90 + 85, this.frame);
//					plantLabel = this.frame.makeLabel(GameJFrame.movd_single_bullet_plant, x * 80 + 240, y * 90 + 85, 100, 100);
//					labels[y][x] = plantLabel;
//					this.frame.add(plantLabel);
//
//				}
//				if (species == 3) {// 铲除？
//					if (plants[y][x] != null) {
//						plants[y][x].setHp(0);
//						playUprootSound();
//					}
//				}
//				if (species == 5 && plants[y][x] == null) {
//					this.frame.count -= 170;
//					this.frame.SetIcePeaBeginTime(System.currentTimeMillis());
//					plants[y][x] = new IcePeaShooter(x * 80 + 240, y * 90 + 85, this.frame);
//					plantLabel = this.frame.makeLabel(GameJFrame.moveIcebean, x * 80 + 240, y * 90 + 85, 100, 100);
//					labels[y][x] = plantLabel;
//					this.frame.add(plantLabel);
//				}
//				if (species == 6 && plants[y][x] == null) {
//					this.frame.count -= 50;
//					this.frame.SetPotatoBeginTime(System.currentTimeMillis());
//					plants[y][x] = new Potato(x * 80 + 240, y * 90 + 85);
//					plantLabel = this.frame.makeLabel(GameJFrame.movePotato, x * 80 + 240, y * 90 + 85, 100, 100);
//					labels[y][x] = plantLabel;
//					this.frame.add(plantLabel);
//				}
//				if (species == 7 && plants[y][x] == null) {
//					this.frame.count -= 150;
//					this.frame.SetChiliBeginTime(System.currentTimeMillis());
//					plants[y][x] = new Pepper(x * 80 + 240, y * 90 + 85, this.frame);
//					plantLabel = this.frame.makeLabel(GameJFrame.chili, x * 80 + 240, y * 90 + 85, 100, 100);
//					labels[y][x] = plantLabel;
//					this.frame.add(plantLabel);
//				}
//			}
//
//			species = 0;	//重置species为0
//
//			this.frame.moveFlowePoint = new Point(300, -100);
//			GameJFrame.moveFlowerLabel.setVisible(false);
//
//			this.frame.movePeaPoint = new Point(360,-100);
//			GameJFrame.movePeaLabel.setVisible(false);
//
//			this.frame.moveShovelPoint = new Point(700,-100);
//			GameJFrame.moveShovelLabel.setVisible(false);
//
//			this.frame.moveIcePeaPoint = new Point(420,-100);
//			GameJFrame.moveIcePeaLabel.setVisible(false);
//
//			this.frame.movePotatoPoint = new Point(480,-100);
//			GameJFrame.movePotatoLabel.setVisible(false);
//
//			this.frame.moveChiliPoint = new Point(560,-100);
//			GameJFrame.moveChiliLabel.setVisible(false);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

//	public class SpeedUpThread implements Runnable {
//
//		@Override
//		public void run() {
//			// TODO 自动生成的方法存根
//			try {
//				Shooter.shootTime = 100;
//				Thread.sleep(10000);
//				Shooter.shootTime = 200;
//			} catch (InterruptedException e) {
//				System.out.println(e);
//			}
//		}
//	}

}

class MyKeyListener extends KeyAdapter {

	public void keyPressed(KeyEvent e) {
		char A = e.getKeyChar();
		if (A == 'w')
			ShooterJFrame.shooter.move("up", ShooterController.status);
		if (A == 's')
			ShooterJFrame.shooter.move("down", ShooterController.status);
		if (A == 'a')
			ShooterJFrame.shooter.move("left", ShooterController.status);
		if (A == 'd')
			ShooterJFrame.shooter.move("right", ShooterController.status);
		if (A == 'j')
			ShooterJFrame.shooter.fire(ShooterController.status);

	}
}