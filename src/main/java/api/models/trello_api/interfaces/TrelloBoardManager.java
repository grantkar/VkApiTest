package api.models.trello_api.interfaces;

import io.restassured.response.Response;

import java.util.Map;

public interface TrelloBoardManager {

    /**
     * return - response с информацией о созданной доске
     * */
    Response createBoard(Map<String, String> params);

    /**
     * return - String с названием ранее созданной доски
     * */
    String checkCreatedBoard(Map<String, String> params);

    /**
     * return - response с информацией о созданной колонке на доске idBoard
     * */
    Response createList(Map<String, String> params, String idBoard, String name);

    /**
     * return - response с информацией о созданной карте в колонке idList
     * */
    Response createCard(Map<String, String> params, String idList);

    /**
     * return - response с информацией о созданной карте в колонке idList
     * */
    Response createAttachment(Map<String, String> params, String idCard);

    /**
     * Метод обновляет информацию о созданной карте idCard, и добавляет в нее срок выполнения
     * */
    void addDueToCard(Map<String, String> params, String idCard);

    /**
     * Метод обновляет информацию о созданной карте idCard, и добавляет в нее описание
     * */
    void addDescriptionToCard(Map<String, String> params, String idCard);

}
