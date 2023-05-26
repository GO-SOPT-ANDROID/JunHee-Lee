package org.android.go.sopt.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val headerRequest = originRequest.newBuilder()
            .header("Authorization" , "")
            .build()
        return chain.proceed(headerRequest)
    }

}
