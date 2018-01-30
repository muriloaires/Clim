package br.com.airescovit.clim.ui.tasks


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 */
class TasksFragment : BaseFragment() {

    companion object {
        fun getInstance(): TasksFragment {
            return TasksFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun setUp(view: View) {

    }

}// Required empty public constructor
