package com.hena.todolistapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hena.todolistapp.databinding.ItemTodosBinding
import com.hena.todolistapp.database.TodoEntity

class TodoRecyclerViewAdapter(
    private val todoList: ArrayList<TodoEntity>,
    private val listener: OnItemLongClickListener
) :
    RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(binding: ItemTodosBinding) : RecyclerView.ViewHolder(binding.root) {
        val tv_importance = binding.tvImportance
        val tv_title = binding.tvTitle
        val root = binding.root
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding: ItemTodosBinding =
            ItemTodosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val todoData = todoList[position]

        when (todoData.importance) {
            1 -> {
                holder.tv_importance.setBackgroundResource(R.color.red)
            }

            2 -> {
                holder.tv_importance.setBackgroundResource(R.color.yellow)
            }

            3 -> {
                holder.tv_importance.setBackgroundResource(R.color.green)
            }
        }
        holder.tv_importance.text = todoData.importance.toString()
        holder.tv_title.text = todoData.title
        holder.root.setOnLongClickListener {
            listener.onLongClickLister(position)
            false
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}
