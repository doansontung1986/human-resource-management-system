package utility;

import java.util.List;

public interface DataWritable<T> {
    void writeDataToFile(T data, String fileName);
}
