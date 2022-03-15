package com.example.intexp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapterNews (private val listener: SchemeItemClicked) : RecyclerView.Adapter<MyAdapterNews.MyViewHolder>() {
    // View holder class whose objects represent each list item

    //private lateinit var binding: ListItemBinding
    val dataModelList: ArrayList<News1> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate out card list item
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_news,
            parent,
            false
        )

        val viewHolder = MyViewHolder(itemView)
        itemView.setOnClickListener{
            listener.onItemClicked(dataModelList[viewHolder.adapterPosition])
        }
        // Return a new view holder
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Bind data for the item at position
        holder.cardtitle.text =  dataModelList[position].title
        //holder.itemView.card_subtitle.text =  dataModelList[position].author
        holder.cardbody.text = dataModelList[position].content
        Glide.with(holder.itemView.context).load(dataModelList[position].imageUrl).into(holder.image)

    }

    fun updateNews(updatedNews: java.util.ArrayList<News1>) {
        dataModelList.clear()
        dataModelList.addAll(updatedNews)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        // Return the total number of items
        return dataModelList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val titleView: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
        val cardtitle : TextView = itemView.findViewById(R.id.card_title)
        val cardbody : TextView = itemView.findViewById(R.id.card_body)
        //val content: TextView = itemView.findViewById(R.id.content)
    }
}

interface SchemeItemClicked {
    fun onItemClicked(item: News1)
}

