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
        
        //���������
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
            	System.out.println("the quit button is pressed");
            	
            	MusicButton.closeMusic();
            	new LevelSelect(); // ������Ϸ����
            	frame.dispose(); // �رտ�ʼ�˵���GUIҳ��
				setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

        });
        
        
		//ȥ���������Ч��
		setContentAreaFilled(false);  
//		//ȥ���۽���
		setFocusPainted(false);
//		//ȥ���߿�
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
