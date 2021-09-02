package com.example.android.todo_list.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.todo_list.data.models.ToDodata

@Database(entities = [ToDodata::class],version=1,exportSchema=false)
@TypeConverters(converter::class)
abstract class ToDoDatabase : RoomDatabase(){
    abstract  fun toDoDao():ToDoDao
    //we will get direct reference of our function to different classes through companion object
    companion object{
        //using volatile any writes to this field will be immediately available to other threads
        @Volatile
        private var  INSTANCE:ToDoDatabase? = null
        fun getDatabase(context: Context): ToDoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
//synchronized:when a thread calls synchronized,it acquires a lock of that synchronized block.
// Other threads don't have permission to call that same synchronized block as long as
// previous thread does not release the lock.
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}