package ebook.eye.tracking.view.binding


import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {
    /**
     * View의 visibility를 변경
     * @param view 속성을 사용하는 view
     * @param isVisible visibility를 변경시키는 기준이 되는 값
     */
    @JvmStatic
    @BindingAdapter("visible")
    fun setVisible(view: View, isVisible: Boolean) {
        Log.d("setVisible", "$isVisible 상태")
        view.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }


}