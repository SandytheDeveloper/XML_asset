package com.example.xml_asset

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

class MainActivity : AppCompatActivity() {
    var i=0
    var name:ArrayList<String> = ArrayList()
    var price:ArrayList<String> = ArrayList()
    var des:ArrayList<String> = ArrayList()
    var cal:ArrayList<String> = ArrayList()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView=findViewById<RecyclerView>(R.id.rv)
        val LinearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = LinearLayoutManager

        try {
            val lv = findViewById<RecyclerView>(R.id.rv)
            val istream = assets.open("breakfast_menu.xml")
            val bf = DocumentBuilderFactory.newInstance()
            val db = bf.newDocumentBuilder()
            val doc = db.parse(istream)
            val nlist = doc.getElementsByTagName("food")


            for (i in 0 until nlist.length){

                val element = nlist.item(i) as Element

                    name.add(getNodeValue("name",element))
                    price.add(getNodeValue("price",element))
                    des.add(getNodeValue("description",element))
                    cal.add(getNodeValue("calories",element))
            }

            val customAdapter = MyAdapter(this@MainActivity,name,price,des,cal)
            recyclerView.adapter = customAdapter

        }catch (e: IOException){
            e.printStackTrace()
        }catch (e: ParserConfigurationException){
            e.printStackTrace()
        }

    }

    fun getNodeValue(tag: String,element: Element): String{
        val nl = element.getElementsByTagName(tag)
        val node = nl.item(0)
        if (node!=null){
            if (node.hasChildNodes()){
                val child = node.getFirstChild()
                while (child!=null){
                    if (child.getNodeType() === Node.TEXT_NODE){
                        return child.getNodeValue()
                    }
                }
            }
        }
        return ""
    }

}