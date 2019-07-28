package io.inprice.scrapper.common.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * This is a helper class to convert Object to byte array and vice versa
 *
 * @author mdpinar
 */
public class Converter {

    private static final Logger log = LoggerFactory.getLogger(Converter.class);

    /**
     * From byte array to Object
     *
     */
    public static <T> T toObject(byte[] byteArray) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(in);

            return (T) ois.readObject();
        } catch (Exception e) {
            log.error("Error", e);
        }

        return null;
    }

    /**
     * From Object to byte array
     *
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
            log.error("Error", e);
        }

        return null;
    }

}
