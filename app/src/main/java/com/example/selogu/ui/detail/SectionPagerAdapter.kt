package com.example.selogu.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.selogu.R



class SectionPagerAdapter(private val ctx: Context, fm: FragmentManager, private val data: Bundle) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val tabTitles = intArrayOf(R.string.tab_1, R.string.tab_2)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FollowersFragment().apply { arguments = data }
            1 -> FollowingFragment().apply { arguments = data }
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return ctx.resources.getString(tabTitles[position])
    }
}
