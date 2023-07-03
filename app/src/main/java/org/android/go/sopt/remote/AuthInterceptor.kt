package org.android.go.sopt.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.android.go.sopt.ApiFactory.isJsonArray
import org.android.go.sopt.ApiFactory.isJsonObject
import org.json.JSONArray
import org.json.JSONObject

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val headerRequest = originRequest.newBuilder()
            .header("Authorization" , "")
            .build()
        return chain.proceed(headerRequest)
    }


}

