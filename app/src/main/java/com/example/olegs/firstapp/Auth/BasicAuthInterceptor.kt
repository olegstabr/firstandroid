package com.example.olegs.firstapp.Auth

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.util.support.Base64

/**
 * Created by superadmin on 03.07.17.
 */

class BasicAuthInterceptor : ClientHttpRequestInterceptor {
    var username = ""
    var password = ""

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }

    override fun intercept(request: HttpRequest?, body: ByteArray?, execution: ClientHttpRequestExecution?): ClientHttpResponse? {
        val headers = request?.headers
        headers?.add("Authorization", encodeCredentialsForBasicAuth(username, password))
        return execution?.execute(request, body)
    }

    fun encodeCredentialsForBasicAuth(username: String, password: String): String {
        val plainCreds = username + ":" + password
        val plainCredsBytes = plainCreds.toByteArray()
        val base64CredsBytes = Base64.encodeBytes(plainCredsBytes)
        val base64Creds = base64CredsBytes.toString()
        return "Basic " + base64Creds
    }
}