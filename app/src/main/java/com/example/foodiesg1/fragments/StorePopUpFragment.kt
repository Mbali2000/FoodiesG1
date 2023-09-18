package com.example.foodiesg1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.foodiesg1.R
import com.example.foodiesg1.databinding.FragmentStorePopUpBinding
import com.example.foodiesg1.databinding.FragmentStoresBinding
import com.google.android.material.textfield.TextInputEditText


class StorePopUpFragment : DialogFragment() {

    private lateinit var binding: FragmentStorePopUpBinding
    private lateinit var listener: PopUpNextListeners

    fun setListener(listener: PopUpNextListeners){
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStorePopUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerEvents()
    }

    private fun registerEvents(){
        binding.nextBtn.setOnClickListener {
            val storeId = binding.shopIdEdt.text.toString()
            val storeName = binding.shopNameEdt.text.toString()
            val storeCuisine = binding.shopCuisineEdt.text.toString()
            val storeTime = binding.shopTimesEdt.text.toString()

            if (storeId.isNotEmpty() && storeName.isNotEmpty() && storeCuisine.isNotEmpty() && storeTime.isNotEmpty()){
                listener.onSaveTask(storeId, binding.shopIdEdt, storeName, binding.shopNameEdt, storeCuisine, binding.shopCuisineEdt, storeTime, binding.shopTimesEdt)
            } else {
                Toast.makeText(context, "There is a missing field", Toast.LENGTH_SHORT).show()
            }
        }

        binding.closeBtn.setOnClickListener {
            dismiss()
        }
    }

    interface PopUpNextListeners{
        fun onSaveTask(id: String, idEdt: TextInputEditText, name:String, nameEdt: TextInputEditText, cuisine:String, cuisineEdt: TextInputEditText, time:String, timeEdt: TextInputEditText)

    }

}