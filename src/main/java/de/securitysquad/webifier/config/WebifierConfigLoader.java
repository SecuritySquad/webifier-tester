package de.securitysquad.webifier.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by samuel on 08.11.16.
 */
public class WebifierConfigLoader {
    private ObjectMapper mapper = new ObjectMapper();

    public WebifierConfig load(InputStream is) throws IOException {
        return mapper.readValue(is, WebifierConfig.class);
    }
}
