package com.cw.myframework.testAct.galleryRvAct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cw.myframework.R

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2021/8/9 下午7:44
 *
 * Description:
 */
class GalleryRvAdapter(val context: Context) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.activity_gallery_recycler_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.render(position)
    }

    override fun getItemCount(): Int {
        return 4
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tv: TextView = itemView.findViewById(R.id.text)

    fun render(position: Int) {
        tv.text = "第${position}位"
    }
}