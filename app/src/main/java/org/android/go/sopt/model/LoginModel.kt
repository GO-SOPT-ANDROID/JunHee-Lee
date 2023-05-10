package org.android.go.sopt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RequestLogin(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String,
)

@Serializable
data class ResponseLogin(
    @SerialName("data")
    val data: Data,
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: String,
        @SerialName("name")
        val name: String,
        @SerialName("skill")
        val skill: String,
    )
}