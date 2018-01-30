package br.com.airescovit.clim.data.network.login.endpoint

import br.com.airescovit.clim.data.network.Single
import br.com.airescovit.clim.data.network.login.model.LoginResponse
import br.com.airescovit.clim.data.network.model.login.model.LoginRequest
import br.com.airescovit.clim.data.network.model.login.model.RegisterRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by murilo aires on 23/01/2018.
 */
interface UserEndpoint {
    companion object {
        const val USER_TOKEN: String = "user_token"
        const val USERS: String = "users"
    }

    @POST(USER_TOKEN)
    fun doLogin(@Body loginRequest: LoginRequest): Observable<LoginResponse>

    @POST(USERS)
    fun doRegister(@Body registerRequest: Single<RegisterRequest>) : Observable<LoginResponse>
}