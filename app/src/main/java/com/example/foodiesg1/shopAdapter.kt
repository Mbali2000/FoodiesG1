package com.example.foodiesg1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class shopAdapter(private val shplst:ArrayList<shopDC>): RecyclerView.Adapter<shopAdapter.shpHolder>() {

    private lateinit var mListener:OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener =listener
    }

    class shpHolder(shpView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(shpView){
        val shpname: TextView =shpView.findViewById(R.id.shopname)
        val shpemail: TextView = shpView.findViewById(R.id.shopemail)
        val shpnumber: TextView = shpView.findViewById(R.id.shop_number)
        val shppassword: TextView = shpView.findViewById(R.id.shop_password)
        val shpimage: ImageView = shpView.findViewById(R.id.profile_image)


        init {
            shpView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): shpHolder {
        val shopView = LayoutInflater.from(parent.context).inflate(R.layout.shop_card, parent, false)
        return shpHolder(shopView, mListener)
    }

    override fun getItemCount(): Int {
        return shplst.size
    }

    override fun onBindViewHolder(holder: shpHolder, position: Int) {
        val currentItem = shplst[position]
        holder.shpname.text = currentItem.shopName.toString()
        holder.shpemail.text = currentItem.shopEmail.toString()
        holder.shpnumber.text = currentItem.shopNumber.toString()
        holder.shppassword.text = currentItem.shopPassword.toString()
        val bytes = android.util.Base64.decode(currentItem.shopImage, android.util.Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        holder.shpimage.setImageBitmap(bitmap)

    }
}