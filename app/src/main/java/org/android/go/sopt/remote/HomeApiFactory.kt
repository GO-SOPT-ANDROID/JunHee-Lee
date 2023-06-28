package org.android.go.sopt


import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import org.android.go.sopt.remote.AuthInterceptor
import retrofit2.Retrofit

object HomeApiFactory {

//    private val client by lazy {
//        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
//            level =
//                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
//        }).addInterceptor(AuthInterceptor()).build()
//
//        OkHttpClient.Builder()
//            .addInterceptor(AuthInterceptor())
//            .addInterceptor(
//                HttpLoggingInterceptor().apply {
//                    level =
//                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
//                }
//            ).build()
//    }


    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.REQRES_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object HomeServicePool {
    val homeService = HomeApiFactory.create<HomeService>()
}


