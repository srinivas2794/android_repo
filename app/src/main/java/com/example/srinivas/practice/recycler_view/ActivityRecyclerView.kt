package com.example.srinivas.practice.recycler_view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.srinivas.practice.R
import kotlinx.android.synthetic.main.activity_recycler_view.*


class ActivityRecyclerView : AppCompatActivity() {
    private var mLinearLayoutManager = LinearLayoutManager(this)
    private var mRecyclerAdapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        recycler_view.layoutManager = mLinearLayoutManager
        recycler_view.adapter = mRecyclerAdapter

        //mocking api. Will be replaced with actual content
        val thread = Thread {
            (1..100).forEach {
                Thread.sleep(100)
                runOnUiThread {
                    mRecyclerAdapter.add("$it${if (it % 10 == 1) "st" else if (it % 10 == 2) "nd" else if (it % 10 == 3) "rd" else "th"} material")
                    mRecyclerAdapter.notifyItemRangeInserted(mRecyclerAdapter.size - 1, 1)
                }
            }
        }
        thread.start()
    }
}

private class RecyclerAdapter(data: MutableList<String> = ArrayList()) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>(), MutableList<String> by data {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val item_recycler_view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_view, parent, false)
        return RecyclerViewHolder(item_recycler_view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Log.e("bind view holder called", position.toString())
        holder.bindView(this[position])
    }

    override fun getItemCount(): Int {
        return size
    }


    class RecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.recycler_view_item_text) as TextView

        fun bindView(textToDisplay: String): Unit {
            textView.text = textToDisplay
            view.setOnClickListener { Toast.makeText(view.context, textToDisplay, Toast.LENGTH_LONG).show() }
        }

    }

}