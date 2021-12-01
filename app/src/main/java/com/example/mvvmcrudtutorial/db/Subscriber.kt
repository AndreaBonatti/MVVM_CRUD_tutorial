package com.example.mvvmcrudtutorial.db

import androidx.room.Entity

@Entity(tableName = "subscriber_data_table")
data class Subscriber(
    val id: Int,
    val name: String,
    val email: String
) {
}