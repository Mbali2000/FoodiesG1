package com.example.foodiesg1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodiesg1.R
import com.example.foodiesg1.databinding.FragmentHomeBinding
import com.example.foodiesg1.databinding.FragmentStoresBinding
import com.example.foodiesg1.utils.model.StoreData
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class StoresFragment : Fragment(), StorePopUpFragment.PopUpNextListeners {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentStoresBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private lateinit var popUpFragment: StorePopUpFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStoresBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()

    }

    private fun registerEvents(){
        binding.previousBtn.setOnClickListener {
            navController.navigate(R.id.action_storesFragment_to_homeFragment)
        }

        binding.addStoreBtn.setOnClickListener {
            popUpFragment = StorePopUpFragment()
            popUpFragment.setListener(this)
            popUpFragment.show(childFragmentManager, "StorePopUpFragment")
        }
    }

    private fun init(view: View){
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Stores")
    }

    override fun onSaveTask(
        id: String,
        idEdt: TextInputEditText,
        name: String,
        nameEdt: TextInputEditText,
        cuisine: String,
        cuisineEdt: TextInputEditText,
        time: String,
        timeEdt: TextInputEditText
    ) {

        val storeData = StoreData(name, cuisine, time)
        databaseRef.child(id).setValue(storeData).addOnSuccessListener {
            idEdt.text?.clear()
            nameEdt.text?.clear()
            cuisineEdt.text?.clear()
            timeEdt.text?.clear()
            Toast.makeText(context, "Successfully Saved", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(context, "Failed to save your store", Toast.LENGTH_SHORT).show()
        }

        popUpFragment.dismiss()

    }


}