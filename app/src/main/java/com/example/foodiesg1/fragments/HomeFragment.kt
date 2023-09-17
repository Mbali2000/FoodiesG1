package com.example.foodiesg1.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodiesg1.R
import com.example.foodiesg1.databinding.FragmentHomeBinding
import com.example.foodiesg1.utils.adapter.restaurantAdapter
import com.example.foodiesg1.utils.model.ToDoData
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment(), AddRestaurantDialogFragment.OnDialogNextBtnClickListener,
    restaurantAdapter.restaurantAdapterInterface {

    private val TAG = "HomeFragment"
    private lateinit var binding: FragmentHomeBinding
    private lateinit var database: DatabaseReference
    private var frag: AddRestaurantDialogFragment? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var authId: String
    private lateinit var navController: NavController

    private lateinit var restaurantAdapter: restaurantAdapter
    private lateinit var toDoItemList: MutableList<ToDoData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        //get data from firebase
        getrestaurantFromFirebase()


        binding.addrestaurantBtn.setOnClickListener {

            if (frag != null)
                childFragmentManager.beginTransaction().remove(frag!!).commit()
            frag = AddRestaurantDialogFragment()
            frag!!.setListener(this)

            frag!!.show(
                childFragmentManager,
                AddRestaurantDialogFragment.TAG
            )

        }

        binding.nextHomeBtn.setOnClickListener{
            navController.navigate(R.id.action_homeFragment_to_storesFragment)
        }
    }

    private fun getrestaurantFromFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                toDoItemList.clear()
                for (restaurantSnapshot in snapshot.children) {
                    val todorestaurant =
                        restaurantSnapshot.key?.let { ToDoData(it, restaurantSnapshot.value.toString()) }

                    if (todorestaurant != null) {
                        toDoItemList.add(todorestaurant)
                    }

                }
                Log.d(TAG, "onDataChange: " + toDoItemList)
                restaurantAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }


        })
    }

    private fun init(view: View) {

        auth = FirebaseAuth.getInstance()
        authId = auth.currentUser!!.uid
        database = Firebase.database.reference.child("Restaurants")
            .child(authId)


        binding.mainRecyclerView.setHasFixedSize(true)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)

        toDoItemList = mutableListOf()
        restaurantAdapter = restaurantAdapter(toDoItemList)
        restaurantAdapter.setListener(this)
        binding.mainRecyclerView.adapter = restaurantAdapter
        navController = Navigation.findNavController(view)
    }

    override fun saverestaurant(todorestaurant: String, todoEdit: TextInputEditText) {

        database
            .push().setValue(todorestaurant)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Restaurant Added Successfully", Toast.LENGTH_SHORT).show()
                    todoEdit.text = null

                } else {
                    Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        frag!!.dismiss()

    }

    override fun updaterestaurant(toDoData: ToDoData, todoEdit: TextInputEditText) {
        val map = HashMap<String, Any>()
        map[toDoData.restaurantId] = toDoData.restaurant
        database.updateChildren(map).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
            frag!!.dismiss()
        }
    }

    override fun onDeleteItemClicked(toDoData: ToDoData, position: Int) {
        database.child(toDoData.restaurantId).removeValue().addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onEditItemClicked(toDoData: ToDoData, position: Int) {
        if (frag != null)
            childFragmentManager.beginTransaction().remove(frag!!).commit()

        frag = AddRestaurantDialogFragment.newInstance(toDoData.restaurantId, toDoData.restaurant)
        frag!!.setListener(this)
        frag!!.show(
            childFragmentManager,
            AddRestaurantDialogFragment.TAG
        )
    }

}