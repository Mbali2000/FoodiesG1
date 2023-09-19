package com.example.foodiesg1.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodiesg1.databinding.EachStoreBinding
import com.example.foodiesg1.utils.model.StoreData

class StoreAdapter(private val list: MutableList<StoreData>) :
    RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    private var listener:StoreAdapterClicksInterface? = null
    fun setListener(listener:StoreAdapterClicksInterface){
        this.listener = listener
    }

    class StoreViewHolder(val binding: EachStoreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = EachStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        with(holder){
            with(list[position]){

                binding.storename.text = this.storeName
                binding.storecuisine.text = this.storeCuisine

                binding.storetime.text = this.storeTime

                binding.deletestore.setOnClickListener{
                    listener?.onDeleteStoreClicked(this)
                }

                binding.editstore.setOnClickListener{
                    listener?.onUpdateStoreClicked(this)
                }
            }
        }
    }

    interface StoreAdapterClicksInterface{
        fun onDeleteStoreClicked(storeData: StoreData)
        fun onUpdateStoreClicked(storeData: StoreData)
    }
}