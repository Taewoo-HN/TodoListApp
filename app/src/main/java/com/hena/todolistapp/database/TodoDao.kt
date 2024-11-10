package com.hena.todolistapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    // get All
    @Query("select * from todoentity")
    fun getAllTodo():List<TodoEntity>

    // insert todo
    @Insert
    fun insertTodo(todo: TodoEntity)

    // delete todo
    @Delete
    fun deleteTodo(todo: TodoEntity)
}