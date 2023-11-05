package socket;

import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

import utils.LoggerUtils;

public class HealthChecker implements Runnable {

	@Override
	public void run() {

		while (true) {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// start the monitor thread

			CyclicBarrier cb = new CyclicBarrier(Server.instances.size());

			for (Entry<String, Boolean> e : Server.instances.entrySet()) {

				String instance = e.getKey();
				Callable<Boolean> c = new HealthCheckService(instance);
				try {
					
					boolean ans = c.call();
					LoggerUtils.log("Checking heartbeat of instance : " + instance + " | ACTIVE : " + ans);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

	}

}
