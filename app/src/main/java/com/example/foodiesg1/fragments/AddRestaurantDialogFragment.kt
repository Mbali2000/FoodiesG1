package com.example.foodiesg1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.foodiesg1.databinding.FragmentAddRestaurantDialogBinding
import com.example.foodiesg1.utils.model.ToDoData
import com.google.android.material.textfield.TextInputEditText


class AddRestaurantDialogFragment : DialogFragment() {

    private lateinit var binding:FragmentAddRestaurantDialogBinding
    private var listener : OnDialogNextBtnClickListener? = null
    private var toDoData: ToDoData? = null


    fun setListener(listener: OnDialogNextBtnClickListener) {
        this.listener = listener
    }

    companion object {
        const val TAG = "DialogFragment"
        @JvmStatic
        fun newInstance(restaurantId: String, restaurant: String) =
            AddRestaurantDialogFragment().apply {
                arguments = Bundle().apply {
                    putString("restaurantId", restaurantId)
                    putString("restaurant", restaurant)
                }
            }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAddRestaurantDialogBinding.inflate(inflater , container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null){

            toDoData = ToDoData(arguments?.getString("restaurantId").toString() ,arguments?.getString("restaurant").toString())
            binding.todoEt.setText(toDoData?.restaurant)
        }


        binding.todoClose.setOnClickListener {
            dismiss()
        }

        binding.todoNextBtn.setOnClickListener {

            val todorestaurant = binding.todoEt.text.toString()
            if (todorestaurant.isNotEmpty()){
                if (toDoData == null){
                    listener?.saverestaurant(todorestaurant , binding.todoEt)
                }else{
                    toDoData!!.restaurant = todorestaurant
                    listener?.updaterestaurant(toDoData!!, binding.todoEt)
                }

            }
        }
    }

    interface OnDialogNextBtnClickListener{
        fun saverestaurant(todorestaurant:String , todoEdit:TextInputEditText)
        fun updaterestaurant(toDoData: ToDoData , todoEdit:TextInputEditText)
    }

}