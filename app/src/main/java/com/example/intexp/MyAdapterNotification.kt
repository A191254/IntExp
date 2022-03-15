package com.example.intexp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapterNotification(private val detailslist: ArrayList<NotificationDetails>): RecyclerView.Adapter<MyAdapterNotification.MyViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_notification
            ,parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentdetail =  detailslist[position]

        holder.showCompanyName.text = currentdetail.CompanyName.toString()
        holder.showOpportunity.text = currentdetail.Opportunity.toString()
        holder.showStipend.text = currentdetail.Stipend.toString()
        holder.showEligiblity.text = "Eligiblity - "+ currentdetail.Eligiblity.toString()
        holder.showJobProfile.text = "Job Profile - "+currentdetail.JobProfile.toString()
        holder.showDate.text = "Date - "+currentdetail.Date.toString()

    }

    override fun getItemCount(): Int {
        return detailslist.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val showCompanyName : TextView = itemView.findViewById(R.id.INtxtCompanyName)
        val showOpportunity : TextView = itemView.findViewById(R.id.INtxtOpportunity)
        val showStipend : TextView = itemView.findViewById(R.id.INtxtStipend)
        val showEligiblity : TextView = itemView.findViewById(R.id.INtxtEligiblity)
        val showJobProfile : TextView = itemView.findViewById(R.id.INtxtJobProfile)
        val showDate : TextView = itemView.findViewById(R.id.INtxtDate)

    }
}