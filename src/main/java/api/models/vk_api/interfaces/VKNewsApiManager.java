package api.models.vk_api.interfaces;

import io.restassured.response.Response;

import java.util.Map;

public interface VKNewsApiManager {

    /**
     * return - response с json-ом 50 рекомендованных новостей для текущего пользователя
     * */
    Response getNewsRecommended (Map<String, String> params);

    /**
     * Метод ставит "лайк" на 5 новость из списка рекомендованных
     * */
    void likesPost (Map<String, String> params, Integer idFivePost, Integer idFivesOwners);

    /**
     * Метод добавляет в черный лист автора 6 новости из списка рекомендованных
     * */
    void banAccountOwnerSixPost (Map<String, String> params, Integer idOwner);

    /**
     * Метод добавляет в 2 новость из списка в закладки
     * */
    void addPostsToFave (Map<String, String> params, Integer idOwner, Integer idPost);
}
