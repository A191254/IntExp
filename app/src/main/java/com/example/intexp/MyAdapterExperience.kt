package com.example.intexp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapterExperience(private val detailslist: ArrayList<ExperienceDetails>): RecyclerView.Adapter<MyAdapterExperience.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_experience,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentdetail =  detailslist[position]

        holder.showCompanyName.text = "Company Name :"+currentdetail.Company.toString()
        holder.showStipend.text = "Stipend :"+currentdetail.Stipend.toString()
        holder.showType.text = "Opportunity :"+currentdetail.Opportunity.toString()
        holder.showRounds.text = "Rounds :"+currentdetail.Rounds.toString()
        holder.showExperience.text = currentdetail.Experience.toString()
        holder.showAuthor.text = "~"+currentdetail.ContributorName.toString()
        holder.showTips.text= "Some Tips - \n :"+currentdetail.Tips.toString()
    }

    override fun getItemCount(): Int {
        return detailslist.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val showCompanyName : TextView = itemView.findViewById(R.id.IEtxtCompanyName)
        val showStipend : TextView = itemView.findViewById(R.id.IEtxtStipend)
        val showType : TextView = itemView.findViewById(R.id.IEtxtType)
        val showRounds : TextView = itemView.findViewById(R.id.IEtxtRounds)
        val showExperience : TextView = itemView.findViewById(R.id.IEtxtExperience)
        val showAuthor : TextView = itemView.findViewById(R.id.IEtxtAuthor)
        val showTips : TextView = itemView.findViewById(R.id.IEtxtTips)

    }


}