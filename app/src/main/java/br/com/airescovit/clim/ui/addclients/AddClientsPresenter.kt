package br.com.airescovit.clim.ui.addclients

import br.com.airescovit.clim.R
import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.data.network.model.AddressRequest
import br.com.airescovit.clim.data.network.model.ClientRequest
import br.com.airescovit.clim.data.network.model.RegisterClientRequest
import br.com.airescovit.clim.ui.base.BasePresenter
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by murilo aires on 29/01/2018.
 */
class AddClientsPresenter<V : AddClientsMvpView> @Inject constructor(dataManager: DataManager) : BasePresenter<V>(dataManager), AddClientsMvpPresenter<V> {


    override fun onCheckbuttonClick(clientName: String, clientPhone: String, postalCode: String?, state: String, city: String, neighborhood: String?, street: String, complement: String?, number: String?, latitude: Double?, longitude: Double?, country: String) {
        when {

            clientName.trim().isEmpty() -> getMvpView()?.onIncorrectClientName(R.string.compulsory_field)
            clientPhone.trim().isEmpty() -> getMvpView()?.onIncorrectClientPhone(R.string.compulsory_field)
            state.trim().isEmpty() -> getMvpView()?.onIncorrectState(R.string.compulsory_field)
            city.trim().isEmpty() -> getMvpView()?.onIncorrectCity(R.string.compulsory_field)
            street.trim().isEmpty() -> getMvpView()?.onIncorrectStreet(R.string.compulsory_field)
            country.trim().isEmpty() -> getMvpView()?.onIncorrectCountry(R.string.choose_a_country)

            else -> {
                getMvpView()?.hideKeyboard()
                getMvpView()?.showLoading()
                val observable = dataManager.doRegisterClientRequest(dataManager.getCurrentAccessToken(), dataManager.getCurrentUserId(), RegisterClientRequest(ClientRequest(clientName, clientPhone, AddressRequest(postalCode, street, complement, number, neighborhood, city, state, country, latitude, longitude))))
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ client ->
                            getMvpView()?.hideLoading()
                            dataManager.insertClient(client).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({ getMvpView()?.finishWithOkResult() }, {})
                        }, { err ->
                            getMvpView()?.hideLoading()
                            handleApiError(err as HttpException)
                        })

            }
        }
    }

    override fun loadCountries() {
        val defaultLocale = Locale.getDefault().displayCountry
        val countriesString = Array<String>(Locale.getAvailableLocales().size, { i ->
            Locale.getAvailableLocales()[i].displayCountry
        }).distinct().sorted()

        val indexOf = countriesString.indexOf(defaultLocale)
        getMvpView()?.configureSpinners(countriesString, indexOf)
    }

}