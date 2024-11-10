package com.hena.todolistapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hena.todolistapp.databinding.ActivityAddTodoBinding
import com.hena.todolistapp.database.AppDatabase
import com.hena.todolistapp.database.TodoDao
import com.hena.todolistapp.database.TodoEntity

class AddTodoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityAddTodoBinding
    lateinit var db: AppDatabase
    lateinit var todoDao: TodoDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        todoDao = db.getTodoDao()

        binding.btnComplete.setOnClickListener {
            insertTodo()
        }
    }

    private fun insertTodo() {
        val todoTitle = binding.editTitle.text.toString()
        val todoImportance = binding.radiogroup.checkedRadioButtonId
        var impotance = 0
        when (todoImportance) {
            R.id.btn_high -> {
                impotance = 1
            }

            R.id.btn_middle -> {
                impotance = 2
            }

            R.id.btn_low -> {
                impotance = 3
            }
        }
        if (impotance == 0 || todoTitle.isBlank()){
            Toast.makeText(this, "다시 확인해주세요!", Toast.LENGTH_SHORT).show()
        } else{
            Thread{
                todoDao.insertTodo(TodoEntity(null, todoTitle, impotance))
                runOnUiThread{
                    Toast.makeText(this, "할 일이 추가되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.start()
        }
    }
}
