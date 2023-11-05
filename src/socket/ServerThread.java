package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import utils.LoggerUtils;

public class ServerThread implements Runnable {

	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {

			LoggerUtils.log("Client has arrived. IP : " + socket.getInetAddress() + " | Port : " + socket.getPort());
			// for reading input from client
			InputStream in = socket.getInputStream();
			// for sending response to client
			OutputStream out = socket.getOutputStream();
			RequestService handler = new RequestService();
			handler.submit(in, out);
			socket.close();

		} catch (Exception e) {
			LoggerUtils.log("ERROR :" + e.getLocalizedMessage());
		}
	}

}
