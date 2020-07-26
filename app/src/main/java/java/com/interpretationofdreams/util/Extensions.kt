package java.com.interpretationofdreams.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.com.interpretationofdreams.data.local.AppDatabase
import java.io.FileOutputStream
import java.io.IOException

fun View.selectableItemBackgroundBorderless() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val outValue = TypedValue()
        this.context.theme.resolveAttribute(
            android.R.attr.selectableItemBackgroundBorderless,
            outValue,
            true
        )
        this.setBackgroundResource(outValue.resourceId)
        this.isClickable = true
        this.isFocusable = true
    }
}

fun View.selectableItemBackground() {
    val outValue = TypedValue()
    this.context.theme.resolveAttribute(
        android.R.attr.selectableItemBackground,
        outValue,
        true
    )
    this.setBackgroundResource(outValue.resourceId)
    this.isClickable = true
    this.isFocusable = true
}

fun Activity.hideSoftInputFromWindow() {
    this.let {
        if (it.currentFocus != null)
            (it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                it.currentFocus!!.windowToken,
                0
            )
    }
}

fun copyAttachedDB(context: Context, dbName: String = AppDatabase.dbName) {
    val dbPath = context.getDatabasePath(dbName)
    if (dbPath.exists()) return

    dbPath.parentFile?.mkdir()

    try {
        val inputStream = context.assets.open(dbName)
        val outputStream = FileOutputStream(dbPath)
        val buffer = ByteArray(8192)
        var length = inputStream.read(buffer, 0, 8192)
        while (length > 0) {
            outputStream.write(buffer, 0, length)
            length = inputStream.read(buffer, 0, 8192)
        }
        outputStream.flush()
        outputStream.close()
        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}