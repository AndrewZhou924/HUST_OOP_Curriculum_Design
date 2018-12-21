package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

//import javax.naming.directory.AttributeInUseException;

//import javafx.scene.paint.Stop;

public class eatMusic implements Runnable {
	public AudioClip aau;

	@Override
	public void run() {
		try {
			File f = new File("curriculum_design\\src\\sounds\\chomp.wav");
			URI uri = f.toURI();
			URL url = uri.toURL();
			aau = Applet.newAudioClip(url);
			aau.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
