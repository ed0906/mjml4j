package ifusion.mjml.configuration;


import java.util.Base64;
import java.util.Objects;

public class MJMLDefaultConfiguration implements MJMLConfiguration {

    private final static String AUTHENTICATION_HEADER_NAME = "Authorization";

    private final String applicationID;
    private final String applicationKey;
    private final String authenticationHeaderValue;

    public MJMLDefaultConfiguration(String applicationID, String applicationKey) {
        Objects.requireNonNull(applicationID, "ApplicationID must be non null");
        Objects.requireNonNull(applicationKey, "ApplicationKey must be non null");
        this.applicationID = applicationID;
        this.applicationKey = applicationKey;
        this.authenticationHeaderValue = "Basic " + Base64.getEncoder().encodeToString((applicationID + ":" + applicationKey).getBytes());
    }

    public String host() {
        return "https://api.mjml.io";
    }

    @Override
    public String version() {
        return "v1";
    }

    @Override
    public String applicationID() {
        return this.applicationID;
    }

    @Override
    public String applicationKey() {
        return this.applicationKey;
    }

    @Override
    public String authenticationHeaderValue() {
        return authenticationHeaderValue;
    }

    @Override
    public String authenticationHeaderName() {
        return AUTHENTICATION_HEADER_NAME;
    }
}
