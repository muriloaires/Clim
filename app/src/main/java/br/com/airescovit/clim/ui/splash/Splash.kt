package br.com.airescovit.clim.ui.splash

import android.os.Bundle
import android.widget.Toast
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseActivity
import butterknife.ButterKnife
import javax.inject.Inject

class Splash : BaseActivity(), SplashMvpView {


    @Inject
    lateinit var mPresenter: SplashMvpPresenter<SplashMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getActivityComponent().inject(this)
        setUnbinder(ButterKnife.bind(this))
        mPresenter!!.onAttach(this)
    }

    override fun openLoginActivity() {
        Toast.makeText(this, "WOW", Toast.LENGTH_SHORT).show()
    }

    override fun openMainActivity() {
        Toast.makeText(this, "WOW", Toast.LENGTH_SHORT).show()
    }
}
