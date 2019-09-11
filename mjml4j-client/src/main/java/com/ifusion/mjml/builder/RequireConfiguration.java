package com.ifusion.mjml.builder;

import com.ifusion.mjml.MJMLClient;
import com.ifusion.mjml.configuration.MJMLConfiguration;

public interface RequireConfiguration {
    MJMLClient withConfiguration(MJMLConfiguration configuration);
}
