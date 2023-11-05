package utils;
import java.util.Date;

public class LoggerUtils {

	public static void log(String... message) {
		System.out.println(new Date() + " --- Thread [" + Thread.currentThread().getName() + "] : " + message[0]);
	}

}