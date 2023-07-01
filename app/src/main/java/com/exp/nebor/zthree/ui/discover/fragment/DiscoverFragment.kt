package com.exp.nebor.zthree.ui.discover.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.exp.nebor.R
import com.exp.nebor.zthree.base.BaseFragment
import com.exp.nebor.zthree.ui.discover.viewmodel.DiscoverViewModel

class DiscoverFragment : BaseFragment(R.layout.fragment_discover) {
    private lateinit var viewModel: DiscoverViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)
    }
}