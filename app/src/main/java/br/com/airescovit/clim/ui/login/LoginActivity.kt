@file:Suppress("DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES")

package br.com.airescovit.clim.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseActivity
import br.com.airescovit.clim.ui.login.login.LoginFragment
import butterknife.ButterKnife
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
        setUnbinder(ButterKnife.bind(this))
        mPresenter.onAttach(this)
    }

    override fun showLoginFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_view, LoginFragment.newInstance(), LoginFragment.TAG)
                .commit()
    }

    override fun showRegisterFragment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openMainActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
