package com.example.android.todo_list.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.todo_list.data.models.ToDodata

@Dao
interface ToDoDao {
    //here instead of using default sql we are defining our own custom query
    @Query("SELECT * FROM ToDo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDodata>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDodata: ToDodata)  //suspend tells us that our function is running inside a coroutine(i.e function run in backround in our view model)

}