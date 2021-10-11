package api.utils.impl;

import api.utils.UtilsHelper;
import io.restassured.response.Response;
import pojo.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilsHelperImpl implements UtilsHelper {

    @Override
    public String findEmptyFields(Root root) {
        try {
            List<String> list = Stream.of(root.getResponse().getBdate(), root.getResponse().getFirst_name(), root.getResponse().getLast_name(), root.getResponse().getStatus()).collect(Collectors.toList());
            String emptyFieldValue = list.stream().filter(String::isEmpty).findFirst().get();
            return emptyFieldValue + "Test";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getUrlUploadFile(Response response){
        return response.jsonPath().getString("response.upload_url");
    }

    @Override
    public Map<String, Object> getParamsForSavePhoto(Response response) {
        Map<String, Object> paramsForSavePhoto = response.jsonPath().getMap("$");
        return paramsForSavePhoto;
    }

    @Override
    public String getUploadDocksInformation(Response response) {
        return response.jsonPath().getString("file");
    }

    @Override
    public List<List<Object>> getInformationFromRecommendedNews(Response response) {
        List<List<Object>> list = new ArrayList<>();
        list.add(response.jsonPath().getList("response.items.post_id"));
        list.add(response.jsonPath().getList("response.items.attachments.photo.owner_id"));
        return list;
    }

    @Override
    public Integer getIds(Response response, String path) {
        return response.jsonPath().getInt(path);
    }

    @Override
    public Integer getIntegerForString(String str) {
        return Integer.parseInt(str.replaceAll("[^0-9]", ""));
    }

}
