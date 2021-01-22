package com.example.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var dbHandler: DBHandler

    }
    lateinit var ref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHandler = DBHandler(this, null, null, 1)

//      btnSave.setOnClickListener {
//            saveReview()
//
//        }



        viewCustomers()
        btnSave.setOnClickListener{

            if (editTextTextPersonName2.text.isEmpty()){
                Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show()
                editTextTextPersonName2.requestFocus()
            } else {
                val customer = Example()
                customer.name = editTextTextPersonName2.text.toString()
                customer.year = editYear.text.toString().toInt()
                MainActivity.dbHandler.addCustomer(this, customer)
                val ref = FirebaseDatabase.getInstance().reference
                val reviewId = ref.push().key
                val review = Example(reviewId, customer.name, customer.year)
                ref.child(reviewId!!).setValue(review).addOnCompleteListener {
                    Toast.makeText(applicationContext, "Review successfully added",Toast.LENGTH_SHORT).show()
                }
                clearEdit()
                editTextTextPersonName2.requestFocus()
            }
            viewCustomers()

        }

    }

//    private fun saveReview() {
//        val name = editTextTextPersonName2.text.toString()
//        val year = editYear.text.toString().toInt()
//        val ref = FirebaseDatabase.getInstance().reference
//
//        val reviewId = ref.push().key
//        val review = Example(reviewId, name, year)
//
//        ref.child(reviewId!!).setValue(review).addOnCompleteListener {
//            Toast.makeText(applicationContext, "Review successfully added",Toast.LENGTH_SHORT).show()
//        }
//    }

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