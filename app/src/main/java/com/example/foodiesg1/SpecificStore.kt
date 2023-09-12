package com.example.foodiesg1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast

class SpecificStore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_store)

        val reviewButton = findViewById<ImageButton>(R.id.add_review_btn)
        reviewButton.setOnClickListener{
            Toast.makeText(applicationContext, "You can add a review", Toast.LENGTH_SHORT).show()
        }
    }
}