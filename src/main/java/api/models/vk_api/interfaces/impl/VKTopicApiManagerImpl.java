package api.models.vk_api.interfaces.impl;

import api.models.vk_api.interfaces.VKTopicApiManager;
import api.utils.BaseUri;
import api.utils.impl.BaseUriImpl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

import static constants.Constants.EndPoint.*;

@Getter
public class VKTopicApiManagerImpl implements VKTopicApiManager {

    private Response response;
    private BaseUri baseUri = new BaseUriImpl();

    @Override
    public Response createGroup(Map<String, String> params) {
        response = RestAssured.given().params(params)
                .param("title", "My test group")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_CREATE_GROUPS)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public Response boardAddTopic(Map<String, String> params, Object idGroup) {
        response = RestAssured.given().params(params)
                .param("group_id", idGroup)
                .param("title", "Discussion")
                .param("text", "Test discussion")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_ADD_TOPIC)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public Response createComment(Map<String, String> params, Object idGroup, Object idTopic, String massage) {
        response = RestAssured.given().params(params)
                .param("group_id", idGroup)
                .param("topic_id", idTopic)
                .param("message", massage)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_CREATE_COMMENT)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public void editComment(Map<String, String> params, Object idGroup, Object idTopic, Object idComment) {
        response = RestAssured.given().params(params)
                .param("group_id", idGroup)
                .param("topic_id", idTopic)
                .param("comment_id", idComment)
                .param("message", "Test Comment")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_EDIT_COMMENT)
                .then().log().body().extract().response();
    }

    @Override
    public void deleteComment(Map<String, String> params, Object idGroup, Object idTopic, Object idComment) {
        response = RestAssured.given().params(params)
                .param("group_id", idGroup)
                .param("topic_id", idTopic)
                .param("comment_id", idComment)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_DELETE_COMMENT)
                .then().log().body().extract().response();
    }
}
