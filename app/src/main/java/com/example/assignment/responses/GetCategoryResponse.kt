package com.example.assignment.responses

import com.google.gson.annotations.SerializedName

/*Response model*/

data class GetCategoryResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
) {
    data class Result(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("authors")
        val authors: List<Author>,
        @SerializedName("translators")
        val translators: List<Any>,
        @SerializedName("subjects")
        val subjects: List<String>,
        @SerializedName("bookshelves")
        val bookshelves: List<String>,
        @SerializedName("languages")
        val languages: List<String>,
        @SerializedName("copyright")
        val copyright: Boolean,
        @SerializedName("media_type")
        val mediaType: String,
        @SerializedName("formats")
        val formats: Formats,
        @SerializedName("download_count")
        val downloadCount: Int
    ) {
        data class Author(
            @SerializedName("name")
            val name: String,
            @SerializedName("birth_year")
            val birthYear: Int,
            @SerializedName("death_year")
            val deathYear: Int
        )

        data class Formats(
            @SerializedName("application/epub+zip")
            val applicationepubzip: String,
            @SerializedName("application/rdf+xml")
            val applicationrdfxml: String,
            @SerializedName("text/html; charset=utf-8")
            val texthtmlCharsetutf8: String?,
            @SerializedName("application/x-mobipocket-ebook")
            val applicationxMobipocketEbook: String,
            @SerializedName("application/zip")
            val applicationzip: String,
            @SerializedName("image/jpeg")
            val imagejpeg: String,
            @SerializedName("text/plain; charset=utf-8")
            val textplainCharsetutf8: String?,
            @SerializedName("text/plain")
            val textplain: String?,
            @SerializedName("text/plain; charset=iso-8859-1")
            val textplainCharsetiso88591: String?,
            @SerializedName("text/html; charset=iso-8859-1")
            val texthtmlCharsetiso88591: String?,
            @SerializedName("text/plain; charset=us-ascii")
            val textplainCharsetusAscii: String?,
            @SerializedName("text/html")
            val texthtml: String?,
            @SerializedName("text/html; charset=us-ascii")
            val texthtmlCharsetusAscii: String?,
            @SerializedName("application/pdf")
            val applicationpdf: String?
        )
    }
}