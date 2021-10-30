package api.utils.impl;

import api.utils.BaseUri;

import static constants.Constants.Path.TRELLO_API_PATH;
import static constants.Constants.ServerName.TRELLO_API_SERVER;

public class BaseUriTrelloImpl implements BaseUri {
    @Override
    public String baseUri() {
        return TRELLO_API_SERVER + TRELLO_API_PATH;
    }
}
