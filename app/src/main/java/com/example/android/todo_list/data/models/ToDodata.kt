package com.example.android.todo_list.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDo_table")
class ToDodata(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var title:String,
    var priority: Priority,
    var description:String
)
