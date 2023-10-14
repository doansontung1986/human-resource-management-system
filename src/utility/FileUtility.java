package utility;

import java.io.*;
import java.util.List;

public class FileUtility<T> implements DataReadable, DataWritable<T> {
    private static FileUtility fileUtility;

    private FileUtility() {

    }

    public static <T> FileUtility getInstance() {
        if (fileUtility == null) {
            fileUtility = new FileUtility();
        }
        return fileUtility;
    }

    @Override
    public void writeDataToFile(T data, String fileName) {
        String filePath = String.join(File.separator, System.getProperty("user.dir"), "data", fileName);

        if (StringUtil.isNullOrEmpty(filePath)) {
            return;
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath, false))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            System.out.println("Ghi danh sách dữ liệu thất bại.");
        }
    }

    @Override
    public Object readDataFromFile(String fileName) {
        String filePath = String.join(File.separator, System.getProperty("user.dir"), "data", fileName);

        if (StringUtil.isNullOrEmpty(filePath)) {
            return null;
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File dữ liệu không tồn tại hoặc không thể khởi tạo danh sách dữ liệu");
        }

        return null;
    }
}
