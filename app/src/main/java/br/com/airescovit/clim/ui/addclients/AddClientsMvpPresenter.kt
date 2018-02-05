package br.com.airescovit.clim.ui.addclients

import br.com.airescovit.clim.ui.base.MvpPresenter

/**
 * Created by murilo aires on 29/01/2018.
 */
interface AddClientsMvpPresenter<V : AddClientsMvpView> : MvpPresenter<V> {
    fun onCheckbuttonClick(clientName: String, clientPhone: String, postalCode: String?, state: String, city: String, neighborhood: String?, street: String, complement: String?, number: String?, latitude: Double?, longitude: Double?, country: String)
    fun loadCountries()
}