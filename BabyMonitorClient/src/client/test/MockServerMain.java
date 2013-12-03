package client.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MockServerMain {
	public PrintWriter p;
	public Socket socket;
	public ServerSocket serversocket;

	public void serverStart() throws InterruptedException {

		try {
			serversocket = new ServerSocket(5000);
			socket = serversocket.accept();
			System.out.println(socket.getInetAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	public void sendTemp(String t) {
		try {
			p = new PrintWriter(socket.getOutputStream(), true);

			p.println("<temp>" + t + "</temp>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void closeConnection(){
		p.close();
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendalarm(String a) {
		try {
			p = new PrintWriter(socket.getOutputStream(), true);

			p.println("<alarm>" + a + "</alarm>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendmsgs() throws InterruptedException {
		try {
			p = new PrintWriter(socket.getOutputStream(), true);
			p.println("<temp>3</temp>");
			Thread.sleep(2000);
			p.println("<alarm>true</alarm> ");
			p.println("<alarm>true</alarm> ");
			p.println("<alarm>true</alarm> ");
			Thread.sleep(2000);
			p.println("<alarm>true</alarm> ");
			p.println("<alarm>true</alarm> ");
			Thread.sleep(2000);
			p.println("<alarm>true</alarm> ");
			p.println("<temp>12</temp>");
			Thread.sleep(2000);
			p.println("<temp>18</temp>");
			Thread.sleep(500);
			p.println("<temp>35</temp>");
			Thread.sleep(1000);
			p.println("<alarm>true</alarm> ");
			Thread.sleep(2000);
			p.println("<alarm>false</alarm> ");
			p.println("<temp>28</temp>");
			Thread.sleep(2000);
			p.println("<temp>22</temp>");
			Thread.sleep(3000);
			p.println("<temp>20</temp>");
			Thread.sleep(2000);
			p.println("<temp>-7</temp>");
			Thread.sleep(2000);
			p.println("<temp>25</temp>");
			Thread.sleep(5000);
			p.println("<temp>10</temp>");
			p.println("<alarm>true</alarm> ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws InterruptedException {

		MockServerMain msm = new MockServerMain();
		msm.serverStart();
		msm.sendmsgs();
		msm.closeConnection();
		
	}

}
