package api.models.vk_api;

import java.util.Map;

public interface VKInfoApiManager {

    /**
     * return - информацию о текущем профиле
     * */
    String getProfileInfo (Map<String, String> params);

    /**
     * Метод заполняет недостающую информацию
     * */
    void setProfileInfo(Map<String, String> params, String emptyField, String emptyValue);

    /**
     * Метод получает в ответ ссылку для загрузки главной фотографии профиля
     * */
    void getUrlForUploadPhoto(Map<String, String> params);

    /**
     * Метод загружает фотографию на сервер
     * return - Мапу содержащую информацию для сохранения фотографии
     * */
    Map<String, Object> uploadPhotoToServer();

    /**
     * Метод сохраняет и устанавливает загруженную фотографию - главной фотографией профиля
     * */
    void savePhoto(Map<String, String> params, Object valueServer, Object valuePhoto, Object valueHash);
}
