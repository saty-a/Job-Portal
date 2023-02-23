package com.example.jobportal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobportal.R
import com.example.jobportal.models.jobs

class AppliedJobsAdapter(private val mlist:List<jobs>):RecyclerView.Adapter<AppliedJobsAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.applicationwithoutbtn, parent, false)
    return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val jobModel = mlist[position]

        holder.designation.text = jobModel.designation
        holder.description.text = jobModel.description
        holder.location.text = jobModel.location

    }

    override fun getItemCount(): Int {
        return mlist.size
    }



    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val location: TextView = itemView.findViewById(R.id.cityTv)
        val designation: TextView = itemView.findViewById(R.id.designationTv)
        val description: TextView = itemView.findViewById(R.id.descriptionTv)
    }

}