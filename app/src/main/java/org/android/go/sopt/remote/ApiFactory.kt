package org.android.go.sopt


import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit

object ApiFactory {

//    private val client by lazy {
//        OkHttpClient.Builder()
//            .addInterceptor(AuthInterceptor())
//            .addInterceptor(
//                HttpLoggingInterceptor().apply {
//                    level =
//                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
//                }
//            ).build()
//
//    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client).build()
    }

    private fun getLogOkHttpClient(): Interceptor {

        val interceptor = HttpLoggingInterceptor { message ->
            when {
                message.isJsonObject() ->
                    Log.d("Retrofit2", JSONObject(message).toString(4))

                message.isJsonArray() ->
                    Log.d("Retrofit2", JSONArray(message).toString(4))

                else -> {
                    Log.d("Retrofit2", "CONNECTION INFO -> $message")
                }
            }
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun String?.isJsonObject(): Boolean = this?.startsWith("{") == true && this.endsWith("}")
    fun String?.isJsonArray(): Boolean = this?.startsWith("[") == true && this.endsWith("]")

    private val client = OkHttpClient.Builder()
        .addInterceptor(getLogOkHttpClient())
        .build()

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object SoptServicePool {
    val signService = ApiFactory.create<SignService>()
    val imageService = ApiFactory.create<ImageService>()
}
