package br.com.airescovit.clim.ui.main

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.widget.TextView
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseActivity
import br.com.airescovit.clim.ui.main.adapter.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), TabLayout.OnTabSelectedListener {

    @Inject
    lateinit var mPagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getActivityComponent().inject(this)
        setSupportActionBar(toolbar)
        setUpTabMenu()
    }

    private fun setUpTabMenu() {
        tab_layout.addOnTabSelectedListener(this)
        view_pager.adapter = mPagerAdapter
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.getTabAt(0)?.setCustomView(R.layout.tasks_tab)
        tab_layout.getTabAt(1)?.setCustomView(R.layout.clients_tab)
    }


    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        view_pager.currentItem = tab?.position!!
        tab.customView?.findViewById<TextView>(R.id.tabText)?.setTextColor(ContextCompat.getColor(this, R.color.colorAccentLighter))
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        view_pager.currentItem = tab?.position!!
        tab.customView?.findViewById<TextView>(R.id.tabText)?.setTextColor(Color.WHITE)
    }

    fun selectTaskTab() {
        tab_layout.getTabAt(0)?.select()
        mPagerAdapter.getTasksFragment().mPresenter.onViewReady()
    }
}
