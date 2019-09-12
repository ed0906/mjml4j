package io.camassia.mjml.builder;

import io.camassia.mjml.MJMLClient;

public interface RequireApplicationKey {
    MJMLClient withApplicationKey(String key);
}
