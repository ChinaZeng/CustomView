package com.zzw.customview.acitvity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.zzw.customview.R
import com.zzw.customview.adapter.SimpleRecyclerViewAdapter

import kotlinx.android.synthetic.main.activity_vertical_drag_list_view.*

class VerticalDragListViewActivity : AppCompatActivity() {

    var datas: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_drag_list_view)
        recyclerview.layoutManager = LinearLayoutManager(this)
        for (x in 0..20)
            datas.add("测试数据" + x)
        recyclerview.adapter = SimpleRecyclerViewAdapter(this, datas)
    }
}
