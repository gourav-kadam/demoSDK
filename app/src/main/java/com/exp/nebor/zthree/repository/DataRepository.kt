package com.exp.nebor.zthree.repository

import com.exp.nebor.zthree.mock.Mock
import com.exp.nebor.zthree.model.StoriesDataModel
import javax.inject.Inject

class DataRepository @Inject constructor(private val mock: Mock) {
    fun getStoriesData(): ArrayList<StoriesDataModel>? {
        val dataList = mock.loadMockData()
        return dataList
    }
}