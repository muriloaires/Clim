package br.com.airescovit.clim.data.network.login.endpoint

import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.data.network.model.RegisterClientRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Logics on 03/02/2018.
 */
interface ClientEndpoint {

    companion object {
        const val CLIENT_REGISTER: String = "users/{userId}/clients"
    }

    @POST(CLIENT_REGISTER)
    fun doClientRegister(@Header("Authorization") header: String, @Path("userId") userId: Long, @Body registerClientRequest: RegisterClientRequest): Observable<Client>

}