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

package com.example.feature.ui.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.example.core.database.entity.Cart
import com.example.core.database.repository.CartRepository
import com.example.core.database.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject


@HiltViewModel
class FoodViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    var isLoading: Boolean = false
    lateinit var toastMessage: String
    private val foodFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    private val foodListFlow = foodFetchingIndex.flatMapLatest { page ->
        foodRepository.fetchFoodList(
            page = page,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = {
                if (it != null) {
                    toastMessage = it
                }
            }
        )
    }
    private var isSessionExpired = false

    suspend fun checkSessionExpiry(): Boolean {
        withContext(Dispatchers.IO) {
            delay(5_000) // to simulate a heavy weight operations
            isSessionExpired = true
        }
        return isSessionExpired
    }

    val foodList = foodListFlow.asLiveData()
    var isCartReady: Boolean = cartRepository.isCart()

    fun UpdateItemCart(mealID: String, total: Int) {
        viewModelScope.launch {
            Thread {
                cartRepository.UpdateItemCart(mealID, total)
            }.start()
        }
    }

    fun InsertCart(mealID: String, total: Int, price:String) {
        viewModelScope.launch {
            cartRepository.createCart(mealID, total, price)
        }
    }

    fun getItem(mealID: String): Cart? {
        var data: Cart? = null
        data = cartRepository.getItemByID(mealID)
        return data
    }

    fun RemoveItem(mealID: String, total: Int, price:String) {
        viewModelScope.launch {
            cartRepository.RemoveItemCart(mealID, total, price)
        }
    }

    fun isItemCart(mealID: String): Boolean {
        return cartRepository.isCart(mealID)
    }

    fun getAllCart() {
        viewModelScope.launch {
            cartRepository.getAllItem().asLiveData()
        }
    }

    init {
        Timber.d("init FoodViewModel")
    }

    @MainThread
    fun fetchNextFoodList() {
        if (!isLoading) {
            foodFetchingIndex.value++
        }
    }
}
