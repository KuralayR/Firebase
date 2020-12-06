package com.example.recyclerview

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception

class DBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, DATABASE_NAME, factory, 1) {
    companion object{
        private val DATABASE_NAME = "MyData.db"

        val TABLE_NAME = "Customers"
        val ID = "customerid"
        val NAME = "customername"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE $TABLE_NAME (" +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NAME TEXT")
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun getCustomers (mCtx : Context) : ArrayList<Customer>{
        val qry = "Select * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val customers = ArrayList<Customer>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "No records found", Toast.LENGTH_SHORT).show() else
        {while (cursor.moveToNext()){
            val customer = Customer()
            customer.id = cursor.getInt(cursor.getColumnIndex(ID))
            customer.name = cursor.getString(cursor.getColumnIndex(NAME))
            customers.add(customer)
        }
            Toast.makeText(mCtx, "${cursor.count.toString()} Record found", Toast.LENGTH_SHORT).show()
    }
        cursor.close()
        db.close()
        return customers
}
    fun addCustomer(mCtx: Context, customer: Customer){
        val values = ContentValues()
        values.put(NAME, customer.name)
        val db = this.writableDatabase
        try {
            db.insert(TABLE_NAME, null, values)
            Toast.makeText(mCtx, "Customer Added", Toast.LENGTH_SHORT).show()
        } catch (e:Exception){
            Toast.makeText(mCtx, e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }
}