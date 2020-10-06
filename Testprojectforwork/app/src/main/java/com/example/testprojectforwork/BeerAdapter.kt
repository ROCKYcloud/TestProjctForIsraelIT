package com.example.testprojectforwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testprojectforwork.api.BeerResponse
import kotlinx.android.synthetic.main.item_beer.view.*

class BeerAdapter(private val beerAdapterListener: BeerAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface LoadMoreListener {
        fun loadMoreBeers()
    }

    private var list: MutableList<BeerResponse> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  InformationViewHolder(inflater.inflate(R.layout.item_beer, parent, false), beerAdapterListener)
        }

    fun refreshData(list: MutableList<BeerResponse>) {
        this.list = list
    }
    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: BeerResponse = list[position]
        when (holder) {
            is InformationViewHolder -> {
                holder.bind(model)
            }
        }
    }
}

class InformationViewHolder(view: View, private val beerAdapterListener: BeerAdapterListener) : RecyclerView.ViewHolder(view) {

    lateinit var item: BeerResponse

    init {
        view.setOnClickListener {
            beerAdapterListener.onBeerSelected(item, it)
        }
    }

    fun bind(result: BeerResponse) {
        this.item = result
        Glide.with(itemView.context).load(result.imageURL).into(itemView.ivBeer)
        itemView.tvName.text = result.name
        itemView.tvStart.text = result.firstBrewed
        itemView.tvDescriptopn.text = result.description
    }
}

interface BeerAdapterListener {
    fun onBeerSelected(beerResponse: BeerResponse?, view: View?)
}