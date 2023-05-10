package org.android.go.sopt


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object HomeApiFactory {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.REQRES_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }



    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object HomeServicePool{
    val homeService = HomeApiFactory.create<HomeService>()
}


