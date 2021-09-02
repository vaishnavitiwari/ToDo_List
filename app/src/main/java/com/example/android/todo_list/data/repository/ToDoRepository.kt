package com.example.android.todo_list.data.repository

import androidx.lifecycle.LiveData
import com.example.android.todo_list.data.ToDoDao
import com.example.android.todo_list.data.models.ToDodata
//Repositories modules handle data operation . they provide a cleanAPI so that the rest of app can retrieve this
//data easily. they are the mediators between data sources


class ToDoRepository(private val toDoDao: ToDoDao) {
    val getAllData: LiveData<List<ToDodata>> = toDoDao.getAllData()


    //function for inserting the data

    suspend fun insertData(toDoData: ToDodata){
        toDoDao.insertData(toDoData)
    }
}