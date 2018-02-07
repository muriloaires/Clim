package br.com.airescovit.clim.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import br.com.airescovit.clim.ClimApp
import br.com.airescovit.clim.R
import br.com.airescovit.clim.di.component.ActivityComponent
import br.com.airescovit.clim.di.component.DaggerActivityComponent
import br.com.airescovit.clim.di.module.ActivityModule
import br.com.airescovit.clim.ui.login.LoginActivity
import br.com.airescovit.clim.utils.CommomUtils
import br.com.airescovit.clim.utils.NetworkUtils

/**
 * Created by Logics on 10/01/2018.
 */
abstract class BaseActivity : AppCompatActivity(), MvpView, BaseFragment.Callback {

    var mProgressDialog: ProgressDialog? = null
    private lateinit var mActivityComponent: ActivityComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as ClimApp).mApplicationComponent)
                .build()
    }

    fun getActivityComponent(): ActivityComponent {
        return mActivityComponent
    }

    fun isLowerThanMarshmallow(): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }


    override fun showLoading() {
        hideLoading()
        mProgressDialog = CommomUtils.showLoadingDialog(this)
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    private fun showSnackBar(message: String) {

        val snackbar = Snackbar.make(findViewById<View>(android.R.id.content),
                message, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
        snackbar.show()
    }

    override fun onError(error: String?) {
        if (error != null) {
            showSnackBar(error)
        } else {
            showSnackBar(getString(R.string.something_wrong))
        }
    }

    override fun onError(resId: Int) {
        onError(getString(resId))
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.something_wrong), Toast.LENGTH_SHORT).show()
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
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }
}