package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import socket.Server;

public class PrintUtils {

	public static void populateInstanceMap() {

		try {
			read("instances.props");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void printBanner() throws IOException {

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
				Server.instances.put(line.replace(" ", ":"), false);
			}

		} finally {
			br.close();
		}
	}

}
