/*
 *
 *  Copyright 2016-2017 Red Hat, Inc, and individual contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package io.openshift.booster;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/")
@Api
public class GreetingEndpoint {

    private static final String template = "Hello, %s!";

    @Context
    private HttpHeaders headers;
    
    @Context
    private SecurityContext sc;
    
    
/*    @GET
    @Path("/greeting")
    @Produces("application/json")
    public Greeting greeting(@QueryParam("name") String name) throws Exception {
    	
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true)
				.build();

		CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpGet httpGet = new HttpGet("https://172.30.0.1/.well-known/oauth-authorization-server");
		httpGet.setHeader("Accept", "application/json");
		HttpResponse response = client.execute(httpGet);	    	
	    HttpEntity entity = response.getEntity();
	    
        return new Greeting(String.format(template, EntityUtils.toString(entity)));
    }*/
    
    @GET
    @Path("/greeting")
    @Produces("application/json")
    @ApiOperation(value = "Greeting Call", response = Greeting.class)
    @ApiResponses(value = {
            @ApiResponse(code = 406, message = "Only JSON is returned by this operation"),
            @ApiResponse(code = 403, message = "An error has occurred.")
        })    
    public Response greeting(@QueryParam("name") String name) throws Exception {
        Greeting entity = new Greeting(String.format(template, name), new Date(System.currentTimeMillis()));
        ResponseBuilder builder = Response.status( Status.OK ).entity(entity);
        return builder.build();
    }    
}
