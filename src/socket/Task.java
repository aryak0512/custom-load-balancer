package socket;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CyclicBarrier;

import utils.LoggerUtils;

/**
 * 
 * The job of this thread is to hit the back-end server and store the health by
 * viewing the HTTP status of the response in concurrent map
 * 
 **/
public class Task implements Runnable {

	private String instance;
	private CyclicBarrier cb;

	public Task(String instance, CyclicBarrier cb) {
		this.cb = cb;
		this.instance = instance;
	}

	@Override
	public void run() {

		try {
			cb.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpURLConnection connection = null;
		boolean isAlive = false;

		try {

			URL url = new URL("http://" + instance + "/health");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			isAlive = connection.getResponseCode() == 200;

		} catch (Exception e) {

			LoggerUtils.log(e.getLocalizedMessage());

		} finally {

			Server.instances.put(instance, isAlive);

			if (connection != null) {
				connection.disconnect();
			}
			
			LoggerUtils.log(""+Server.instances);
		}

	}

}
