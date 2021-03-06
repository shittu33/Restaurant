package com.example.testapi

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*
import org.abdulmujeeb.application.R
import org.abdulmujeeb.application.api.Restaurant

class RestaurantAdapter(
    private val restaurants: ArrayList<Restaurant>,
    private val onItemClick: (restaurant: Restaurant) -> Unit
) :
    RecyclerView.Adapter<RestaurantAdapter.DVHolder>() {
    class DVHolder(iv: View) :
        RecyclerView.ViewHolder(iv) {
        fun bind(
            restaurant: Restaurant,
            onItemClick: (restaurant: Restaurant) -> Unit
        ) {

            itemView.apply {
                this.findViewById<TextView>(R.id.title).text = restaurant.name
                this.findViewById<TextView>(R.id.rate).text = restaurant.rating.toString()
                this.findViewById<TextView>(R.id.type).text = restaurant.plus_code.compound_code
                this.findViewById<TextView>(R.id.price).text = "$"
                this.findViewById<TextView>(R.id.price)
                    .append(restaurant.price_level?.toString() ?: "2")
                this.findViewById<TextView>(R.id.status).text = restaurant.business_status
                this.findViewById<TextView>(R.id.vicinity).text = restaurant.vicinity
                Glide.with(context).load(Uri.parse(restaurant.icon)).into(icon);
                itemView.setOnClickListener {
                    onItemClick(restaurant)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DVHolder =
        DVHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )

    override fun getItemCount(): Int = restaurants.size

    override fun onBindViewHolder(holder: DVHolder, position: Int) {
        holder.bind(
            restaurants[position],
            onItemClick = this.onItemClick
        )
    }

    fun addRestaurants(restaurants: List<Restaurant>) {
        this.restaurants.apply {
            clear()
            addAll(restaurants)
        }
    }

}