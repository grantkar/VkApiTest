package api.models.vk_api.interfaces.impl;

import api.models.vk_api.interfaces.VKTopicApiManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;
import java.util.Map;
import static constants.Constants.EndPoint.*;

@Getter
public class VKTopicApiManagerImpl implements VKTopicApiManager {

    private Response response;
    private static final String GROUP = "group_id";
    private static final String TOPIC = "topic_id";

    @Override
    public Response createGroup(Map<String, String> params) {
        response = RestAssured.given().params(params)
                .param("title", "My test group")
                .log().uri()
                .when().get(VK_API_ENDPOINT_CREATE_GROUPS)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public Response boardAddTopic(Map<String, String> params, Object idGroup) {
        response = RestAssured.given().params(params)
                .param(GROUP, idGroup)
                .param("title", "Discussion")
                .param("text", "Test discussion")
                .log().uri()
                .when().get(VK_API_ENDPOINT_ADD_TOPIC)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public Response createComment(Map<String, String> params, Object idGroup, Object idTopic, String massage) {
        response = RestAssured.given().params(params)
                .param(GROUP, idGroup)
                .param(TOPIC, idTopic)
                .param("message", massage)
                .log().uri()
                .when().get(VK_API_ENDPOINT_CREATE_COMMENT)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public void editComment(Map<String, String> params, Object idGroup, Object idTopic, Object idComment) {
        response = RestAssured.given().params(params)
                .param(GROUP, idGroup)
                .param(TOPIC, idTopic)
                .param("comment_id", idComment)
                .param("message", "Test Comment")
                .log().uri()
                .when().get(VK_API_ENDPOINT_EDIT_COMMENT)
                .then().log().body().extract().response();
    }

    @Override
    public void deleteComment(Map<String, String> params, Object idGroup, Object idTopic, Object idComment) {
        response = RestAssured.given().params(params)
                .param(GROUP, idGroup)
                .param(TOPIC, idTopic)
                .param("comment_id", idComment)
                .log().uri()
                .when().get(VK_API_ENDPOINT_DELETE_COMMENT)
                .then().log().body().extract().response();
    }
}
