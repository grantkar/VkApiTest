package api.models.vk_api.interfaces;

import java.util.Map;

public interface VKDocksApiManager {

    /**
     * return - url для загрузки документа на сервер
     * */
    void getURIUploadServerDocks (Map<String, String> params);

    /**
     * Метод загружает документ на сервер
     * */
    void uploadDockToServer ();

    /**
     * Метод сохраняет загруженный документ
     * */
    void saveDocksFromServer (Map<String, String> params);

    /**
     * Метод меняет название загруженного документа
     * */
    void editDocksTitle (Map<String, String> params, Object owner_id);
}
