package io.camassia.mjml.builder;

import io.camassia.mjml.MJMLClient;
import io.camassia.mjml.configuration.MJMLConfiguration;

public interface RequireConfiguration {
    MJMLClient withConfiguration(MJMLConfiguration configuration);
}
