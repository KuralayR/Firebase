package com.example.recyclerview

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class DBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, DATABASE_NAME, factory, 1) {
    companion object{
        private val DATABASE_NAME = "DatabaseExample.db"

        val TABLE_NAME = "NewExample"
        val ID = "exampleid"
        val NAME = "examplename"
        val YEAR = "exampleyear"
    }

    override fun onCreate(db: SQLiteDatabase?) {
//        val CREATE_TABLE = ("CREATE TABLE $TABLE_NAME (" +
//                "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "$NAME TEXT")
//        db?.execSQL(CREATE_TABLE)
        db!!.execSQL("CREATE TABLE $TABLE_NAME ($ID  INTEGER PRIMARY KEY, $NAME TEXT, $YEAR INT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun getCustomers (mCtx : Context) : ArrayList<Example>{
        val qry = "Select * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val customers = ArrayList<Example>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "No records found", Toast.LENGTH_SHORT).show() else
        {while (cursor.moveToNext()){
            val customer = Example()
            customer.id = cursor.getString(cursor.getColumnIndex(ID))
            customer.name = cursor.getString(cursor.getColumnIndex(NAME))
            customer.year = cursor.getInt(cursor.getColumnIndex(YEAR))
            customers.add(customer)
        }
            Toast.makeText(mCtx, "${cursor.count.toString()} Record found", Toast.LENGTH_SHORT).show()
    }
        cursor.close()
        db.close()
        return customers
}
    fun addCustomer(mCtx: Context, example: Example){
        val values = ContentValues()
        values.put(ID, example.id )
        values.put(NAME, example.name)
        values.put(YEAR, example.year)
        val db = this.writableDatabase
        try {
            db.insert(TABLE_NAME, null, values)
            Toast.makeText(mCtx, "Item Added", Toast.LENGTH_SHORT).show()
        } catch (e:Exception){
            Toast.makeText(mCtx, e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun deleteCustomer(customerID: Int) : Boolean {
        val qry = "Delete From $TABLE_NAME where $ID = $customerID"
        val db = this.writableDatabase
        var result = false
        try {
//            val cursor = db.delete(TABLE_NAME, "$ID = ?", arrayOf(customerID.toString()))
            val cursor = db.execSQL(qry)
            result = true
        } catch (e: Exception){
            Log.e(ContentValues.TAG, "Error deleting")
        }
        db.close()
        return result
    }


}