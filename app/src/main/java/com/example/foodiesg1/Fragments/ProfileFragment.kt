package com.example.foodiesg1.Fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.foodiesg1.R
import com.example.foodiesg1.databinding.ActivityMainBinding
import com.example.foodiesg1.databinding.FragmentOverviewBinding
import com.example.foodiesg1.databinding.FragmentProfileBinding
import com.example.foodiesg1.shopDC
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Base64
import java.util.Calendar


class ProfileFragment : Fragment() {
    
    private lateinit var binding: FragmentProfileBinding
    private lateinit var db: DatabaseReference
    
    var nodeID = " "
    var shopImage: String? = " "
    //val dtl: Long = 0
    //val calendar = Calendar.getInstance()
    //private val dateFormat = SimpleDateFormat("dd-MM-yyyy")    
   

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
        
        val root: View = binding.root
        //var dt = dateFormat.format(calendar.time)
        
        binding.btnPhoto.setOnClickListener(){
            var myfileintent = Intent(Intent.ACTION_GET_CONTENT)
            myfileintent.setType("image/*")
            ActivityResultLauncher.launch(myfileintent)
        }
        
        binding.btnEditprofie.setOnClickListener(){
            add_Data()
        }
        
        return root
    }

    private fun add_Data() {
        val shopName = binding.shopname.text.toString()
        val shopEmail = binding.shopemail.text.toString()
        val shopNumber = binding.shopNumber.text.toString()
        val shopPassword = binding.shopPassword.text.toString()
        
        db = FirebaseDatabase.getInstance().getReference("Shops")
        val shop = shopDC(shopName, shopImage, shopEmail, shopNumber, shopPassword)
        val databaseReference = FirebaseDatabase.getInstance().reference
        val id = databaseReference.push().key
        db.child(id.toString()).setValue(shop).addOnSuccessListener {
            binding.shopname.text.clear()
            shopImage = " "
            binding.shopemail.text.clear()
            binding.shopNumber.text.clear()
            binding.shopPassword.text.clear()

            binding.profileImage.setImageBitmap(null)
            Toast.makeText(context,"Data Inserted!", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private val ActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result:ActivityResult ->
        if(result.resultCode== AppCompatActivity.RESULT_OK){
            val uri = result.data!!.data
            try{
                val inputStream = context?.contentResolver?.openInputStream(uri!!)
                val myBitmap = BitmapFactory.decodeStream(inputStream)
                val stream = ByteArrayOutputStream()
                shopImage = Base64.encodeToString(bytes, Base.DEFAULT)
                binding.profileImage.setImageBitmap(myBitmap)
                inputStream!!.close()
                Toast.makeText(context, "Image Selected", Toast.LENGTH_SHORT).show()
            }catch (ex: Exception){
                Toast.makeText(context, ex.message.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

  
}