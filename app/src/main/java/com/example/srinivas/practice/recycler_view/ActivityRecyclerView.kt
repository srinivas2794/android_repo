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
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.srinivas.practice.R
import kotlinx.android.synthetic.main.activity_recycler_view.*
import org.json.JSONObject


class ActivityRecyclerView : AppCompatActivity() {
    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var mRecyclerAdapter: RecyclerAdapter? = null
    private var mRequestQueue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        mLinearLayoutManager = LinearLayoutManager(this)
        mRecyclerAdapter = RecyclerAdapter()

        recycler_view.layoutManager = mLinearLayoutManager
        recycler_view.adapter = mRecyclerAdapter
        mRequestQueue = Volley.newRequestQueue(this)


        //mocking api. Will be replaced with actual api
        var stringRequest = JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/321612/similar?api_key=150d4acea63bc73968317383bfeacb23&language=en-US&page=1", null,
                {
                    var results = it.getJSONArray("results")
                    var movieNames = ArrayList<String>()
                    for (i in 0..results.length() - 1) {
                        var movie = (results.get(i) as JSONObject)
                        movie?.let {
                            movieNames.add(movie.getString("title"))
                        }
                    }
                    mRecyclerAdapter?.addItems(movieNames)
                },
                {
                    Log.e("fail", it.toString())
                })

        mRequestQueue?.add(stringRequest)

    }

    override fun onStop() {
        mRequestQueue?.cancelAll(this)
        super.onStop()
    }
}

private class RecyclerAdapter(val data: MutableList<String> = ArrayList()) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val item_recycler_view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_view, parent, false)
        return RecyclerViewHolder(item_recycler_view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItem(item: String) {
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

    fun addItems(items: Array<String>) {
        data.addAll(items)
        notifyItemRangeInserted(data.size - 1, items.size)
    }

    fun addItems(items: List<String>) {
        data.addAll(items)
        notifyItemRangeInserted(data.size - 1, items.size)
    }

    class RecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.recycler_view_item_text) as TextView

        fun bindView(textToDisplay: String): Unit {
            textView.text = textToDisplay
            view.setOnClickListener { Toast.makeText(view.context, textToDisplay, Toast.LENGTH_LONG).show() }
        }

    }

}