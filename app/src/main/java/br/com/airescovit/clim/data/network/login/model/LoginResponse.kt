package br.com.airescovit.clim.data.network.login.model

import com.google.gson.annotations.SerializedName

/**
 * Created by murilo aires on 23/01/2018.
 */
data class LoginResponse(@SerializedName("token") val token: String, @SerializedName("user") val user: UserResponse)