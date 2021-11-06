package jdbs;

import api.utils.UtilsMethod;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class GetToken {
   public static final DBRequestHandler handler = new DBRequestHandler();

    public String decrypt() {
        handler.connect();
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(UtilsMethod.getValue("KEY"));
        return encryptor.decrypt(handler.getToken());
    }
}
