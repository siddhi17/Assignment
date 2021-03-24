package com.example.assignment.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignment.*
import com.example.assignment.adapters.BooksAdapter
import com.example.assignment.base.Constants
import com.example.assignment.databinding.ActivityBooksBinding
import com.example.assignment.responses.GetCategoryResponse
import com.example.assignment.viewModels.BooksViewModel
import com.storepanel.utils.ErrorUtil
import com.storepanel.utils.NetworkUtils
import com.storepanel.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.activity_books.*
import java.util.*
import kotlin.collections.ArrayList


class BooksActivity : AppCompatActivity(), View.OnClickListener, BooksAdapter.BooksListListener {

    private lateinit var booksViewModel: BooksViewModel
    var booksAdapter: BooksAdapter? = null
    private var booksList: ArrayList<GetCategoryResponse.Result> = ArrayList()

    private fun init() {

        if (intent.getStringExtra(Constants.CATEGORY_TYPE).equals(getString(R.string.fiction))) {
            booksViewModel.category = getString(R.string.fiction)
            text_title_1.text = getString(R.string.fiction)
            getBooksList()

        } else if (intent.getStringExtra(Constants.CATEGORY_TYPE)
                .equals(getString(R.string.drama))
        ) {
            booksViewModel.category = getString(R.string.drama)
            text_title_1.text = getString(R.string.drama)
            getBooksList()
        } else if (intent.getStringExtra(Constants.CATEGORY_TYPE)
                .equals(getString(R.string.politics))
        ) {
            booksViewModel.category = getString(R.string.politics)
            text_title_1.text = getString(R.string.politics)
            getBooksList()
        } else if (intent.getStringExtra(Constants.CATEGORY_TYPE)
                .equals(getString(R.string.philosophy))
        ) {
            booksViewModel.category = getString(R.string.philosophy)
            text_title_1.text = getString(R.string.philosophy)
            getBooksList()
        } else if (intent.getStringExtra(Constants.CATEGORY_TYPE)
                .equals(getString(R.string.humor))
        ) {
            booksViewModel.category = getString(R.string.humor)
            text_title_1.text = getString(R.string.humor)
            getBooksList()
        } else if (intent.getStringExtra(Constants.CATEGORY_TYPE)
                .equals(getString(R.string.adventure))
        ) {
            booksViewModel.category = getString(R.string.adventure)
            text_title_1.text = getString(R.string.adventure)
            getBooksList()
        } else if (intent.getStringExtra(Constants.CATEGORY_TYPE)
                .equals(getString(R.string.history))
        ) {
            booksViewModel.category = getString(R.string.history)
            text_title_1.text = getString(R.string.history)
            getBooksList()
        }


        val gridLayoutManager = GridLayoutManager(applicationContext, 3)
        recycler_view_books.layoutManager = gridLayoutManager

        if (booksAdapter == null)
            booksAdapter = BooksAdapter(this, booksList, this)

        recycler_view_books.adapter = booksAdapter

    }

    private fun getBooksList() {
        if (NetworkUtils.isInternetAvailable(this))
            booksViewModel.hitGetCategoryListApi()
        else
            showToast(getString(R.string.error_internet))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*data binding*/

        booksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        val binding: ActivityBooksBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_books)

        binding.data = booksViewModel
        binding.click = this

        observer()

        init()

        search()
    }

    /*Search list*/
    private fun search() {

        edt_search.afterTextChangedDelayed {

            if (edt_search.text?.isEmpty()!!) {
                init()
            } else {

                if (NetworkUtils.isInternetAvailable(this@BooksActivity))
                    booksViewModel.hitGetSearchListApi()
                else
                    showToast(getString(R.string.error_internet))
            }

        }


    }

    /*text change event*/

    private fun TextView.afterTextChangedDelayed(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            var timer: CountDownTimer? = null

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                card_view_search.setBackgroundResource(R.drawable.search_border)
                imageView_close.visibility = View.VISIBLE

            }

            override fun afterTextChanged(editable: Editable?) {
                timer?.cancel()
                timer = object : CountDownTimer(1000, 1500) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {

                        afterTextChanged.invoke(editable.toString())
                    }
                }.start()
            }
        })
    }

    /*observer to get responses*/

    private fun observer() {

        booksViewModel.getCategoryListResponse.observe(this, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            booksList.clear()
            booksList.addAll(it.results)
            booksAdapter?.notifyDataSetChanged()
        })

        booksViewModel.errorGetCategoryList.observe(this, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            ErrorUtil.handlerGeneralError(this, it)
        })

        booksViewModel.getSearchListResponse.observe(this, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            booksList.clear()
            booksList.addAll(it.results)
            booksAdapter?.notifyDataSetChanged()
        })

        booksViewModel.errorGetSearchList.observe(this, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            ErrorUtil.handlerGeneralError(this, it)
        })

        booksViewModel.mProgess.observe(this, Observer {
            if (it) {
                ProgressDialogUtils.getInstance().hideProgress()
                ProgressDialogUtils.getInstance()
                    .showProgress(this, false)
            } else {
                ProgressDialogUtils.getInstance().hideProgress()
            }
        })

    }

    /*Show book in next screen*/
    override fun clickItem(
        url: String,
    ) {

        startActivity(Intent(this@BooksActivity, BookViewActivity::class.java).putExtra("url", url))

    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.imageView_search -> {

                if (NetworkUtils.isInternetAvailable(this@BooksActivity))
                    booksViewModel.hitGetSearchListApi()
                else
                    showToast(getString(R.string.error_internet))

            }
            R.id.imageView_close -> {

                edt_search.setText("")
                imageView_close.visibility = View.GONE
                card_view_search.setBackgroundResource(R.drawable.search_drawable)

                init()

            }
            R.id.imageView_back -> {

                finish()
            }
        }
    }
}