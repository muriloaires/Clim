package br.com.airescovit.clim.ui.login.register


import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseFragment
import br.com.airescovit.clim.ui.login.LoginActivity
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : BaseFragment(), RegisterMvpView {

    @Inject lateinit var mPresenter: RegisterMvpPresenter<RegisterMvpView>

    private lateinit var mEdtName: EditText
    private lateinit var mEdtLastname: TextInputEditText
    private lateinit var mEdtEmail: TextInputEditText
    private lateinit var mEdtPassword: TextInputEditText
    private lateinit var mEdtConfirmPassword: TextInputEditText
    private lateinit var mBtnRegistrar: View

    companion object {
        val TAG: String = "RegisterFragment"
        fun newInstance(): RegisterFragment {
            val fragment = RegisterFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView: View = inflater.inflate(R.layout.fragment_register, container, false)
        val component = getActivityComponent()
        if (component != null) {
            component.inject(this)
            mPresenter.onAttach(this)
        }
        mEdtName = mView.findViewById(R.id.edt_first_name)
        mEdtLastname = mView.findViewById(R.id.edt_last_name)
        mEdtEmail = mView.findViewById(R.id.edt_email)
        mEdtPassword = mView.findViewById(R.id.edt_password)
        mEdtConfirmPassword = mView.findViewById(R.id.edt_confirm_password)
        mBtnRegistrar = mView.findViewById(R.id.btn_register)
        return mView
    }

    override fun setUp(view: View) {
        mBtnRegistrar.setOnClickListener({
            mPresenter.onBtnRegisterClick(mEdtName.text.toString(), mEdtLastname.text.toString(), mEdtEmail.text.toString(), mEdtPassword.text.toString(), mEdtConfirmPassword.text.toString())
        })
    }

    override fun onIncorrectName(resId: Int) {
        view?.findViewById<TextInputLayout>(R.id.text_input_first_name)?.error = getString(resId)
    }

    override fun onIncorrectEmail(resId: Int) {
        view?.findViewById<TextInputLayout>(R.id.text_input_email)?.error = getString(resId)
    }

    override fun onIncorrectPassword(resId: Int) {
        view?.findViewById<TextInputLayout>(R.id.text_input_password)?.error = getString(resId)
    }

    override fun onDifferentsPasswords(resId: Int) {
        view?.findViewById<TextInputLayout>(R.id.text_input_confirm_password)?.error = getString(resId)
    }

    override fun startMainActivity() {
        (getBaseActivity() as LoginActivity).showMainActivity()
    }


}
