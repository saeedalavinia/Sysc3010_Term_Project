package client.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
//import the sun.audio package

@SuppressWarnings("serial")
public class NoiseAlarm extends JPanel {

	private BufferedImage image;
	private Clip clip;
	private static NoiseAlarm na = null;
	private boolean alarmsound = true;
	private boolean alarmPlaying=false;

	private NoiseAlarm() {

		try {
			image = ImageIO.read(new File("src/rsc/greenAlarm.png"));
		} catch (IOException ex) {
			System.err.println("Image file not found..."); // handle
															// exception...
		}
	}

	public static NoiseAlarm getInstance() {
		if (na == null) {
			na = new NoiseAlarm();

		}
		return na;

	}

	public void notify(Boolean status) {
		try {
			if (status){
				image = ImageIO.read(new File("src/rsc/redAlarm.png"));
				if(this.isAlarmPlaying())
					this.playAlarm();
			}else{
				image = ImageIO.read(new File("src/rsc/greenAlarm.png"));
				
			}
		} catch (IOException ex) {
			System.err.println("Image file not found..."); // handle
															// exception...
		}
		this.repaint();
	}
 
	public void playAlarm() {
		//this.setAlarmPlaying(true);
		
		try {
			clip = AudioSystem.getClip();
	        // getAudioInputStream() also accepts a File or InputStream
			File file= new File("src/rsc/beeper_alarm.wav" );
	        AudioInputStream ais;
	        ais = AudioSystem.getAudioInputStream(file );
				clip.open(ais);
				clip.start();
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the
										// parameters
	}

	public boolean isAlarmsound() {
		return alarmsound;
	}

	public void setAlarmsound(boolean alarmsound) {
		this.alarmsound = alarmsound;
	}

	public boolean isAlarmPlaying() {
		return alarmPlaying;
	}

	public void setAlarmPlaying(boolean alarmPlaying) {
		this.alarmPlaying = alarmPlaying;
	}

}