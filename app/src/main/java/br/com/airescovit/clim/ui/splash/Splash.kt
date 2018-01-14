package br.com.airescovit.clim.ui.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseActivity
import butterknife.ButterKnife
import javax.inject.Inject

class Splash : BaseActivity(), SplashMvpView {


    @Inject
    public var mPresenter: SplashMvpPresenter<SplashMvpView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getActivityComponent().inject(this)
        setUnbinder(ButterKnife.bind(this))
        mPresenter!!.onAttach(this)
    }

    override fun openLoginActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openMainActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
