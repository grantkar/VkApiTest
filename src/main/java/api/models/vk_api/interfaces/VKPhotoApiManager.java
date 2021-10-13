package api.models.vk_api.interfaces;

import io.restassured.response.Response;

import java.util.Map;

public interface VKPhotoApiManager {

    /**
     * return - response содержащий информацию о созданном приватном альбоме
     * */
    Response createPrivetAlbum (Map<String, String> params);

    /**
     * return - response содержащий информацию о Url на который нужно загрузить фотографию
     * */
    Response getUrlForUploadPhotoToAlbum (Map<String, String> params, Object idAlbum);

    /**
     * return - response содержащий информацию о загруженной фотографии в idAlbum альбом
     * */
    Response uploadPhotoToAlbum (String path);

    /**
     * return - response содержащий информацию о сохраненной фотографии в альбоме
     * */
    Response savePhotoToAlbum (Map<String, String> params, Map<String, Object> paramsForSave ,Object idAlbum);

    /**
     * Метод делает idPhoto фотографию обложкой альбома idAlbum
     * */
    void makerPhotoCoverAlbum (Map<String, String> params, Object idAlbum, Object idOwner, Object idPhoto);

    /**
     * Метод комментирует idPhoto фотографию
     * */
    void createCommentPhoto (Map<String, String> params, Object idOwner, Object idPhoto);

    /**
     * Метод отмечает пользователя idOwner на idPhoto фотографии
     * */
    void putTagToPhoto (Map<String, String> params, Object idOwner, Object idPhoto);

    /**
     * return - response содержащий информацию о созданном публичном альбоме
     * */
    Response createAlbum (Map<String, String> params);

    /**
     * Метод переносит фотографию idPhoto загруженную пользователем idOwner в альбом TargetAlbumId
     * */
    void movePhotoToAlbum (Map<String, String> params, Object idOwner, Object TargetAlbumId, Object idPhoto);

    /**
     * Метод удаляет альбом idAlbum
     * */
    void deletePhotoAlbum (Map<String, String> params, Object idAlbum);
}
