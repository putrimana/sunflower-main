/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.ui.adapters.GARDEN_HARVEST_PAGE_INDEX
import com.google.samples.apps.sunflower.ui.adapters.MY_GARDEN_PAGE_INDEX
import com.google.samples.apps.sunflower.ui.adapters.PLANT_LIST_PAGE_INDEX
import com.google.samples.apps.sunflower.ui.adapters.SunflowerPagerAdapter
import com.google.samples.apps.sunflower.databinding.FragmentViewPagerBinding
import com.google.samples.apps.sunflower.ui.activity.CartActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = SunflowerPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.imageViewBadge.setOnClickListener {
            navigateToCartActivity()
        }
        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
            PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
            GARDEN_HARVEST_PAGE_INDEX -> R.drawable.harvest_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }
    private fun navigateToCartActivity() {
        try {
            //val uri = Uri.parse("myApp://feature2detail")
            //findNavController().navigate(uri)
            //val intent = Intent (this@HomeViewPagerFragment.context, CartActivity::class.java)
            //startActivity(intent)
        }catch (ex:ExceptionInInitializerError){
            Log.d("TAG", "messagenya:" + ex.toString())
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> getString(R.string.my_garden_title)
            PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_list_title)
            GARDEN_HARVEST_PAGE_INDEX -> "Harvest"
            else -> null
        }
    }

}
