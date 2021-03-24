package com.example.assignment.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.R
import com.example.assignment.base.Constants
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.viewModels.BooksViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var booksViewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Data binding*/

        booksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.data = booksViewModel
        binding.click = this

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.card_view_fiction -> {
                startActivity(
                    Intent(
                        this@MainActivity,
                        BooksActivity::class.java
                    ).putExtra(Constants.CATEGORY_TYPE,getString(R.string.fiction))
                )
            }

            R.id.card_view_adventure -> {
                startActivity(
                    Intent(
                        this@MainActivity,
                        BooksActivity::class.java
                    ).putExtra(Constants.CATEGORY_TYPE,getString(R.string.adventure))
                )
            }
            R.id.card_view_drama -> {
                startActivity(
                    Intent(
                        this@MainActivity,
                        BooksActivity::class.java
                    ).putExtra(Constants.CATEGORY_TYPE,getString(R.string.drama))
                )
            }
            R.id.card_view_history -> {
                startActivity(
                    Intent(
                        this@MainActivity,
                        BooksActivity::class.java
                    ).putExtra(Constants.CATEGORY_TYPE,getString(R.string.history))
                )
            }
            R.id.card_view_humor -> {
                startActivity(
                    Intent(
                        this@MainActivity,
                        BooksActivity::class.java
                    ).putExtra(Constants.CATEGORY_TYPE,getString(R.string.humor))
                )
            }
            R.id.card_view_philosophy -> {
                startActivity(
                    Intent(
                        this@MainActivity,
                        BooksActivity::class.java
                    ).putExtra(Constants.CATEGORY_TYPE,getString(R.string.philosophy))
                )
            }
            R.id.card_view_politics -> {
                startActivity(
                    Intent(
                        this@MainActivity,
                        BooksActivity::class.java
                    ).putExtra(Constants.CATEGORY_TYPE,getString(R.string.politics))
                )
            }
        }
    }
}