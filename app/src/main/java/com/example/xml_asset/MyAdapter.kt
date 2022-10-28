package com.example.xml_asset

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var con:Context,var name:ArrayList<String>,var price:ArrayList<String>
,var des:ArrayList<String>,var cal:ArrayList<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    inner class MyViewHolder(iv: View) : RecyclerView.ViewHolder(iv){
        var name : TextView=iv.findViewById(R.id.name) as TextView
        var price : TextView=iv.findViewById(R.id.price) as TextView
        var des : TextView=iv.findViewById(R.id.description) as TextView
        var cal : TextView=iv.findViewById(R.id.calories) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.itemlist,parent,false)

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text=name[position]
        holder.price.text=price[position]
        holder.des.text=des[position]
        holder.cal.text=cal[position]

        holder.itemView.setOnClickListener {
            Toast.makeText(con, name[position], Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return name.size
    }
}