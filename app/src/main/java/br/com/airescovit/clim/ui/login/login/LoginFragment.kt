package br.com.airescovit.clim.ui.login.login


import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseFragment
import br.com.airescovit.clim.ui.login.LoginActivity
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : BaseFragment(), LoginFragmentMvpView {


    @Inject lateinit var mPresenter: LoginFragmentMvpPresenter<LoginFragmentMvpView>

    private lateinit var btnEntrar: TextView
    private lateinit var btnCriarConta: Button
    private lateinit var edtEmail: TextInputEditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var textInputEmail: TextInputLayout
    private lateinit var textInputPassword: TextInputLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView: View = inflater.inflate(R.layout.fragment_login, container, false)
        val component = getActivityComponent()
        if (component != null) {
            component.inject(this)
            mPresenter.onAttach(this)
        }
        btnEntrar = mView.findViewById(R.id.btn_entrar)
        btnCriarConta = mView.findViewById(R.id.btn_criar_conta)
        edtEmail = mView.findViewById(R.id.edt_email)
        edtPassword = mView.findViewById(R.id.edt_password)
        textInputEmail = mView.findViewById(R.id.text_input_email)
        textInputPassword = mView.findViewById(R.id.text_input_password)


        return mView
    }

    companion object {
        val TAG: String = "LoginFragment"
        fun newInstance(): LoginFragment? {
            return LoginFragment()
        }
    }

    override fun setUp(view: View) {
        btnEntrar.setOnClickListener({
            mPresenter.onButtonServerClick(edtEmail.text.toString(), edtPassword.text.toString())
        })
        btnCriarConta.setOnClickListener({
            mPresenter.onButtonCriarContaClick()
        }
        )
    }

    override fun showRegisterFragment() {
        (getBaseActivity() as LoginActivity).showRegisterFragment()
    }

    override fun onIncorrectEmail(resId: Int) {
        textInputEmail.error = getString(resId)
    }

    override fun onIncorrectPassword(resId: Int) {
        textInputPassword.error = getString(resId)
    }

    override fun startMainActivity() {
        (getBaseActivity() as LoginActivity).showMainActivity()
    }

}
