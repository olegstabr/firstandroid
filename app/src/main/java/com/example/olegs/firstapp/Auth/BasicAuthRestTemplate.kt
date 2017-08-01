package com.example.olegs.firstapp.Auth

import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.InterceptingClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.util.*

/**
 * Created by superadmin on 03.07.17.
 */

class BasicAuthRestTemplate : RestTemplate {

    private constructor()

//    private constructor(username: String, password: String) {
//        addAuthentication(username, password)
//    }

    fun addAuthentication(username: String, password: String) {
        if (username.isEmpty()) {
            throw RuntimeException("Username is mandatory for Basic Auth");
        }
        val interceptors = Collections.singletonList(BasicAuthInterceptor(username, password))
        setRequestFactory(InterceptingClientHttpRequestFactory(requestFactory, interceptors as List<ClientHttpRequestInterceptor>?))
    }

    //
    // Singleton
    //

    private object Holder { val INSTANCE = BasicAuthRestTemplate() }

    companion object Factory {
        var username = ""
        var password = ""
        val instance : BasicAuthRestTemplate by lazy { Holder.INSTANCE }
    }
}