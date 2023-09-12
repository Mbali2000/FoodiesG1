package com.example.foodiesg1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class ShopAdapter(private val shopList : ArrayList<Shop>) : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.overview_card, parent,false )

        return ShopViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return shopList.size
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {

        val currentItem = shopList[position]

        holder.name.text = currentItem.Name
    }

    class ShopViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.shop_name)

    }
}