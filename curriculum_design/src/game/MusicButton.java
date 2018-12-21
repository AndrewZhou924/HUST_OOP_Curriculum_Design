package game;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class MusicButton extends JButton {
	private Image img;
	private static final long serialVersionUID = 1L;
	ImageIcon music1 = new ImageIcon(GameJFrame.class.getResource("/images/gif/musicicon.png"));
	ImageIcon music2 = new ImageIcon(GameJFrame.class.getResource("/images/gif/musiciconoff.png"));
	
	private boolean playMusic = true;
	public static AudioClip aau = null;
	
	public MusicButton(AudioClip aau) {
        super();
      //���������
        MusicButton.aau = aau;
        
        this.addMouseListener(
        		
        	new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //��������ʱ,������״̬��ΪTRUE�����ػ水ť
//                isMouseEntered = 0;
//                repaint();
//                super.mouseEntered(e);
            }
            @Override
            
            public void mousePressed(MouseEvent arg0) {
            	System.out.println("the music button is pressed");
            	if(playMusic == true) {
            		setIcon(music2);
            		playMusic = false;
            		MusicButton.aau.stop();
            	} else {
            		setIcon(music1);
            		playMusic = true;
            		MusicButton.aau.loop();
            	}
            	
            	
            	//TODO ���뵽��Ϸ�˵�
            }

        });
        
        
		//ȥ���������Ч��
		setContentAreaFilled(false);  
		//ȥ���۽���
		setFocusPainted(false);
		//ȥ���߿�
		setBorder(null); 

		setIcon(music1);

		setBounds(1250, 550, 89, 22);
    }

    public static void closeMusic() {
    	MusicButton.aau.stop();
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
