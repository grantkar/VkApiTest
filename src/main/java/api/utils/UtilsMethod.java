package api.utils;

import lombok.experimental.UtilityClass;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@UtilityClass
public class UtilsMethod {

    public String getValue(String value){
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/" + "userData.properties");) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = properties.getProperty(value);
        try{
            data = new String(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
