package com.example.foodiesg1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OverviewGrid : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var shopRecyclerView: RecyclerView
    private lateinit var  shopArrayList : ArrayList<Shop>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview_grid)

        //create layout for recycler items
        val recyclerView : RecyclerView = findViewById(R.id.shopList)
        val adaptor = ShopAdapter(fetchData())
        recyclerView.layoutManager = GridLayoutManager(this, 3)// number of tiles in row
        recyclerView.adapter = adaptor

        shopRecyclerView = findViewById(R.id.shopList)
        shopRecyclerView.layoutManager = LinearLayoutManager(this)
        shopRecyclerView.setHasFixedSize(true)

        shopArrayList = arrayListOf<Shop>()
        getUserData()
    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("Shop")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){

                    for(shopSnapshot in snapshot.children){

                        val shop = shopSnapshot.getValue(Shop::class.java)
                        shopArrayList.add(shop!!)

                    }

                    shopRecyclerView.adapter = ShopAdapter(shopArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun fetchData() : ArrayList<Shop>{
        val item = ArrayList<Shop>()
        for(i in 0  until 30){
            item.add("$i")
        }
        return item
    }
}