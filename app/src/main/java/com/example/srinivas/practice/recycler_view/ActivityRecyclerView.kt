package com.example.srinivas.practice.recycler_view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
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