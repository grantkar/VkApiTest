package api.models.vk_api.interfaces.impl;

import api.models.vk_api.interfaces.VKNewsApiManager;
import api.utils.BaseUri;
import api.utils.impl.BaseUriImpl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.Map;

import static constants.Constants.EndPoint.*;

@Getter
public class VKNewsApiManagerImpl implements VKNewsApiManager {

    private Response response;
    private BaseUri baseUri = new BaseUriImpl();

    @Override
    public Response getNewsRecommended(Map<String, String> params) {
        response = RestAssured.given().params(params)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_GET_NEWS_RECOMMENDED)
                .then().log().body().extract().response();
        return response;
    }

    @Override
    public void likesPost(Map<String, String> params, Integer idFivePost, Integer idFivesOwners) {
        response = RestAssured.given().params(params)
                .param("type", "post")
                .param("owner_id", idFivesOwners)
                .param("item_id", idFivePost)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_ADD_LIKES)
                .then().log().body().extract().response();
    }

    @Override
    public void banAccountOwnerSixPost(Map<String, String> params, Integer idOwner) {
        response = RestAssured.given().params(params)
                .param("owner_id", idOwner)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_ACCOUNT_BAN)
                .then().log().body().extract().response();
    }

    @Override
    public void addPostsToFave(Map<String, String> params, Integer idOwner, Integer idPost) {
        response = RestAssured.given().params(params)
                .param("owner_id", idOwner)
                .param("id", idPost)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_ADD_POST_FIVE)
                .then().log().body().extract().response();
    }
}
