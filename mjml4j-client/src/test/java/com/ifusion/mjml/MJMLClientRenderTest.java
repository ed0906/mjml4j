package com.ifusion.mjml;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.ifusion.mjml.exception.MJMLBadRequestException;
import com.ifusion.mjml.exception.MJMLForbiddenException;
import com.ifusion.mjml.exception.MJMLInternalServerException;
import com.ifusion.mjml.exception.MJMLUnauthorizedException;
import com.ifusion.mjml.model.request.RenderRequest;
import com.ifusion.mjml.model.response.RenderResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MJMLClientRenderTest extends MJMLClientHTTPTestBase {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    private MJMLClient client;

    @Before
    public void beforeEach() {
        client = MJMLClient.newClient().withConfiguration(new MJMLTestConfiguration());
    }

    @Test
    public void validRequestWithSuccessfulResponse() {
        // Given
        givenThat(post(urlPathEqualTo("/" + VERSION + "/render"))
                .withHeader("Authorization", equalTo(CONFIGURATION.authenticationHeaderValue()))
                .willReturn(
                        aResponse()
                        .withBody(fromFile("/com/ifusion/mjml/render-response_success.json"))
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                )
        );

        RenderRequest request = new RenderRequest("<mlml></mjml>");

        // When
        RenderResponse response = client.render(request);

        // Then
        assertThat(response.getErrors()).hasSize(1);
        assertThat(response.getErrors().get(0).getFormattedMessage()).isEqualTo("formatted message");
        assertThat(response.getErrors().get(0).getMessage()).isEqualTo("message");
        assertThat(response.getErrors().get(0).getLineNumber()).isEqualTo(1);
        assertThat(response.getErrors().get(0).getTagName()).isEqualTo("tag");
        assertThat(response.getHTML()).isEqualTo("html");
        assertThat(response.getMJML()).isEqualTo("mjml");
        assertThat(response.getMJMLVersion()).isEqualTo("version");
    }

    @Test(expected = MJMLUnauthorizedException.class)
    public void unauthorizedResponse() {
        // Given
        givenThat(post(urlPathEqualTo("/" + VERSION + "/render"))
                .willReturn(
                        aResponse()
                                .withBody(fromFile("/com/ifusion/mjml/render-response_unsuccessful.json"))
                                .withHeader("Content-Type", "application/json")
                                .withStatus(401)
                )
        );

        RenderRequest request = new RenderRequest("<mlml></mjml>");

        // When
        client.render(request);
    }

    @Test(expected = MJMLForbiddenException.class)
    public void forbiddenResponse() {
        // Given
        givenThat(post(urlPathEqualTo("/" + VERSION + "/render"))
                .willReturn(
                        aResponse()
                                .withBody(fromFile("/com/ifusion/mjml/render-response_unsuccessful.json"))
                                .withHeader("Content-Type", "application/json")
                                .withStatus(403)
                )
        );

        RenderRequest request = new RenderRequest("<mlml></mjml>");

        // When
        client.render(request);
    }

    @Test(expected = MJMLBadRequestException.class)
    public void badRequestResponse() {
        // Given
        givenThat(post(urlPathEqualTo("/" + VERSION + "/render"))
                .willReturn(
                        aResponse()
                                .withBody(fromFile("/com/ifusion/mjml/render-response_unsuccessful.json"))
                                .withHeader("Content-Type", "application/json")
                                .withStatus(400)
                )
        );

        RenderRequest request = new RenderRequest("<mlml></mjml>");

        // When
        client.render(request);
    }

    @Test(expected = MJMLInternalServerException.class)
    public void internalServerErrorResponse() {
        // Given
        givenThat(post(urlPathEqualTo("/" + VERSION + "/render"))
                .willReturn(
                        aResponse()
                                .withBody(fromFile("/com/ifusion/mjml/render-response_unsuccessful.json"))
                                .withHeader("Content-Type", "application/json")
                                .withStatus(500)
                )
        );

        RenderRequest request = new RenderRequest("<mlml></mjml>");

        // When
        client.render(request);
    }

}