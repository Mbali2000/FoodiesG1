package com.example.foodiesg1.utils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodiesg1.databinding.EachTodoItemBinding
import com.example.foodiesg1.utils.model.ToDoData

class restaurantAdapter(private val list: MutableList<ToDoData>) : RecyclerView.Adapter<restaurantAdapter.restaurantViewHolder>() {

    private  val TAG = "restaurantAdapter"
    private var listener:restaurantAdapterInterface? = null
    fun setListener(listener:restaurantAdapterInterface){
        this.listener = listener
    }
    class restaurantViewHolder(val binding: EachTodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): restaurantViewHolder {
        val binding =
            EachTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return restaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: restaurantViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.todorestaurant.text = this.restaurant

                Log.d(TAG, "onBindViewHolder: "+this)
                binding.editrestaurant.setOnClickListener {
                    listener?.onEditItemClicked(this , position)
                }

                binding.deleterestaurant.setOnClickListener {
                    listener?.onDeleteItemClicked(this , position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface restaurantAdapterInterface{
        fun onDeleteItemClicked(toDoData: ToDoData , position : Int)
        fun onEditItemClicked(toDoData: ToDoData , position: Int)
    }

}