package com.example.recyclerview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.LinearLayout.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.LinearLayout.VERTICAL as LinearLayoutVERTICAL

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var dbHandler: DBHandler
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHandler = DBHandler(this, null, null, 1)

        viewCustomers()

        fab.setOnClickListener{
            val i = Intent(this,AddCustomer::class.java)
            startActivity(i)

        }
    }

    @SuppressLint("WrongConstant")
    private fun viewCustomers(){
        val customerslist = dbHandler.getCustomers(this)
        val adapter = CustomerAdapter(this, customerslist)
        val rv : RecyclerView = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutVERTICAL, false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }

    override fun onResume() {
        viewCustomers()
        super.onResume()
    }
}