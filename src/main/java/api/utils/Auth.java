package api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import static constants.Constants.EndPoint.TRELLO_ENDPOINT_AUTHORIZATION;
import static constants.Constants.Path.TRELLO_API_PATH;
import static constants.Constants.ServerName.TRELLO_API_SERVER;

@UtilityClass
public class Auth {

    public void getAuth(String apiKey, String apiToken) {
        Response response = RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .log().uri()
                .get(TRELLO_API_SERVER + TRELLO_API_PATH + TRELLO_ENDPOINT_AUTHORIZATION)
                .then().extract().response();
    }
}
