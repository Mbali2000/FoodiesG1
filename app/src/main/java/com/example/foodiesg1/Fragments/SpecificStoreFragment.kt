package com.example.foodiesg1.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodiesg1.R
import com.example.foodiesg1.databinding.FragmentOverviewBinding
import com.example.foodiesg1.databinding.FragmentSpecificStoreBinding
import com.google.firebase.auth.FirebaseAuth

class SpecificStoreFragment : Fragment() {

    private lateinit var  auth: FirebaseAuth
    private lateinit var  navControl: NavController
    private lateinit var  binding: FragmentSpecificStoreBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSpecificStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()
    }

    private fun init(view:View){
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    private fun registerEvents(){

        binding.backBtn.setOnClickListener{
            //val shop1 = binding.shop1Name.text.toString().trim()
            Toast.makeText(context, "Back button clicked", Toast.LENGTH_LONG).show()
            navControl.navigate(R.id.action_specificStoreFragment_to_overviewFragment)
        }
    }
}