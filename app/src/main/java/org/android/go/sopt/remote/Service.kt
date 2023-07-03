package org.android.go.sopt

import okhttp3.MultipartBody
import org.android.go.sopt.model.ResponseHome
import retrofit2.Call
import retrofit2.http.*

interface SignService {
    @POST("sign-up")
    suspend fun signup(
        @Body request: RequestSignUpDto
    ): ResponseSignUpDto

    @POST("sign-in")
    suspend fun signin(
        @Body request: RequestLogin
    ): ResponseLogin
}

interface HomeService {
    @GET("/api/users")
    suspend fun listuser(
        @Query("page") page: Int = 2
    ): ResponseHome
}

interface ImageService {
    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part file: MultipartBody.Part,
    ): Call<Unit>
}


