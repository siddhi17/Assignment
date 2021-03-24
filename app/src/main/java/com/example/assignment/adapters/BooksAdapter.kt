package com.example.assignment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.R
import com.example.assignment.responses.GetCategoryResponse
import kotlinx.android.synthetic.main.activity_main.view.text_title
import kotlinx.android.synthetic.main.category_item_layout.view.*

/*Adapter to show books list*/

class BooksAdapter(
    context: Context,
    list: ArrayList<GetCategoryResponse.Result>,
    val click: BooksListListener,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: Context = context
    var booksList: ArrayList<GetCategoryResponse.Result> = list

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val recyclerViewModel = booksList[position]

            itemView.text_title.text = recyclerViewModel.title

            /*Show available formats*/
            itemView.img_book.setOnClickListener {

                when {
                    recyclerViewModel.formats.texthtml?.isEmpty() == false -> {
                        click.clickItem(recyclerViewModel.formats.texthtml)
                    }
                    recyclerViewModel.formats.texthtmlCharsetiso88591?.isEmpty() == false -> {
                        click.clickItem(recyclerViewModel.formats.texthtmlCharsetiso88591)
                    }
                    recyclerViewModel.formats.texthtmlCharsetusAscii?.isEmpty() == false -> {
                        click.clickItem(recyclerViewModel.formats.texthtmlCharsetusAscii)
                    }
                    recyclerViewModel.formats.texthtmlCharsetutf8?.isEmpty() == false -> {
                        click.clickItem(recyclerViewModel.formats.texthtmlCharsetutf8)
                    }
                    recyclerViewModel.formats.applicationpdf?.isEmpty() == false -> {
                        click.clickItem(recyclerViewModel.formats.applicationpdf)
                    }
                    recyclerViewModel.formats.textplain?.isEmpty() == false -> {
                        click.clickItem(recyclerViewModel.formats.textplain)
                    }
                    recyclerViewModel.formats.textplainCharsetiso88591?.isEmpty() == false -> {
                        click.clickItem(recyclerViewModel.formats.textplainCharsetiso88591)
                    }
                    recyclerViewModel.formats.textplainCharsetusAscii?.isEmpty() == false -> {
                        click.clickItem(recyclerViewModel.formats.textplainCharsetusAscii)
                    }
                    recyclerViewModel.formats.textplainCharsetutf8?.isEmpty() == false -> {
                        click.clickItem(recyclerViewModel.formats.textplainCharsetutf8)
                    }
                }

            }

            for (items in recyclerViewModel.authors) {

                if (items.name != "" && items.name != null) {
                    itemView.text_author.text = items.name
                }
            }

            Glide.with(context)
                .load(recyclerViewModel.formats.imagejpeg)
                .centerCrop()
                .into(itemView.img_book)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return View1ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.category_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as View1ViewHolder).bind(position)
    }

    interface BooksListListener {

        fun clickItem(url: String)

    }
}