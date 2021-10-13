package tests.vk_api;


import base.BaseTest;
import constants.Constants;
import org.testng.annotations.Test;

public class ApiTest extends BaseTest {
    private Constants constants = new Constants();
    private String VkApi = constants.getVK_API();
    private String VkVersion = constants.getVK_VERSION();

    @Test(priority = 1,description = "search user info")
    public void getInfoProfileVK(){
        apiManager.getVkApiManager().getProfileInfo(VkApi, VkVersion);
    }

    @Test(priority = 2, description = "set not enough information")
    public void setInfoByProfile() {
        apiManager.getVkApiManager().setProfileInfo(VkApi, VkVersion);
    }

    @Test(priority = 3, description = "get url for upload photo to server")
    public void getSaveOwnerPhoto() {
        apiManager.getVkApiManager().getUrlForUploadPhoto(VkApi, VkVersion);
    }
    @Test(priority = 4, description = "upload photo to server")
    public void uploadPhotoToServer() {
        apiManager.getVkApiManager().uploadPhotoToServer();
    }

    @Test(priority = 5, description = "save upload photo")
    public void savePhoto() {
        apiManager.getVkApiManager().savePhoto(VkApi, VkVersion);
    }

    @Test(priority = 6, description = "get url for upload docks to server")
    public void getUploadServerDocks() {
        apiManager.getVkApiManager().getURIUploadServerDocks(VkApi, VkVersion);
    }

    @Test(priority = 7, description = "upload docks to server")
    public void uploadDocksFromServer() {
        apiManager.getVkApiManager().uploadDockToServer();
    }

    @Test(priority = 8, description = "save uploading docks")
    public void saveUploadingDocks() {
        apiManager.getVkApiManager().saveDocksFromServer(VkApi, VkVersion);
    }

    @Test(priority = 9, description = "edit name uploading documents")
    public void editDocks() {
        apiManager.getVkApiManager().editDocksTitle(VkApi, VkVersion);
    }

      @Test(priority = 10, description = "get list recommended news")
      public void getNewsRecommended() {
        apiManager.getVkApiManager().getNewsRecommended(VkApi, VkVersion);
    }

    @Test(priority = 11, description = "likes 5 post from list recommended news")
    public void likeFivesNewsRecommended() {
        apiManager.getVkApiManager().likesPost(VkApi, VkVersion);
    }

    @Test(priority = 12, description = "ban account owners 6 post from list recommended news")
    public void banAccountOwnersPost() {
        apiManager.getVkApiManager().banAccountOwnerSixPost(VkApi, VkVersion);
    }

    @Test(priority = 13, description = "add post to five list")
    public void addPostToFive() {
        apiManager.getVkApiManager().addPostsToFave(VkApi, VkVersion);
    }

    @Test(priority = 14, description = "create groups")
    public void createGroups() {
        apiManager.getVkApiManager().createGroup(VkApi, VkVersion);
    }

    @Test(priority = 15, description = "add Topic to Group")
    public void addTopic() {
        apiManager.getVkApiManager().boardAddTopic(VkApi, VkVersion);
    }

    @Test(priority = 16, invocationCount = 3, description = "create 3 Comment from topic")
    public void createCommentFromTopic() {
            apiManager.getVkApiManager().createComment(VkApi, VkVersion, (int) (Math.random()*1000));
    }

    @Test(priority = 17, description = "edit 2 comment")
    public void editComment() {
        apiManager.getVkApiManager().editComment(VkApi, VkVersion);
    }

    @Test(priority = 18, description = "delete first comment")
    public void deleteComment() {
        apiManager.getVkApiManager().deleteComment(VkApi, VkVersion);
    }

    @Test(priority = 19, description = "create privet photo album")
    public void createPrivetAlbum() {
        apiManager.getVkApiManager().createPrivetAlbum(VkApi, VkVersion);
    }

    @Test(priority = 20, description = "get URL for upload photo to server in Album")
    public void getUrlForUploadPhotoToAlbum() {
        apiManager.getVkApiManager().getUrlForUploadPhotoToAlbum(VkApi, VkVersion);
    }

    @Test(priority = 20, description = "upload photo to Server")
    public void uploadPhotoToAlbum() {
        apiManager.getVkApiManager().uploadPhotoToAlbum();
    }

    @Test(priority = 21, description = "save upload photo to Album")
    public void saveUploadPhotoToAlbum() {
        apiManager.getVkApiManager().savePhotoToAlbum(VkApi, VkVersion);
    }

    @Test(priority = 22, description = "maker photo cover to Album")
    public void marekPhotoCoverToAlbum() {
        apiManager.getVkApiManager().makerPhotoCoverAlbum(VkApi, VkVersion);
    }

    @Test(priority = 23, description = "create comment photo from Album")
    public void createCommentPhoto() {
        apiManager.getVkApiManager().createCommentPhoto(VkApi, VkVersion);
    }

    @Test(priority = 24, description = "put tag to photo from Album")
    public void putTagToPhoto() {
        apiManager.getVkApiManager().putTagToPhoto(VkApi, VkVersion);
    }

    @Test(priority = 25, description = "create photo album")
    public void createPublicAlbum() {
        apiManager.getVkApiManager().createAlbum(VkApi, VkVersion);
    }

    @Test(priority = 26, description = "create photo album")
    public void movePhotoToAlbum() {
        apiManager.getVkApiManager().movePhotoToAlbum(VkApi, VkVersion);
    }

    @Test(priority = 27, description = "delete photo album")
    public void deletePhotoAlbum() {
        apiManager.getVkApiManager().deletePhotoAlbum(VkApi, VkVersion);
    }
}
