package br.com.airescovit.clim.data.network.model.login.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Logics on 16/01/2018.
 */
data class LoginRequest(@SerializedName("auth") val auth: Auth)