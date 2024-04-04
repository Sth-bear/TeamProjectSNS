package com.example.teamproject1.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.teamproject1.PostInfo
import com.example.teamproject1.R

class ListAdapter(private val context: Context, private val arrayList: MutableList<PostInfo>) : BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        /*        val inflater : LayoutInflater = LayoutInflater.from(context)
                val view : View = inflater.inflate(R.layout.item_post,null)*/
        var convertView = convertView
        val viewHolder: ViewHolder

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
            viewHolder = ViewHolder(convertView) //null이 아닌
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        val postInfo = getItem(position) as PostInfo

        viewHolder.userImage.setImageResource(postInfo.userImage)
        viewHolder.userName.text = postInfo.name
        viewHolder.postImage.setImageResource(postInfo.image)

        return convertView
    }

    private class ViewHolder(view: View) {
        val userImage: ImageView = view.findViewById(R.id.ivUserImage)
        val userName: TextView = view.findViewById(R.id.tvName)
        val postImage: ImageView = view.findViewById(R.id.ivDetail)
    }
}
