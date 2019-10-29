package com.example.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.Adapter.MyAdapter
import com.example.menu.Common.Common
import com.example.menu.Common.SpaceItemDecoration
import com.example.menu.Model.MyModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MyAdapter
    lateinit var itemList: MutableList<MyModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        setData()
    }

    private fun setData() {
        adapter = MyAdapter(this ,itemList)

        val layoutManager = GridLayoutManager(this,Common.NUM_OF_COLUMN)
        layoutManager.orientation = RecyclerView.VERTICAL
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(i: Int): Int {
                return if(adapter != null){
                    when(adapter!!.getItemViewType(i))
                    {
                        1 -> 1
                        0 -> Common.NUM_OF_COLUMN
                        else -> -1
                    }
                }else{
                    -1
                }
            }

        }
        recycler_view.layoutManager = layoutManager
        recycler_view.addItemDecoration(SpaceItemDecoration(8))

        recycler_view.adapter = adapter
    }

    private fun initData() {
        itemList = ArrayList()

        itemList.add(MyModel(R.drawable.me_time,"Me"))
        itemList.add(MyModel(R.drawable.family_time,"Family"))
        itemList.add(MyModel(R.drawable.lovely_time,"Lovely"))
        itemList.add(MyModel(R.drawable.team_time,"Team"))
        itemList.add(MyModel(R.drawable.friends,"Friends"))
        itemList.add(MyModel(R.drawable.calendar,"Calendars"))
        itemList.add(MyModel(R.drawable.calendar,"Calendars"))

    }
}
