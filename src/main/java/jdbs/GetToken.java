package jdbs;

import api.utils.UtilsMethod;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class GetToken {
   public DBRequestHandler handler = new DBRequestHandler();

    public String trelloApiKey() {
        handler.connect();
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(UtilsMethod.getValue("KEY"));
        return encryptor.decrypt(handler.getApiKey());
    }

    public String trelloApiToken() {
        handler.connect();
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(UtilsMethod.getValue("KEY"));
        return encryptor.decrypt(handler.getTrelloToken());
    }
}
