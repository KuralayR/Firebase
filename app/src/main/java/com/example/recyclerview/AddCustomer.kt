package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_customer.*

class AddCustomer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer)

        btnSave.setOnClickListener{
            if (editTextTextPersonName.text.isEmpty()){
                Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show()
                editTextTextPersonName.requestFocus()
            } else {
                val customer = Customer()
                customer.name = editTextTextPersonName.text.toString()
                MainActivity.dbHandler.addCustomer(this, customer)
                clearEdit()
                editTextTextPersonName.requestFocus()
            }

        }

        btnCancel.setOnClickListener{
            clearEdit()
            finish()

        }

    }

    private fun clearEdit(){
        editTextTextPersonName.text.clear()
    }

}