package com.example.android.todo_list.data

import androidx.room.TypeConverter
import com.example.android.todo_list.data.models.Priority

//we make ths converter class as room does not allow custom classes or data types(like priority )
// it only takes primitive datatypes like string,float etc..
//so we need to convert priority->string when we write in our data base
//and we need convert back string -> priority when we read from our database
class converter {
    @TypeConverter
    fun fromPriority(priority: Priority):String{
        return priority.name
    }
    @TypeConverter
    fun toPriority(priority:String): Priority {
        return Priority.valueOf(priority)
    }
}