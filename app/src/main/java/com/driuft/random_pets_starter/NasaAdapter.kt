package com.driuft.random_pets_starter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NasaAdapter (private val nasaList: List<String>): RecyclerView.Adapter<NasaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nasaImage: ImageView

        init {
            // Find our RecyclerView item's ImageView for future use
            nasaImage = view.findViewById(R.id.nasa_image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nasa_item, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nasaImage.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Nasa picture at position $position clicked", Toast.LENGTH_SHORT).show()
        }
        Glide.with(holder.itemView)
            .load(nasaList[position])
            .centerCrop()
            .into(holder.nasaImage)
    }

        override fun getItemCount() = nasaList.size
    }

