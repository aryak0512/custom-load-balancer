package socket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.LoggerUtils;

public class Server {

	public static Map<String, Boolean> instances = new ConcurrentHashMap<>();

	public static void main(String[] args) throws Exception {

		// server will listen to client requests on this port
		ServerSocket serverSocket = new ServerSocket(7200);

		printBanner();
		populateInstanceMap();

		// start a daemon thread for health check of servers
		Thread t = new Thread(new HealthChecker());
		t.setName("HEALTH-CHECK");
		t.start();

		LoggerUtils.log("Server is ready to accept requests on port 7200...");
		System.out.println(instances);

		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		while (true) {

			LoggerUtils.log("Waiting for client ...");

			// waiting to accept connection request from client
			Socket socket = serverSocket.accept();

			// delegate request to separate thread from thread pool
			executorService.execute(new ServerThread(socket));

		}

	}

	private static void populateInstanceMap() {

		try {
			read("instances.props");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void printBanner() throws IOException {

		FileReader fr = new FileReader("banner.txt");
		int i;
		while ((i = fr.read()) != -1)
			System.out.print((char) i);
		fr.close();
		System.out.println();

	}

	private static void read(String filename) throws IOException {

		String fileName = "instances.props";
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		try {

			String line;
			while ((line = br.readLine()) != null) {
				instances.put(line.replace(" ", ":"), false);
			}

		} finally {
			br.close();
		}
	}

}
