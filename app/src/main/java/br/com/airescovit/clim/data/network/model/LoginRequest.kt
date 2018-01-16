package br.com.airescovit.clim.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Logics on 16/01/2018.
 */
data class LoginRequest(@SerializedName("first_name") val firstName: String,
                        @SerializedName("last_name") val lastName: String,
                        @SerializedName("email") val email: String,
                        @SerializedName("password") val password: String,
                        @SerializedName("password_confirmation") val passwordConfirmation: String) {
}