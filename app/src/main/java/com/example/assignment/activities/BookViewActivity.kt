package com.example.assignment.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.URLUtil
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.R
import com.example.assignment.showToast
import kotlinx.android.synthetic.main.activity_book_view.*


class BookViewActivity : AppCompatActivity() {

    var url: String = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_view)

        url = intent.getStringExtra("url").toString()

        /*show book in web view*/

        if (URLUtil.isValidUrl(url)) {

            webView1.settings.javaScriptEnabled = true
            webView1.settings.domStorageEnabled = true;
            webView1.loadUrl(url)
        } else {

            finish()
            showToast("No viewable version available.")
        }

    }
}