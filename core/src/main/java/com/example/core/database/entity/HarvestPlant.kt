/*
 * Copyright 2018 Google LLC
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

package com.example.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "harvest_plantings")
data class HarvestPlant(
        @ColumnInfo(name = "harvest_plant_id") val harvest_plant_id: String,
        @ColumnInfo(name = "harvest_amount", defaultValue = 0.toString())val harvest_amount: Int,
        @ColumnInfo(name = "harvest_date") val harvest_date: String = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gardenHarvestId")
    var gardenHarvestId: Long = 0
}
