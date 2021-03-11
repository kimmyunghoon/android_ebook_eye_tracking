package ebook.eye.tracking.view

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
import ebook.eye.tracking.view.list.MainRecyclerAdapter
import ebook.eye.tracking.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapterMain: MainRecyclerAdapter by lazy { MainRecyclerAdapter() }
    private val viewmodel: MainViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main
        )
        binding.apply {
            lifecycleOwner = this@MainActivity
            activity=this@MainActivity
            mainvm = viewmodel       // xml 파일에 선언한 activity
            binding.listView.adapter = adapterMain
        }
        binding.listView.layoutManager = LinearLayoutManager(this)
        val dataObserver: Observer<ArrayList<Directory>> =
                Observer { livedata ->
                    var data = MutableLiveData<ArrayList<Directory>>()
                    data.value = livedata
                    adapterMain.data = data
                    adapterMain.notifyDataSetChanged()
                }
        Log.d("VERSION", Build.VERSION.SDK_INT.toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val downloads: Uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI
            Log.d("URI", downloads.toString())
        } else {

            viewmodel.liveData.observe(this, dataObserver)
            viewmodel.initDir(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath)
        }

    }


}