package com.spotlight.platform.userprofile.api.web.exceptionmappers;

import com.spotlight.platform.userprofile.api.core.exceptions.InvalidParameterTypeException;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class InvalidParameterTypeExceptionMapperTest {
    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new InvalidParameterTypeExceptionMapperTest.MockResource())
            .setRegisterDefaultExceptionMappers(false)
            .addProvider(new InvalidParameterTypeExceptionMapper())
            .build();

    private Client client;

    @BeforeEach
    void setUp() {
        client = EXT.client();
    }

    @Test
    void entityNotFound_ResultsIn404() {
        Response response = client.target(InvalidParameterTypeExceptionMapperTest.MockResource.RESOURCE_URLS.THROW_EXCEPTION).request().post(Entity.json("{}"));

        assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
    }

    @Path("/")
    public static class MockResource {
        public static class RESOURCE_URLS {
            public static final String THROW_EXCEPTION = "/throwInvalidParameterTypeException";
        }

        @POST
        @Path(InvalidParameterTypeExceptionMapperTest.MockResource.RESOURCE_URLS.THROW_EXCEPTION)
        public void throwException() {
            throw new InvalidParameterTypeException();
        }
    }
}
