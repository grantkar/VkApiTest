package api.models.vk_api;

import api.utils.BaseUri;
import api.utils.UtilsHelper;
import api.utils.impl.BaseUriImpl;
import api.utils.impl.UtilsHelperImpl;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import pojo.Root;
import java.io.File;
import java.util.*;

import static constants.Constants.EndPoint.*;

@Getter
@Setter
public class VKApiManager {
    protected BaseUri baseUri = new BaseUriImpl();
    protected UtilsHelper utilsHelper = new UtilsHelperImpl();
    protected Root root;
    protected Response response;
    private List<Object> idUniversals = new ArrayList<>();
    private List<Integer> idComments = new ArrayList<>();
    private List<Object> forPhotos = new ArrayList<>();
    private List<Object> ids;
    private List<Object> ownerIds;

    protected Map<String, Object> paramsForSave = new HashMap<>();


    public VKApiManager getProfileInfo(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion)).log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_GET_PROFILE_INFO)
                .then().log().body().extract().response();
        String jsonText = response.body().asString();
        Gson gson = new Gson();
        root = gson.fromJson(jsonText, Root.class);
        idUniversals.add(root.getResponse().getId());
        return this;
    }

    public VKApiManager setProfileInfo(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("status", utilsHelper.findEmptyFields(root)).log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_SET_PROFILE_INFO)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager getUrlForUploadPhoto(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion)).log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_GET_SAVE_URL_PHOTO)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager uploadPhotoToServer () {
        String filePath = "src/test/resources/file/photo.jpg";
        String path = getUtilsHelper().getUrlUploadFile(getResponse());
        RequestSpecification request = RestAssured.given();
        response = request.contentType("multipart/form-data")
                .multiPart(new File(filePath)).log().all()
                .post(path)
                .then()
                .log().all().extract().response();
        paramsForSave = utilsHelper.getParamsForSavePhoto(getResponse());
        return this;
    }

    public VKApiManager savePhoto(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("server", paramsForSave.get("server"))
                .param("photo", paramsForSave.get("photo"))
                .param("hash", paramsForSave.get("hash"))
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_UPLOAD_PHOTO_TO_SERVER)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager getURIUploadServerDocks(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion)).log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_GET_SAVE_URL_DOCKS)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager uploadDockToServer() {
        String filePath = "src/test/resources/file/dock.txt";
        String path = getUtilsHelper().getUrlUploadFile(getResponse());
        RequestSpecification request = RestAssured.given();
        response = request.contentType("multipart/form-data")
                .multiPart(new File(filePath)).log().all()
                .post(path)
                .then()
                .log().all().extract().response();
        return this;
    }

    public VKApiManager saveDocksFromServer(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("file", utilsHelper.getUploadDocksInformation(getResponse()))
                .param("title", "TEST")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_SAVE_DOCK)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager editDocksTitle(String VKApi, String VKVersion) {
        int idDocks = response.jsonPath().getInt("response.doc.id");
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("owner_id", idUniversals.get(0))
                .param("doc_id", idDocks)
                .param("title", "test-doc123")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_EDIT_DOCK)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager getNewsRecommended (String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_GET_NEWS_RECOMMENDED)
                .then().log().body().extract().response();
        ids = utilsHelper.getInformationFromRecommendedNews(response).get(0);
        ownerIds = utilsHelper.getInformationFromRecommendedNews(response).get(1);
        return this;
    }

    public VKApiManager likesPost (String VKApi, String VKVersion) {
        Integer idFivePost = (Integer) ids.get(4);
        List<Integer> idFivesOwners = (List<Integer>) ownerIds.get(4);
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("type", "post")
                .param("owner_id", idFivesOwners.get(0))
                .param("item_id", idFivePost)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_ADD_LIKES)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager banAccountOwnerSixPost(String VKApi, String VKVersion) {
        List<Integer> idOwner = (List<Integer>) ownerIds.get(6);
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("owner_id", idOwner.get(0))
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_ACCOUNT_BAN)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager addPostsToFave(String VKApi, String VKVersion) {
        List<Integer> idOwners = (List<Integer>) ownerIds.get(1);
        Integer idPost = (Integer) ids.get(1);
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("owner_id", idOwners)
                .param("id", idPost)
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_ADD_POST_FIVE)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager createGroup(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("title", "My test group")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_CREATE_GROUPS)
                .then().log().body().extract().response();
        idUniversals.add(utilsHelper.getIds(response, "response.id")); // id группы 2 элемент
        return this;
    }

    public VKApiManager boardAddTopic(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("group_id", idUniversals.get(1))
                .param("title", "Discussion")
                .param("text", "Test discussion")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_ADD_TOPIC)
                .then().log().body().extract().response();
        idUniversals.add(utilsHelper.getIds(response, "response")); // id topic 3 элемент
        return this;
    }

    public VKApiManager createComment(String VKApi, String VKVersion, int countTextSymbol) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("group_id", idUniversals.get(1))
                .param("topic_id", idUniversals.get(2))
                .param("message", RandomStringUtils.random(countTextSymbol, true, false))
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_CREATE_COMMENT)
                .then().log().body().extract().response();
        idComments.add(utilsHelper.getIds(response, "response"));
        return this;
    }

    public VKApiManager editComment(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("group_id", idUniversals.get(1))
                .param("topic_id", idUniversals.get(2))
                .param("comment_id", idComments.get(idComments.size()-2))
                .param("message", "Test Comment")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_EDIT_COMMENT)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager deleteComment(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("group_id", idUniversals.get(1))
                .param("topic_id", idUniversals.get(2))
                .param("comment_id", idComments.get(0))
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_DELETE_COMMENT)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager createPrivetAlbum(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("title", "My test privet album")
                .param("privacy_view", "nobody / only_me")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_CREATE_ALBUM)
                .then().log().body().extract().response();
        forPhotos.add(utilsHelper.getIds(response, "response.id")); // forPhoto.get(0) - id privet album
        return this;
    }

    public VKApiManager getUrlForUploadPhotoToAlbum(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("album_id", forPhotos.get(0))
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_GET_SAVE_URL_PHOTO_TO_ALBUM)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager uploadPhotoToAlbum() {
        String filePath = "src/test/resources/file/tester.jpg";
        String path = getUtilsHelper().getUrlUploadFile(getResponse());
        RequestSpecification request = RestAssured.given();
        response = request.contentType("multipart/form-data")
                .multiPart(new File(filePath)).log().all()
                .post(path)
                .then()
                .log().body().extract().response();
        paramsForSave = utilsHelper.getParamsForSavePhoto(getResponse());
        return this;
    }

    public VKApiManager savePhotoToAlbum(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("album_id", forPhotos.get(0))
                .param("server", paramsForSave.get("server"))
                .param("photos_list", paramsForSave.get("photos_list"))
                .param("aid", paramsForSave.get("aid"))
                .param("hash", paramsForSave.get("hash"))
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_SAVE_PHOTO_TO_ALBUM)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager makerPhotoCoverAlbum(String VKApi, String VKVersion) {
        forPhotos.add(utilsHelper.getIntegerForString(getResponse().jsonPath().getString("response.owner_id")));
        forPhotos.add(utilsHelper.getIntegerForString(getResponse().jsonPath().getString("response.id")));
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("album_id", forPhotos.get(0))
                .param("owner_id", forPhotos.get(1))
                .param("photo_id", forPhotos.get(2))
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_MAKER_PHOTO_COVER_ALBUM)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager createCommentPhoto(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("owner_id", forPhotos.get(1))
                .param("photo_id", forPhotos.get(2))
                .param("message", "Test comment by My")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_CREATE_COMMENT_PHOTO)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager putTagToPhoto(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("owner_id", forPhotos.get(1))
                .param("photo_id", forPhotos.get(2))
                .param("user_id", forPhotos.get(1))
                .param("x", "10")
                .param("y", "10")
                .param("x2", "80")
                .param("y2", "80")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_PUT_TAG)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager createAlbum(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("title", "My test album")
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_CREATE_ALBUM)
                .then().log().body().extract().response();
        forPhotos.add(utilsHelper.getIds(response, "response.id")); // forPhotos.get(3) - id public album
        return this;
    }

    public VKApiManager movePhotoToAlbum(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("owner_id", forPhotos.get(1))
                .param("target_album_id", forPhotos.get(3))
                .param("photo_id", forPhotos.get(2))
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_MOVE_PHOTO)
                .then().log().body().extract().response();
        return this;
    }

    public VKApiManager deletePhotoAlbum(String VKApi, String VKVersion) {
        response = RestAssured.given().params(getParams(VKApi, VKVersion))
                .param("album_id", forPhotos.get(0))
                .log().uri()
                .when().get(baseUri.baseUri() + VK_API_ENDPOINT_DELETE_PHOTO_ALBUM)
                .then().log().body().extract().response();
        return this;
    }

    public Map <String, String> getParams (String VKApi, String VKVersion){
        Map<String, String> params = new HashMap<>();
        params.put("access_token", VKApi);
        params.put("v", VKVersion);
        return params;
    }
}
