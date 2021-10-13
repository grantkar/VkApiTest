package api.models.vk_api.interfaces.impl;

import api.models.vk_api.interfaces.VKInfoApiManager;
import api.utils.BaseUri;
import api.utils.UtilsHelper;
import api.utils.impl.BaseUriImpl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import java.io.File;
import java.util.Map;
import static constants.Constants.EndPoint.*;

@Getter
public class VKInfoApiManagerImpl implements VKInfoApiManager {

    private Response response;
    private BaseUri baseUri = new BaseUriImpl();

    @Override
    public String getProfileInfo(Map<String, String> params) {
        response = RestAssured.given().params(params).log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_GET_PROFILE_INFO)
                .then().log().body().extract().response();
        return response.body().asString();
    }

    @Override
    public void setProfileInfo(Map<String, String> params, String emptyField, String emptyValue) {
        response = RestAssured.given().params(params)
                .param(emptyField, emptyValue).log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_SET_PROFILE_INFO)
                .then().log().body().extract().response();
    }

    @Override
    public void getUrlForUploadPhoto(Map<String, String> params) {
        response = RestAssured.given().params(params).log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_GET_SAVE_URL_PHOTO)
                .then().log().body().extract().response();
        System.out.println(UtilsHelper.getUrlUploadFile(response));
    }

    @Override
    public Map<String, Object> uploadPhotoToServer() {
        String filePath = "src/test/resources/file/photo.jpg";
        String path = UtilsHelper.getUrlUploadFile(getResponse());
        RequestSpecification request = RestAssured.given();
        response = request.contentType("multipart/form-data")
                .multiPart(new File(filePath)).log().all()
                .post(path)
                .then()
                .log().all().extract().response();
        return UtilsHelper.getParamsForSavePhoto(getResponse());
    }

    @Override
    public void savePhoto(Map<String, String> params, Object valueServer, Object valuePhoto, Object valueHash) {
        response = RestAssured.given().params(params)
                .param("server", valueServer)
                .param("photo", valuePhoto)
                .param("hash", valueHash)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_UPLOAD_PHOTO_TO_SERVER)
                .then().log().body().extract().response();
    }
}
