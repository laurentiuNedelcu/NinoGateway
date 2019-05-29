package com.example.ninosproject.Activities.MultiPlayerActivities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ListView
import com.example.ninosproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_server_selector.*
import java.util.ArrayList

class ServerActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private lateinit var dataBase: FirebaseDatabase

    private val servers = ArrayList<String>()
    private var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        dataBase = FirebaseDatabase.getInstance()
        dbReference = dataBase.reference.child("Servers")

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_server_selector)

        val list_lvl : ListView = findViewById(R.id.server_selector)
        val button_back : Button = findViewById(R.id.button_back)
        val serverCreate: Button = findViewById((R.id.button_create_server))

        adapter = MyAdapter(servers, this)

        var listView: RecyclerView = findViewById(R.id.server_selector)
        listView.adapter = adapter
        listView.setLayoutManager(LinearLayoutManager(this))

        dbReference.addValueEventListener(
            object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        servers.clear()
                        for (server in dataSnapshot.children) {
                            val serverName = server.key.toString()
                            var open: Int = server.getValue().toString().toInt()
                            if(open == 1){
                                servers.add(serverName)
                            }
                        }
                        adapter?.notifyDataSetChanged()
                    }
                }
            }
        )
        button_back.setOnClickListener {
            finish()
        }
        button_create_server.setOnClickListener {
            FirebaseDatabase.getInstance().reference.child("Servers")
                .child("testServer")
                .setValue(1)
        }
    }
}