/*
 * Copyright 2020 Google LLC
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

package com.google.samples.apps.sunflower.di

import android.content.Context
import com.example.core.database.AppDatabase
import com.example.core.database.dao.CartDao
import com.example.core.database.dao.GardenPlantingDao
import com.example.core.database.dao.PlantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun providePlantDao(appDatabase: AppDatabase): PlantDao {
        return appDatabase.plantDao()
    }

    @Provides
    fun provideGardenPlantingDao(appDatabase: AppDatabase): GardenPlantingDao {
        return appDatabase.gardenPlantingDao()
    }

    @Provides
    fun provideHarvestPlantingDao(appDatabase: AppDatabase): com.example.core.database.dao.HarvestPlantingDao {
        return appDatabase.harvestDao()
    }

    @Provides
    fun provideCartDao(appDatabase: AppDatabase): CartDao {
        return appDatabase.cartDao()
    }
}
