package ebook.eye.tracking.viewmodel

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ebook.eye.tracking.model.Directory
import java.io.File


class MainViewModel :ViewModel() {
    var liveData : MutableLiveData<ArrayList<Directory>> = MutableLiveData<ArrayList<Directory>>()
    init{


    }
    fun initDir(path: String){
        if(File(path).isDirectory){
            Log.e("info", "$path$ 현재 경로는 디렉토리")
        }
        val files  =  File(path).listFiles()
        if(files ==null || files.isEmpty()){
            Log.e("err", "파일 대상이 존재하지 않습니다.")
            return
        }
        var DirListData = ArrayList<Directory>()

        for(file in files){
            if(file.extension == "txt" || file.extension == "zip")
                DirListData.add(Directory(false, file.toString()))
        }

        liveData.postValue(DirListData)
    }

}