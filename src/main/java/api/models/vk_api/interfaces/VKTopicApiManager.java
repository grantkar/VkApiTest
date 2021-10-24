package api.models.vk_api.interfaces;

import io.restassured.response.Response;

import java.util.Map;

public interface VKTopicApiManager {

    /**
     * return - response содержащий информацию о созданном сообществе
     * */
    Response createGroup (Map<String, String> params);

    /**
     * return - response содержащий информацию о созданной теме для обсуждений в ранее созданной группе
     * */
    Response boardAddTopic (Map<String, String> params, Object idGroup);

    /**
     * return - response содержащий информацию о добавленном комментарии в обсужение
     * */
    Response createComment (Map<String, String> params, Object idGroup, Object idTopic, String massage);

    /**
     * Метод редактирует текст idComment комментария
     * */
    void editComment (Map<String, String> params, Object idGroup, Object idTopic, Object idComment);

    /**
     * Метод удаляет idComment комментарий
     * */
    void deleteComment (Map<String, String> params, Object idGroup, Object idTopic, Object idComment);
}
