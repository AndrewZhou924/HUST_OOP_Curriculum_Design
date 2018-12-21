package game;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class quitButton extends JButton {
	private Image img;
	private static final long serialVersionUID = 1L;
	ImageIcon quit_img = new ImageIcon(GameJFrame.class.getResource("/images/gif/exitgame.png"));
	private JFrame frame;
	
	public quitButton() {
        super();

    }

    public quitButton(String buttonText) {
        super(buttonText);
        
        //添加鼠标监听
        this.addMouseListener(
        		
        	new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //当鼠标进入时,鼠标进入状态改为TRUE，并重绘按钮
//                isMouseEntered = 0;
//                repaint();
//                super.mouseEntered(e);
            }
            
            @Override
            public void mousePressed(MouseEvent arg0) {
            	System.out.println("the quit button is pressed");
            	
            	MusicButton.closeMusic();
            	new LevelSelect(); // 进入游戏界面
            	frame.dispose(); // 关闭开始菜单的GUI页面
				setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

        });
        
        
		//去掉背景点击效果
		setContentAreaFilled(false);  
//		//去掉聚焦线
		setFocusPainted(false);
//		//去掉边框
		setBorder(null); 
		setIcon(quit_img);
		setBounds(1300, 10, 89, 22);

    }
    
    public void setJFrame(JFrame frame) {
    	this.frame = frame;
    }
    
    
	@Override
	public void setIcon(Icon icon){
		img = ((ImageIcon)icon).getImage();
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if(img != null)
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
