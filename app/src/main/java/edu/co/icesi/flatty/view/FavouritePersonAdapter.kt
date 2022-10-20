package edu.co.icesi.flatty.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.flatty.R
import edu.co.icesi.flatty.model.FavouritePerson
import java.util.ArrayList

class FavouritePersonAdapter: RecyclerView.Adapter<FavouritePersonView>() {

    private val favouritePersonsList = ArrayList<FavouritePerson>()

    init {
        favouritePersonsList.add(FavouritePerson("Julian Arias", "1.002.777.650"))
        favouritePersonsList.add(FavouritePerson("Andy Rose", "5.080.907.600"))
        favouritePersonsList.add(FavouritePerson("Naty Lay", "2.010.007.050"))
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
        skeleton.idDocumentTV.text = favouritePerson.id
    }

    override fun getItemCount(): Int {
        return favouritePersonsList.size
    }

}