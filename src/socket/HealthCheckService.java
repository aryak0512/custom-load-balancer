package socket;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class HealthCheckService implements Callable<Boolean> {

	private String instance;

	public HealthCheckService(String instance) {
		this.instance = instance;
	}

	@Override
	public Boolean call() {

		HttpURLConnection connection = null;

		try {

			URL url = new URL("http://" + instance + "/health");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			return connection.getResponseCode() == 200;

		} catch (Exception e) {
			
			return false;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

}
