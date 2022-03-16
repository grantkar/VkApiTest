package tests.trello_api;

import api.utils.BaseUri;
import api.utils.impl.BaseUriTrelloImpl;
import base.BaseTest;
import constants.Constants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TrelloAssertionTest extends BaseTest {

    private final Constants constants = new Constants();
    private final String apiKey = constants.getTRELLO_API_KEY();
    private final String apiToken = constants.getTRELLO_API_TOKEN();

    private final BaseUri base = new BaseUriTrelloImpl();

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = base.baseUri();
    }

    @Test(priority = 1,description = "check title a created Board")
    public void checkCreateABoard(){
        apiManager.getTrelloApiManager().checkCreateBoard(apiKey, apiToken);
    }
}
