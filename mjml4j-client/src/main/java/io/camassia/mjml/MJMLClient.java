package io.camassia.mjml;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.camassia.mjml.builder.RequireApplicationID;
import io.camassia.mjml.builder.RequireApplicationKey;
import io.camassia.mjml.builder.RequireConfiguration;
import io.camassia.mjml.configuration.MJMLConfiguration;
import io.camassia.mjml.configuration.MJMLDefaultConfiguration;
import io.camassia.mjml.exception.MJMLAPIException;
import io.camassia.mjml.exception.MJMLUnhandledResponseException;
import io.camassia.mjml.model.request.RenderRequest;
import io.camassia.mjml.model.response.ErrorResponse;
import io.camassia.mjml.model.response.RenderResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

public class MJMLClient {

    private final MJMLConfiguration configuration;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    MJMLClient(MJMLConfiguration configuration, HttpClient httpClient, ObjectMapper mapper) {
        this.configuration = configuration;
        this.httpClient = httpClient;
        this.mapper = mapper;
    }

    MJMLClient(MJMLConfiguration configuration) {
        this.configuration = configuration;
        this.mapper = new ObjectMapper();
        try {
            SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, (chain, authType) -> true)
                    .build();
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
            this.httpClient = HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RenderResponse render(RenderRequest request) {
        try {
            HttpPost httpRequest = new HttpPost(configuration.host() + "/" + configuration.version() + "/render");
            httpRequest.addHeader(configuration.authenticationHeaderName(), configuration.authenticationHeaderValue());
            httpRequest.addHeader("accept", "application/json");
            HttpEntity httpRequestEntity = new StringEntity(mapper.writeValueAsString(request), ContentType.APPLICATION_JSON);
            httpRequest.setEntity(httpRequestEntity);

            HttpResponse response = httpClient.execute(httpRequest);
            HttpEntity httpResponseEntity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            switch (statusCode) {
                case 200:
                    return mapper.readValue(httpResponseEntity.getContent(), RenderResponse.class);
                case 400:
                case 401:
                case 403:
                case 500:
                    ErrorResponse errorResponse = mapper.readValue(httpResponseEntity.getContent(), ErrorResponse.class);
                    throw errorResponse.asException(statusCode);
                default:
                    String body = IOUtils.toString(httpResponseEntity.getContent(), Charset.forName("UTF-8"));
                    throw new MJMLUnhandledResponseException(statusCode, body);
            }
        } catch (IOException e) {
            throw new MJMLAPIException(e);
        }
    }

    public static RequireApplicationID newDefaultClient() {
        return new Builder();
    }

    public static RequireConfiguration newClient() {
        return new Builder();
    }

    public MJMLConfiguration configuration() {
        return configuration;
    }

    private static class Builder implements RequireApplicationKey, RequireApplicationID, RequireConfiguration {

        private String applicationId;

        @Override
        public MJMLClient withApplicationKey(String key) {
            Objects.requireNonNull(key);
            return new MJMLClient(new MJMLDefaultConfiguration(applicationId, key));
        }

        @Override
        public RequireApplicationKey withApplicationID(String id) {
            Objects.requireNonNull(id);
            this.applicationId = id;
            return this;
        }

        @Override
        public MJMLClient withConfiguration(MJMLConfiguration configuration) {
            return new MJMLClient(configuration);
        }
    }
}
