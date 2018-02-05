package br.com.airescovit.clim.ui.addclients

import br.com.airescovit.clim.ui.base.MvpView

/**
 * Created by murilo aires on 29/01/2018.
 */
interface AddClientsMvpView : MvpView {

    fun onIncorrectClientName(resId: Int)
    fun onIncorrectClientPhone(resId: Int)
    fun onIncorrectState(resId: Int)
    fun onIncorrectCity(resId: Int)
    fun configureSpinners(countriesString: List<String>, defaultPosition: Int)
    fun onIncorrectStreet(resId: Int)
    fun onIncorrectCountry(resId: Int)
    fun finishWithOkResult()

}