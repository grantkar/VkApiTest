package tests.trello_api;

import api.utils.BaseUri;
import api.utils.impl.BaseUriTrelloImpl;
import base.BaseTest;
import constants.Constants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TrelloTest extends BaseTest {

    private final Constants constants = new Constants();
    private final String apiKey = constants.getTRELLO_API_KEY();
    private final String apiToken = constants.getTRELLO_API_TOKEN();

    private final BaseUri base = new BaseUriTrelloImpl();


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = base.baseUri();
    }

    @Test(priority = 1,description = "create a Board")
    public void createABoard(){
        apiManager.getTrelloApiManager().createBoard(apiKey, apiToken);
    }

    @Test(priority = 2,description = "create a List")
    public void createAList(){
        apiManager.getTrelloApiManager().createList(apiKey, apiToken);
    }

    @Test(priority = 3,description = "create a Card")
    public void createACard(){
        apiManager.getTrelloApiManager().createCard(apiKey, apiToken);
    }

    @Test(priority = 4,description = "create a Attachment")
    public void createAttachment(){
        apiManager.getTrelloApiManager().createAttachment(apiKey, apiToken);
    }

    @Test(priority = 5,description = "add Due to Card")
    public void addToCardDate(){
        apiManager.getTrelloApiManager().addToCardDate(apiKey, apiToken);
    }

    @Test(priority = 6,description = "add Description to Card")
    public void addDescriptionToCard(){
        apiManager.getTrelloApiManager().addDescriptionToCard(apiKey, apiToken);
    }

    @Test(priority = 7,description = "create CheckList to Card")
    public void createCheckList(){
        apiManager.getTrelloApiManager().createCheckList(apiKey, apiToken);
    }

    @Test(priority = 8,description = "create CheckItem to Card")
    public void createCheckItemOne(){
        apiManager.getTrelloApiManager().createCheckItemsOne(apiKey, apiToken);
    }

    @Test(priority = 9,description = "create CheckItem to Card")
    public void createCheckItemTwo(){
        apiManager.getTrelloApiManager().createCheckItemsTwo(apiKey, apiToken);
    }

    @Test(priority = 10,description = "update CheckItem to Card")
    public void updateCheckItemOne(){
        apiManager.getTrelloApiManager().updateCheckItemsOne(apiKey, apiToken);
    }

    @Test(priority = 11,description = "create a List")
    public void createAListDone(){
        apiManager.getTrelloApiManager().createListDone(apiKey, apiToken);
    }

    @Test(priority = 12,description = "update a Card")
    public void updateACard(){
        apiManager.getTrelloApiManager().updateCard(apiKey, apiToken);
    }

    @Test(priority = 13,description = "archive A List Backlog")
    public void archiveAList(){
        apiManager.getTrelloApiManager().archiveAList(apiKey, apiToken);
    }

    @Test(priority = 14,description = "update CheckItem to Card")
    public void updateCheckItemTwo(){
        apiManager.getTrelloApiManager().updateCheckItemsTwo(apiKey, apiToken);
    }

    @Test(priority = 15,description = "create action")
    public void createAction(){
        apiManager.getTrelloApiManager().createAction(apiKey, apiToken);
    }

    @Test(priority = 16,description = "Check what the card is on List")
    public void checkingCardOnList() {
    }

    @Test(priority = 17,description = "check title a created Board")
    public void checkCreateABoard(){
        apiManager.getTrelloApiManager().checkCreateBoard(apiKey, apiToken);
    }
}

