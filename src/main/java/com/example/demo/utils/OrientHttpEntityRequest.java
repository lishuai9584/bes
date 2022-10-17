package com.example.demo.utils;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * HttpClient请求类
 *
 * @author User
 */
public class OrientHttpEntityRequest extends HttpEntityEnclosingRequestBase {


    /**
     * 请求类型
     */
    private String requestMethod = "GET";

    public OrientHttpEntityRequest() {
        super();
    }

    public OrientHttpEntityRequest(final URI uri) {
        super();
        setURI(uri);
    }

    public OrientHttpEntityRequest(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    public OrientHttpEntityRequest(final String requestMethod, final String uri) {
        super();
        this.requestMethod = requestMethod;
        setURI(URI.create(uri));
    }


    @Override
    public String getMethod() {
        if (requestMethod == null) {
            requestMethod = "GET";
        }
        return requestMethod.toUpperCase();
    }
}
