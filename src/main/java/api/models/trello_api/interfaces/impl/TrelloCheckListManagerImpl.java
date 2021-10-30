package api.models.trello_api.interfaces.impl;

import api.models.trello_api.interfaces.TrelloCheckListManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import java.util.Map;

import static constants.Constants.EndPoint.*;

public class TrelloCheckListManagerImpl implements TrelloCheckListManager {

    private Response response;

    @Override
    public Response createACheckList(Map<String, String> params, String idCard) {
        JSONObject object = new JSONObject(params);
        object.put("idCard", idCard);
        response = RestAssured.given().contentType("application/json")
                .body(object.toString()).when().post(TRELLO_ENDPOINT_CREATE_A_CHECKLIST)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public Response createACheckItems(Map<String, String> params, String idCheckList, String name) {
        JSONObject object = new JSONObject(params);
        object.put("name", name);
        response = RestAssured.given().contentType("application/json")
                .body(object.toString()).when().post(TRELLO_ENDPOINT_CREATE_A_CHECKLIST + "/" + idCheckList + TRELLO_ENDPOINT_CREATE_A_CHECKITEMS)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public void updateACheckItems(Map<String, String> params, String idCard, String idCheckItem) {
        response = RestAssured.given().contentType("application/json")
                .pathParam("id", idCard)
                .pathParam("idCheckItem", idCheckItem)
                .params(params)
                .queryParam("state", "complete")
                .when().put(TRELLO_ENDPOINT_UPDATE_CHECKITEM)
                .then().log().body().extract().response();
    }

    @Override
    public void updateCard(Map<String, String> params, String idCard, String idList) {
        JSONObject object = new JSONObject(params);
        object.put("id", idCard);
        object.put("idList", idList);
        response = RestAssured.given().contentType("application/json")
                .body(object.toString()).when().put(TRELLO_ENDPOINT_CREATE_A_CARD + "/" + idCard)
                .then().log().body().extract().response();
    }

    @Override
    public void archiveList(Map<String, String> params, String idList) {
        JSONObject object = new JSONObject(params);
        object.put("value", "true");
        response = RestAssured.given().contentType("application/json")
                .pathParam("id", idList)
                .log().uri()
                .body(object.toString()).when().put(TRELLO_ENDPOINT_ARCHIVE_A_LIST)
                .then().log().body().extract().response();
    }

    @Override
    public void createAction(Map<String, String> params, String idCard) {
        JSONObject object = new JSONObject(params);
        object.put("text", ":thumbsup:");
        response = RestAssured.given().contentType("application/json")
                .pathParam("id", idCard)
                .log().uri()
                .body(object.toString()).when().post(TRELLO_ENDPOINT_ADD_ACTION)
                .then().log().body().extract().response();
    }

}
