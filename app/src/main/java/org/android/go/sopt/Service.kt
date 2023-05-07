package org.android.go.sopt

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("sign-up")
    fun login(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto>
}
