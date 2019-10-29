package com.example.menu.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.Common.Common
import com.example.menu.Interface.ICardItemClickListener
import com.example.menu.Model.MyModel
import com.example.menu.R
import kotlinx.android.synthetic.main.layout_card_squre.view.*

class MyAdapter(internal var context: Context,
                internal var myItems: List<MyModel>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.layout_card_squre,p0, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return myItems.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.img_icon.setImageResource(myItems[p1].icon)
        p0.txt_description.text = myItems[p1].description

        p0.setEvent(object:ICardItemClickListener{
            override fun onCartItemClick(view: View, position: Int) {
                Toast.makeText(context,"Clicked: "+myItems[position].description,Toast.LENGTH_SHORT).show()
            }

        })
    }

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),View.OnClickListener {

        internal  var img_icon:ImageView
        internal  var txt_description:TextView
        internal lateinit var iCardItemClickListener:ICardItemClickListener

        fun setEvent(iCardItemClickListener: ICardItemClickListener){
            this.iCardItemClickListener = iCardItemClickListener
        }

        init{
            img_icon = itemView.findViewById<View>(R.id.img_icon) as ImageView
            txt_description = itemView.findViewById(R.id.txt_description) as TextView

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
             iCardItemClickListener.onCartItemClick(p0!!,adapterPosition)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (myItems.size == 1)
            0
        else{
            if(myItems.size % Common.NUM_OF_COLUMN == 0)
                1
            else
                if(position > 1 && position == myItems.size - 1) 0 else 1


        }
    }
}