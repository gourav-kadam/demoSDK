package com.exp.nebor.zthree.ui.home.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.exp.nebor.R
import com.exp.nebor.zthree.base.BaseFragment
import com.exp.nebor.zthree.model.ResultData
import com.exp.nebor.zthree.model.StoriesDataModel
import com.exp.nebor.zthree.ui.home.adapter.StoriesPagerAdapter
import com.exp.nebor.zthree.ui.main.viewmodel.MainViewModel
import com.exp.nebor.zthree.utils.Constants
import com.exp.nebor.zthree.work.PreCachingService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val homeViewModel by activityViewModels<MainViewModel>()

    private lateinit var storiesPagerAdapter: StoriesPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storiesData = homeViewModel.getDataList()

        storiesData.observe(viewLifecycleOwner, Observer { value ->
            when(value) {
                is ResultData.Loading -> {
                }
                is ResultData.Success -> {
                    if (!value.data.isNullOrEmpty()) {
                        val dataList = value.data
                        storiesPagerAdapter = StoriesPagerAdapter(this, dataList)
                        view_pager_stories.adapter = storiesPagerAdapter

                        startPreCaching(dataList)
                    }
                }
            }
        })
    }

    private fun startPreCaching(dataList: ArrayList<StoriesDataModel>) {
        val urlList = arrayOfNulls<String>(dataList.size)
        dataList.mapIndexed { index, storiesDataModel ->
            urlList[index] = storiesDataModel.storyUrl
        }
        val inputData = Data.Builder().putStringArray(Constants.KEY_STORIES_LIST_DATA, urlList).build()
        val preCachingWork = OneTimeWorkRequestBuilder<PreCachingService>().setInputData(inputData)
            .build()
        WorkManager.getInstance(requireContext())
            .enqueue(preCachingWork)
    }
}