package ru.chernov.urlshortener.helper

import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder


fun getJson(url: String?, vararg urlVariables: Any?): MockHttpServletRequestBuilder {
    return get(url, urlVariables).contentType(MediaType.APPLICATION_JSON)
}


fun postJson(url: String?, vararg urlVariables: Any?): MockHttpServletRequestBuilder {
    return post(url, urlVariables).contentType(MediaType.APPLICATION_JSON)
}


fun putJson(url: String?, vararg urlVariables: Any?): MockHttpServletRequestBuilder {
    return put(url, urlVariables).contentType(MediaType.APPLICATION_JSON)
}


fun deleteJson(url: String?, vararg urlVariables: Any?): MockHttpServletRequestBuilder {
    return delete(url, urlVariables).contentType(MediaType.APPLICATION_JSON)
}