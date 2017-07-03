package com.example.srinivas.practice.root

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.srinivas.practice.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class ActivityMain : AppCompatActivity() {

    internal var projectNames = arrayOf("Recycler View Example")
    internal var projectRoots = ArrayList<Class<out AppCompatActivity>>()

    init {
        projectRoots.add(this::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        home_list.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, projectNames)
        home_list.onItemClickListener = MyItemClickListener()
    }

    private inner class MyItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val i = Intent(this@ActivityMain, projectRoots[position])
            startActivity(i)
        }
    }
}
