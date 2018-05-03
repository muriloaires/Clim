package br.com.airescovit.clim.ui.base

import br.com.airescovit.clim.R
import br.com.airescovit.clim.data.DataManager
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Logics on 12/01/2018.
 */
open class BasePresenter<V : MvpView> @Inject constructor(val dataManager: DataManager, val mCompositeDisposable: CompositeDisposable) : MvpPresenter<V> {


    private var mMvpView: V? = null
    override fun onAttach(mvpView: V) {
        mMvpView = mvpView
    }

    override fun onDetach() {
        mCompositeDisposable.dispose()
        mMvpView = null
    }

    override fun handleApiError(error: HttpException) {
        when (error.code()) {
            401 -> {
                setUserAsLoggedOut()
                getMvpView()?.openActivityOnTokenExpire()
            }
        }
        if (error.code() == 500) {
            getMvpView()?.onError(R.string.something_wrong)
        }
    }

    override fun setUserAsLoggedOut() {
        dataManager.setAccessToken(null)
    }

    fun isViewAttached(): Boolean {
        return mMvpView != null
    }

    fun getMvpView(): V? {
        return mMvpView
    }
}