package br.com.airescovit.clim.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseActivity
import br.com.airescovit.clim.ui.login.LoginActivity
import javax.inject.Inject

class Splash : BaseActivity(), SplashMvpView {


    @Inject
    lateinit var mPresenter: SplashMvpPresenter<SplashMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getActivityComponent().inject(this)
        mPresenter!!.onAttach(this)
    }

    override fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun openMainActivity() {
        Toast.makeText(this, "WOW", Toast.LENGTH_SHORT).show()
    }



}
