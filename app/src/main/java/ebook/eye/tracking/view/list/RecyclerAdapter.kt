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

class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var data: LiveData<ArrayList<Directory>>? =null

    inner class MyViewHolder constructor(v: View) : RecyclerView.ViewHolder(v) {
        var cb:CheckBox = v.findViewById(R.id.checkBox)

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
                Log.e("test", item.toString())
                holder.cb.isChecked = item.isChecked
                holder.cb.setOnClickListener {
                    item.isChecked = !item.isChecked
                }
                holder.path.text = item.dir_path
                holder.path.setOnClickListener {

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (data ==null) 0 else data?.value!!.size
    }


}