package br.com.airescovit.clim.ui.login.login

import br.com.airescovit.clim.R
import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.data.network.model.login.model.Auth
import br.com.airescovit.clim.data.network.model.login.model.LoginRequest
import br.com.airescovit.clim.ui.base.BasePresenter
import br.com.airescovit.clim.utils.TextUtils
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Logics on 15/01/2018.
 */
class LoginFragmentPresenter<V : LoginFragmentMvpView> @Inject constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), LoginFragmentMvpPresenter<V> {
    override fun onButtonCriarContaClick() {
        getMvpView()?.showRegisterFragment()
    }

    override fun onButtonServerClick(email: String, password: String) {
        when {
            !TextUtils.validEmail(email) -> getMvpView()?.onIncorrectEmail(R.string.incorrect_email)
            email.isEmpty() -> getMvpView()!!.onIncorrectEmail(R.string.compulsory_field)
            password.isEmpty() -> getMvpView()!!.onIncorrectEmail(R.string.compulsory_field)
            password.length < 6 -> getMvpView()!!.onIncorrectEmail(R.string.incorrect_password)
            else -> {
                getMvpView()?.hideKeyboard()
                getMvpView()?.showLoading()
                val observable = dataManager.doLoginRequest(LoginRequest(Auth(email, password)))
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ loginResponse ->
                            dataManager.updateUserInfo(loginResponse.token, loginResponse.user.id,
                                    DataManager.LoginMode.LOGGE_IN_MODE_SERVER,
                                    loginResponse.user.firstName + loginResponse.user.lastName,
                                    loginResponse.user.email)
                            getMvpView()?.hideLoading()
                            getMvpView()?.startMainActivity()
                        }, { err ->
                            getMvpView()?.hideLoading()
                            handleApiError(err as HttpException)
                        })
            }
        }
    }

    override fun handleApiError(error: HttpException) {
        super.handleApiError(error)
        when (error.code()) {
            404 -> getMvpView()?.onError(R.string.user_not_found)
        }

    }
}