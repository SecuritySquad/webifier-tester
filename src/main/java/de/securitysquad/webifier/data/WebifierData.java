package de.securitysquad.webifier.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

/**
 * Created by samuel on 17.02.17.
 */
public class WebifierData {
    private static final String WEBIFIER_DATA = "https://data.webifier.de/";

    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static boolean pushResult(WebifierTesterResultData resultData) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post(WEBIFIER_DATA + "push")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .body(resultData)
                    .asJson();
            return jsonResponse.getBody().getObject().getBoolean("success");
        } catch (UnirestException e) {
            return false;
        }
    }
}