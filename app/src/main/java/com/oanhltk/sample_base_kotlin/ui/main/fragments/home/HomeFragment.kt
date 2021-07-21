package com.oanhltk.sample_base_kotlin.ui.main.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.oanhltk.sample_base_kotlin.R
import com.oanhltk.sample_base_kotlin.databinding.FragmentHomeBinding
import com.oanhltk.sample_base_kotlin.ui.main.adapter.HomeFragmentAdapter
import com.oanhltk.sample_base_kotlin.ui.main.fragments.favorite.FavoriteFragment
import com.oanhltk.sample_base_kotlin.ui.main.fragments.movie.MoviesFragment

class HomeFragment  : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val fragmentList : MutableList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentList.add(MoviesFragment())
        fragmentList.add(FavoriteFragment())
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0))    }

    private fun initViews() {
        binding.pager.adapter = HomeFragmentAdapter(this, fragmentList)
        binding.pager.setUserInputEnabled(false)
        binding.pager.offscreenPageLimit = 2

        TabLayoutMediator(
                binding.tabLayout, binding.pager) {
            tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_film)
                1 -> tab.setIcon(R.drawable.ic_star)
            }
        }.attach()

    }
}
