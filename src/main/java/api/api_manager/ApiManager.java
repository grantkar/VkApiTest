package api.api_manager;

import api.models.trello_api.TrelloApiManager;
import lombok.Getter;
import lombok.Setter;

public class ApiManager {

    @Getter
    @Setter
    TrelloApiManager trelloApiManager = new TrelloApiManager();
}
