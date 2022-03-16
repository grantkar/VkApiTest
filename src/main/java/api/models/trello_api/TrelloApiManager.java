package api.models.trello_api;

import api.models.trello_api.interfaces.TrelloBoardManager;
import api.models.trello_api.interfaces.TrelloCheckListManager;
import api.models.trello_api.interfaces.impl.TrelloBoardManagerImpl;
import api.models.trello_api.interfaces.impl.TrelloCheckListManagerImpl;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TrelloApiManager {

    protected Response response;

    private static final String ID = "id";
    private static final String ID_BOARD = "idBoard";
    private static final String ID_CARD = "idCard";
    private static final String ID_LIST = "idList";
    private static final String ID_CHECKLIST = "idCheckList";

    private Map<String, String> parseInformation = new HashMap<>();

    private TrelloBoardManager trelloBoardManager = new TrelloBoardManagerImpl();
    private TrelloCheckListManager trelloCheckListManager = new TrelloCheckListManagerImpl();


    public TrelloApiManager createBoard(String apiKey, String apiToken) {
        response = trelloBoardManager.createBoard(getParams(apiKey, apiToken));
        parseInformation.put(ID_BOARD, response.jsonPath().getString(ID));
        return this;
    }

    public TrelloApiManager createList(String apiKey, String apiToken) {
        response = trelloBoardManager
                .createList(getParams(apiKey, apiToken), parseInformation.get(ID_BOARD), "Backlog");
        parseInformation.put(ID_LIST, response.jsonPath().getString(ID));
        return this;
    }

    public TrelloApiManager createCard(String apiKey, String apiToken) {
        response = trelloBoardManager.createCard(getParams(apiKey, apiToken), parseInformation.get(ID_LIST));
        parseInformation.put(ID_CARD, response.jsonPath().getString(ID));
        return this;
    }

    public TrelloApiManager createAttachment(String apiKey, String apiToken) {
        response = trelloBoardManager.createAttachment(getParams(apiKey, apiToken), parseInformation.get(ID_CARD));
        parseInformation.put(ID_CHECKLIST, response.jsonPath().getString(ID));
        return this;
    }

    public TrelloApiManager addToCardDate(String apiKey, String apiToken) {
        trelloBoardManager.addDueToCard(getParams(apiKey, apiToken), parseInformation.get(ID_CARD));
        return this;
    }

    public TrelloApiManager addDescriptionToCard(String apiKey, String apiToken) {
        trelloBoardManager.addDescriptionToCard(getParams(apiKey, apiToken), parseInformation.get(ID_CARD));
        return this;
    }

    public TrelloApiManager createCheckList(String apiKey, String apiToken) {
        response = trelloCheckListManager.createACheckList(getParams(apiKey, apiToken), parseInformation.get(ID_CARD));
        parseInformation.put(ID_CHECKLIST, response.jsonPath().getString(ID));
        return this;
    }

    public TrelloApiManager createCheckItemsOne(String apiKey, String apiToken) {
        String name = "Понять протокол HTTP";
        response = trelloCheckListManager
                .createACheckItems(getParams(apiKey, apiToken), parseInformation.get(ID_CHECKLIST) , name);
        parseInformation.put("idItemsOne", response.jsonPath().getString(ID));
        return this;
    }

    public TrelloApiManager createCheckItemsTwo(String apiKey, String apiToken) {
        String name = "Выучить методы запросов";
        response = trelloCheckListManager
                .createACheckItems(getParams(apiKey, apiToken), parseInformation.get(ID_CHECKLIST), name);
        parseInformation.put("idItemsTwo", response.jsonPath().getString(ID));
        return this;
    }

    public TrelloApiManager updateCheckItemsOne(String apiKey, String apiToken) {
        trelloCheckListManager.updateACheckItems(getParams(apiKey, apiToken), parseInformation.get(ID_CARD)
                , parseInformation.get("idItemsOne"));
        return this;
    }
    public TrelloApiManager createListDone(String apiKey, String apiToken) {
        response = trelloBoardManager
                .createList(getParams(apiKey, apiToken), parseInformation.get(ID_BOARD), "Done");
        parseInformation.put("idListDone", response.jsonPath().getString(ID));
        return this;
    }

    public TrelloApiManager updateCard(String apiKey, String apiToken) {
        trelloCheckListManager.updateCard(getParams(apiKey, apiToken), parseInformation.get(ID_CARD)
                , parseInformation.get("idListDone"));
        return this;
    }

    public TrelloApiManager archiveAList(String apiKey, String apiToken) {
        trelloCheckListManager.archiveList(getParams(apiKey, apiToken), parseInformation.get(ID_LIST));
        return this;
    }

    public TrelloApiManager updateCheckItemsTwo(String apiKey, String apiToken) {
        trelloCheckListManager.updateACheckItems(getParams(apiKey, apiToken), parseInformation.get(ID_CARD)
                , parseInformation.get("idItemsTwo"));
        return this;
    }

    public TrelloApiManager createAction(String apiKey, String apiToken) {
        trelloCheckListManager.createAction(getParams(apiKey, apiToken), parseInformation.get(ID_CARD));
        return this;
    }

    public Map<String, String> getParams (String apiKey, String apiToken){
        Map<String, String> params = new HashMap<>();
        params.put("key", apiKey);
        params.put("token", apiToken);
        return params;
    }
}
