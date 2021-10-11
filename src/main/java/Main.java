import api.utils.UtilsMethod;
import jdbs.DBRequestHandler;
import org.apache.commons.lang3.RandomStringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Main {


    public static void main(String[] args) {

        String s = RandomStringUtils.random(100, true, false);
        System.out.println(s);
        System.out.println(s);
    }

    public static String decrypt(String ciphertext) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(UtilsMethod.getValue("KEY"));
        return encryptor.decrypt(ciphertext);
    }

    public static String encryptedPassword (String ciphertext) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(UtilsMethod.getValue("KEY"));
        return encryptor.encrypt(ciphertext);
    }

    }
