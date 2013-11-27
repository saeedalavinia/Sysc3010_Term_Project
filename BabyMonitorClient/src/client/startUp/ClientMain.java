package client.startUp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import client.model.NoiseAlarm;
import client.model.ThermometerBarChart;
import client.view.BMS_GUI;

public class ClientMain {
	private Socket connectionSocket;
	private BufferedReader buffReader;
	private String receivedMsg;
	private volatile boolean stop = false;
	private ThermometerBarChart tbc;
	private NoiseAlarm na;

	public ClientMain(NoiseAlarm na, ThermometerBarChart tbc) {
		this.na=na;
		this.tbc=tbc;
	}

	public void connect() {

		try {
			connectionSocket = new Socket("Localhost", 5000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			buffReader = new BufferedReader(new InputStreamReader(
					connectionSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void receiveMsg() {
		
		while (!stop) {
			// receive msg
			try {

				receivedMsg = buffReader.readLine();
				if (receivedMsg == null) {
					stop = true;
					break;
				}
				parseXmlMsg(receivedMsg);
			} catch (IOException e) {
				System.err.println("Error receiving msg!");
			}
		}
	}
	public void parseXmlMsg(String s) {

		// parse the xml
		String delim = "[<>]";
		String[] parsedFragments = s.split(delim);

		// if xml tag was temp
		if (parsedFragments[1].equalsIgnoreCase("temp")) {

			System.out.println("Current Temperature is: " + parsedFragments[2]
					+ "C");

			// update thermometer gui
			tbc.notify(
					Integer.valueOf(parsedFragments[2]));

		}

		// if xml tag was alarm
		if (parsedFragments[1].equalsIgnoreCase("alarm")) {
			
			
			if(!parsedFragments[2].equalsIgnoreCase("true")&& !parsedFragments[2].equalsIgnoreCase("false")){
				try {
					throw new Exception("non boolean input Detected");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			}
			// update the alarm status
			na.notify(Boolean.valueOf(parsedFragments[2]));

			System.out.println("Voice alarm is: " + parsedFragments[2]);

		}
	}

	public static void main(String[] args) {
		BMS_GUI gui = new BMS_GUI();
		
		ClientMain client = new ClientMain(gui.getController().getNa(),gui.getController().getTbc());
		client.connect();
		client.receiveMsg();

	}

}
