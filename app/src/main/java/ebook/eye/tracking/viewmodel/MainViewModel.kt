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
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class MainViewModel :ViewModel() {
    var liveData : MutableLiveData<ArrayList<Directory>> = MutableLiveData<ArrayList<Directory>>()

    val liveVisible = MutableLiveData<Boolean>()

    val _liveData : LiveData<ArrayList<Directory>>
        get() = liveData
    init{
        liveVisible.postValue(true)
    }
    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    fun initDir(path: String){
        if(File(path).isDirectory){
            Log.e("info", "$path 현재 경로는 디렉토리")
        }
        val files  =  File(path).listFiles()
        if(files ==null || files.isEmpty()){
            Log.e("err", "파일 대상이 존재하지 않습니다.")
            return
        }
        var DirListData = ArrayList<Directory>()
        Log.e("info", "files ${files.size}")
        for(file in files) {
            val extension = file.extension.toLowerCase(Locale.ROOT)
            if(extension == "txt" || extension == "zip")
                DirListData.add(Directory(false, file.parent, file.name))
        }

        liveData.postValue(DirListData)
    }


    fun nextPage(context: Context){
        val dataList : ArrayList<Directory>? = _liveData.value
        var gson: Gson = Gson()
        val sb = StringBuilder()
        Log.d("체크 ", dataList.toString())
        dataList?.forEach { dirObj ->
            if (dirObj.isChecked) {
                Log.d("체크된 항목", dirObj.toString())
            }
            else {
                dataList.remove(dirObj)
            }
        }

        if(dataList?.size==0)
            return

        sb.append(gson.toJson(dataList));
        Log.d("저장될 형식",sb.toString())
        val nextIntent = Intent(context, FileListActivity::class.java)
        nextIntent.putExtra("data",sb.toString())
        context.startActivity(nextIntent)
    }


    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    fun refresh(){
        initDir(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath)
    }

}
