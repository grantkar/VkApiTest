package jdbs;

import api.utils.UtilsMethod;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class GetToken {
   public static final DBRequestHandler handler = new DBRequestHandler();

    /**
     * Метод декодирует Api Key
     */
    public String trelloApiKey() {
        handler.connect();
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(UtilsMethod.getValue("KEY"));
        handler.disconnect();
        return encryptor.decrypt(handler.getApiKey());
    }

    /**
     * Метод декодирует Trello Token
     */
    public String trelloApiToken() {
        handler.connect();
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(UtilsMethod.getValue("KEY"));
        handler.disconnect();
        return encryptor.decrypt(handler.getTrelloToken());
    }
}
