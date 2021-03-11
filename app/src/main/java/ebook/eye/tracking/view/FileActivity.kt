package ebook.eye.tracking.view

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ebook.eye.tracking.R
import ebook.eye.tracking.databinding.ActivityFileBinding
import ebook.eye.tracking.databinding.ActivityFilelistBinding
import ebook.eye.tracking.databinding.ActivityMainBinding

class FileActivity : AppCompatActivity() {

    private val binding: ActivityFileBinding =  DataBindingUtil.setContentView(this, R.layout.activity_file)

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {


        }


    }


}