package com.example.jobportal.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobportal.R
import com.example.jobportal.models.Response.JobApply
import com.example.jobportal.utils.Constants.TAG

class ApplyJobAdapter(private val mList: List<JobApply>) : RecyclerView.Adapter<ApplyJobAdapter.ViewHolder>() {

        // create new views
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // inflates the card_view_design view
            // that is used to hold list item
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.applications, parent, false)

            return ViewHolder(view)
        }

        // binds the list items to a view
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val job = mList[position]

            Log.d(TAG,"List starts $position :"+mList[position])
            holder.designation.text = job.title
            holder.description.text = job.description
            holder.location.text = job.location

        }

        // return the number of the items in the list
        override fun getItemCount(): Int {
            return mList.size
        }

        // Holds the views for adding it to image and text
        class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
            val location: TextView = itemView.findViewById(R.id.cityTv)
            val designation: TextView = itemView.findViewById(R.id.designationTv)
            val description: TextView = itemView.findViewById(R.id.descriptionTv)

        }
    }