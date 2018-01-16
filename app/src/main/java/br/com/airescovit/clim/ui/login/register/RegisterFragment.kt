package br.com.airescovit.clim.ui.login.register


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseFragment
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : BaseFragment(), RegisterMvpView {

    @Inject lateinit var mPresenter: RegisterMvpPresenter<RegisterMvpView>

    companion object {
        val TAG:String = "RegisterFragment"
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
        return mView;
    }

    override fun setUp(view: View) {
    }


}
