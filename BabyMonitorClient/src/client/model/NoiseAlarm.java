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

import client.view.BMS_GUI;

//import the sun.audio package

@SuppressWarnings("serial")
public class NoiseAlarm extends JPanel {

	private BufferedImage image;
	private Clip clip;
	private boolean status = false;
	private boolean alarmsound = true;
	private boolean alarmPlaying = true;
	private BMS_GUI gui;

	public NoiseAlarm(BMS_GUI gui) {
		if (gui != null) {
			this.setGui(gui);
			gui.setNoiseAlarmPanel(this);
		}
		try {
			clip = AudioSystem.getClip();
			File file = new File("src/rsc/beeper_alarm.wav");
			AudioInputStream ais;
			ais = AudioSystem.getAudioInputStream(file);
			clip.open(ais);
		} catch (LineUnavailableException | UnsupportedAudioFileException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// getAudioInputStream() also accepts a File or InputStream

		try {
			image = ImageIO.read(new File("src/rsc/greenAlarm.png"));
		} catch (IOException ex) {
			System.err.println("Image file not found..."); // handle
															// exception...
		}
	}

	public void notify(Boolean status) {
		this.setStatus(status);
		// load the correct pic wrt current status
		try {
			if (this.isShowing()) {
				image = ImageIO.read(new File("src/rsc/redAlarm.png"));
				if (this.isAlarmPlaying())
					this.playAlarm();
			} else {
				image = ImageIO.read(new File("src/rsc/greenAlarm.png"));

			}
		} catch (IOException ex) {
			System.err.println("Image file not found..."); // handle
															// exception...
		}
		
		//update gui
		this.getGui().repaint();
	}

	public void playAlarm() {

		clip.start();

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public BMS_GUI getGui() {
		return gui;
	}

	public void setGui(BMS_GUI gui) {
		this.gui = gui;
	}

}