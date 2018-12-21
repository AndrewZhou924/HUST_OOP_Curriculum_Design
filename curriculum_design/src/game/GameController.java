package game;
//可能全部要改




import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.JLabel;
import plants.IcePeaShooter;
import plants.PeaShooter;
import plants.Pepper;
import plants.Plant;
import plants.Potato;
import plants.SunFlower;
import sun.Sun;

// 用于捕捉用户的鼠标事件
public class GameController  implements MouseMotionListener, MouseListener {
	private GameJFrame frame;
	
	public GameController(GameJFrame frame) {
		this.frame = frame;
	}
	
    @Override
	public void mouseDragged(MouseEvent e) { 	//从菜单栏上面拽拉植物
    	int species = this.frame.getSpecies();
		if(species == 1) {
			//uFlowerLabel.setSize(uFlowerLabel.getWidth(), 70);

			this.frame.moveFlowePoint = e.getPoint();
			this.frame.moveFlowerLabel.setBounds((int)e.getPoint().getX()-50, (int)e.getPoint().getY()-50, 100, 100);
		}

		
		if(species == 2)
			this.frame.movePeaPoint = e.getPoint();
        if(species == 3)
        	this.frame.moveShovelPoint = e.getPoint();
        if(species == 5) {
        	this.frame.moveIcePeaPoint = e.getPoint();
        }
        if(species == 6) {
        	this.frame.movePotatoPoint = e.getPoint();
        }
		if(species == 7) {
			this.frame.moveChiliPoint = e.getPoint();
		}
	}

		@Override
		public void mouseMoved(MouseEvent e) {
//			if(this.frame.isOk(e.getX(), e.getY()) > 0) {
//				this.frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
//			} else {
//				this.frame.setCursor(Cursor.getDefaultCursor());
//			}
			
		}

		@Override
		public void mouseClicked(MouseEvent e) { //语义事件

//			// 判断是否有太阳被点击
//			for(int i = 0; i < Sun.suns.size();i++) {
//				if(Sun.suns.get(i).isClicked() == false && Math.abs(Sun.suns.get(i).getX() - e.getX()) <= 35 && Math.abs(Sun.suns.get(i).getY() - e.getY()) <= 35) {
//					Sun.suns.get(i).setClicked(true);
//					this.frame.count += 50;
//					Sun.suns.get(i).removeSun();
//				}			
//			}			
		}

