package br.com.airescovit.clim.ui.login.login


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseFragment
import br.com.airescovit.clim.ui.login.LoginActivity
import kotterknife.bindView
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : BaseFragment(), LoginFragmentMvpView {

    @Inject lateinit var mPresenter: LoginFragmentMvpPresenter<LoginFragmentMvpView>

    val btnCriarConta: Button by bindView(R.id.btn_criar_conta)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView: View = inflater.inflate(R.layout.fragment_login, container, false)
        val component = getActivityComponent()
        if (component != null) {
            component.inject(this)
            mPresenter.onAttach(this)
        }

        return mView
    }

    companion object {
        val TAG: String = "LoginFragment"
        fun newInstance(): LoginFragment? {
            return LoginFragment()
        }
    }

    override fun setUp(view: View) {
        btnCriarConta.setOnClickListener({ mPresenter.onButtonCriarContaClick() })
    }

    override fun showRegisterFragment() {
        (getBaseActivity() as LoginActivity).showRegisterFragment()
    }

    override fun onIncorrectEmail(resId: Int) {
    }

    override fun onIncorrectPassword(resId: Int) {
    }


}
