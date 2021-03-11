package ebook.eye.tracking.view

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ebook.eye.tracking.R
import ebook.eye.tracking.databinding.ActivityFilelistBinding
import ebook.eye.tracking.model.Directory
import ebook.eye.tracking.view.list.FileListRecyclerAdapter
import ebook.eye.tracking.viewmodel.FileListViewModel
import org.json.JSONArray


class FileListActivity : AppCompatActivity() {

    lateinit var binding: ActivityFilelistBinding
    private val adapterFileList: FileListRecyclerAdapter by lazy { FileListRecyclerAdapter() }
    private val viewmodel: FileListViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =   DataBindingUtil.setContentView(this, R.layout.activity_filelist)
        binding.apply {
            lifecycleOwner = this@FileListActivity
            activity=this@FileListActivity

            binding.listView.adapter = adapterFileList
        }
        binding.listView.layoutManager = LinearLayoutManager(this)
        viewmodel.adpter = adapterFileList
        val dataObserver: Observer<ArrayList<Directory>> =
            Observer { livedata ->
                var data = MutableLiveData<ArrayList<Directory>>()
                data.value = livedata
                adapterFileList.data = data
                adapterFileList.notifyDataSetChanged()
            }
        val _intent = intent


        Log.d("data", _intent.getStringExtra("data").toString())
        val listdata = ArrayList<Directory>()
        val jArray = JSONArray(_intent.getStringExtra("data"))
        for (i in 0 until jArray.length()) {
            Log.d(",data", jArray.getJSONObject(i)["dir_path"] as String)
            listdata.add(Directory(jArray.getJSONObject(i)["isChecked"] as Boolean, jArray.getJSONObject(i)["dir_path"] as String?, jArray.getJSONObject(i)["name"] as String))
        }
        Log.d("VERSION", Build.VERSION.SDK_INT.toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val downloads: Uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI
            Log.d("URI", downloads.toString())
        } else {

            viewmodel.liveData.observe(this, dataObserver)
            viewmodel.liveData.postValue(listdata)
        }


    }


}