		@Override
		public void mousePressed(MouseEvent e) {

			//判断用户点击了哪个植物
			this.frame.species = this.frame.isOk(e.getX(), e.getY());
			int species = this.frame.getSpecies();
			if(species == 1) {
				GameJFrame.uFlowerLabel.setSize(GameJFrame.uFlowerLabel.getWidth(), 70);
				GameJFrame.moveFlowerLabel.setVisible(true);	    
			}
			
			if(species == 2) {
				GameJFrame.uPeaLabel.setSize(GameJFrame.uPeaLabel.getWidth(), 70);
				GameJFrame.movePeaLabel.setVisible(true);
			}
			
			if(species == 3) {
				GameJFrame.uFlowerLabel.setSize(GameJFrame.uFlowerLabel.getWidth(), 70);
				GameJFrame.moveShovelLabel.setVisible(true);
			}
			
			if(species == 5) {
				GameJFrame.uIcePeaLabel.setSize(GameJFrame.uIcePeaLabel.getWidth(), 70);
				GameJFrame.moveIcePeaLabel.setVisible(true);
			}
			
			if(species == 6) {
				GameJFrame.uPotatoLabel.setSize(GameJFrame.uPotatoLabel.getWidth(), 70);
				GameJFrame.movePotatoLabel.setVisible(true);
			}
			if(species == 7) {
				GameJFrame.uChiliLabel.setSize(GameJFrame.uChiliLabel.getWidth(), 70);
				GameJFrame.moveChiliLabel.setVisible(true);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// 鼠标松开事件
			// 判断当前鼠标在哪个格子上面
			int x = (e.getX() - 54) / 81;
			int y = (e.getY() - 91) / 95;
			int species = this.frame.getSpecies();
			
			Plant[][] plants = GameJFrame.plants;
			JLabel[][] labels = GameJFrame.labels;
			JLabel plantLabel = GameJFrame.plantLabel;

			if (species > 0 && x >= 0 && x < 9 && y >= 0 && y < 5 && e.getX() >= 54 && e.getX() <= 836 && e.getY() >= 91
					&& e.getY() <= 600) {

				if (species == 1 && plants[y][x] == null) {
					this.frame.count -= 50;
					this.frame.SetFlowerBeginTime(System.currentTimeMillis());
					plants[y][x] = new SunFlower(x * 80 + 54, y * 90 + 85, this.frame);
					plantLabel = this.frame.makeLabel(GameJFrame.move_sun_flower, x * 80 + 54, y * 90 + 85, 73, 74);
					labels[y][x] = plantLabel;
					this.frame.add(plantLabel);

				}
				if (species == 2 && plants[y][x] == null) {
					this.frame.count -= 100;
					this.frame.SetPeaBeginTime(System.currentTimeMillis());
					plants[y][x] = new PeaShooter(x * 80 + 54, y * 90 + 85, this.frame);
					plantLabel = this.frame.makeLabel(GameJFrame.movd_single_bullet_plant, x * 80 + 54, y * 90 + 85, 71,
							71);
					labels[y][x] = plantLabel;
					this.frame.add(plantLabel);

				}
				if (species == 3) {// 铲除？
					if (plants[y][x] != null) {
						plants[y][x].setHp(0);
						playUprootSound();
					}
				}
				if (species == 5 && plants[y][x] == null) {
					this.frame.count -= 170;
					this.frame.SetIcePeaBeginTime(System.currentTimeMillis());
					plants[y][x] = new IcePeaShooter(x * 80 + 54, y * 90 + 85, this.frame);
					plantLabel = this.frame.makeLabel(GameJFrame.moveIcebean, x * 80 + 54, y * 90 + 85, 100, 100);
					labels[y][x] = plantLabel;
					this.frame.add(plantLabel);
				}
				if (species == 6 && plants[y][x] == null) {
					this.frame.count -= 50;
					this.frame.SetPotatoBeginTime(System.currentTimeMillis());
					
					plants[y][x] = new Potato(x * 80 + 54, y * 90 + 85);
					plantLabel = this.frame.makeLabel(GameJFrame.movePotato, x * 80 + 54, y * 90 + 85, 83, 119);
					labels[y][x] = plantLabel;
					this.frame.add(plantLabel);
				}
				if (species == 7 && plants[y][x] == null) {
					this.frame.count -= 150;
					this.frame.SetChiliBeginTime(System.currentTimeMillis());
					plants[y][x] = new Pepper(x * 80 + 54, y * 90 + 85, this.frame);
					plantLabel = this.frame.makeLabel(GameJFrame.chili, x * 80 + 54, y * 90 + 85, 100, 100);
					labels[y][x] = plantLabel;
					this.frame.add(plantLabel);
				}
			}

			species = 0; // 重置species为0

			this.frame.moveFlowePoint = new Point(300, -100);
			GameJFrame.moveFlowerLabel.setVisible(false);

			this.frame.movePeaPoint = new Point(360, -100);
			GameJFrame.movePeaLabel.setVisible(false);

			this.frame.moveShovelPoint = new Point(700, -100);
			GameJFrame.moveShovelLabel.setVisible(false);

			this.frame.moveIcePeaPoint = new Point(420, -100);
			GameJFrame.moveIcePeaLabel.setVisible(false);

			this.frame.movePotatoPoint = new Point(480, -100);
			GameJFrame.movePotatoLabel.setVisible(false);

			this.frame.moveChiliPoint = new Point(560, -100);
			GameJFrame.moveChiliLabel.setVisible(false);

		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
		private void playUprootSound() {
			AudioClip aau;
			try {
				File f = new File("src\\sounds\\coffee.wav");
				URI uri = f.toURI();
				URL url = uri.toURL();
				aau = Applet.newAudioClip(url);
				aau.play();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
