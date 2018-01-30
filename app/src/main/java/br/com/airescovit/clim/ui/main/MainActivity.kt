package br.com.airescovit.clim.ui.main

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseActivity
import br.com.airescovit.clim.ui.main.adapter.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), TabLayout.OnTabSelectedListener {

    @Inject lateinit var mPagerAdapter: PagerAdapter

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

    }


    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        view_pager.currentItem = tab?.position!!
        tab.icon?.setColorFilter(ContextCompat.getColor(this, android.R.color.darker_gray), PorterDuff.Mode.MULTIPLY)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        view_pager.currentItem = tab?.position!!
        tab.icon?.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY)
    }
}
