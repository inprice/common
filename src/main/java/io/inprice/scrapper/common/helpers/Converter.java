package io.inprice.scrapper.common.helpers;

import java.io.*;

/**
 * This is a helper class to convert Object to byte array and vice versa
 *
 * @author mdpinar
 */
public class Converter {

    /**
     * From byte array to Object
     *
     * @param byteArray
     * @param <T>
     * @return
     */
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

    /**
     * From Object to byte array
     *
     * @param object
     * @return
     */
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
