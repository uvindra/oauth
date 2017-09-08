package org.wso2.carbon.security.rest.api.oauth;


import io.swagger.annotations.ApiParam;

import org.wso2.carbon.security.rest.api.oauth.dto.TokenResponseDTO;
import org.wso2.carbon.security.rest.api.oauth.factories.TokenApiServiceFactory;

import org.wso2.msf4j.Microservice;
import org.wso2.msf4j.Request;
import org.wso2.msf4j.formparam.FileInfo;
import org.wso2.msf4j.formparam.FormDataParam;
import org.osgi.service.component.annotations.Component;

import java.io.InputStream;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Component(
    name = "org.wso2.carbon.security.rest.api.oauth.TokenApi",
    service = Microservice.class,
    immediate = true
)
@Path("/uvindra/oauth/1.[\\d]+/token")


@ApplicationPath("/token")
@io.swagger.annotations.Api(description = "the token API")
public class TokenApi implements Microservice  {
   private final TokenApiService delegate = TokenApiServiceFactory.getTokenApi();

    @POST
    
    @Consumes({ "application/x-www-form-urlencoded" })
    @Produces({ "application/json;charset=UTF-8" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = TokenResponseDTO.class, tags={ "OAuth Token", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK. Successful response from authorization endpoint. ", response = TokenResponseDTO.class) })
    public Response tokenPost(@ApiParam(value = "Authentication scheme header" ,required=true)@HeaderParam("Authorization") String authorization
,@ApiParam(value = "Required OAuth grant type",required=true) @QueryParam("grant_type") String grantType
,@ApiParam(value = "Authorization code to be sent for authorization grant type") @QueryParam("code") String code
,@ApiParam(value = "Clients redirection endpoint") @QueryParam("redirect_uri") String redirectUri
,@ApiParam(value = "OAuth client identifier") @QueryParam("client_id") String clientId
 ,@Context Request request)
    throws NotFoundException {
        return delegate.tokenPost(authorization,grantType,code,redirectUri,clientId,request);
    }
}
