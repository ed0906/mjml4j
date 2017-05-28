package ifusion.mjml;


import ifusion.mjml.MJMLClient;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MJMLClientConfigurationTest {

    private static final String APPLICATION_ID = "id";
    private static final String APPLICATION_KEY = "key";

    @Test
    public void buildsAClientUsingDefaultConfiguration() {
        MJMLClient client = MJMLClient.newDefaultClient()
                .withApplicationID(APPLICATION_ID)
                .withApplicationKey(APPLICATION_KEY);

        assertThat(client.configuration()).isNotNull();
        assertThat(client.configuration().applicationID()).isEqualTo(APPLICATION_ID);
        assertThat(client.configuration().applicationKey()).isEqualTo(APPLICATION_KEY);
    }

}