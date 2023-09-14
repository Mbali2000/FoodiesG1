package com.example.foodiesg1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodiesg1.Fragments.OverviewFragment
import com.example.foodiesg1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater)
        setContentView(binding.root)

        val fragment = OverviewFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit()


    }
}