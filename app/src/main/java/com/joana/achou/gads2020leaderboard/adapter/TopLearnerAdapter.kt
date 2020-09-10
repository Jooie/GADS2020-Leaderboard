package com.joana.achou.gads2020leaderboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joana.achou.gads2020leaderboard.R
import com.joana.achou.gads2020leaderboard.models.TopLearnerModel

class TopLearnerAdapter(val context: Context): RecyclerView.Adapter<TopLearnerAdapter.ViewHolder>() {
    var list = mutableListOf<TopLearnerModel>()

    class ViewHolder (v: View): RecyclerView.ViewHolder(v){
        val badge: ImageView = v.findViewById<ImageView>(R.id.imageViewBadge)
        val name: TextView = v.findViewById<TextView>(R.id.textViewName)
        val extras: TextView = v.findViewById<TextView>(R.id.textViewExtras)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.badge.setImageResource(R.drawable.top_learner)
        holder.name.text = list[position].name
        holder.extras.text = "${list[position].hours} learning hours, ${list[position].country}"


    }


    fun loadList(list: List<TopLearnerModel>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }
}