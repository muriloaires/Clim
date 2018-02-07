package br.com.airescovit.clim.data.network.endpoint

import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.data.network.model.RegisterClientRequest
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Logics on 03/02/2018.
 */
interface ClientEndpoint {

    companion object {
        const val CLIENT_ENDPOINT: String = "users/{userId}/clients"
    }

    @POST(CLIENT_ENDPOINT)
    fun doClientRegister(@Header("Authorization") header: String, @Path("userId") userId: Long, @Body registerClientRequest: RegisterClientRequest): Observable<Client>

    @GET(CLIENT_ENDPOINT)
    fun getClients(@Header("Authorization") header: String, @Path("userId") userId: Long, @Query("page") page: Int): Observable<List<Client>>
}