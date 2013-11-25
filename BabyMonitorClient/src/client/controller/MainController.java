package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.NoiseAlarm;
import client.model.ThermometerBarChart;
import client.view.BMS_GUI;

public class MainController implements ActionListener {

	BMS_GUI gui;

	public MainController(BMS_GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equalsIgnoreCase("Sound Off")) {

			
				NoiseAlarm.getInstance().setAlarmPlaying(false);
				gui.getAlarmSoundOff().setText("Sound ON");
		}
		if (e.getActionCommand().equalsIgnoreCase("Sound ON")) {
				NoiseAlarm.getInstance().setAlarmPlaying(true);
				gui.getAlarmSoundOff().setText("Sound OFF");
			
		}
		if (e.getActionCommand().equalsIgnoreCase("Toggle Units")) {
			ThermometerBarChart.getInstance().toggleUnits();
			// gui.repaint();

		}
	
	}
}
