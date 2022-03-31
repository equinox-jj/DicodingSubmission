package com.dicodingsubmission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.dicodingsubmission.databinding.ItemListHomeActivityBinding
import com.dicodingsubmission.model.GamesDescData

class ItemListHomeAdapter(val gameList: ArrayList<GamesDescData>) :
    RecyclerView.Adapter<ItemListHomeAdapter.ItemListHomeViewHolder>() {

    // SETUP ONCLICK LISTENER
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ItemListHomeViewHolder(private val binding: ItemListHomeActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(desc: GamesDescData) {
            desc.let {
                binding.tvTitle.text = desc.name
                binding.tvDesc.text = desc.desc
                binding.imageView.load(desc.image) { transformations(CircleCropTransformation()) }
            }
        }

        companion object { // BINDING THE LAYOUT ITEMLISTHOMEACTIVITY
            fun from(parent: ViewGroup): ItemListHomeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListHomeActivityBinding.inflate(layoutInflater, parent, false)
                return ItemListHomeViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListHomeViewHolder {
        return ItemListHomeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemListHomeViewHolder, position: Int) {
        val currentGameList = gameList[position]
        holder.bind(currentGameList)
        // ITEM VIEW ONCLICK
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(gameList[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    /** ON CLICK CALLBACK */
    fun setOnItemClickCallback(
        onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClick(desc: GamesDescData)
    }
    /** ON CLICK CALLBACK */

}