package br.com.airescovit.clim.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.View
import br.com.airescovit.clim.di.component.ActivityComponent
import br.com.airescovit.clim.utils.CommomUtils
import butterknife.Unbinder

/**
 * Created by Logics on 15/01/2018.
 */
abstract class BaseFragment : Fragment(), MvpView {

    private var mActivity: BaseActivity? = null
    private var mUnBinder: Unbinder? = null
    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.mActivity = activity
            this.mActivity!!.onFragmentAttached()
        }
    }

    override fun showLoading() {
        hideLoading()
        mProgressDialog = CommomUtils.showLoadingDialog(mActivity as Context)
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    override fun onError(error: String?) {
        if (mActivity != null) {
            mActivity!!.onError(error)
        }
    }

    override fun onError(@StringRes resId: Int) {
        if (mActivity != null) {
            mActivity!!.onError(resId)
        }
    }

    override fun showMessage(message: String?) {
        if (mActivity != null) {
            mActivity!!.showMessage(message)
        }
    }

    override fun showMessage(@StringRes messageResId: Int) {
        if (mActivity != null) {
            mActivity!!.showMessage(messageResId)
        }
    }

    override fun isNetworkConnected(): Boolean {
        return if (mActivity != null) {
            mActivity!!.isNetworkConnected()
        } else false
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun hideKeyboard() {
        if (mActivity != null) {
            mActivity!!.hideKeyboard()
        }
    }

    override fun openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity!!.openActivityOnTokenExpire()
        }
    }

    fun getActivityComponent(): ActivityComponent? {
        return if (mActivity != null) {
            mActivity!!.getActivityComponent()
        } else null
    }

    fun getBaseActivity(): BaseActivity? {
        return mActivity
    }

    fun setUnBinder(unBinder: Unbinder) {
        mUnBinder = unBinder
    }

    protected abstract fun setUp(view: View)

    override fun onDestroy() {
        if (mUnBinder != null) {
            mUnBinder!!.unbind()
        }
        super.onDestroy()
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}