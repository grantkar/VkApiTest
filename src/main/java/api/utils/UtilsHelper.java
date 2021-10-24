package api.utils;

import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import pojo.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@UtilityClass
public class UtilsHelper {

    public String findEmptyFields(Root root) {
        try {
            Stream<String> stringStream = Stream.of(root.getResponse().getBDate(), root.getResponse().getFirstName(), root.getResponse().getLastName(), root.getResponse().getStatus());
            String emptyFieldValue = stringStream.filter(String::isEmpty).findFirst().orElse("Исключительный случай");
            return emptyFieldValue + "Test";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String findEmptyFieldsName(Root root) {
        List<String> emptyFields = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("first_name", root.getResponse().getFirstName());
        map.put("last_name", root.getResponse().getLastName());
        map.put("bdate", root.getResponse().getBDate());
        map.put("status", root.getResponse().getStatus());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().isEmpty()) {
                emptyFields.add(entry.getKey());
            }
        }
        if (!emptyFields.isEmpty()) {
            return emptyFields.get(0);
        }
        return "";
    }

    public String getUrlUploadFile(Response response){
        return response.jsonPath().getString("response.upload_url");
    }

    public Map<String, Object> getParamsForSavePhoto(Response response) {
        Map<String, Object> paramsForSavePhoto = response.jsonPath().getMap("$");
        return paramsForSavePhoto;
    }

    public String getUploadDocksInformation(Response response) {
        return response.jsonPath().getString("file");
    }

    public List<List<Object>> getInformationFromRecommendedNews(Response response) {
        List<List<Object>> list = new ArrayList<>();
        list.add(response.jsonPath().getList("response.items.post_id"));
        list.add(response.jsonPath().getList("response.items.attachments.photo.owner_id"));
        return list;
    }

    public Integer getIds(Response response, String path) {
        return response.jsonPath().getInt(path);
    }

    public Integer getIntegerForString(String str) {
        return Integer.parseInt(str.replaceAll("[^0-9]", ""));
    }
}
