package com.exp.nebor.zthree.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.exp.nebor.zthree.model.StoriesDataModel
import com.exp.nebor.zthree.ui.story.StoryViewFragment

class StoriesPagerAdapter(fragment: Fragment, val dataList: MutableList<StoriesDataModel> = mutableListOf()) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun createFragment(position: Int): Fragment {
        return StoryViewFragment.newInstance(dataList[position])
    }
}