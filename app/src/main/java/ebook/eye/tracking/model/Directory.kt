package ebook.eye.tracking.model

data class Directory (var isChecked: Boolean,var dir_path: String?,var name: String){
    override fun toString() : String{
        return "dir_path : $dir_path  isChecked : $isChecked name : $name"
    }
}