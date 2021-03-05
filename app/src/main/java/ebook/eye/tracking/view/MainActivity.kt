package ebook.eye.tracking.view

import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
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
import ebook.eye.tracking.databinding.ActivityMainBinding
import ebook.eye.tracking.model.Directory
import ebook.eye.tracking.view.list.RecyclerAdapter
import ebook.eye.tracking.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: RecyclerAdapter by lazy { RecyclerAdapter() }
    private val viewmodel: MainViewModel by viewModels()
    var data = MutableLiveData<ArrayList<Directory>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main
        )

        binding.listView.layoutManager = LinearLayoutManager(this)

        val dataObserver: Observer<ArrayList<Directory>> =
            Observer { livedata ->
                data.value = livedata

                adapter.data = data
                adapter.notifyDataSetChanged()
            }
//        val resolver = this.contentResolver
//        val contentValues = ContentValues().apply {
//            put(MediaStore.Downloads.MIME_TYPE, "plain/text")
//            put(MediaStore.Downloads.RELATIVE_PATH, "Download")
//        }
        Log.d("VERSION", Build.VERSION.SDK_INT .toString())
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.Q){
            val downloads: Uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI
            Log.d("URI", downloads.toString())
        }
        else {

            viewmodel.liveData.observe(this, dataObserver)
            viewmodel.initDir(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath)
        }

        binding.listView.adapter = adapter
    }
}