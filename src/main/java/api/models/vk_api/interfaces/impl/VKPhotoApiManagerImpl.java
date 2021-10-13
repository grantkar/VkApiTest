package api.models.vk_api.interfaces.impl;

import api.models.vk_api.interfaces.VKPhotoApiManager;
import api.utils.BaseUri;
import api.utils.UtilsHelper;
import api.utils.impl.BaseUriImpl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

import static constants.Constants.EndPoint.*;

public class VKPhotoApiManagerImpl implements VKPhotoApiManager {

    private Response response;
    private BaseUri baseUri = new BaseUriImpl();

    @Override
    public Response createPrivetAlbum(Map<String, String> params) {
        response = RestAssured.given().params(params)
                .param("title", "My test privet album")
                .param("privacy_view", "nobody / only_me")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_CREATE_ALBUM)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public Response getUrlForUploadPhotoToAlbum(Map<String, String> params, Object idAlbum) {
        response = RestAssured.given().params(params)
                .param("album_id", idAlbum)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_GET_SAVE_URL_PHOTO_TO_ALBUM)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public Response uploadPhotoToAlbum(String path) {
        String filePath = "src/test/resources/file/tester.jpg";
        RequestSpecification request = RestAssured.given();
        response = request.contentType("multipart/form-data")
                .multiPart(new File(filePath)).log().all()
                .post(path)
                .then()
                .log().body().extract().response();
        return response;
    }

    @Override
    public Response savePhotoToAlbum(Map<String, String> params, Map<String, Object> paramsForSave, Object idAlbum) {
        response = RestAssured.given().params(params)
                .param("album_id", idAlbum)
                .param("server", paramsForSave.get("server"))
                .param("photos_list", paramsForSave.get("photos_list"))
                .param("aid", paramsForSave.get("aid"))
                .param("hash", paramsForSave.get("hash"))
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_SAVE_PHOTO_TO_ALBUM)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public void makerPhotoCoverAlbum(Map<String, String> params, Object idAlbum, Object idOwner, Object idPhoto) {
        response = RestAssured.given().params(params)
                .param("album_id", idAlbum)
                .param("owner_id", idOwner)
                .param("photo_id", idPhoto)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_MAKER_PHOTO_COVER_ALBUM)
                .then().log().body().extract().response();
    }

    @Override
    public void createCommentPhoto(Map<String, String> params, Object idOwner, Object idPhoto) {
        response = RestAssured.given().params(params)
                .param("owner_id", idOwner)
                .param("photo_id", idPhoto)
                .param("message", "Test comment by My")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_CREATE_COMMENT_PHOTO)
                .then().log().body().extract().response();
    }

    @Override
    public void putTagToPhoto(Map<String, String> params, Object idOwner, Object idPhoto) {
        response = RestAssured.given().params(params)
                .param("owner_id", idOwner)
                .param("photo_id", idPhoto)
                .param("user_id", idOwner)
                .param("x", "10")
                .param("y", "10")
                .param("x2", "80")
                .param("y2", "80")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_PUT_TAG)
                .then().log().body().extract().response();
    }

    @Override
    public Response createAlbum(Map<String, String> params) {
        response = RestAssured.given().params(params)
                .param("title", "My test album")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_CREATE_ALBUM)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public void movePhotoToAlbum(Map<String, String> params, Object idOwner, Object TargetAlbumId, Object idPhoto) {
        response = RestAssured.given().params(params)
                .param("owner_id", idOwner)
                .param("target_album_id", TargetAlbumId)
                .param("photo_id", idPhoto)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_MOVE_PHOTO)
                .then().log().body().extract().response();
    }

    @Override
    public void deletePhotoAlbum(Map<String, String> params, Object idAlbum) {
        response = RestAssured.given().params(params)
                .param("album_id", idAlbum)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_DELETE_PHOTO_ALBUM)
                .then().log().body().extract().response();
    }

}
