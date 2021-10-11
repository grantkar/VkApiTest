package constants;
import jdbs.GetToken;
import lombok.Getter;

@Getter
public class Constants {

   private static GetToken token = new GetToken();

    //domain name
    public static class ServerName{
        public static String VK_API_SERVER = "https://api.vk.com/";
    }

    //path
    public static class Path{
        public static String VK_API_PATH = "method/";
    }

    //endpoint
    public static class EndPoint{
        public static final String VK_API_ENDPOINT_GET_PROFILE_INFO = "account.getProfileInfo";
        public static final String VK_API_ENDPOINT_SET_PROFILE_INFO = "account.saveProfileInfo";

        public static final String VK_API_ENDPOINT_GET_SAVE_URL_PHOTO = "photos.getOwnerPhotoUploadServer";
        public static final String VK_API_ENDPOINT_UPLOAD_PHOTO_TO_SERVER = "photos.saveOwnerPhoto";

        public static final String VK_API_ENDPOINT_GET_SAVE_URL_DOCKS = "docs.getUploadServer";
        public static final String VK_API_ENDPOINT_SAVE_DOCK = "docs.save";
        public static final String VK_API_ENDPOINT_EDIT_DOCK = "docs.edit";

        public static final String VK_API_ENDPOINT_GET_NEWS_RECOMMENDED = "newsfeed.getRecommended";

        public static final String VK_API_ENDPOINT_ADD_LIKES = "likes.add";

        public static final String VK_API_ENDPOINT_ACCOUNT_BAN = "account.ban";

        public static final String VK_API_ENDPOINT_ADD_POST_FIVE = "fave.addPost";

        public static final String VK_API_ENDPOINT_CREATE_GROUPS = "groups.create";

        public static final String VK_API_ENDPOINT_ADD_TOPIC = "board.addTopic";
        public static final String VK_API_ENDPOINT_CREATE_COMMENT = "board.createComment";
        public static final String VK_API_ENDPOINT_EDIT_COMMENT = "board.editComment";
        public static final String VK_API_ENDPOINT_DELETE_COMMENT = "board.deleteComment";

        public static final String VK_API_ENDPOINT_CREATE_ALBUM = "photos.createAlbum";
        public static final String VK_API_ENDPOINT_GET_SAVE_URL_PHOTO_TO_ALBUM = "photos.getUploadServer";
        public static final String VK_API_ENDPOINT_SAVE_PHOTO_TO_ALBUM = "photos.save";
        public static final String VK_API_ENDPOINT_MAKER_PHOTO_COVER_ALBUM = "photos.makeCover";
        public static final String VK_API_ENDPOINT_CREATE_COMMENT_PHOTO = "photos.createComment";
        public static final String VK_API_ENDPOINT_PUT_TAG= "photos.putTag";
        public static final String VK_API_ENDPOINT_MOVE_PHOTO= "photos.move";
        public static final String VK_API_ENDPOINT_DELETE_PHOTO_ALBUM= "photos.deleteAlbum";
    }

    private final String VK_API = token.decrypt();
    private final String VK_VERSION = "5.131";

}
