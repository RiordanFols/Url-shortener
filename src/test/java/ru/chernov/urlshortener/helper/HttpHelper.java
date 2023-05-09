package ru.chernov.urlshortener.helper;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;


public class HttpHelper {

    public static MockHttpServletRequestBuilder getJson(String url, Object... urlVariables) {
        return get(url, urlVariables).contentType(MediaType.APPLICATION_JSON);
    }


    public static MockHttpServletRequestBuilder postJson(String url, Object... urlVariables) {
        return post(url, urlVariables).contentType(MediaType.APPLICATION_JSON);
    }


    public static MockHttpServletRequestBuilder putJson(String url, Object... urlVariables) {
        return put(url, urlVariables).contentType(MediaType.APPLICATION_JSON);
    }


    public static MockHttpServletRequestBuilder deleteJson(String url, Object... urlVariables) {
        return delete(url, urlVariables).contentType(MediaType.APPLICATION_JSON);
    }

}
