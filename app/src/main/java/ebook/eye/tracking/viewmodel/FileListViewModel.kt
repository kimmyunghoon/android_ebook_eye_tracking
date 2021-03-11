package ebook.eye.tracking.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import ebook.eye.tracking.model.Directory
import ebook.eye.tracking.view.FileListActivity
import ebook.eye.tracking.view.list.FileListRecyclerAdapter
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class FileListViewModel :ViewModel() {
    lateinit var adpter: FileListRecyclerAdapter
    var liveData : MutableLiveData<ArrayList<Directory>> = MutableLiveData<ArrayList<Directory>>()

    val _liveData : LiveData<ArrayList<Directory>>
        get() = liveData

    fun itemClick(){

    }

}
