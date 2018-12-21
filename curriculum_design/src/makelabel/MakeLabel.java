package makelabel;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MakeLabel {
	public static JLabel makeLabel(ImageIcon im, int x, int y, int w, int h) {
		JLabel temp = new JLabel(im);
		temp.setBounds(x, y, w, h);
		return temp;
	}
	
	public static JLabel makeLabel(ImageIcon im, Point p, int w, int h)
	{
		JLabel temp = new JLabel(im);
		temp.setBounds((int)p.getX(), (int)p.getY(), w, h);
		return temp;
	}

}
