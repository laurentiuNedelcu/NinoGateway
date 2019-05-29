package com.example.ninosproject.Activities.MultiPlayerActivities

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_server_selector.view.*
import kotlinx.android.synthetic.main.item_list_background.view.*

class MyHolder(view:View): RecyclerView.ViewHolder(view) {
    var server = view.listText
    init {

        server.setOnClickListener{
            val db = FirebaseDatabase.getInstance().reference
            db.child("Servers").child(server.text.toString()).setValue(0)

        }
    }
}
