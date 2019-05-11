package io.inprice.scrapper.common.helpers;

import java.io.*;

public class Converter {

    public static <T> T toObject(byte[] byteArray) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(in);

            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] fromObject(Serializable object) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            oos.close();

            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
