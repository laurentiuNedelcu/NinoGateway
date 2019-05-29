package com.example.ninosproject.Activities.MultiPlayerActivities

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import com.example.ninosproject.R

class MyAdapter(var servers:ArrayList<String>, val context: Context) : RecyclerView.Adapter<MyHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_list_background, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var serverName: String = servers.get(position)
        holder?.server.text = serverName
    }

    override fun getItemCount(): Int {
        return servers.size
    }

}