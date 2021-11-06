package api.models.vk_api;

import api.models.vk_api.interfaces.*;
import api.models.vk_api.interfaces.impl.*;
import api.utils.UtilsHelper;
import com.google.gson.Gson;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import pojo.Root;
import java.util.*;

@Getter
@Setter
public class VKApiManager {
    protected Root root;
    protected Response response;
    private static final String PARSE = "response.id";
    private List<Object> idUniversals = new ArrayList<>();
    private List<Integer> idComments = new ArrayList<>();
    private List<Object> forPhotos = new ArrayList<>();
    private List<Object> ids;
    private List<Object> ownerIds;

    private VKInfoApiManager vkInfoApiManager = new VKInfoApiManagerImpl();
    private VKDocksApiManager vkDocksApiManager = new VKDocksApiManagerImpl();
    private VKNewsApiManager vkNewsApiManager = new VKNewsApiManagerImpl();
    private VKTopicApiManager vkTopicApiManager = new VKTopicApiManagerImpl();
    private VKPhotoApiManager vkPhotoApiManager = new VKPhotoApiManagerImpl();

    protected Map<String, Object> paramsForSave = new HashMap<>();


    public VKApiManager getProfileInfo(String VKApi, String VKVersion) {
        Gson gson = new Gson();
        root = gson.fromJson(vkInfoApiManager.getProfileInfo(getParams(VKApi,VKVersion)), Root.class);
        idUniversals.add(root.getResponse().getId());
        return this;
    }

    public VKApiManager setProfileInfo(String VKApi, String VKVersion) {
        vkInfoApiManager.setProfileInfo(getParams(VKApi, VKVersion), UtilsHelper.findEmptyFieldsName(root), UtilsHelper.findEmptyFields(root));
        return this;
    }

    public VKApiManager getUrlForUploadPhoto(String VKApi, String VKVersion) {
        vkInfoApiManager.getUrlForUploadPhoto(getParams(VKApi, VKVersion));
        return this;
    }

    public VKApiManager uploadPhotoToServer () {
        paramsForSave = vkInfoApiManager.uploadPhotoToServer();
        return this;
    }

    public VKApiManager savePhoto(String VKApi, String VKVersion) {
        vkInfoApiManager.savePhoto(getParams(VKApi, VKVersion), paramsForSave.get("server"), paramsForSave.get("photo"), paramsForSave.get("hash"));
        return this;
    }

    public VKApiManager getURIUploadServerDocks(String VKApi, String VKVersion) {
        vkDocksApiManager.getURIUploadServerDocks(getParams(VKApi, VKVersion));
        return this;
    }

    public VKApiManager uploadDockToServer() {
        vkDocksApiManager.uploadDockToServer();
        return this;
    }

    public VKApiManager saveDocksFromServer(String VKApi, String VKVersion) {
        vkDocksApiManager.saveDocksFromServer(getParams(VKApi, VKVersion));
        return this;
    }

    public VKApiManager editDocksTitle(String VKApi, String VKVersion) {
        vkDocksApiManager.editDocksTitle(getParams(VKApi, VKVersion), idUniversals.get(0));
        return this;
    }

    public VKApiManager getNewsRecommended (String VKApi, String VKVersion) {
        response = vkNewsApiManager.getNewsRecommended(getParams(VKApi, VKVersion));
        ids = UtilsHelper.getInformationFromRecommendedNews(response).get(0);
        ownerIds = UtilsHelper.getInformationFromRecommendedNews(response).get(1);
        return this;
    }

    public VKApiManager likesPost (String VKApi, String VKVersion) {
        Integer idFivePost = (Integer) ids.get(4);
        List<Integer> idFivesOwners = (List<Integer>) ownerIds.get(4);
        vkNewsApiManager.likesPost(getParams(VKApi, VKVersion), idFivePost ,idFivesOwners.get(0));
        return this;
    }

    public VKApiManager banAccountOwnerSixPost(String VKApi, String VKVersion) {
        List<Integer> idOwner = (List<Integer>) ownerIds.get(6);
        vkNewsApiManager.banAccountOwnerSixPost(getParams(VKApi, VKVersion), idOwner.get(0));
        return this;
    }

    public VKApiManager addPostsToFave(String VKApi, String VKVersion) {
        List<Integer> idOwners = (List<Integer>) ownerIds.get(1);
        Integer idPost = (Integer) ids.get(1);
        vkNewsApiManager.addPostsToFave(getParams(VKApi, VKVersion),idOwners.get(0), idPost);
        return this;
    }

