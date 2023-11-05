package socket;

import java.util.Map.Entry;
import java.util.concurrent.CyclicBarrier;

/**
 * 
 * This thread will make HTTP calls to back-end server instances & monitor
 * health at configured frequency
 * 
 **/
public class HealthChecker implements Runnable {

	@Override
	public void run() {

		while (true) {

			try {
				Thread.sleep(Constants.SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			CyclicBarrier cb = new CyclicBarrier(Server.instances.size());

			for (Entry<String, Boolean> e : Server.instances.entrySet()) {

				Thread t = new Thread(new Task(e.getKey(), cb));
				t.setName("HTTP-" + e.getKey());
				t.start();

			}

		}

	}

}
