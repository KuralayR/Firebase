package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import kotlin.jvm.java as java

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    lateinit var dataBase : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataBase = FirebaseDatabase.getInstance().getReference("Users")
        recyclerView = findViewById(R.id.rv)


    }

    private fun recyclerView(){
        var recyclerAdapter = object : FirebaseRecyclerAdapter<Profile, UserViewHolder>(
            Profile::class.java,
            R.layout.item,
            UserViewHolder::class.java,
            dataBase
        ){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
                TODO("Not yet implemented")
            }

            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: Profile) {
                TODO("Not yet implemented")
            }

        }


    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


    }
}