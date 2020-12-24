package com.example.recyclerview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.*
import android.widget.Toast
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
        btnSave.setOnClickListener{
            if (editTextTextPersonName2.text.isEmpty()){
                Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show()
                editTextTextPersonName2.requestFocus()
            } else {
                val customer = Item()
                customer.name = editTextTextPersonName2.text.toString()
                MainActivity.dbHandler.addCustomer(this, customer)
                clearEdit()
                editTextTextPersonName2.requestFocus()
            }
            viewCustomers()

        }

    }

    private fun viewCustomers(){
        val customerslist = dbHandler.getCustomers(this)
        val adapter = CustomerAdapter(this, customerslist)
        val rv : RecyclerView = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
        rv.adapter = adapter

    }

    override fun onResume() {
        viewCustomers()
        super.onResume()
    }
    private fun clearEdit(){
        editTextTextPersonName2.text.clear()
    }

}