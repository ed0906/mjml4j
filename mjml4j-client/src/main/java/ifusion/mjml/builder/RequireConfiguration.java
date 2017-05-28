package ifusion.mjml.builder;

import ifusion.mjml.MJMLClient;
import ifusion.mjml.configuration.MJMLConfiguration;

public interface RequireConfiguration {
    MJMLClient withConfiguration(MJMLConfiguration configuration);
}
