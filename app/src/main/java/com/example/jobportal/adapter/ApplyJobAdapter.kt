package com.example.jobportal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobportal.R
import com.example.jobportal.models.jobs

    class ApplyJobAdapter(private val mList: List<jobs>) : RecyclerView.Adapter<ApplyJobAdapter.ViewHolder>() {

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

            val jobModel = mList[position]

            // sets the image to the imageview from our itemHolder class
            // sets the text to the textview from our itemHolder class
            holder.designation.text = jobModel.designation
            holder.description.text = jobModel.description
            holder.location.text = jobModel.location

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