package br.com.airescovit.clim.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Logics on 03/02/2018.
 */
data class RegisterClientRequest(@SerializedName("client") val clientRequest: ClientRequest)

data class ClientRequest(@SerializedName("name") val name: String,
                         @SerializedName("phone") val phone: String,
                         @SerializedName("address_attributes") val addressRequest: AddressRequest?)

data class AddressRequest(@SerializedName("zip_code") val zipCode: String?,
                          @SerializedName("primary_address") val street: String,
                          @SerializedName("secondary_address") val complement: String?,
                          @SerializedName("number") val number: String?,
                          @SerializedName("neighborhood") val neighborhood: String?,
                          @SerializedName("city") val city: String,
                          @SerializedName("state") val state: String,
                          @SerializedName("country") val country: String,
                          @SerializedName("latitude") val latitude: Double?,
                          @SerializedName("longitude") val longitude: Double?)