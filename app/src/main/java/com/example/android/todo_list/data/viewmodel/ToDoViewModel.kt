package com.example.android.todo_list.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.todo_list.data.ToDoDatabase
import com.example.android.todo_list.data.models.ToDodata
import com.example.android.todo_list.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Viewmodel:provide data to ui and survive configurational changes ,acts communication center
// between repository and ui

class ToDoViewModel(application: Application):AndroidViewModel(application){
    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
    private val repository : ToDoRepository
    private val getAllData :LiveData<List<ToDodata>>

    init {
        repository = ToDoRepository(toDoDao)
        getAllData = repository.getAllData
    }

    fun insertData(toDodata: ToDodata){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDodata)
        }
    }
}