package client.view;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import client.controller.MainController;
import client.model.NoiseAlarm;

public class BMS_GUI {

	private JFrame frame;
	private MainController controller = new MainController(this);
	private JButton toggleUnits;
	private JButton alarmSoundOff;
	/**
	 * Create the application.
	 */
	public BMS_GUI() {
		initialize();
	}
	public void repaint(){
		frame.repaint();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//JPanel panel = ThermometerBarChart.getInstance().getChartPanel();
		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JLabel lblBabyMonitoringSystem = new JLabel("Baby Monitoring System");

		JLabel lblTemperature = new JLabel("Temperature");

		JLabel lblVideoSurvailance = new JLabel("Video Survailance");

		JLabel lblVoiceAlarm = new JLabel("Voice Alarm:");

		this.toggleUnits = new JButton("Toggle Units");

		this.alarmSoundOff = new JButton("Sound Off");

		toggleUnits.addActionListener(controller);
		alarmSoundOff.addActionListener(controller);
		
		JPanel panel_3 = NoiseAlarm.getInstance();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(427)
							.addComponent(lblBabyMonitoringSystem))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTemperature))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVideoSurvailance)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblVoiceAlarm)
										.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE)
										.addComponent(alarmSoundOff)
										.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(toggleUnits)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBabyMonitoringSystem)
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTemperature)
						.addComponent(lblVideoSurvailance))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblVoiceAlarm)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(toggleUnits)
						.addComponent(alarmSoundOff))
					.addContainerGap(30, Short.MAX_VALUE))
		);

		frame.getContentPane().setLayout(groupLayout);

	}
	public JButton getToggleUnits() {
		return toggleUnits;
	}
	public void setToggleUnits(JButton toggleUnits) {
		this.toggleUnits = toggleUnits;
	}
	public JButton getAlarmSoundOff() {
		return alarmSoundOff;
	}
	public void setAlarmSoundOff(JButton alarmSoundOff) {
		this.alarmSoundOff = alarmSoundOff;
	}
}
