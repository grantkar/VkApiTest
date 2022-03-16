package api.models.trello_api.interfaces.impl;

import api.models.trello_api.interfaces.TrelloBoardManager;
import api.utils.UtilsHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import static constants.Constants.EndPoint.*;

@Getter
public class TrelloBoardManagerImpl implements TrelloBoardManager {

    private Response response;
    private String idBoard;

    @Override
    public Response createBoard(Map<String, String> params) {
        JSONObject object = new JSONObject(params);
        object.put("name", "KanbanTool");
        response = RestAssured.given().contentType("application/json")
                .body(object.toString()).when().post(TRELLO_ENDPOINT_CREATE_A_BOARD)
                .then().statusCode(200).log().body().extract().response();
        idBoard = response.jsonPath().getString("id");
        return response;
    }

    @Override
    public String checkCreatedBoard(Map<String, String> params) {
        response = RestAssured.given().params(params)
                .when().get(TRELLO_ENDPOINT_CREATE_A_BOARD + "/" + idBoard)
                .then().statusCode(200).log().body().extract().response();
        return response.jsonPath().getString("name");
    }

    @Override
    public Response createList(Map<String, String> params, String idBoard, String name) {
        JSONObject object = new JSONObject(params);
        object.put("name", name);
        object.put("idBoard", idBoard);
        response = RestAssured.given().contentType("application/json")
                .body(object.toString()).when().post(TRELLO_ENDPOINT_CREATE_A_LIST)
                .then().statusCode(200).log().body().extract().response();
        return response;
    }

    @Override
    public Response createCard(Map<String, String> params, String idList) {
        JSONObject object = new JSONObject(params);
        object.put("name", "Карточка для изучения API");
        object.put("idList", idList);
        response = RestAssured.given().contentType("application/json")
                .body(object.toString()).when().post(TRELLO_ENDPOINT_CREATE_A_CARD)
                .then().statusCode(200).log().body().extract().response();
        return response;
    }

    @Override
    public Response createAttachment(Map<String, String> params, String idCard) {
        params.put("name", "Tester");
        String filePath = "src/test/resources/file/tester.jpg";

        response = RestAssured.given()
                .params(params)
                .contentType("multipart/form-data")
                .multiPart(new File(filePath))
                .post(TRELLO_ENDPOINT_CREATE_A_CARD + "/" + idCard + TRELLO_ENDPOINT_CREATE_ATTACHMENT)
                .then().statusCode(200).log().body().extract().response();
        return response;
    }

    @Override
    public void addDueToCard(Map<String, String> params, String idCard) {
        JSONObject object = new JSONObject(params);
        object.put("due", UtilsHelper.getDateTomorrow());
        response = RestAssured.given().contentType("application/json")
                .body(object.toString()).when().put(TRELLO_ENDPOINT_CREATE_A_CARD + "/" + idCard)
                .then().statusCode(200).log().body().extract().response();
    }

    @Override
    public void addDescriptionToCard(Map<String, String> params, String idCard) {
        JSONObject object = new JSONObject(params);
        object.put("desc", "Тут будет отмечаться прогресс обучения");
        response = RestAssured.given().contentType("application/json")
                .body(object.toString()).when().put(TRELLO_ENDPOINT_CREATE_A_CARD + "/" + idCard)
                .then().statusCode(200).log().body().extract().response();
    }
}