    public VKApiManager createGroup(String VKApi, String VKVersion) {
        idUniversals.add(UtilsHelper.getIds(vkTopicApiManager.createGroup(getParams(VKApi, VKVersion)), PARSE)); //id группы 2 элемент
        return this;
    }

    public VKApiManager boardAddTopic(String VKApi, String VKVersion) {
        idUniversals.add(UtilsHelper.getIds(vkTopicApiManager.boardAddTopic(getParams(VKApi, VKVersion), idUniversals.get(1)), "response"));  // id topic 3 элемент
        return this;
    }

    public VKApiManager createComment(String VKApi, String VKVersion, int countTextSymbol) {
        String message = RandomStringUtils.random(countTextSymbol, true, false);
        response = vkTopicApiManager.createComment(getParams(VKApi, VKVersion),idUniversals.get(1),idUniversals.get(2), message);
        idComments.add(UtilsHelper.getIds(response, "response"));
        return this;
    }

    public VKApiManager editComment(String VKApi, String VKVersion) {
        vkTopicApiManager.editComment(getParams(VKApi, VKVersion),idUniversals.get(1),idUniversals.get(2),idComments.get(idComments.size()-2));
        return this;
    }

    public VKApiManager deleteComment(String VKApi, String VKVersion) {
        vkTopicApiManager.deleteComment(getParams(VKApi, VKVersion),idUniversals.get(1),idUniversals.get(2),idComments.get(0));
        return this;
    }

    public VKApiManager createPrivetAlbum(String VKApi, String VKVersion) {
        response = vkPhotoApiManager.createPrivetAlbum(getParams(VKApi, VKVersion));
        forPhotos.add(UtilsHelper.getIds(response, PARSE)); // forPhoto.get(0) - id privet album
        return this;
    }

    public VKApiManager getUrlForUploadPhotoToAlbum(String VKApi, String VKVersion) {
        response = vkPhotoApiManager.getUrlForUploadPhotoToAlbum(getParams(VKApi, VKVersion), forPhotos.get(0));
        return this;
    }

    public VKApiManager uploadPhotoToAlbum() {
        String path = UtilsHelper.getUrlUploadFile(getResponse());
        response = vkPhotoApiManager.uploadPhotoToAlbum(path);
        paramsForSave = UtilsHelper.getParamsForSavePhoto(getResponse());
        return this;
    }

    public VKApiManager savePhotoToAlbum(String VKApi, String VKVersion) {
        response = vkPhotoApiManager
                .savePhotoToAlbum(getParams(VKApi, VKVersion), paramsForSave, forPhotos.get(0));
        return this;
    }

    public VKApiManager makerPhotoCoverAlbum(String VKApi, String VKVersion) {
        forPhotos.add(UtilsHelper.getIntegerForString(getResponse().jsonPath().getString("response.owner_id")));
        forPhotos.add(UtilsHelper.getIntegerForString(getResponse().jsonPath().getString(PARSE)));
        vkPhotoApiManager.makerPhotoCoverAlbum(getParams(VKApi, VKVersion),
                forPhotos.get(0), forPhotos.get(1), forPhotos.get(2));
        return this;
    }

    public VKApiManager createCommentPhoto(String VKApi, String VKVersion) {
        vkPhotoApiManager.createCommentPhoto(getParams(VKApi, VKVersion),
                forPhotos.get(1), forPhotos.get(2));
        return this;
    }

    public VKApiManager putTagToPhoto(String VKApi, String VKVersion) {
        vkPhotoApiManager.putTagToPhoto(getParams(VKApi, VKVersion), forPhotos.get(1), forPhotos.get(2));
        return this;
    }

    public VKApiManager createAlbum(String VKApi, String VKVersion) {
        response = vkPhotoApiManager.createAlbum(getParams(VKApi, VKVersion));
        forPhotos.add(UtilsHelper.getIds(response, PARSE)); // forPhotos.get(3) - id public album
        return this;
    }

    public VKApiManager movePhotoToAlbum(String VKApi, String VKVersion) {
        vkPhotoApiManager.movePhotoToAlbum(getParams(VKApi, VKVersion),
                forPhotos.get(1), forPhotos.get(3), forPhotos.get(2));
        return this;
    }

    public VKApiManager deletePhotoAlbum(String VKApi, String VKVersion) {
        vkPhotoApiManager.deletePhotoAlbum(getParams(VKApi, VKVersion), forPhotos.get(0));
        return this;
    }

    public Map <String, String> getParams (String VKApi, String VkVersion){
        Map<String, String> params = new HashMap<>();
        params.put("access_token", VKApi);
        params.put("v", VkVersion);
        return params;
    }
}
