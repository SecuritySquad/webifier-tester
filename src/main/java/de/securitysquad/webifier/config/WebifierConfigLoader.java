package de.securitysquad.webifier.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by samuel on 08.11.16.
 */
public class WebifierConfigLoader {
    private ObjectMapper mapper = new ObjectMapper();

    public WebifierConfig load(String externName, String internName) throws IOException {
        try {
            return loadExternal(externName);
        } catch (IOException e) {
            System.out.println("Loading internal configuration!");
            return loadInternal(internName);
        }
    }

    private WebifierConfig loadExternal(String name) throws IOException {
        InputStream is = new FileInputStream(getExternalFile(name));
        return load(is);
    }

    private File getExternalFile(String name) {
        return new File(new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile()).getParent(), name);
    }

    private WebifierConfig loadInternal(String name) throws IOException {
        InputStream is = ClassLoader.getSystemResourceAsStream(name);
        return load(is);
    }

    private WebifierConfig load(InputStream is) throws IOException {
        return mapper.readValue(is, WebifierConfig.class);
    }
}
