package org.wso2.carbon.security.rest.api.oauth.factories;

import org.wso2.carbon.security.org.wso2.carbon.security.oauth.core.impl.ClientRegistryImpl;
import org.wso2.carbon.security.rest.api.oauth.AuthorizeApiService;
import org.wso2.carbon.security.rest.api.oauth.impl.AuthorizeApiServiceImpl;

public class AuthorizeApiServiceFactory {
    private static final AuthorizeApiService service = new AuthorizeApiServiceImpl(new ClientRegistryImpl());

    public static AuthorizeApiService getAuthorizeApi() {
        return service;
    }
}
