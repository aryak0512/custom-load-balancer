package socket;

import java.io.InputStream;
import java.io.OutputStream;

import utils.LoggerUtils;

public class RequestService {

	public void submit(InputStream in, OutputStream out) {

		try {

			// this array will store the input sent by client
			byte[] input = new byte[1024];
			in.read(input);

			// input captured in a string
			String s = new String(input);

			LoggerUtils.log(s);

			// extracting request information from first line of request
			String requestType = s.split("\n")[0].split(" ")[0];
			String mapping = s.split("\n")[0].split(" ")[1];
			String protocol = s.split("\n")[0].split(" ")[2];

			LoggerUtils.log("Type = " + requestType + " | Mapping = " + mapping + " | Protocol = " + protocol);

			// setting response parameters
			out.write("HTTP/1.1 200 OK\r\n".getBytes());

			// intimating browser to understand that this is a web page
			out.write("ContentType: text/html".getBytes());
			out.write("\r\n".getBytes());

			out.flush();

		} catch (Exception e) {

			LoggerUtils.log("ERROR !! " + e.getLocalizedMessage());
			e.printStackTrace();
		}

	}

}
