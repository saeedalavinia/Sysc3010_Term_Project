package client.startUp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import client.model.NoiseAlarm;
import client.model.ThermometerBarChart;
import client.view.BMS_GUI;

public class ClientMain {
	Socket connectionSocket;
	BufferedReader buffReader;
	String receivedMsg;
	volatile boolean stop = false;
	String parsedTemperature, alarm, parsedTag;
	BMS_GUI gui;

	public ClientMain() {

		try {
			connectionSocket = new Socket("Localhost", 5000);
			gui = new BMS_GUI();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void receiveMsg() {
		try {
			buffReader = new BufferedReader(new InputStreamReader(
					connectionSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			ThermometerBarChart.getInstance().notify(
					Integer.valueOf(parsedFragments[2]));

		}

		// if xml tag was alarm
		if (parsedFragments[1].equalsIgnoreCase("alarm")) {
			// update the alarm status
			NoiseAlarm.getInstance()
					.notify(Boolean.valueOf(parsedFragments[2]));

			System.out.println("Voice alarm is: " + parsedFragments[2]);

		}
	}

	public static void main(String[] args) {
		ClientMain client = new ClientMain();
		client.receiveMsg();

	}

}
