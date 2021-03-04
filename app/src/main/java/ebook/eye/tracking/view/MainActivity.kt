package ebook.eye.tracking.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.viewModels
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
    private lateinit var adapter: RecyclerAdapter
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
                var newAdapter = RecyclerAdapter(data)
                binding.listView.adapter = newAdapter
            }
        var files: Array<String> = this.fileList()
        Log.d("test2",files.size.toString())
        for (file in files){
            Log.d("test3",file)
        }
        viewmodel.liveData.observe(this, dataObserver)
        viewmodel.initDir(Environment.getExternalStorageDirectory().toString())

    }
}