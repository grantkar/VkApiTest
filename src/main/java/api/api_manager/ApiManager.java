package api.api_manager;

import api.models.vk_api.VKApiManager;
import lombok.Getter;
import lombok.Setter;

public class ApiManager {

    @Getter
    @Setter
    VKApiManager vkApiManager = new VKApiManager();
}
