package api.utils;

import lombok.experimental.UtilityClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@UtilityClass
public class UtilsMethod {

    public static final String prop = "userData.properties";

    public String getValue(String value){
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/" + prop);) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удалось считать данные из файла " + prop);
        }
        String data = properties.getProperty(value);
        try{
            data = new String(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Ошибка кодирования");
        }
        return data;
    }
}
