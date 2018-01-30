@file:Suppress("DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES")

package br.com.airescovit.clim.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseActivity
import br.com.airescovit.clim.ui.login.login.LoginFragment
import br.com.airescovit.clim.ui.login.register.RegisterFragment
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginMvpView {

    @Inject lateinit var mPresenter: LoginMvpPresenter<LoginMvpView>

    fun getStartIntent(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getActivityComponent().inject(this)
        mPresenter.onAttach(this)
    }

    override fun showLoginFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_view, LoginFragment.newInstance(), LoginFragment.TAG)
                .commit()
    }

    override fun showRegisterFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_view, RegisterFragment.newInstance(), RegisterFragment.TAG)
                .commit()
    }

    override fun openMainActivity() {
    }

    fun showMainActivity() {
    }
}
