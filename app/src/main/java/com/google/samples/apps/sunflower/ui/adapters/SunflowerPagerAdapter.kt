/*
 * Copyright 2019 Google LLC
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

package com.google.samples.apps.sunflower.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.samples.apps.sunflower.ui.fragment.GardenFragment
import com.google.samples.apps.sunflower.ui.fragment.HarvestFragment
import com.google.samples.apps.sunflower.ui.fragment.PlantListFragment

const val MY_GARDEN_PAGE_INDEX = 0
const val PLANT_LIST_PAGE_INDEX = 1
const val GARDEN_HARVEST_PAGE_INDEX = 2

class SunflowerPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MY_GARDEN_PAGE_INDEX to { GardenFragment() },
        PLANT_LIST_PAGE_INDEX to { PlantListFragment() },
        GARDEN_HARVEST_PAGE_INDEX to { HarvestFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
