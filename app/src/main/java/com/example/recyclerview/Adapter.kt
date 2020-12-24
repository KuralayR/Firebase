package com.example.recyclerview

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class CustomerAdapter(mCtx: Context, val customers: ArrayList<Item>) : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {
    val mCtx = mCtx

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.textView
        val btnDelete = itemView.btnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return customers.size
    }
//    var removedPosition : Int ? = null
//    fun getRemoveItemPosition() : Int? {
//        val position = removedPosition
//        return position
//    }

    override fun onBindViewHolder(holder: CustomerAdapter.ViewHolder, position: Int) {

        val item: Item = customers[position]
        holder.txtName.text = item.name
        holder.btnDelete.setOnClickListener {
            val customerName = item.name
            if (MainActivity.dbHandler.deleteCustomer(item.id)) {
                customers.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, customers.size)
                Toast.makeText(mCtx, "Item: $customerName Deleted", Toast.LENGTH_SHORT).show()

            }
            }
        }

//        holder.btnDelete.setOnClickListener {
//            customers.removeAt(position)
//            removedPosition = position
//            notifyDataSetChanged()
        }
