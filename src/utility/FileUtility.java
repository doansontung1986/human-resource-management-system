package utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtility {
    public static void saveDataToFive(String fileName, List data) {
        String filePath = String.join(File.separator, System.getProperty("user.dir"), "data", fileName);
        File file = new File(filePath);
        FileOutputStream fos;
        ObjectOutputStream oos;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            fos = new FileOutputStream(file, false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List loadDataFromFile(String fileName) {
        String filePath = String.join(File.separator, System.getProperty("user.dir"), "data", fileName);
        File file = new File(filePath);
        FileInputStream fis;
        ObjectInputStream ois;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            while (fis.available() > 0) {
                ois = new ObjectInputStream(fis);
                return (ArrayList) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void saveDataToFive(String fileName, int data) {
        String filePath = String.join(File.separator, System.getProperty("user.dir"), "data", fileName);
        File file = new File(filePath);
        FileOutputStream fos;
        DataOutputStream dos;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            fos = new FileOutputStream(file, false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            dos = new DataOutputStream(fos);
            dos.writeInt(data);
            dos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer loadIntegerDataFromFile(String fileName) {
        String filePath = String.join(File.separator, System.getProperty("user.dir"), "data", fileName);
        File file = new File(filePath);
        FileInputStream fis;
        DataInputStream dis;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            while (fis.available() > 0) {
                dis = new DataInputStream(fis);
                return dis.readInt();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
