package api.utils;

import io.restassured.response.Response;
import pojo.Root;

import java.util.List;
import java.util.Map;

public interface UtilsHelper {

    /**
     * Метод находит пустые поля в POJO и для первого пустого STRING поля устанавливает значение "Тест"
     * @return - или значение "Тест" если нашлось пустое поле, или "" Если пустого поля нет
     */
    String findEmptyFields(Root root);

    /**
     * Метод парсит в строку response JSON, извлекая из него Path файла
     * @return - Строку содержащую Path для загрузки файла
     */
    String getUrlUploadFile(Response response);

    /**
     * Метод парсит в мапу response JSON полученный после загрузки фотографии на сервер
     * , извлекая из него Path файла
     * @return - Map<String, String> с ключами server, photo, hash
     */
    Map<String, Object> getParamsForSavePhoto(Response response);

    /**
     * Метод парсит в строку response JSON, извлекая из него информацию о загруженном документе
     * @return - Строку содержащую информацию о "file" для сохранения документа
     */
    String getUploadDocksInformation(Response response);

    /**
     * @return - List<List<Object>> - лист с листами нужной информации рекомендованных новостей для пользователя
     */
    List<List<Object>> getInformationFromRecommendedNews(Response response);

    /**
     * @return - целечисленное значение ID созданного сообщества
     */
    Integer getIds(Response response, String path);

    /**
     * @return - целечисленное значение из строки str
     */
    Integer getIntegerForString(String str);
}
