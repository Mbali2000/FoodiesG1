package com.example.foodiesg1.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodiesg1.R
import com.example.foodiesg1.databinding.FragmentHomeBinding
import com.example.foodiesg1.databinding.FragmentProfileBinding
import com.example.foodiesg1.shopAdapter
import com.example.foodiesg1.shopDC
import com.example.foodiesg1.tempClass
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {
    private lateinit var db: DatabaseReference
    private lateinit var shopArrayList: ArrayList<shopDC>
    private lateinit var nodeList: ArrayList<tempClass>
    private var d1: Long = 0
    private var d2: Long = 0



    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.itemList.layoutManager=LineaLayoutManager(context)
        binding..hasFixedSize()
        shopArrayList = arrayListOf<shopDC>()

        nodeList = arrayListOf<tempClass>()
        getShopData()
        // Inflate the layout for this fragment
        return binding.root

    }

    private fun getShopData() {
        db = FirebaseDatabase.getInstance().getReference("shops")
        var query: Query
        query = db.orderByChild("shopName")
        query.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    var ky : String = ""
                    var shpnm : String = ""
                    for(shpsnapshot in snapshot.children){
                        val shop = shpsnapshot.getValue(shopDC::class.java)
                        shopArrayList.add(shop!!)
                        ky = shpsnapshot.key.toString()
                        shpnm= shop.shopName.toString()
                        val tmpshop = tempClass(ky, shpnm)
                        nodeList.add(tmpshop)



                    }
                    var adapter = shopAdapter(shopArrayList)
                    //binding.shplst.adapter = adapter
                    adapter.setOnItemClickListener(object : shopAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val ctshp = nodeList[position]
                            val nodePath = ctshp.id.toString()
                            val fragment = ProfileFragment()
                            val bundle = Bundle()
                            bundle.putString("shop_id", nodePath.toString())
                            fragment.arguments = bundle
                            val fragmentManager = activity?.supportFragmentManager
                            val fragmentTransaction = fragmentManager!!.beginTransaction()
                            fragmentTransaction.replace(com.example.foodiesg1.R.id.frameLayout, fragment).commit()
                        }

                    })

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}