package org.wso2.carbon.security.rest.api.oauth;

import org.wso2.carbon.security.rest.api.oauth.*;
import org.wso2.carbon.security.rest.api.oauth.dto.*;

import org.wso2.msf4j.formparam.FormDataParam;
import org.wso2.msf4j.formparam.FileInfo;
import org.wso2.msf4j.Request;


import java.util.List;
import org.wso2.carbon.security.rest.api.oauth.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public abstract class AuthorizeApiService {
    public abstract Response authorizeGet(String responseType
 ,String clientId
 ,String redirectUri
 ,String scope
 ,String state
  ,Request request) throws NotFoundException;
}
