package com.example.assignment

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

var toast: Toast? = null
var gson: Gson? = null

fun getGsonInstance(): Gson {
    if (gson == null)
        gson = Gson()
    return gson!!
}

fun Fragment.showToast(message: String) {
    if (toast != null) toast!!.cancel()
    Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
}

fun ViewModel.showToast(context: Context?, message: String) {
    if (toast != null) toast!!.cancel()
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showToast(message: String) {
    if (toast != null) toast!!.cancel()
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}



















































































































