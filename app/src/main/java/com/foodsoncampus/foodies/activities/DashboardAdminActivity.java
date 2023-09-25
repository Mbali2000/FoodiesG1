package com.foodsoncampus.foodies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.foodsoncampus.foodies.adapters.AdapterRestaurant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.foodsoncampus.foodies.databinding.ActivityDashboardAdminBinding;
import com.foodsoncampus.foodies.models.ModelCategory;

import java.util.ArrayList;

public class DashboardAdminActivity extends AppCompatActivity {

    //view binding
    private ActivityDashboardAdminBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    //arraylist to store category
    private ArrayList<ModelCategory> categoryArrayList;
    //adapter
    private AdapterRestaurant adapterRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();
        loadCategories();


        //edit text change listern, search
//        binding.searchEt.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //called as and when user type each letter
//                try {
//                    adapterRestaurant.getFilter().filter(s);
//                }
//                catch (Exception e){
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        //handle click, logout
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkUser();
            }
        });

        //handle click, start restaurant add screen
        binding.addRestaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardAdminActivity.this, RestaurantAddActivity.class));
            }
        });

        //handle click, start pdf add screen
        binding.addPdfFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardAdminActivity.this, PdfAddActivity.class));
            }
        });

        //handle click, open profile
        binding.profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardAdminActivity.this, ProfileActivity.class));
            }
        });


    }

    private void loadCategories() {
        //init araylist
        categoryArrayList = new ArrayList<>();

        //get all categories from firebase > Categories
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Restaurants");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //clear arraylist before adding data into it
                categoryArrayList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    //get data
                    ModelCategory model = ds.getValue(ModelCategory.class);

                    //add to arraylist
                    categoryArrayList.add(model);
                }
                //setup adapter
                adapterRestaurant = new AdapterRestaurant(DashboardAdminActivity.this, categoryArrayList);
                //set adapter to recyclerview
                binding.categoriesRv.setAdapter(adapterRestaurant);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkUser() {
        //get current user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser==null){
            //not logged in, goto main screen
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        else {
            //logged in, get user info
            String email = firebaseUser.getEmail();
            //set in textview of toolbar
            binding.subTitleTv.setText(email);
        }
    }
}