package br.com.airescovit.clim.data.network

import br.com.airescovit.clim.data.network.model.LoginRequest

/**
 * Created by Logics on 12/01/2018.
 */
interface ApiHelper {

    fun doLoginRequest(loginRequest: LoginRequest)
}