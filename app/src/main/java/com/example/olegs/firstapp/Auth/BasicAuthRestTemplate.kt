package com.example.olegs.firstapp.Auth

import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.InterceptingClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.util.*

/**
 * Created by superadmin on 03.07.17.
 */

class BasicAuthRestTemplate : RestTemplate {
    var username = ""
    var password = ""

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
        addAuthentication()
    }

    fun addAuthentication() {
        if (username.isEmpty()) {
            throw RuntimeException("Username is mandatory for Basic Auth");
        }
        val interceptors = Collections.singletonList(BasicAuthInterceptor(username, password))
        setRequestFactory(InterceptingClientHttpRequestFactory(requestFactory, interceptors as List<ClientHttpRequestInterceptor>?))
    }
}