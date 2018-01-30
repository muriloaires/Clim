package br.com.airescovit.clim.data.network.login.model

import com.google.gson.annotations.SerializedName

/**
 * Created by murilo aires on 24/01/2018.
 */
data class UserResponse(@SerializedName("id") val id: Long, @SerializedName("first_name") val firstName: String,
                        @SerializedName("last_name") val lastName: String, @SerializedName("email") val email: String)