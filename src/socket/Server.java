package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.LoggerUtils;
import utils.PrintUtils;

public class Server {

	public static Map<String, Boolean> instances = new ConcurrentHashMap<>();

	private Server() throws IOException {

		PrintUtils.printBanner();
		PrintUtils.populateInstanceMap();

		// start a daemon thread for health check of servers
		Thread t = new Thread(new HealthChecker());
		t.setName("HEALTH-CHECK");
		t.start();

	}

	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = new ServerSocket(7200);

		// to trigger start up jobs
		new Server();

		LoggerUtils.log("Server is ready to accept requests on port 7200...");
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		while (true) {

			LoggerUtils.log("Waiting for client ...");

			// waiting to accept connection request from client
			Socket socket = serverSocket.accept();

			// delegate request to separate thread from thread pool
			executorService.execute(new ServerThread(socket));

		}

	}

}
