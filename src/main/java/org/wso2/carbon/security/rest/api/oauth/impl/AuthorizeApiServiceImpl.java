package org.wso2.carbon.security.rest.api.oauth.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.security.oauth.core.ClientRegistry;
import org.wso2.carbon.security.oauth.core.OAuthConstants;
import org.wso2.carbon.security.rest.api.oauth.*;


import org.wso2.carbon.security.rest.api.oauth.NotFoundException;

import org.wso2.msf4j.Request;
import javax.ws.rs.core.Response;

public class AuthorizeApiServiceImpl extends AuthorizeApiService {
    private static final Logger log = LoggerFactory.getLogger(AuthorizeApiServiceImpl.class);
    private ClientRegistry clientRegistry;

    public AuthorizeApiServiceImpl(ClientRegistry clientRegistry) {
        this.clientRegistry = clientRegistry;
    }

    @Override
    public Response authorizeGet(String responseType, String clientId, String redirectUri,
                                 String scope, String state, Request request) throws NotFoundException {
        // If redirectUri is not specified in request try to lookup pre registered redirectUri for this clientId
        if (StringUtils.isEmpty(redirectUri)) {
            redirectUri = clientRegistry.getRedirectUri(clientId);
        }

        // Validate response type
        if (!OAuthConstants.RESPONSE_TYPE_CODE.equals(responseType)) {
            return generateErrorResponse(redirectUri, OAuthConstants.ErrorCondition.ERROR_INVALID_REQUEST, state);
        }

        // Error if clientId does not exist
        if (StringUtils.isEmpty(clientId)) {
            return generateErrorResponse(redirectUri, OAuthConstants.ErrorCondition.ERROR_INVALID_REQUEST, state);
        }

        


        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    private Response generateErrorResponse(String redirectUri,
                                           OAuthConstants.ErrorCondition errorCondition, String state) {
        if (!StringUtils.isEmpty(redirectUri)) {
            String locationHeader = redirectUri + '?'
                    + OAuthConstants.ERROR_QUERY_PARAM + '=' + errorCondition.getCondition();

            if (!StringUtils.isEmpty(state)) {
                locationHeader += '&' + OAuthConstants.STATE_QUERY_PARAM + '=' + state;
            }

            return Response.status(Response.Status.FOUND).
                    header(OAuthConstants.LOCATION_HEADER, locationHeader).build();
        }

        throw new IllegalArgumentException("Valid redirectUri has not been provided");
    }

    private Response generateAuthResponse(String redirectUri, String code, String state) {
        if (!StringUtils.isEmpty(redirectUri)) {
            String locationHeader = redirectUri + '?'
                    + OAuthConstants.CODE_QUERY_PARAM + '=' + code;

            if (!StringUtils.isEmpty(state)) {
                locationHeader += '&' + OAuthConstants.STATE_QUERY_PARAM + '=' + state;
            }

            return Response.status(Response.Status.FOUND).
                    header(OAuthConstants.LOCATION_HEADER, locationHeader).build();
        }

        throw new IllegalArgumentException("Valid redirectUri has not been provided");
    }
}
