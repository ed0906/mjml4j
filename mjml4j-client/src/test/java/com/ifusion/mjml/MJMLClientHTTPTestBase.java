package com.ifusion.mjml;

import com.ifusion.mjml.configuration.MJMLConfiguration;

import java.io.InputStream;
import java.util.Objects;

public class MJMLClientHTTPTestBase {

    protected static final String APPLICATION_AUTH_HEADER_NAME = "Authorization";
    protected static final Integer PORT = 8315;
    protected static final String HOST = "http://localhost:" + PORT;
    protected static final String VERSION = "v1";
    protected static final String APPLICATION_ID = "applicationId";
    protected static final String APPLICATION_KEY = "applicationKey";
    protected static final String APPLICATION_AUTH_HEADER_VALUE = "Basic x";
    protected static final MJMLConfiguration CONFIGURATION = new MJMLTestConfiguration();

    protected static String fromFile(String filename) {
        InputStream resource = MJMLClientHTTPTestBase.class.getResourceAsStream(filename);
        Objects.requireNonNull(resource, "File " + filename + " Not found");
        StringBuilder builder = new StringBuilder();
        int i;
        try {
            while((i= resource.read()) != -1) {
                builder.append((char)i);
            }
            return builder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resource.close();
            } catch (Exception ignored) {}
        }
    }

    protected static class MJMLTestConfiguration implements MJMLConfiguration {

        @Override
        public String host() {
            return HOST;
        }

        @Override
        public String version() {
            return VERSION;
        }

        @Override
        public String applicationID() {
            return APPLICATION_ID;
        }

        @Override
        public String applicationKey() {
            return APPLICATION_KEY;
        }

        @Override
        public String authenticationHeaderValue() {
            return APPLICATION_AUTH_HEADER_VALUE;
        }

        @Override
        public String authenticationHeaderName() {
            return APPLICATION_AUTH_HEADER_NAME;
        }
    }

}