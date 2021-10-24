package api.models.vk_api.interfaces.impl;

import api.models.vk_api.interfaces.VKDocksApiManager;
import api.utils.UtilsHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.io.File;
import java.util.Map;

import static constants.Constants.EndPoint.*;

@Getter
public class VKDocksApiManagerImpl implements VKDocksApiManager {

    private Response response;

    @Override
    public void getURIUploadServerDocks(Map<String, String> params) {
        response = RestAssured.given().params(params).log().uri()
                .when().get(VK_API_ENDPOINT_GET_SAVE_URL_DOCKS)
                .then().log().body().extract().response();
    }

    @Override
    public void uploadDockToServer() {
        String filePath = "src/test/resources/file/dock.txt";
        String path = UtilsHelper.getUrlUploadFile(getResponse());
        RequestSpecification request = RestAssured.given();
        response = request.contentType("multipart/form-data")
                .multiPart(new File(filePath)).log().all()
                .post(path)
                .then()
                .log().all().extract().response();
    }

    @Override
    public void saveDocksFromServer(Map<String, String> params) {
        response = RestAssured.given().params(params)
                .param("file", UtilsHelper.getUploadDocksInformation(getResponse()))
                .param("title", "TEST")
                .log().uri()
                .when().get(VK_API_ENDPOINT_SAVE_DOCK)
                .then().log().body().extract().response();
    }

    @Override
    public void editDocksTitle(Map<String, String> params, Object owner_id) {
        int idDocks = response.jsonPath().getInt("response.doc.id");
        response = RestAssured.given().params(params)
                .param("owner_id", owner_id)
                .param("doc_id", idDocks)
                .param("title", "test-doc123")
                .log().uri()
                .when().get(VK_API_ENDPOINT_EDIT_DOCK)
                .then().log().body().extract().response();
    }
}
