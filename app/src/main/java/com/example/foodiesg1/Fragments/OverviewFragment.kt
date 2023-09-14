package com.example.foodiesg1.Fragments

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodiesg1.R
import com.example.foodiesg1.databinding.FragmentOverviewBinding
import com.google.firebase.database.DatabaseReference
import android.os.Bundle as Bundle1


class OverviewFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle1?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)

    }


}