package com.karanchuk.core.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val urlWithKey = original.url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        val requestWithHeader = original.newBuilder()
            .url(urlWithKey)
            .build()
        return chain.proceed(requestWithHeader)
    }
}