package br.com.airescovit.clim.ui.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import br.com.airescovit.clim.ui.clients.ClientsFragment
import br.com.airescovit.clim.ui.tasks.TasksFragment

/**
 * Created by murilo aires on 27/01/2018.
 */
class PagerAdapter(fragmentManager: FragmentManager, val numOfTabs: Int) : FragmentStatePagerAdapter(fragmentManager) {

    private lateinit var clientsFragment: ClientsFragment
    private lateinit var taskFragment: TasksFragment


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TasksFragment.getInstance()
            else -> ClientsFragment.getInstance()
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val createdFragment = super.instantiateItem(container, position)
        when (position) {
            0 -> taskFragment = createdFragment as TasksFragment
            else -> clientsFragment = createdFragment as ClientsFragment
        }
        return createdFragment
    }


}