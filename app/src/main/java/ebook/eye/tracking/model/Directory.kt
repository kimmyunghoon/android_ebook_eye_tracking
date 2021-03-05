package ebook.eye.tracking.model

class Directory (var isChecked: Boolean,var dir_path: String){
    override fun toString() : String{
        return "dir_path : $dir_path  isChecked : $isChecked"
    }
}