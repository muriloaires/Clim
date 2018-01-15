package br.com.airescovit.clim.ui.login.login


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : BaseFragment(), LoginFragmentMvpView {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        val TAG:String = "LoginFragment"
        fun newInstance(): LoginFragment? {
            return LoginFragment()
        }
    }

    override fun setUp(view: View) {

    }
}// Required empty public constructor
