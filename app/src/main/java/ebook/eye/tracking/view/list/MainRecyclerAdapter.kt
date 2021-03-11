package ebook.eye.tracking.view.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ebook.eye.tracking.R
import ebook.eye.tracking.model.Directory

class MainRecyclerAdapter() : RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder>() {

    var data: LiveData<ArrayList<Directory>>? =null

    inner class MyViewHolder constructor(v: View) : RecyclerView.ViewHolder(v) {
        var cb:CheckBox = v.findViewById(R.id.checkBox)
        var name:TextView = v.findViewById(R.id.name)
        var path:TextView = v.findViewById(R.id.dir_path)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        data?.value!![position].let { item ->
            with(holder) {

                holder.cb.isChecked = item.isChecked
                holder.cb.setOnClickListener {
                    item.isChecked = !item.isChecked
                }
                holder.name.text = item.name
                holder.name.setOnClickListener {

                    holder.cb.isChecked = !holder.cb.isChecked
                    item.isChecked = !item.isChecked
                }
                holder.path.text = item.dir_path
                holder.path.setOnClickListener {
                    holder.cb.isChecked = !holder.cb.isChecked
                    item.isChecked = !item.isChecked
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (data ==null) 0 else data?.value!!.size
    }


}