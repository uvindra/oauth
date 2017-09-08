package org.wso2.carbon.security.rest.api.oauth.factories;

import org.wso2.carbon.security.rest.api.oauth.TokenApiService;
import org.wso2.carbon.security.rest.api.oauth.impl.TokenApiServiceImpl;

public class TokenApiServiceFactory {
    private static final TokenApiService service = new TokenApiServiceImpl();

    public static TokenApiService getTokenApi() {
        return service;
    }
}
