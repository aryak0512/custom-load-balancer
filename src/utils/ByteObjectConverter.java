package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ByteObjectConverter {

	public static byte[] objectToByte(Object obj) {

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos)) {

			oos.writeObject(obj);
			return bos.toByteArray();

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

	}
	
	/*
	public static byte[] byteToObject(Object obj) {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
			     ObjectInputStream ois = new ObjectInputStream(bis)) {
			    User deserializedUser = (User) ois.readObject();
			    System.out.println(deserializedUser);
			}

	}
	*/

}
