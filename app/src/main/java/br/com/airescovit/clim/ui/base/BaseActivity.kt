package br.com.airescovit.clim.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import br.com.airescovit.clim.ClimApp
import br.com.airescovit.clim.R
import br.com.airescovit.clim.di.component.ActivityComponent
import br.com.airescovit.clim.di.component.DaggerActivityComponent
import br.com.airescovit.clim.di.module.ActivityModule
import br.com.airescovit.clim.utils.CommomUtils
import br.com.airescovit.clim.utils.NetworkUtils
import butterknife.Unbinder

/**
 * Created by Logics on 10/01/2018.
 */
abstract class BaseActivity : AppCompatActivity(), MvpView {

    var mProgressDialog: ProgressDialog? = null
    private var mUnBinder: Unbinder? = null
    private lateinit var mActivityComponent: ActivityComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as ClimApp).mApplicationComponent)
                .build()
        Log.d("", "")
    }

    fun getActivityComponent(): ActivityComponent {
        return mActivityComponent
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    override fun showLoading() {
        hideLoading()
        mProgressDialog = CommomUtils.showLoadingDialog(this);
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    private fun showSnackBar(message: String) {

        val snackbar = Snackbar.make(findViewById<View>(android.R.id.content),
                message, Snackbar.LENGTH_SHORT)
        val sbView = snackbar.getView()
        val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }

    override fun onError(error: String?) {
        if (error != null) {
            showSnackBar(error)
        } else {
            showSnackBar(getString(R.string.algo_errado_ocorreu))
        }
    }

    override fun onError(restId: Int) {
        onError(getString(restId))
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.algo_errado_ocorreu), Toast.LENGTH_SHORT).show()
        }
    }

    override fun showMessage(messageResId: Int) {
        showSnackBar(getString(messageResId))
    }

    override fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }

    override fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun openActivityOnTokenExpire() {
        //startActivity()
    }

    fun setUnbinder(unbinder: Unbinder) {
        this.mUnBinder = unbinder
    }

    override fun onDestroy() {
        if (mUnBinder != null)
            mUnBinder!!.unbind()

        super.onDestroy()
    }
}