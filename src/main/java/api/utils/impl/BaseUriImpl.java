package api.utils.impl;

import api.utils.BaseUri;
import static constants.Constants.Path.VK_API_PATH;
import static constants.Constants.ServerName.VK_API_SERVER;

public class BaseUriImpl implements BaseUri {

    @Override
    public String baseUri() {
        return VK_API_SERVER + VK_API_PATH;
    }
}
