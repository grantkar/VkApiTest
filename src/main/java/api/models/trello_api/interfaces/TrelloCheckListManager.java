package api.models.trello_api.interfaces;

import io.restassured.response.Response;

import java.util.Map;

public interface TrelloCheckListManager {

    /**
     * return - response с информацией о созданном чеклисте
     * */
    Response createACheckList(Map<String, String> params, String idCard);

    /**
     * return - response с информацией о созданном элементе в чеклисте
     * */
    Response createACheckItems(Map<String, String> params, String idCheckList, String name);

    /**
     * Метод редактирует чеклист - idCheckList
     * */
    void updateACheckItems(Map<String, String> params, String idCard, String idCheckItem);

    /**
     * Метод перемещает карточку idCard в колонку idList
     * */
    void updateCard(Map<String, String> params, String idCard, String idList);

    /**
     * Метод перемещает карточку idCard в колонку idList
     * */
    void archiveList(Map<String, String> params, String idList);

    /**
     * Метод ставит в карточке эмоджи Палец вверх
     * */
    void createAction(Map<String, String> params, String idCard);

}
