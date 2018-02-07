package br.com.airescovit.clim.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Murilo Aires on 07/02/2018.
 */
data class RegisterTaskRequest(@SerializedName("task") val task: TaskRequest)

data class TaskRequest(@SerializedName("title") val title: String,
                       @SerializedName("description") val description: String,
                       @SerializedName("start_at") val startAt: Date,
                       @SerializedName("finish_at") val finishAt: Date,
                       @SerializedName("service_fee") val serviceFee: Double)