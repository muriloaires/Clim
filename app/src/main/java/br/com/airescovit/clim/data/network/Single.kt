package br.com.airescovit.clim.data.network

import com.google.gson.annotations.SerializedName

/**
 * Created by murilo aires on 25/01/2018.
 */
data class Single<V>(@SerializedName(value = "user", alternate = arrayOf("a", "b")) var v: V)
