package org.android.go.sopt

import okhttp3.MultipartBody
import org.android.go.sopt.model.ResponseHome
import retrofit2.Call
import retrofit2.http.*

interface SignService {
    @POST("sign-up")
    fun signup(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto>

    @POST("sign-in")
    fun signin(
        @Body request: RequestLogin
    ): Call<ResponseLogin>
}

interface HomeService {
    @GET("/api/users")
    suspend fun listuser(
        @Query("page") page: Int = 2
    ): List<ResponseHome.Data>
}

interface ImageService {
    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part file: MultipartBody.Part,
    ): Call<Unit>
}


