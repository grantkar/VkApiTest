package api.models.vk_api.interfaces.impl;

import api.models.vk_api.interfaces.VKNewsApiManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;
import java.util.Map;
import static constants.Constants.EndPoint.*;

@Getter
public class VKNewsApiManagerImpl implements VKNewsApiManager {

    private Response response;
    private static final String OWNER = "owner_id";

    @Override
    public Response getNewsRecommended(Map<String, String> params) {
        response = RestAssured.given().params(params)
                .log().uri()
                .when().get(VK_API_ENDPOINT_GET_NEWS_RECOMMENDED)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public void likesPost(Map<String, String> params, Integer idFivePost, Integer idFivesOwners) {
        response = RestAssured.given().params(params)
                .param("type", "post")
                .param(OWNER, idFivesOwners)
                .param("item_id", idFivePost)
                .log().uri()
                .when().get(VK_API_ENDPOINT_ADD_LIKES)
                .then().log().body().extract().response();
    }

    @Override
    public void banAccountOwnerSixPost(Map<String, String> params, Integer idOwner) {
        response = RestAssured.given().params(params)
                .param(OWNER, idOwner)
                .log().uri()
                .when().get(VK_API_ENDPOINT_ACCOUNT_BAN)
                .then().log().body().extract().response();
    }

    @Override
    public void addPostsToFave(Map<String, String> params, Integer idOwner, Integer idPost) {
        response = RestAssured.given().params(params)
                .param(OWNER, idOwner)
                .param("id", idPost)
                .log().uri()
                .when().get(VK_API_ENDPOINT_ADD_POST_FIVE)
                .then().log().body().extract().response();
    }
}
