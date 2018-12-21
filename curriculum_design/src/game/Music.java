package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class Music implements Runnable {

	@Override
	public void run() {
		try {
//		            File f = new File("E:\\JAVA\\eclipse x64\\MyGame\\src\\sounds\\backmusic3.wav"); 
			File f = new File("src\\sounds\\UraniwaNi.wav"); // 统一修改为相对路径
			URI uri = f.toURI();
			URL url = uri.toURL();
			AudioClip aau;
			aau = Applet.newAudioClip(url);
			aau.loop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
