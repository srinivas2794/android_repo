package com.example.srinivas.practice.recycler_view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.srinivas.practice.R
import kotlinx.android.synthetic.main.activity_recycler_view.*


class ActivityRecyclerView : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mLinearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLinearLayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = mLinearLayoutManager
    }
}

private class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val item_recycler_view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_view, parent, false)
        return RecyclerViewHolder(item_recycler_view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.home_list) as TextView

        init {
            view.setOnClickListener { Toast.makeText(view.context, "hello", Toast.LENGTH_LONG) }
        }

    }

}