package edu.co.icesi.flatty.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.model.FavouritePerson
import java.util.ArrayList

class FavouritePersonAdapter: RecyclerView.Adapter<FavouritePersonView>() {

    var favouritePersonsList = ArrayList<FavouritePerson>()

    init {
        favouritePersonsList.add(FavouritePerson("Julian Arias","NvnSBVHkAjaa6Y66kx73", "1.002.777.650"))
        favouritePersonsList.add(FavouritePerson("Andy Rose", " sXUAg5FZQpwJQFRQzH5K", "5.080.907.600"))
        favouritePersonsList.add(FavouritePerson("Naty Lay", "DvUKJUtbEehGVHqxsUrhe0LnFrk2", "2.010.007.050"))
    }

    fun addFavouritePerson(newPerson: FavouritePerson){
        favouritePersonsList.add(newPerson)
        notifyItemInserted(favouritePersonsList.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritePersonView {

        var inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.favourite_person_item, parent, false)
        val itemView = FavouritePersonView(item)
        return itemView
    }

    override fun onBindViewHolder(skeleton: FavouritePersonView, position: Int) {
        val favouritePerson = favouritePersonsList[position]
        skeleton.personNameTV.text = favouritePerson.name
        skeleton.idDocumentTV.text = favouritePerson.number
    }

    override fun getItemCount(): Int {
        return favouritePersonsList.size
    }

}