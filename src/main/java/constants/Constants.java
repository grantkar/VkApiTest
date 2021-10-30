package constants;
import jdbs.GetToken;
import lombok.Getter;

@Getter
public class Constants {

   private static GetToken token = new GetToken();

    //domain name
    public static class ServerName{
        public static String TRELLO_API_SERVER = "https://api.trello.com/";
    }

    //path
    public static class Path{
        public static String TRELLO_API_PATH = "1/";
    }

    //endpoint
    public static class EndPoint{
        public static final String TRELLO_ENDPOINT_CREATE_A_BOARD = "boards";
        public static final String TRELLO_ENDPOINT_CREATE_A_LIST = "lists";
        public static final String TRELLO_ENDPOINT_CREATE_A_CARD = "cards";
        public static final String TRELLO_ENDPOINT_CREATE_ATTACHMENT = "/attachments"; // TRELLO_ENDPOINT_CREATE_A_CARD + /{id}
        public static final String TRELLO_ENDPOINT_CREATE_A_CHECKLIST = "checklists";
        public static final String TRELLO_ENDPOINT_CREATE_A_CHECKITEMS = "/checkItems"; // TRELLO_ENDPOINT_CREATE_A_CHECKLIST + /{id}
        public static final String TRELLO_ENDPOINT_ARCHIVE_A_LIST = "lists/{id}/closed";
        public static final String TRELLO_ENDPOINT_UPDATE_CHECKITEM = "cards/{id}/checkItem/{idCheckItem}";
        public static final String TRELLO_ENDPOINT_ADD_ACTION = "cards/{id}/actions/comments";
    }

    private final String TRELLO_API_KEY = token.trelloApiKey();
    private final String TRELLO_API_TOKEN = token.trelloApiToken();

}
